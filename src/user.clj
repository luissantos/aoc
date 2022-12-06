(ns user
  (:require [nextjournal.clerk :as clerk]))

;; start Clerk's built-in webserver on the default port 7777, opening the browser when done
#_(clerk/serve! {:watch-paths ["notebooks"] :browse? true})

#_(clerk/show! "notebooks/day_1.clj")

#_(clerk/build! {:out-path "public/build/day1" :paths ["notebooks/day_1.clj"] :bundle? false})