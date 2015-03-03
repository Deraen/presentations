(ns types.06-occurence-typing
  (:require [clojure.core.typed :as t]))

(t/defn do-something
  [coll :- (t/Coll t/Int)] :- t/Int
  (if (seq coll)
    ; Type of coll is (t/NonEmptyColl t/Int)
    (first coll)
    ; Type of coll is (t/Coll t/Int)
    42))

(t/defn do-something-else
  [coll :- (t/Coll t/Int)] :- t/Int
  (do
    (assert (seq coll))
    ; Type of coll is (t/NonEmptyColl t/Int)
    (first coll)))

(t/ann first-or-zero [(t/U nil (t/Coll Number)) -> Number])
(defn first-or-zero [coll]
  (if (seq coll)
    (first coll)
    0))

(t/defn :forall [x] only-symbols
  [coll :- (t/Coll x)]
  ; :- (t/Coll t/Sym) ; Can be infered
  (filter symbol? coll))

(comment
  (t/cf first)
  ; (t/All [x] (t/IFn [(t/HSequential [x t/Any *]) -> x :object {:path [(Nth 0)], :id 0}]
  ;                   [(t/Option (t/EmptySeqable x)) -> nil]
  ;                   [(t/NonEmptySeqable x) -> x]
  ;                   [(t/Option (clojure.lang.Seqable x)) -> (t/Option x)]))

  (t/cf first-or-zero)
  ;; => [(t/Coll java.lang.Number) -> java.lang.Number]
  (t/cf (first-or-zero nil))

  (t/cf (only-symbols [1 2 'b 'c]))
  )

(t/ann plus-or-concat (t/All [x] (t/IFn [t/Num -> t/Num]
                                        [t/Str -> t/Str])))
(defn plus-or-concat [a]
  (cond
    (number? a) (+ a 42)
    (string? a) (str a "Foobar")))
