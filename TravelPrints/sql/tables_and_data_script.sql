CREATE TABLE IF NOT EXISTS `TravelPrints`.`country` (
  `COUNTRY_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`COUNTRY_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `TravelPrints`.`state` (
  `STATE_NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`STATE_NAME`,`COUNTRY_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `TravelPrints`.`city` (
  `CITY_NAME` varchar(45) NOT NULL,
  `STATE_NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `LATITUDE` varchar(45) NOT NULL,
  `LONGITUDE` varchar(45) NOT NULL,
  PRIMARY KEY (`CITY_NAME`,`STATE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `TravelPrints`.`user` (
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_INITIAL` varchar(1) DEFAULT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`FIRST_NAME`,`LAST_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `TravelPrints`.`user_visits` (
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_INITIAL` varchar(1) DEFAULT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `CITY_NAME` varchar(45) NOT NULL,
  `STATE_NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) DEFAULT NULL,
  `DATE_VISITED` date DEFAULT NULL,
  `NUMBER_OF_DAYS` int(11) DEFAULT NULL,
  PRIMARY KEY (`FIRST_NAME`,`CITY_NAME`,`STATE_NAME`,`LAST_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Load a few initial rows into COUNTRY
INSERT INTO `travelprints`.`country`(`COUNTRY_NAME`)VALUES('United States');
INSERT INTO `travelprints`.`country`(`COUNTRY_NAME`)VALUES('Mexico');
INSERT INTO `travelprints`.`country`(`COUNTRY_NAME`)VALUES('Italy');

-- Load a bunch of initial STATES into STATE from a csv file
LOAD DATA INFILE 'C:\\<pathToFile>\\<pathToFile>\\initial_states.csv' 
INTO TABLE STATE 
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

-- Load a bunch of initial CITIES into CITY from a csv file
LOAD DATA INFILE 'C:\\<pathToFile>\\<pathToFile>\\initial_cities.csv' 
INTO TABLE CITY 
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';



/*
-- Useful Queries To Use While Testing

SELECT * FROM travelprints.country;
SELECT * FROM travelprints.state where COUNTRY_NAME like '%Mexico%';
SELECT * FROM travelprints.city where CITY_NAME in ('Alpine', 'Las Vegas')
SELECT * FROM travelprints.user;
SELECT * FROM travelprints.user_visits;

-- delete from travelprints.state where STATE_NAME like '%Alabam%'
-- delete from travelprints.user where STATE_NAME like '%Alabam%'
-- delete from travelprints.user_visits where STATE_NAME like '%Alabam%'
-- delete from travelprints.country;
-- delete from travelprints.state;
*/

