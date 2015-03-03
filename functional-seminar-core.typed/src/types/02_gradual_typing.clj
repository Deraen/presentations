(ns types.02-gradual-typing
  (:require [clojure.core.typed :as t]
            [clojure.test :refer [deftest is testing]]))

; - Core.typed doesn't change how Clojure is run, still a dynamic language
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

; - Gradual Typing: Work is being done so that some type checks would be done
;   at runtime
;   - To protect interoperability between typed and untyped code

; - Example of a problem:
(defn ^:no-check wrong [x] (+ "hello" x))
(comment
  (wrong 5)
  (t/cf (t/fn [x :- Number] (+ "hello" 5))))
