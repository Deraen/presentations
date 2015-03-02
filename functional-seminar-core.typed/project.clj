(defproject functional-seminar-core.typed "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.typed "0.2.82"]]
  :core.typed {:check [types.00-init
                       types.01-typed-clojure
                       types.02-gradual-typing
                       types.03-type-annotations
                       types.05-real-world
                       types.06-practical-typing-hmaps]}
  :plugins [[lein-typed "0.3.5"]])
