(ns mortgag-calculator.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [mortgag-calculator.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[mortgag-calculator started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[mortgag-calculator has shut down successfully]=-"))
   :middleware wrap-dev})
