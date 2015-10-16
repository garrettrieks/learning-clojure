(ns learning-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn chapter-one []
  ;; LISP -> LISt Processing
  1/3 ;; -> 1/3
  4/2 ;; -> 2
  (/ 1 3) ;; -> 1/3
  (/ 1 3.0) ;; -> 0.33333333333333
  (+ 1 (+ 8 3)) ;; -> 12

  ;; List ;;
  (def firstList '(1 2 "three" :four)) ;; mix and match types in collection
                 '(1, 2, "three", :four) ;; possible, but idiomatic not to use commas
                 (list 1 2 "three" :four) ;; build with `list` function

  (first firstList) ;; head; -> 1
  (rest firstList) ;; tail; -> (2 "three" :four)
  (first (rest firstList)) ;; -> 2
  (first (rest (rest (rest (rest firstList))))) ;; -> nil
  (cons 0 firstList) ;; -> (0 1 2 "three" :four)
  (cons 0 nil) ;; builds a list; -> (0)

  ;; Vector ;;
  (def firstVector [1 2 "three" :four])

  (first (rest firstVector)) ;; -> 2
  ;; fast index access
  (nth firstVector 3) ;; -> :four
  (nth firstVector 4) ;; -> IndexOutOfBoundsException
  (last firstVector) ;; -> :four
  (count firstVector) ;; -> 4

  ;; conj -> adds elements the most natural way for a given data structure
  (conj [1 2] 3) ;; -> [1 2 3]
  (conj [1 2] 3 4) ;; -> [1 2 3 4]
  (conj '(1 2) 3) ;; -> (3 1 2)
  (conj '(1 2) 3 4) ;; -> (4 3 1 2)

  ;; Map ;;
  (def firstMap {:first "one", :second 2}) ;; idiomatic to separate pairs with commas
                {:first "one" :second 2}   ;; but possible without
                                           ;; idiomatic to use keywords as keys
  (get firstMap :first) ;; -> "one"
  (get firstMap :third) ;; -> nil
  (get firstMap :third "not found") ;; -> "not found"
  (:second firstMap) ;; idiomatic; -> 2
  (keys firstMap) ;; -> (:first :second)
  (vals firstMap) ;; -> ("one" 2)
  (assoc firstMap :first 1) ;; -> {:first 1, :second 2}
  (dissoc firstMap :second) ;; -> {:first 1}
  (merge {:order 1 :url "www.google.com"}
         {:order 2 :http-type "GET"}) ;; -> {:order 2, :url "www.google.com", :http-type "GET"}

  ;; Set ;;
  (def firstSet #{1 2 3})  ;; all elements must be unique
  #{1 1 2 3} ;; IllegalArgumentException Duplicate key: 1
  (clojure.set/union #{1 2 3} #{3 4 5}) ;; -> #{1 4 3 2 5}
  (clojure.set/difference #{1 2 3} #{3 4 5}) ;; -> #{1 2}
  (clojure.set/intersection #{1 2 3} #{3 4 5}) ;; -> #{3}
  (set firstVector) ;; -> #{1 "three" :four 2}
  (set {:a 1 :b 2 :c 3}) ;; -> #{[:c 3] [:b 2] [:a 1]}
  (contains? firstSet 2) ;; -> true

  ;; functions ;;
  (defn shop-for-jams [jam1 jam2]
    {:name "jam-basket"
     :jam jam1
     :jam jam2})

  (shop-for-jams "strawberry" "marmalade") ;; -> {:name "jam-basket", :jam1 "strawberry", :jam2 "marmalade"}

  ;; anonymous function, evoked by outer parens
  ((fn [] (str "Off we go" "!"))) ;; -> "Off we go!"
  (def follow-again (fn [] (str "Off we go" "!"))) ;; defn the same as using def and binding name to anonymous function

  ;; shorthand form for anonymous function
  (#(str "Off we go" "!")) ;; -> "Off we go!"

  ;; percent sign used for one parameter
  (#(str "Off we go" "!" " - " %) "again") ;; -> "Off we go! - again"

  ;; add number to percents for more than one parameter
  (#(str "Off we go" "!" " - " %1 %2) "again" "?") ;; -> "Off we go! - again?"
)
