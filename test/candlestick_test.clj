(ns candlestick-test
  (:use clojure.test)
  (:use argentum.domain.candlestick)
  (:use argentum.domain.negocio)
  (:import java.util.Calendar))


(deftest candlestick-test
  (let [
    hoje (Calendar/getInstance)
    negocio1 (criar-negocio 40.5 100 hoje)
    negocio2 (criar-negocio 45.0 100 hoje)
    negocio3 (criar-negocio 39.8 100 hoje)
    negocio4 (criar-negocio 42.3 100 hoje)
    negocios [negocio1,negocio2,negocio3,negocio4]
    ]
    (testing "deve criar um candlestick baseado em uma lista de negocios"
      (let [candlestick (criar-candlestick negocios)]
        (is (= 40.5 (:abertura candlestick)))
        (is (= 42.3 (:fechamento candlestick)))
        (is (= 45.0 (:preco-maximo candlestick)))
        (is (= 39.8 (:preco-minimo candlestick)))
        (is (= 16760 (:volume candlestick)))
        )
      )
    (testing "não deve ser possível criar um candlestick quando a lista de negocios for vazia"
      (is (thrown? AssertionError (criar-candlestick [])))
      )
    (testing "não deve ser possível criar um candlestick quando a lista de negócio for nula"
      (is (thrown? AssertionError (criar-candlestick nil)))
      )
    (testing "deve ser possível criar um candlestick quando a lista conter apenas um negócio"
      (let [candlestick (criar-candlestick [negocio1])]
        (is (= 40.5 (:abertura candlestick)))
        (is (= 40.5 (:fechamento candlestick)))
        (is (= 40.5 (:preco-maximo candlestick)))
        (is (= 40.5 (:preco-minimo candlestick)))
        (is (= 4050 (:volume candlestick)))
        )
      )
    (testing "deve ser possível identificar um candlestick de alta"
      (let [candlestick (criar-candlestick negocios)]
        (is (candlestick-de-alta? candlestick)))
      )
    (testing "deve ser possível identificar um candlestick de baixa"
      (let[
        n1 (criar-negocio 10.0 4  hoje)
        n2 (criar-negocio 14.0 5 hoje)
        n3 (criar-negocio 9.0  6 hoje)
        n4 (criar-negocio  8.0 7 hoje)
        all [n1,n2,n3,n4]
        candlestick (criar-candlestick all)]
        (is (candlestick-de-baixa? candlestick))
        )
      )
    ))
