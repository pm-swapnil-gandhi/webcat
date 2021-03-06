(ns webcat.gui
  "Graphical user interface for webcat."
  (:use [seesaw.core]
        [seesaw.font])
  (:require [webcat.dat :as dat]
            [seesaw.bind :refer [bind property tee transform]]))

(declare main-window layout results input-bar
         best-category-text best-url-text
         url-input-box search-button)

;; (def layout
;;   (vertical-panel
;;    :items [(label "hello world")]))

;; (defn a-new [e]
;;   "Action: Create new categorization site database file."
;;   (let [selected (select-file :save)]
;;     (if (.exists @current-file)
;;       (alert "File already exists.")
;;       (set-current-file selected))))

;; (defn a-open [e]
;;   "Action: Open categorization sites database file."
;;   (let [selected (select-file :open)]
;;     (set-current-file selected))
;;   (let [site-categories (io/open-file @current-file)]
;;     (cat/set-categories site-categories)))

;; (defn a-save [e]
;;   "Action: Save categorization sites to current file."
;;   (spit @current-file (dat/serialize)))

;; (defn a-save-as [e]
;;   "Action: Save categorization sites to selected file."
;;   (let [selected (select-file :save)]
;;     (set-current-file selected)
;;     (a-save e)))

;; (defn a-exit [e]
;;   "Action: Exit the program."
;;   (dispose! e))

;; (defn a-add [e]
;;   "Action: Add site and category to database."
;;   (let [site-category (fn get-site-and-category [] nil)
;;         duplicate (cat/add site-cat)]; !define a real fn!
;;     (if duplicate
;;       (alert (str "Error: Category " duplicate " already exists.")))))

;; (defn a-remove [e]
;;   "Action: Remove a site from the database."
;;   nil)

;; (defn a-refresh [e]
;;   "Action: Refresh the websites."
;;   nil)

;; (defn a-clear [e]
;;   "Action: Clear the database."
;;   nil)

;; (defn a-about [e]
;;   "Action: Display about dialog"
;;   nil)

;; (def menus
;;   (let [;; File ;;
;;         a-new (action :handler a-new :name "New"
;;                       :tip "Create a new file." :key "menu N")
;;         a-open (action :handler a-open :name "Open"
;;                        :tip "Open a file." :key "menu O")
;;         a-save (action :handler a-save :name "Save"
;;                        :tip "Save to a file." :key "menu S")
;;         a-exit (action :handler a-exit :name "Exit"
;;                        :tip "Exit the program.")
;;         ;; Edit ;;
;;         a-add (action :handler a-add :name "Add"
;;                       :tip "Add a site and its category." :key "menu A")
;;         a-remove (action :handler a-remove :name "Remove"
;;                          :tip "Remove a site and its category." :key "menu R")
;;         a-refresh (action :handler a-refresh :name "Refresh"
;;                           :tip "Refresh the websites." :key "menu R") ; try F5
;;         a-clear (action :handler a-clear :name "Clear"
;;                         :tip "Clear all sites and categories.")
;;         ;; Help ;;
;;         a-about (action :handler a-about :name "About"
;;                         :tip "About this program.")
;;         ]
;;     (menubar
;;      :items [(menu :text "File" :items [a-new a-open a-save a-exit])
;;              (menu :text "Edit" :items [a-add a-remove a-refresh a-clear])
;;              (menu :text "Help" :items [a-about])])))

;; (def main-window
;;   (frame :title "webcat"
;;          :content layout
;;          :minimum-size [640 :by 480]
;;          :menubar menus
;;          :on-close :exit))

(defn process-url
  ([e] (let [url (config url-input-box :text)
             best-url      (dat/closest-url url)
             best-category (dat/closest-category url)]
         (config! best-url-text      :text (first best-url))
         (config! best-category-text :text best-category))))


(def url-input-box
  (text :text ""))

(def search-button
  (button :text "Search"
          :halign :right
          :valign :center
          :listen [:action process-url]))

(def input-bar
  (horizontal-panel :items [url-input-box
                            search-button]
                    :maximum-size [1000 :by 50]
                    :minimum-size [0 :by 50]))

(def best-category-text
  (label "THIS IS A CATEGORY"))

(def best-url-text
  (label "THIS IS A URL"))

(def results
  (grid-panel :items [(label "Category:") best-category-text
                      (label "URL:")      best-url-text]
              :columns 2))

(def layout
  (vertical-panel :items [results
                          input-bar]))

(def main-window
  (frame :title "webcat"
         :content layout
         :minimum-size [640 :by 480]
         :on-close :exit))






;; Notes ;;
; For nice presentation, bind the title to
; (tee "webcat - " (transform .getName current-file)`,
; or however it works. Basically you want "webcat - <current-file>",
; updating whenever the file changes.
;
;
;
;
