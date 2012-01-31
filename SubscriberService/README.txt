NTIS Subscriber Service - README and FAQ
========================================
	
This is an example implementation of NTIS Subscriber Service that uses the Spring web-services framework. 
You could import the project into eclipse and tailor it accordingly for your needs.
	
DEPLOYING PRE-BUILT WAR FILE MANUALLY TO A TOMCAT 6.X INSTALLATION:
--------------------------------------------------------
Simply copy the SubscriberService.war file from build/libs folder to %CATALINA_HOME%/webapps folder. 
Once deployed you could invoke the WSDL from http://localhost:8080/SubscriberService/services/subscriber.wsdl  

BUILD THE WAR FROM THE SOURCE:
-----------------------------------------------------	
NOTE: For all of the following steps Gradle 1.x and JDK 1.6.x needs to be on your path (e.g. PATH=%PATH%;c:\gradle\bin;c:\jdk1.6.0_26\bin).

From the project root directory simply run
gradle -i build
and the script will build the SubscriberService.war file under build/libs.

JETTY APPLICATION DEPLOYMENT FROM THE SCRIPT:
------------------------------------------------------
To deploy the application war file on jetty server with gradle, simply run
gradle -i jettyRunWar
It will build and deploy the SubscriberService.war on jetty and the wsdl could be accessed from 
http://localhost:8080/SubscriberService/services/subscriber.wsdl 


TOMCAT APPLICATION DEPLOYMENT FROM THE SCRIPT:
----------------------------------------------
Ensure CATALINA_HOME points to a valid Tomcat 6.x installation. 

From the project root directory simply run
gradle -i deployTomcat
and the script will build and deploy the SubscriberService.war file on Tomcat and the wsdl could be accessed from 
http://localhost:8080/SubscriberService/services/subscriber.wsdl 
	
