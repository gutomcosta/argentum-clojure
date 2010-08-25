(ns candlestick)

(defn create-candlestick
	[negocios]
	(assert (not (nil? negocios)))
	(assert (not (empty? negocios)))
	(let [
			preco-minimo (apply min (map (fn[negocio] (:preco negocio)) negocios))
			preco-maximo (apply max (map (fn[negocio] (:preco negocio)) negocios))
			abertura (:preco (first negocios))
			fechamento (:preco (last negocios))
			volume (apply + (map (fn[negocio] (* (:preco negocio) (:quantidade negocio))) negocios))
		]
		
		{:abertura abertura :fechamento fechamento :preco-minimo preco-minimo :preco-maximo preco-maximo :volume volume}
		)
	)
