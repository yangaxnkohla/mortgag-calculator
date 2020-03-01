(ns mortgag-calculator.cljs.home)

(defn set-element-text
  ([id text]
   (set! (.-innerHTML (.getElementById js/document id)) text)))


