(ns types.03-type-annotations
  (:require [clojure.core.typed :as t]))

; - Includes type annotations for clojure.core
;   - “Some” functions are still missing annotations
(comment
  (t/cf assoc))

; - Only a few libraries currently contain annotations

; - You can annotate libraries you are using in your own project
(comment
  (ns my-project.types ...)
  (t/ann foo-bar.library/do-stuff ))

; - *Non-checked annotations*: These provide an interface between typed and untyped code. “Function takes in this and returns this! Please don’t check it’s implementation.”

(t/ann ^:no-check some-magic [t/Int t/Str -> t/Bool])
(defn some-magic [a b]
  ; Some code which can't be type hinted
  (apply str (repeat a b)))

(comment
  (some-magic 5 "a")
  (t/cf some-magic)
  (t/cf (some-magic 5 "a")))
