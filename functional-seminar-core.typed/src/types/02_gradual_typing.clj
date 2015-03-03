(ns types.02-gradual-typing
  (:require [clojure.core.typed :as t]
            [clojure.test :refer [deftest is testing]]))

; - Core.typed doesn't change how Clojure is run, still a dynamic language
;   - Functions still have multipler arities, no pattern matching...
; - Type checking is a function
; - Type checking stage before compilation (linting)
;   - Or constantly at editor (Vim, Emacs, Cursive, …)

(comment
  (t/cf 5)
  (t/cf "Hello World" t/Int))

;   - or from REPL
;   - or from unit tests
(deftest ^:no-check foobar-test
  (testing "Types should work!"
    (is (t/cf "Hello World" t/Str))))

; - Because Clojure is dynamicly typed, type system has to work
;   together with non-typed code...
; - *Gradual Typing*: To protect interoperability between typed and untyped code
;   - Work is being done so that some type checks would be done at runtime
;
; Non-typed land        Typed land
; ------------------------------------
; non-t fn            some fn
;       -----------W---->
;
;         wrapper -^
; - wrapper would do runtime checks to check that the fn
;   is being called as it's type annotated
; - Not implemented on core.typed yet!
