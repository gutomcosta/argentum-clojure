(ns negocio-test
  (:use clojure.test)
  (:use argentum.domain.negocio)
  (:import java.util.Calendar))

(deftest negocios-test
  (testing "n�o deve ser poss�vel criar um neg�cio sem data"
    (is (thrown? AssertionError (create-negocio 30.4 22 nil))))
  (testing "n�o deve permitir um neg�cio com pre�o negativo"
    (is (thrown? AssertionError (create-negocio -32.0 100 (Calendar/getInstance))))
    )
  )


