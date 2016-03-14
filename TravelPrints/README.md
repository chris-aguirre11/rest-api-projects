# Travel Prints REST API Project

# Technologies Used
J2EE, Jersey, Jackson, Hibernate, mySQL

# What Has Been Implemented
GET 	/travelprints/state/{state}/cities	<br/>
POST 	/travelprints/user/{user}/visits	<br/>
DEL 	/travelprints/user/{user}/visit/{visit}	<br/>
GET 	/travelprints/user/{user}/visits	<br/>
GET 	/travelprints/user/{user}/visits/states	<br/>
GET		/travelprints/user/{user}/visits/markers	<br/>
	Note: <br/>
		1) Provides an XML file so users of this API can use to call 
		   the Google Maps API and easily plot all points latitude/longitude) <br/>
		2) Reference: https://developers.google.com/maps/articles/phpsqlajax_v3#checking-that-xml-output-works


# What Has Not Been Implemented
1) Proper handling of all improper requests and error scenarios/cases <br/>
2) Proper handling of requests that result in large data sets	<br/>
3) Handling authentication of users prior to allowing changes to their visits	<br/>


# Improvements Needed
1) Database schema can be improved to make use of foreign keys to keep data integrity <br/>
2) Database triggers can be used to limit the need to insert into dependant tables before adding new userVisit entries <br/>
3) Can use a Template Method Design Pattern in all the DAO Objects to limit the code duplication of Hibernate database calls <br/>


# Background/Thought Process
My original intention was to do this project in C#, but after struggling with Entity Framework and ASP.NET I decided to use Java J2EE instead in order to complete by the ETA


