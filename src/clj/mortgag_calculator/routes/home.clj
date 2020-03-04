(ns mortgag-calculator.routes.home
    (:require
      [mortgag-calculator.layout :as layout]
      [mortgag-calculator.db.core :as db]
      [clojure.java.io :as io]
      [mortgag-calculator.middleware :as middleware]
      [ring.util.anti-forgery :refer [anti-forgery-field]]
      [ring.util.response]
      [ring.util.http-response :as response]
      [cljs.core :as cljs])
    (:gen-class))

(defn home-page [request]
  (println "---> [Calculations]: " (db/get-calculations))
  (layout/render request "home.html"
                 {:calculations (db/get-calculations)}))

; perform the calculation
(defn calculate [{:keys [params]}]

  (println "[Calculate]: ")
  (println "---> purchase_price: " (:purchase_price params))
  (println "---> deposit_paid: " (:deposit_paid params))
  (println "---> fixed_interest_rate: " (:fixed_interest_rate params))
  (println "---> bond_term: " (:bond_term params))

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
  (layout/render params "home.html"
                 {:purchase_price      (:purchase_price params)
                  :deposit_paid        (:deposit_paid params)
                  :bond_term           (:bond_term params)
                  :fixed_interest_rate (:fixed_interest_rate params)
                  :monthly_payment     (format "%.2f" monthly_payment)}))

; insert calculation into database
(defn save-calculation! [{:keys [params]}]

  (println "[Save]: ")
  (println "---> calculation_name: " (:calculation_name params))
  (println "---> purchase_price: " (:res_purchase_price params))
  (println "---> deposit_paid: " (:res_deposit_paid params))
  (println "---> fixed_interest_rate: " (:res_fixed_interest_rate params))
  (println "---> bond_term: " (:res_bond_term params))
  (println "---> Result: " (:res_monthly_payment params))

  (db/save-calculation!
   (assoc params
          :calculation_name    (:calculation_name params),
          :purchase_price      (Double/parseDouble  (:res_purchase_price params)),
          :deposit_paid        (Double/parseDouble (:res_deposit_paid params)),
          :bond_term           (Integer/parseInt (:res_bond_term params)),
          :fixed_interest_rate (Double/parseDouble (:res_fixed_interest_rate params)),
          :monthly_payment     (Double/parseDouble  (:res_monthly_payment params)),
          :timestamp           (java.util.Date.)))
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
   ["/calculation" {:get calculate}]
   ["/save" {:post save-calculation!}]])

