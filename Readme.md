# Simple CQRS in Java

A porting in Java (with some slight modifications) of Greg Young's Simple CQRS example.

Building
========

    $ mvn package

Run
====

    $ java -jar target/api.jar

Invoke API
==========

    $ curl -v -X POST --data "" "http://localhost:8080/createItem?name=item_name"

Knowing the UUID of the aggreate and the version of the aggregate you can for example deactivate it as follows:

    $ curl -v -X POST --data "" "http://localhost:8080/deactivateItem?id=1e356fd3-047f-46e9-8aff-03be44103af7&version=0"
