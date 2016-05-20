# Asuisstant

## Overview
Assuisstant is an android application built for Credit Suisse's hackathon, Code Suisse.
Our android application allows users to log notes about their meetings and write messages that will be displayed on a Twitter-like feed.

## Features
* Users can login to view a list of their meetings
* Users can log notes about their meetings
* Meetings are color coded based on their completion of the log notes
* After filling in log notes, a user can create a Twitter-like message that other users can view.
* Users can tag stock ticks that they discussed during their meeting.
* All information (logs,users,messages,etc) are stored on an external web server
* The web server listens from POST/GET requests and creates an appropriate response

## Software Used
* Android Application
  * Built using Andriod Studio (Java)
* Web Server
  * Built in NodeJS 
  * Using package ngrok to tunnel HTTP traffic to localhost running HTTPServer.js
  * Our web server is running a postgresql database
  * The server communicates with the database based on POST/GET requests
