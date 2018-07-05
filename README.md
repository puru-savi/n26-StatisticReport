# n26-StatisticReport
Java project to add transactions and get statistic report of those transactions.

Problem Statement

﻿We would like to have a restful API for our statistics. The main use case for our API is to
calculate realtime statistic from the last 60 seconds. There will be two APIs, one of them is
called every time a transaction is made. It is also the sole input of this rest API. The other one
returns the statistic based of the transactions of the last 60 seconds.

Tech Stack Used:

Java,
Spring Boot,
Maven

End Points

﻿POST /addtransaction
     
 URL: http://localhost:8080/api/addtransaction
 
 Method: POST
 
 Body: 
 
 {
    "amount": 80,
    "timestamp": 1530794116000
}


﻿GET /getstatistic

URL: http://localhost:8080/api/getstatistic

Method: GET

Response:

﻿{
  "sum": 1000,
  "avg": 100,
  "max": 200,
  "min": 50,
  "count": 10
}

      
      












