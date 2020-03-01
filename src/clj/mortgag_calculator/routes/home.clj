(ns mortgag-calculator.routes.home
    (:require
      [mortgag-calculator.layout :as layout]
      [mortgag-calculator.db.core :as db]
      [clojure.java.io :as io]
      [mortgag-calculator.middleware :as middleware]
      [ring.util.anti-forgery :refer [anti-forgery-field]]
      [ring.util.response]
      [ring.util.http-response :as response])
    (:gen-class)
    (:import '[mortgag-calculator.cljs.home]))

(defn home-page [request]
  (layout/render request "home.html"
                 {:calculations (db/get-calculations)}))

; perform the calculation
(defn calculate! [{:keys [params]}]

  (println "---> fixed_interest_rate: " (:fixed_interest_rate params))
  (println "---> purchase_price: " (:purchase_price params))
  (println "---> bond_term: " (:bond_term params))
  (println "---> deposit_paid: " (:deposit_paid params))

  ; evaluate the numerator of the equation
  (def nume_rator
    (* (/ (/ (Double/parseDouble (:fixed_interest_rate params)) 100) 12)
       (- (Double/parseDouble (:purchase_price params))
          (Double/parseDouble (:deposit_paid params)))))

  (println "---> Numerator: " nume_rator)

  ; evaluate the denominator of the equation
  (def denomi_nator
    (- 1
       (Math/pow
        (+ 1 (/ (/ (Double/parseDouble (:fixed_interest_rate params)) 100) 12))
        (* -1 (* (Double/parseDouble (:bond_term params)) 12)))))

  (println "---> Denominator: " denomi_nator)

  ; finally, calculate the monthly payment
  (def monthly_payment (/ nume_rator denomi_nator))

  (println "---> Result: " monthly_payment)

  ; set the html element values
  (cljs/set-element-text "purchase_price" (:purchase_price params))
  (cljs/set-element-text "deposit_paid" (:deposit_paid params))
  (cljs/set-element-text "bond_term" (:bond_term params))
  (cljs/set-element-text "fixed_interest_rate" (:fixed_interest_rate params))
  (cljs/set-element-text "monthly_payment" (:monthly_payment params))

  (response/found "/"))

; insert calculation into database
(defn save-calculation! [{:keys [params]}]
  (db/save-calculation!
   (assoc params :id 1, :calculation_name params, :purchase_price params, :deposit_paid params, :bond_term params, :fixed_interest_rate params, :timestamp (java.util.Date.)))
  (response/found "/"))

(defn base-page [request]
  (layout/render request "base.html"
                 {:calculations (db/get-calculations)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/calculation" {:post calculate!}]
   ["/save" {:post save-calculation!}]])

