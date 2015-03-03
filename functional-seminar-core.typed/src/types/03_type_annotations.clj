(ns types.03-type-annotations
  (:require [clojure.core.typed :as t]))

; - Includes type annotations for clojure.core
;   - “Some” functions are still missing annotations
(comment
  (t/cf assoc)
  (t/All [b c d] (t/IFn [(t/Map b c) b c -> (t/Map b c)]
                        [(t/Vec d) t/AnyInteger d -> (t/Vec d)]))
  )

; - Only a few libraries currently contain annotations

; - You can annotate libraries you are using in your own project
(comment
  (ns my-project.types ...)
  (t/ann foo-bar.library/do-stuff [String -> Number]))

; - *Non-checked annotations*: These provide an interface between typed and
;   untyped code. “Function takes in this and returns this! Please don’t
;   check it’s implementation.”

(t/ann ^:no-check some-magic [t/Int t/Str -> t/Bool])
(defn some-magic [a b]
  ; Some code which can't be type hinted
  "foo")

(t/ann some-magic2 [t/Int t/Str -> t/Bool])
(defn some-magic2 [a b]
  ; Some code which can't be type hinted
  "foo")

(comment
  (some-magic 5 "a")
  (t/cf some-magic)
  (t/cf (some-magic 5 "a"))

  (t/cf (t/fn [a :- t/Int b :- t/Str] "foo"))
  (t/cf (t/fn [a :- t/Int b :- t/Str] "foo") t/Str)
  (t/cf (t/fn [a :- t/Int b :- t/Str] "foo")
        t/Bool))
