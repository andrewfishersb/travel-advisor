# Travel Website using spark, java, javascript and postgresql

## This site will uses java and postgresql to save and display your information, 10/06/16

#### By **Adam Gorbahn, Andrew Fisher, Andrew Merrell, David Bethune, and Jackson Meyer**

![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402013/55b040be-29ac-11e7-8c83-e9c3158d4fbc.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402014/55b30ba0-29ac-11e7-82c1-eadf9875764a.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402018/55be7b70-29ac-11e7-840a-585a4a3737c7.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402017/55be0028-29ac-11e7-8a3b-ff31439beb14.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402015/55bb3fd2-29ac-11e7-857b-7799d304618e.png)
![ScreenShot](https://cloud.githubusercontent.com/assets/17396138/25402016/55bd25fe-29ac-11e7-8efd-f230a19441b3.png)


## Description
This site is a travel site where users can book hotels, flights and cars, using SkyScanner's cheap flight tracking API.

## Specifications

#### Login Page

|user input                | output
|------------------------- | -------------
|Type in the create an account fields and click submit| creates a user, and logs them in automatically
|Type in login fields and click login| Searches for any existing logins if found that user if logged in, else an error page appears.

#### Main Page

|user input                | output
|------------------------- | -------------
|To book a flight the user enters flight information | if the requested flight query exists send the user to the flight page, else the user is sent a "cant find" error message and sent back to the main page
|To book a hotel the user enters all hotel information | the hotel is added to their itinerary
|To book a car the user enters all car information | the car is added to their itinerary
|User can then hit the logout button | will send the user to the login page and delete their itinerary

#### Flight Page

|user input                | output
|------------------------- | -------------
|User can look over all flights from their request and click add a flight| This will add users chosen site to their itinerary.

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
