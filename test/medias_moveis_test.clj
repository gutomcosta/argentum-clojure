(ns medias-moveis-test
  (:use clojure.test)
  (:use argentum.domain.medias-moveis)
  (:import [argentum.domain.candlestick Candlestick]))

(defn criar-serie
  [valores]
  (vec (map #(Candlestick. %,%,%,%,1000) valores)))


(deftest serie-temporal-test
  (let [serie-temporal (criar-serie [1,2,3,4,3,4,5,4,3])
        ultimos-3-dias (candles-ultimos-3-dias serie-temporal 3)]
    (testing "deve retonar os candlesticks dos 3 ultimos dias"
      (is (= 3 (count ultimos-3-dias))))
    (testing "deve retornar 1 no fechamento do primeiro dia"
      (is (= 1 (:fechamento (get ultimos-3-dias 0)))))
    (testing "deve retornar 2 no fechamento do segundo dia"
      (is (= 2 (:fechamento (get ultimos-3-dias 1)))))
    (testing "deve retornar 3 no fechamento do terceiro dia"
      (is (= 3 (:fechamento (get ultimos-3-dias 2)))))))

(deftest por-fechamento-test
  (let [candlestick (Candlestick. 10,10,10,10,20)
        fechamento (por-fechamento candlestick)]
    (testing "deve retornar 10 no fechamento"
      (is (= 10 fechamento)))))

(deftest media-movel-simples-test
  (let [serie-temporal (criar-serie [1,2,3,4,3,4,5,4,3])]

    (testing "deve retonar 2 quando a posição for 3 e a media for por fechamento"
      (is (= 2 (media-movel-simples por-fechamento serie-temporal 3))))

    (testing "deve retornar 3 quando a posição for 4 e a media for por fechamento"
      (is (= 3 (media-movel-simples por-fechamento serie-temporal 4)))
      )))
