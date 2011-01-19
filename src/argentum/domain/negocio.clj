(ns argentum.domain.negocio)


(defrecord Negocio [data preco quantidade])

(defn create-negocio
  [preco quantidade data]
  (assert (not (nil? data)))
  (assert (> preco 0))
  (Negocio. data preco quantidade)
  )
