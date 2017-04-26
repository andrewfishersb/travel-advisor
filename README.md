# Frugal Flights


#### By **Adam Gorbahn, Andrew Fisher, Andrew Merrell, David Bethune, and Jackson Meyer**


## Images
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402013/55b040be-29ac-11e7-8c83-e9c3158d4fbc.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402014/55b30ba0-29ac-11e7-82c1-eadf9875764a.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402018/55be7b70-29ac-11e7-840a-585a4a3737c7.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402017/55be0028-29ac-11e7-8a3b-ff31439beb14.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402015/55bb3fd2-29ac-11e7-857b-7799d304618e.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25452414/6468cb7a-2a7a-11e7-8365-a167f63eec89.png)


## Description
This site is a travel site where users can book hotels, flights and cars, using SkyScanner's cheap flight tracking API.

## User Stories

* As a user, I want to be able to create and account and login to the travel site.
* As a user, I want to book a cheap flight on set dates.
* As a user, I want to book a hotel for a number of nights.
* As a user, I want to book a vehicle for a number of days.

## Specifications

##### Login Page
* Create an account.
* Login to an account.

##### Main Page

* User can book a flight by entering the following information:
  - Departure location and end location.
  - Departure and return dates.
  - Size of group you are booking.

* User can book a hotel by entering the following information:
  - The hotel you want.
  - Number of nights of the stay.
  - Number of rooms required.

* User can book a vehicle by entering the following information:
  - The car you want.
  - Number of days the car is needed.
* View itinerary with a cost breakdown for flight, hotel, car rental and overall cost.

##### Flight Page

* Calls the SkyScanner API to find the cheapest flights.
* View cheap flights with the following information:
  - Airline
  - Cost per person
  - Whether in direct or non-direct
  - Departure and return date.
* Book a flight.

## Setup/Installation Requirements

* Require Java, Gradle, Spark and PostreSQL
* terminal: git clone https://github.com/andrewfishersb/travel-advisor-java-group-project/
* terminal: cd travel-advisor-java-group-project
* terminal: postgres
* open another terminal tab and type psql

```
In Postgresql:

create database travel_advisor;

\c travel_advisor

create table users (id serial PRIMARY KEY, name varchar, email varchar, age int, password varchar);

create table flights (id serial PRIMARY KEY, startdate varchar, enddate varchar, price int, groupsize int, userid int, startlocation varchar, endlocation varchar, carrier varchar);

create table hotels (id serial PRIMARY KEY, name varchar, roomsbooked int, duration int, price int, userid int);

create table cars (id serial PRIMARY KEY, name varchar, rentaldays int, price int, userid int);

create database travel_advisor_test;

\c travel_advisor_test
create table users (id serial PRIMARY KEY, name varchar, email varchar, age int, password varchar);

create table flights (id serial PRIMARY KEY, startdate varchar, enddate varchar, price int, groupsize int, userid int, startlocation varchar, endlocation varchar, carrier varchar);

create table hotels (id serial PRIMARY KEY, name varchar, roomsbooked int, duration int, price int, userid int);

create table cars (id serial PRIMARY KEY, name varchar, rentaldays int, price int, userid int);

```
* Go back to the original terminal tab
* terminal: gradle build
* terminal: gradle run
* open up browser and enter localhost:4567 as your url


## Technologies Used

* Java
* Spark
* JUint
* Gradle
* Postgresql
* json.simple
* SkyScanner API
* HTML/CSS

### License

*GPL*

Copyright (c) 2016 **Adam Gorbahn, Andrew Fisher, Andrew Merrell, Jackson Meyer and David Bethune**
