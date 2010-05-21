
(ns cov3-test
  (:use [cov3] :reload-all)
  (:use [clojure.test]))

(import 'java.util.Date)
(import 'java.util.Calendar)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; PAGE VALIDATOR ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest page-validator
  (cov3/with-browser 
    b :ff 
    (is (= 
	 "http://al3xandr3.github.com/,al3xandr3,true"
	 (cov3/validate-page b "http://al3xandr3.github.com/" 
			     '("document.title" "(typeof jQuery !== 'undefined')"))))
    (is (=
	 "http://al3xandr3.github.com/,al3xandr3"
	 (cov3/validate-page b "http://al3xandr3.github.com/" 
			     '("document.title") (fn[b](cov3/remove-cookie b "_github_ses")))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; WEBDRIVER API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro browse-and-test
  [b type & body]
  `(cov3/with-browser ~b ~type 
    (cov3/go ~b "http://al3xandr3.github.com/")
    ~@body))
;; (macroexpand '(cov-test/browse-and-test b :hu (= "al3xandr3" (cov3/js-eval b "document.title"))))

(deftest thecookies
  (let [cal (Calendar/getInstance)]
    (.add cal Calendar/MONTH 1)
    (browse-and-test b :ff
      (is (= "" (cov3/read-cookie b "bla")))
      (cov3/write-cookie b "bla" "123" (.getTime cal))
      (is (= "123" (cov3/read-cookie b "bla")))
      (cov3/remove-cookie b "bla")
      (is (= "" (cov3/read-cookie b "bla"))))))

(deftest thelinks
  ;; gets the site's title header link, that should always exist as pointing to home page
  (browse-and-test b :hu (is (= "http://al3xandr3.github.com/" (first (cov3/get-links-in-domain b))))))

(deftest domain-from-open-browser
  (browse-and-test b :hu (is (= "al3xandr3.github.com" (cov3/get-domain b)))))


(deftest protocol
  (browse-and-test b :hu (is (= "http:" (cov3/get-protocol b)))))


(deftest firefox-can-eval-js
  (browse-and-test b :ff (is (= "al3xandr3" (cov3/js-eval b "document.title")))))


(deftest htmlunit-can-eval-js
  (browse-and-test b :hu (is (= "al3xandr3" (cov3/js-eval b "document.title")))))
