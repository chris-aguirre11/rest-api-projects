# Travel Prints REST API Project

# Technologies Used
J2EE, Jersey, Jackson, Hibernate, mySQL

# What Has Been Implemented
GET 	/travelprints/state/{state}/cities
POST 	/travelprints/user/{user}/visits
DEL 	/travelprints/user/{user}/visit/{visit}
GET 	/travelprints/user/{user}/visits
GET 	/travelprints/user/{user}/visits/states
GET		/travelprints/user/{user}/visits/markers
	Note: 
		1) Provides an XML file so users of this API can use to call 
		   the Google Maps API and easily plot all points latitude/longitude)
		2) Reference: https://developers.google.com/maps/articles/phpsqlajax_v3#checking-that-xml-output-works


# What Has Not Been Implemented
1) Proper handling of all improper requests and error scenarios/cases
2) Proper handling of requests that result in large data sets
3) Handling authentication of users prior to allowing changes to their visits


# Improvements Needed
1) Database schema can be improved to make use of foreign keys to keep data integrity
2) Database triggers can be used to limit the need to insert into dependant tables before adding new userVisit entries
3) Can use a Template Method Design Pattern in all the DAO Objects to limit the code duplication of Hibernate database calls


# Background/Thought Process
My original intention was to do this project in C#, but after struggling with Entity Framework and ASP.NET I decided to use Java J2EE instead in order to complete by the ETA


