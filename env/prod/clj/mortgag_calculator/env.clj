(ns mortgag-calculator.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[mortgag-calculator started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[mortgag-calculator has shut down successfully]=-"))
   :middleware identity})
