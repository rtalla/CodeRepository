# Assumptions
* Java v8 [java version "1.8.0_152"] and Apache Maven v3.5.0 are used to build the application
* set JAVA_HOME environment variable appropriately [Example: c:\progra~1\java\jdk1.8.0_152].
* Main Processing Logic is in "com.walmart.TicketService.TicketServiceImpl.java".
* JUnit Test Cases are in "com.walmart.TicketService.TicketServiceImplTest.java".
* Program reads following properties from "TicketServiceApp/src/main/resources/config.properties" using java.util.ResourceBundle
  * Rows=9
  * SeatsInEachRow=34
  * SeatHoldTimeInSecs=5

# Instructions to run the application

* Using Maven

  * git clone https://github.com/rtalla/CodeRepository.git

  * cd CodeRespository/TicketServiceApp

  * mvn clean install

* From Eclipse

  * git clone

  * Import the project into eclipse as a maven project

  * Right click [TicketServiceImpl.java] and Run->As Application
 
  * Right click [TicketServiceImplTest.java] and Run as JUnit.      
      

# Log of the application as run from eclipse [ Eclise-->Right click [TicketServiceImpl.java] and Run->As Application].
* 
* * ======================numSeatsAvailable===============================================
* TotalAvailableSeats = 306
* TotalNumRows = 9
* TotalSeatsInEachRow = 34
* ======================================================================================

* 
* ===================findAndHoldSeats==================================================
* Placing Seats on Hold = 10
* Setting to HOLD [ROW-COL] =[9-18]
* Setting to HOLD [ROW-COL] =[9-17]
* Setting to HOLD [ROW-COL] =[9-19]
* Setting to HOLD [ROW-COL] =[9-16]
* Setting to HOLD [ROW-COL] =[9-20]
* Setting to HOLD [ROW-COL] =[9-15]
* Setting to HOLD [ROW-COL] =[9-21]
* Setting to HOLD [ROW-COL] =[9-14]
* Setting to HOLD [ROW-COL] =[9-22]
* Setting to HOLD [ROW-COL] =[9-13]

* A=AVAIABLE, H=HOLD, R=RESERVED
* [ROW-1]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-2]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-3]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-4]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-5]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-6]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-7]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-8]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-9]   A A A A A A A A A A A A H H H H H H H H H H A A A A A A A A A A A A 
* TotalAvailableSeats After Hold= 296
* ======================================================================================

* ====================ReserveSeats======================================================
* Setting to Reserved [ROW-COL] =[9-18]
* Setting to Reserved [ROW-COL] =[9-17]
* Setting to Reserved [ROW-COL] =[9-19]
* Setting to Reserved [ROW-COL] =[9-16]
* Setting to Reserved [ROW-COL] =[9-20]
* Setting to Reserved [ROW-COL] =[9-15]
* Setting to Reserved [ROW-COL] =[9-21]
* Setting to Reserved [ROW-COL] =[9-14]
* Setting to Reserved [ROW-COL] =[9-22]
* Setting to Reserved [ROW-COL] =[9-13* * ]

* A=AVAIABLE, H=HOLD, R=RESERVED
* [ROW-1]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-2]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-3]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-4]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
* [ROW-5]   A A A A A A A A A A A A A A A*  A A A A A A A A A A A A A A A A A A A 
* [ROW-6]   A A A A A A A A A A A A A A * A A A A A A A A A A A A A A A A A A A A 
* [ROW-7]   A A A A A A A A A A A A A A * A A A A A A A A A A A A A A A A A A A A 
* [ROW-8]   A A A A A A A A A A A A A A * A A A A A A A A A A A A A A A A A A A A 
* [ROW-9]   A A A A A A A A A A A A R R * R R R R R R R R A A A A A A A A A A A A 
* TotalAvailableSeats After Reservation * = 296
* ======================================* ================================================
