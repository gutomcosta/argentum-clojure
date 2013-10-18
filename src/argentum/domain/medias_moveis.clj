(ns argentum.domain.medias-moveis
  (:use argentum.domain.candlestick))


(defn candles-ultimos-3-dias
  "retornar os candlesticks dos últimos 3 dias a partir da posição"
  [serie-temporal posicao] 
  (subvec serie-temporal (- posicao 3) posicao))



(defn por-fechamento
  "retornar o valor do fechamento de um candlestick"
  [candlestick]
  (:fechamento candlestick))


(defn por-abertura
  "retornar o valor da abertura de um candlestick"
  [candlestick]
  (:abertura candlestick))


(defn media-movel-simples
  "calcula a média móvel simples com base
    na função informada"
  [funcao serie-temporal posicao]
  (let [candles
          (candles-ultimos-3-dias serie-temporal posicao)
        valores (map funcao candles)
        soma (reduce + valores)]
    (/ soma 3)))



