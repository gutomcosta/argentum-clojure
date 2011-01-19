(ns argentum.domain.candlestick)


(defrecord Candlestick [preco-minimo preco-maximo abertura fechamento volume])

(defn create-candlestick
  [negocios]
  (assert (not (nil? negocios)))
  (assert (not (empty? negocios)))
  (let [
    preco-minimo (apply min (map (fn [negocio] (:preco negocio)) negocios))
    preco-maximo (apply max (map (fn [negocio] (:preco negocio)) negocios))
    abertura (:preco (first negocios))
    fechamento (:preco (last negocios))
    volume (apply + (map (fn [negocio] (* (:preco negocio) (:quantidade negocio))) negocios))]
    (Candlestick. preco-minimo preco-maximo abertura fechamento volume)))


(defn  candlestick-de-alta?
  [candlestick]
  (or (< (:abertura candlestick) (:fechamento candlestick))
      (= (:abertura candlestick) (:fechamento candlestick)))
  )

(defn candlestick-de-baixa?
  [candlestick]
  (> (:abertura candlestick) (:fechamento candlestick))
  )