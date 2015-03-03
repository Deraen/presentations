(ns types.07-something
  (:require [clojure.core.typed :as t])
  (:import [java.io File]))

(comment
  (t/cf map)
  ; (All [c a b ...]
  ;   (t/IFn [[a b ... b -> c] (t/NonEmptySeqable a) (t/NonEmptySeqable b) ... b -> (t/NonEmptyASeq c)]
  ;          [[a b ... b -> c] (t/U nil (Seqable a)) (t/U nil (Seqable b)) ... b -> (t/ASeq c)]))


  (map (fn [a b c]
         )
       [1 2 3]
       ["a" "b" "c"]
       [:a :b :c])

(t/ann f File)
(def f (File. "a"))

(t/ann prt (U nil String))
(def prt (.getParent ^File f))

(t/non-nil-return java.io.File/getName :all)
(t/ann name String)
(def nme (.getName ^File f))
