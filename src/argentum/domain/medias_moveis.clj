(ns argentum.domain.medias-moveis
  (:use argentum.domain.candlestick))


(defn obter-candles-ultimos-3-dias
  "retornar os candlesticks dos últimos 3 dias a partir da posição"
  [serie-temporal posicao] 
  (subvec serie-temporal (- posicao 3) posicao))



(defn por-fechamento
  "retornar o valor do fechamento de um candlestick"
  [candlestick]
  (:fechamento candlestick))


(defn media-movel-simples
  "calcula a média móvel simples com base na função informada"
  [f serie-temporal posicao]
  (let [candles-3-ultimos-dias (obter-candles-ultimos-3-dias serie-temporal posicao)
        valores (map f candles-3-ultimos-dias)
        soma (apply + valores)]
    (/ soma 3)))
