(ns negocio)


(defn create-negocio
  [preco quantidade data]
  (assert (not (nil? data)))
  (assert (> preco 0))
  {:preco preco, :quantidade quantidade :data data}
  )
