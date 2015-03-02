(ns types.05-practical-typing-hmaps
  (:require [clojure.core.typed :as t]))

; - So Clojure uses lots of **maps**
; - We could type their keys and values...
;   - Thats impractical
; - *HMaps* - Heterogeneous Maps
;   - Predefined keys and type for each

(t/defalias Person
  (t/HMap :mandatory
          {:name t/Str
           :langs (t/Set t/Keyword)}))

(t/def juho :- Person
  {:name "Juho"
   :langs #{:clj :cljs :js :cpp :scala :python}})

(comment
  (t/cf juho)
  (t/cf (assoc juho :hates #{:scala}) Person))

(t/defalias OnlyName
  (t/HMap :mandatory {:name t/Str} :complete? true))

(t/ann select-name [Person -> OnlyName])
(defn select-name [{:keys [name]}]
  ; FIXME: select-keys is not annotated to return HMaps
  ; (select-keys person [:name])
  {:name name})

(comment
  (select-name juho)
  ;; => {:name "Juho"}

  (t/cf juho OnlyName)
  (t/cf (select-name juho)))
