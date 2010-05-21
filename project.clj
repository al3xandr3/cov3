(defproject cov3 "0.1.0"
  :description "cov3"
  
  ;; Find more here http://jarvana.com/jarvana/
  :dependencies [[org.clojure/clojure "1.2.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib "1.2.0-SNAPSHOT"]
		 [org.seleniumhq.selenium/selenium-htmlunit-driver "2.0a2"]
		 [org.seleniumhq.selenium/selenium-firefox-driver "2.0a2"]
		 [org.seleniumhq.selenium/selenium-ie-driver "2.0a2"]
		 [org.seleniumhq.selenium/selenium-chrome-driver "2.0a2"]
		 [net.sf.opencsv/opencsv "2.1"]]
  
  :dev-dependencies [[swank-clojure "1.2.0"]
		     [autodoc "0.7.1"]]                  
  
  :autodoc { :name "Cov3", :page-title "Cov3 API Documentation", :author "al3xandr3"} 
  
  :main cov3
  )