# counter-api
This is a simple project to count number of words in a file. File is part of build itself and placed under resource folder. It has 2 methods, one to accept strings to search in json format and return output in json format. 

url - http://localhost:8080/counter-api/search
sample search string - {"searchString":["Duis", "Sed", "123", "Pellentesque"]}

Second method is to return top occuring words, response could be downloaded to csv file

url - http://localhost:8080/counter-api/top/20

Entire project is built using Spring MVC rest approach. This is a good kick start to implement simple rest web service which secured using Basic Base64 authentication using header token.
