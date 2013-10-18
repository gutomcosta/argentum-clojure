(ns leitor-xml-test
  (:use clojure.test)
  (:use argentum.xml.leitor-xml)
  (:use argentum.domain.negocio)
  (:import java.io.FileInputStream))


(deftest leitor-xml-test
  (testing "deve ser possível importar um negócio a partir de um xml"
    (let [xml-file "file.xml"
          negocios (carregar-negocios xml-file)]
      (is (= 2 (count negocios)))
      (is (= 43.5 (:preco (first negocios))))
      )
    )
  )