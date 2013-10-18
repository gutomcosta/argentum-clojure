(ns argentum.domain.candlestick
  (:use argentum.domain.negocio))


(defrecord Candlestick [preco-minimo preco-maximo abertura fechamento volume])


(defn-  obter-dados-de
  [negocios atributo ]
  (map (fn [negocio] (atributo negocio)) negocios))

(defn- obter-preco-minimo-de
  [negocios]
  (reduce min (obter-dados-de negocios :preco)))

(defn- obter-preco-maximo-de
  [negocios]
  (reduce max (obter-dados-de negocios :preco)))

(defn- obter-volume-total-de
  [negocios]
  (reduce + (map obter-volume negocios)))

(defn criar-candlestick
  [negocios]
  (assert (not (nil? negocios)))
  (assert (not (empty? negocios)))
  (let [
    preco-minimo (obter-preco-minimo-de negocios)
    preco-maximo (obter-preco-maximo-de negocios)
    abertura (:preco (first negocios))
    fechamento (:preco (last negocios))
    volume (obter-volume-total-de negocios)]
    (Candlestick. preco-minimo preco-maximo abertura fechamento volume)))


(defn  candlestick-de-alta?
  [candlestick]
  (or (< (:abertura candlestick) (:fechamento candlestick))
      (= (:abertura candlestick) (:fechamento candlestick))))

(defn candlestick-de-baixa?
  [candlestick]
  (> (:abertura candlestick) (:fechamento candlestick)))
