(ns leiningen.new.newnew-test-template
  (:use [clojure.java.io :only [input-stream]])
  (:require [leiningen.newnew.templates :as t]))

(defn newnew-test-template
  "An example template for websites"
  [name]
  {:template true
   :data 
    {:raw-name name
     :name (t/project-name name)
     :namespace (t/sanitize-ns name)
     :nested-dirs (t/name-to-path name)
     :year (t/year)}

   :directives ;; key, input/output or [["input" "output"]] 
     {:render-dirs  [["" :except ["src" "example" "resources"]]
                     ["src"]
                     ["example/dir" "example/render-dirs/dir"]
                     ["example/render-dirs/{{name}}" "example/render-dirs/{{name}}/rendered"]]
      :copy-dirs    [["resources"]
                     ["example/dir" "example/copy-dirs/dir"]
                     ["example/copy-dirs/{{name}}" "example/copy-dirs/{{name}}/copy"]]
      :copy-files   [["example/copy-files/file.txt"] 
                     ["example/copy-files/file.txt" "example/copy-files/copy.txt"]
                     ["example/copy-files/{{name}}.txt" "example/copy-files/{{name}}-copy.txt"]]
      :render-files [["example/render-files/{{name}}.txt"]
                     ["example/render-files/{{name}}.txt"] 
                     ["example/render-files/render.txt"]]
      :make-dirs     [["scratch"]
                     ["example/new-dirs/dir1"] 
                     ["example/new-dirs/dir2"]]
      :make-files   [["" "example/create-files/{{name}}/empty.txt"]
                     [(input-stream "http://www.google.com") "example/create-files/{{name}}/google.html"]
                     ["hello {{name}} is not rendered"  "example/create-files/{{name}}/string.txt"]]}
})