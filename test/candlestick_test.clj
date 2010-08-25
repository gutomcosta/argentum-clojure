(ns candlestick-test
  (:use clojure.test)
  (:use candlestick)
  (:use negocio)
  (:import java.util.Calendar))


(deftest candlestick-test
  (let [
    today (Calendar/getInstance)
    negocio1 (create-negocio 40.5 100 today)
    negocio2 (create-negocio 45.0 100 today)
    negocio3 (create-negocio 39.8 100 today)
    negocio4 (create-negocio 42.3 100 today)
    negocios [negocio1,negocio2,negocio3,negocio4]
    ]
    (testing "deve criar um candlestick baseado em uma lista de negocios"
      (let [candlestick (create-candlestick negocios)]
        (is (= 40.5 (:abertura candlestick)))
        (is (= 42.3 (:fechamento candlestick)))
        (is (= 45.0 (:preco-maximo candlestick)))
        (is (= 39.8 (:preco-minimo candlestick)))
        (is (= 16760 (:volume candlestick)))
        )
      )
    (testing "não deve ser possível criar um candlestick quando a lista de negocios for vazia"
      (is (thrown? AssertionError (create-candlestick [])))
      )
    (testing "não deve ser possível criar um candlestick quando a lista de negócio for nula"
      (is (thrown? AssertionError (create-candlestick nil)))
      )
    (testing "deve ser possível criar um candlestick quando a lista conter apenas um negócio"
      (let [candlestick (create-candlestick [negocio1])]
        (is (= 40.5 (:abertura candlestick)))
        (is (= 40.5 (:fechamento candlestick)))
        (is (= 40.5 (:preco-maximo candlestick)))
        (is (= 40.5 (:preco-minimo candlestick)))
        (is (= 4050 (:volume candlestick)))
        )
      )
    (testing "deve ser possível identificar um candlestick de alta"
      (let [candlestick (create-candlestick negocios)]
        (is (candlestick-de-alta? candlestick)))
      )
    (testing "deve ser possível identificar um candlestick de baixa"
      (let[
        n1 (create-negocio 10.0 4  today)
        n2 (create-negocio 14.0 5 today)
        n3 (create-negocio 9.0  6 today)
        n4 (create-negocio 8.0 7 today)
        all [n1,n2,n3,n4]
        candlestick (create-candlestick all)]
        (is (candlestick-de-baixa? candlestick))
        )
      )
    ))
