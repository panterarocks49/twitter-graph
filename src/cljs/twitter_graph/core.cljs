(ns twitter-graph.core
  (:require [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload

(defn new-board [n]
  (vec (repeat n (vec (repeat n 0)))))



(defonce app-state
  (atom {:text "Welcome to tic tac toe"
         :board (new-board 3)}))



(defn tictactoe []
  [:center
   [:h1 (:text @app-state)]
   [:svg
    {:view-box "0 0 3 3"
     :width    500
     :hieght   500}
    

   (doall (for [i (range (count (:board @app-state)))
          j (range (count (:board @app-state)))]
      [:rect {:width  0.9
              :height 0.9
              :x      i
              :y      j
              :on-click
              (fn [e]
                (println "You clicked me!" i j)
                (swap! app-state update-in [:board j i] inc))}]))]])













(defn start []
  (reagent/render-component [tictactoe]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))





