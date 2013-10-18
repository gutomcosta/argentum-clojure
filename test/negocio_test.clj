(ns negocio-test
  (:use clojure.test)
  (:use argentum.domain.negocio)
  (:import java.util.Calendar))

(deftest negocios-test
  (testing "não deve ser possível criar um negócio sem data
    (is (thrown? AssertionError (create-negocio 30.4 22 nil)))")
  (testing "não deve permitir um negócio com preço negativo"
    (is (thrown? AssertionError (criar-negocio -32.0 100 (Calendar/getInstance))))
    )
  )


