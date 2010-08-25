(ns candlestick-test 
	(:use clojure.test)
	(:use candlestick)
	(:import java.util.Calendar))
	



(deftest candlestick-test
	(let [
		today (Calendar/getInstance)
		negocio1 {:preco 40.5 :quantidade 100 :date today}
		negocio2 {:preco 45.0 :quantidade 100 :date today}
		negocio3 {:preco 39.8 :quantidade 100 :date today}
		negocio4 {:preco 42.3 :quantidade 100 :date today}
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
	))
