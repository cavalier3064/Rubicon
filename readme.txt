Farms API Prerequisite:-

1. Installed java8 on server
2. Installed STS or eclipse on server

Application Instllation:-
1.Import as existing maven application in eclipse or STS
2.Right click on application and update maven dependency using maven menu
3.Right click on application, select as RUN AS menu option and Run apllication using spring-boot:run command



Farms API Service:-
1ST API
Create Order
URL:- http://localhost:8080/farms/createOrder?farmId=1002&startDateTime=25-Feb-2020%2010%3A00%3A00&duration=1

Input parameter:-

farmId (int) Required
startDateTime(date time) required. Format(MM/dd/yyyy-HH:mm:ss) e.g. 04/02/2020-16:20:20
duration(int) required- (duration is number of hours)

Response:-
Order has been placed but not yet delivered.

2ND API
Get Farm details

URL:- http://localhost:8080/farms/getFarms

NO ANY INPUT PARAMETER REQUIRED

Response:-
{
  "1001": {
    "farmId": 1001,
    "status": "Requested",
    "startDateTime": "04/02/2020-16:20:20",
    "duration": 1
  },
  "1002": {
    "farmId": 1002,
    "status": "Requested",
    "startDateTime": "04/02/2020-16:20:20",
    "duration": 1
  }
}
 
3rd API
Cancel Order
URL:-http://localhost:8080/farms/cancelOrder?farmId=1001
Input Parameter:-
farmId(int) required

Response:-
Order was cancelled before delivery.

Scheduled Job:-

1. When order is getting placed, log will print "New Water order for farm XYZ created"
2. When current date is equal and less than Start Date + duration hour then log will print "Water delivery to farm for XYZ started"
3. When current date is equal or greater than Start Date + duration hour then log will print "Water delivery to farm for XYZ stop"

Source File:-
1. FarmsController.java where request will land from swagger
2. FarmServiceImpl.java where request will process, scheduled the API status update and return API result
3. FarmsUtil.java where all utiliteis and service validation applied
4.logback.xml file where logs file location and logs pattern are defined

Date validation:-
1.Date format should be(MM/dd/yyyy-HH:mm:ss) e.g. 04/02/2020-16:20:20, where time should be in 24 hours format
2. start date time should be greater than or equal to current date time

Logs Generation:-
Location is defined in logback.xml, where you can change your log location

