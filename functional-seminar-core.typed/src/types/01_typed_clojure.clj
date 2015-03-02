(ns types.01-typed-clojure
  (:require [clojure.core.typed :as t]))

; - (an optional) type system for Clojure (and ClojureScript)
;   - ”Typed Clojure preserves Clojure's strengths, enhancing many of them with the
;     safety of static type checking”

; - Heavily influenced by Typed Racket (another Lisp)
; - Created by Ambrose Bonnaire-Sergeant
;   - +1 year of development paid with crowdfunding
;   - Honours thesis “A Practical Optional Type System for Clojure”
;   - Currently studying at Indiana University

(t/ann constant t/Int)
(def constant 42)
