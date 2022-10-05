# DowJonesIndex
DowJonesIndex

# Welcome to the RBC Coding challenge!

    Please timebox your efforts to 2 - 4 hours for the following challenge.

## Problem Background

    As a programmer with a fascination for stock markets, you got excited about a new data set that you discovered

    that there is a collection of records from the [Dow Jones Index from 2011](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#) that caught your attention. 

 
    You decided you'd like to build an application server (Spring Boot) that would allow multiple users to perform the following operations concurrently:

        - upload a bulk data set

        - query for data by stock ticker (e.g. input: AA, would return 12 elements if the only data uploaded were the single data set above)

        - add a new record

## Requirements

    - Your solution source code should be publicly available to share via a github / gitlab / bitbucket link

    - Solution should be able to run locally (e.g. no dependencies on managed services), but you are encouraged to leverage open source technologies (e.g. Docker, spring boot, mongo, postgresql, mysql etc) for your solution.

    - The solution does not need to be perfect. Please complete what you deem necessary and prioritize accordingly. Most importantly, you should be prepared to discuss how you would potentially enhance the solution given more time, and decisions / tradeoffs that you made.

## Implementation

1. Spring Boot MVC Application with JPA In-Memory DataStore
2. Multi-User via X-Client_Id supplied header by utilizing a field in the table to mark the clientId
3. REST-API
   1. POST /api/stock-data/bulk-insert with Multipart file attached for bulk upload
   2. POST /api/stock-data with JSON request body for single upload
   3. GET /api/stock-data/{id} to retrieve JSON list of elements that match the stock name "id"
4. 

## Usage

    $ ./gradlew :bootRun
    - curl -F "file=@dow_jones_index.data" -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/bulk-insert 
    - curl -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/AA
    - curl -H "Content-Type: application/json" -H "X-Client_Id: abc123" -X POST -d @dow_jones_missing.json http://127.0.0.1:8080/api/stock-data/
