(ns types.05-practical-typing-hmaps
  (:require [clojure.core.typed :as t]))

; - So Clojure uses lots of **maps**
; - We could type their keys and values...
;   - Thats impractical
;   - Scala: Map[String, Any]
;   - Scala solution -> Case classes
;   - Creating a Record for each variant would require lots of work
; - *HMaps* - Heterogeneous Maps
;   - Predefined keys and type for each
; - btw. there are also HVec, HSeq etc.

(t/defalias Person
  (t/HMap :mandatory
          {:name t/Str
           :langs (t/Set t/Keyword)}))

(t/def juho :- Person
  {:name "Juho"
   :langs #{:clj :cljs :js :cpp :scala :python}})

(comment
  (t/cf juho)
  (t/cf (assoc juho :hates #{:scala}))
  (t/cf (assoc juho :hates #{:scala}) Person))

(t/defalias OnlyName
  (t/HMap :mandatory {:name t/Str} :complete? true))

(t/ann select-name [Person -> OnlyName])
(defn select-name [{:keys [name] :as person}]
  ; FIXME: Usally one would use select-keys here...
  ; but it's not annotated to work with HMaps
  {:name name})

(comment
  (select-name juho)
  ;; => {:name "Juho"}

  (t/cf juho OnlyName)
  (t/cf select-name)
  (t/cf (select-name juho)))
