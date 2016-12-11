# Travel Website using spark, java, javascript and postgresql

## This site will uses java and postgresql to save and display your information, 10/06/16

#### By **Adam Gorbahn, Andrew Fisher, Andrew Merrell, David Bethune, and Jackson Meyer**

## Description
This site is a travel site where users can book hotels, flights and cars, using SkyScanner's cheap flight tracking API.

## Specifications

#### Login Page

|user input                | output
|------------------------- | -------------
|Type in the create an account fields and click submit| creates a user, and logs them in automatically

|user input                | output
|------------------------- | -------------
|Type in login fields and click login| Searches for any existing logins if found that user if logged in, else an error page appears.

#### Main Page

|user input                | output
|------------------------- | -------------
|To book a flight the user enters flight information | if the requested flight query exists send the user to the flight page, else the user is sent a "cant find" error message and sent back to the main page

|user input                | output
|------------------------- | -------------
|To book a hotel the user enters all hotel information | the hotel is added to their itinerary

|user input                | output
|------------------------- | -------------
|To book a car the user enters all car information | the car is added to their itinerary

|user input                | output
|------------------------- | -------------
|User can then hit the logout button | will send the user to the login page and delete their itinerary

#### Flight Page

|user input                | output
|------------------------- | -------------
|User can look over all flights from their request and click add a flight| This will add users chosen site to their itinerary.

## Setup/Installation Requirements

* need Java, gradle, spark, and Postgresql.

## code

```
In Postgresql:

create database travel_advisor;
create table users (id serial PRIMARY KEY, name varchar, email varchar, age int, password varchar);

create table flights (id serial PRIMARY KEY, startdate varchar, enddate varchar, price int, groupsize int, userid int, startlocation varchar, endlocation varchar, carrier varchar);

create table hotels (id serial PRIMARY KEY, name varchar, roomsbooked int, duration int, price int, userid int);

create table cars (id serial PRIMARY KEY, name varchar, rentaldays int, price int, userid int);

create database travel_advisor_test;
create table users (id serial PRIMARY KEY, name varchar, email varchar, age int, password varchar);

create table flights (id serial PRIMARY KEY, startdate varchar, enddate varchar, price int, groupsize int, userid int, startlocation varchar, endlocation varchar, carrier varchar);

create table hotels (id serial PRIMARY KEY, name varchar, roomsbooked int, duration int, price int, userid int);

create table cars (id serial PRIMARY KEY, name varchar, rentaldays int, price int, userid int);

```

## Technologies Used

* HTML
* Java
* Spark
* JUint
* Gradle
* Postgresql
* json.simple
* SkyScanner API

### License

*GPL*

Copyright (c) 2016 **Adam Gorbahn, Andrew Fisher, Andrew Merrell, Jackson Meyer and David Bethune**
