(ns argentum.domain.negocio)


(defrecord Negocio [data preco quantidade])

(defn criar-negocio
  [preco quantidade data]
  (assert (not (nil? data)))
  (assert (> preco 0))
  (Negocio. data preco quantidade)
  )


(defn obter-volume
  [negocio]
  (* (:preco negocio) (:quantidade negocio)))
