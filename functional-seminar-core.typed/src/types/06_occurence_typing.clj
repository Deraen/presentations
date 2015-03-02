(ns types.06-occurence-typing
  (:require [clojure.core.typed :as t]))

(t/defn do-something
  [coll :- (t/Coll t/Int)] :- t/Int
  (if (seq coll)
    (do
      ; Type of coll is (t/NonEmptyColl t/Int)
      )
    (do
      ; Type of coll is (t/Coll t/Int)
      )))

(t/defn do-something-else
  [coll :- (t/Coll t/Int)] :- t/Int
  (do
    (assert (seq coll))
    ; Type of coll is (t/NonEmptyColl t/Int)
    ))
