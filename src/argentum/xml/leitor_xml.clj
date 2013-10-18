(ns argentum.xml.leitor-xml
  (:use argentum.domain.negocio
        clojure.xml)
  (:import java.io.FileInputStream)
  (:import java.util.Calendar)
  (:require [clojure.walk :as walk]))


(defn- carregar-arquivo-xml
  [arquivo-xml]
  (xml-seq (parse (FileInputStream. arquivo-xml))))

(defn-  eliminar-elementos-nulos
  [negocios]
  (filter #(not (nil? %)) negocios))

(defn- obter-elementos-do-xml
  [name xml]
  (let [elementos (map #(if (= name (:tag %)) (:content %)) xml)]
    (eliminar-elementos-nulos elementos)))

(defn- obter-negocios-do-xml
  [xml]
  (obter-elementos-do-xml :negocio xml))

(defn- obter-data
  [negocio-em-xml]
  (let [data (flatten (obter-elementos-do-xml :data negocio-em-xml))
        time (first (flatten (obter-elementos-do-xml :time data)))
        hoje (Calendar/getInstance)]
    (.setTimeInMillis hoje (Long/parseLong time))
    hoje))


(defn- criar-um-negocio
  [negocio-em-xml]
  (let [preco (first (flatten  (obter-elementos-do-xml :preco negocio-em-xml)))
        quantidade (first (flatten (obter-elementos-do-xml :quantidade negocio-em-xml)))
        data (obter-data negocio-em-xml)]
    (criar-negocio (Double/parseDouble preco) quantidade data)))


(defn carregar-negocios
  [arquivo-xml]
  (let [xml (carregar-arquivo-xml arquivo-xml)
        negocios ( obter-negocios-do-xml xml)]
    (map criar-um-negocio negocios)))

