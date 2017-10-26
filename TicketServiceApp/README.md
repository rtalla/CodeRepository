# Assumptions
* Java v8[java version "1.8.0_152"] and Apache Maven v3.5.0 are used to build the application
* set JAVA_HOME environment variable appropriately [Example: c:\progra~1\java\jdk1.8.0_152]  
* Main Processing Logic is in com.walmart.TicketService.TicketServiceImpl.java
* JUnit Test Cases are in com.walmart.TicketService.TicketServiceImplTest.java
* Program reads following properties from "TicketServiceApp/src/main/resources/config.properties" using java.util.ResourceBundle
* * Rows=9
* * SeatsInEachRow=34
* * SeatHoldTimeInSecs=5

# Instructions to run the application

* Using Maven

** git clone https://github.com/rtalla/CodeRepository.git

** cd CodeRespository/TicketServiceApp

** mvn clean install

* From Eclipse

** git clone

** Import the project into eclipse as a maven project

** Right click [TicketServiceImpl.java] and Run->As Application
 
** Right click [TicketServiceImplTest.java] and Run as JUnit.      
      
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project .

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

1)Assumption



  


6)Here is a log of application when run from eclipse

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
======================numSeatsAvailable===============================================
TotalAvailableSeats = 306
TotalNumRows = 9
TotalSeatsInEachRow = 34
======================================================================================


===================findAndHoldSeats==================================================
Placing Seats on Hold = 10
Setting to HOLD [ROW-COL] =[9-18]
Setting to HOLD [ROW-COL] =[9-17]
Setting to HOLD [ROW-COL] =[9-19]
Setting to HOLD [ROW-COL] =[9-16]
Setting to HOLD [ROW-COL] =[9-20]
Setting to HOLD [ROW-COL] =[9-15]
Setting to HOLD [ROW-COL] =[9-21]
Setting to HOLD [ROW-COL] =[9-14]
Setting to HOLD [ROW-COL] =[9-22]
Setting to HOLD [ROW-COL] =[9-13]

A=AVAIABLE, H=HOLD, R=RESERVED
[ROW-1]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-2]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-3]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-4]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-5]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-6]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-7]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-8]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-9]   A A A A A A A A A A A A H H H H H H H H H H A A A A A A A A A A A A 
TotalAvailableSeats After Hold= 296
======================================================================================

====================ReserveSeats======================================================
Setting to Reserved [ROW-COL] =[9-18]
Setting to Reserved [ROW-COL] =[9-17]
Setting to Reserved [ROW-COL] =[9-19]
Setting to Reserved [ROW-COL] =[9-16]
Setting to Reserved [ROW-COL] =[9-20]
Setting to Reserved [ROW-COL] =[9-15]
Setting to Reserved [ROW-COL] =[9-21]
Setting to Reserved [ROW-COL] =[9-14]
Setting to Reserved [ROW-COL] =[9-22]
Setting to Reserved [ROW-COL] =[9-13]

A=AVAIABLE, H=HOLD, R=RESERVED
[ROW-1]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-2]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-3]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-4]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-5]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-6]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-7]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-8]   A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
[ROW-9]   A A A A A A A A A A A A R R R R R R R R R R A A A A A A A A A A A A 
TotalAvailableSeats After Reservation = 296
======================================================================================
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
