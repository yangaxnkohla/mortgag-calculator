(ns mortgag-calculator.test.db.core
    (:require
      [mortgag-calculator.db.core
       :refer [*db*]
       :as    db]
      [java-time.pre-java8]
      [luminus-migrations.core :as migrations]
      [clojure.test :refer :all]
      [clojure.java.jdbc :as jdbc]
      [mortgag-calculator.config :refer [env]]
      [mount.core :as mount]))

(use-fixtures
 :once
 (fn [f]
   (mount/start
    #'mortgag-calculator.config/env
    #'mortgag-calculator.db.core/*db*)
   (migrations/migrate ["migrate"] (select-keys env [:database-url]))
   (f)))

(deftest test-calculations
  (jdbc/with-db-transaction [t-conn *db*]
                            (jdbc/db-set-rollback-only! t-conn)
                            (let [timestamp (java.util.Date.)]
                              (is
                               (= 1
                                  (db/save-calculation!
                                   {:id                  1
                                    :calculation_name    "Yanga"
                                    :purchase_price      2000000
                                    :deposit_paid        120000
                                    :bond_term           20
                                    :fixed_interest_rate 10.5
                                    :monthly_payment     11500
                                    :timestamp          timestamp}
                                   {:connection t-conn})))

                              (is
                               (=
                                {:id                  1
                                 :calculation_name    "Yanga"
                                 :purchase_price      2000000
                                 :deposit_paid        120000
                                 :bond_term           20
                                 :fixed_interest_rate 10.5
                                 :monthly_payment     11500
                                 :timestamp          timestamp}
                                (db/get-calculations t-conn {:id 1}))))))

;          (deftest test-users
;            (jdbc/with-db-transaction [t-conn *db*]
;                                      (jdbc/db-set-rollback-only! t-conn)
;                                      (is
;                                       (= 1
;                                          (db/create-user!
;                                           t-conn
;                                           {:id         "1"
;                                            :first_name "Sam"
;                                            :last_name  "Smith"
;                                            :email      "sam.smith@example.com"
;                                            :pass       "pass"})))
;                                      (is
;                                       (=
;                                        {:id         "1"
;                                         :first_name "Sam"
;                                         :last_name  "Smith"
;                                         :email      "sam.smith@example.com"
;                                         :pass       "pass"
;                                         :admin      nil
;                                         :last_login nil
;                                         :is_active  nil}
;                                        (db/get-user t-conn {:id "1"})))))
