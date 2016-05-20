# Asuisstant

## Overview
Assuisstant is an android application built for Credit Suisse's hackathon, Code Suisse.
Our android application allows users to log notes about their meetings and write messages that will be displayed on a Twitter-like feed. These messages can be processed and read appropriately, 

## Features
* Users can login to view a list of their meetings
* Users can log notes about their meetings
* Meetings are color coded based on their completion of the log notes
* After filling in log notes, a user can create a Twitter-like message that other users can view.
* After filling in log notes, a user can view the logs written
* Users can tag stock ticks that they discussed during their meeting.
* Users can fill in log notes through speech recognition
* Sentiment Analysis ran on written logs to determine viability of initiave regarding specific tickers
* All information (logs,users,messages,etcn) is stored on a remote web server

## Software Used
* Android Application
  * Built using Andriod Studio (Java)
  * Utilized Fragments for displaying Meeting and Activity Feed tabs
  * Used Async Java Functions for database queries, parsed data to populate models
  * Login information stored in SQL lite Database for continuous verification of Database, for ease of user
  * Google Speak implemented for speech recognition
  * Sentiment Analysis done through scraping of positive&negative word dictionaries; parsed log compared with dictionaries yielding sentiment
* Web Server
  * Built in NodeJS 
  * Using package ngrok to tunnel HTTP traffic to localhost running HTTPServer.js
  * Our web server communicates with a local postgresql database
  * GET requests trigger the server to p rovide a JSON of a specific table in the database
  * POST requests trigger the server to execute a postgresql query to update the database or insert entries
