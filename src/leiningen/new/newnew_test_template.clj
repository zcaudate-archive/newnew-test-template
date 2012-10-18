(ns leiningen.new.newnew-test-template
  (:use [leiningen.newnew.templates :only [deftemplate]]
        [clojure.java.io :only [input-stream]])
  (:require [leiningen.newnew.templates :as t]))

(deftemplate newnew-test-template
  "An example template for websites"
  [name]
  {:data 
    {:raw-name name
     :name (t/project-name name)
     :namespace (t/sanitize-ns name)
     :nested-dirs (t/name-to-path name)
     :year (t/year)}

   :directives ;; key, input/output or [["input" "output"]] 
     {:new-dirs     ["scratch"
                     "example/new-dirs/dir1" 
                     "example/new-dirs/dir2"]
      :copy-dirs    ["resource"
                     ["example/dir" "example/copy-dirs/dir"]
                     "example/{{name}}"
                     ["example/copy-dirs/{{name}}" "example/copy-dirs/{{name}}/copy"]]
      :render-dirs  ["src"
                     ["example/dir" "example/render-dirs/dir"]
                     "example/{{name}}"
                     ["example/render-dirs/{{name}}" "example/render-dirs/{{name}}/rendered"]]
      :copy-files   ["example/copy-files/file.txt" 
                     ["example/copy-files/file.txt" "example/copy-files/copy.txt"]
                     "example/copy-files/{{name}}/file.txt"
                     ["example/copy-files/{{name}}/file.txt" "example/copy-files/{{name}}/copy.txt"]]
      :render-files ["example/render-files/{{name}}/file.txt"
                     ["example/render-files/{{name}}/file.txt" 
                      "example/render-files/{{name}}/render.txt"]]
      :create-files ["example/create-files/{{name}}/empty.txt"
                    [(input-stream "http://www.google.com") "example/create-files/{{name}}/inputstream.txt"]
                    ["hello {{name}}"  "example/create-files/{{name}}/string.txt"]]
     }})