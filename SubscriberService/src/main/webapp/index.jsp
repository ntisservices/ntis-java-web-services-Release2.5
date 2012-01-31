<html>
	<h2>NTIS Subscriber Service - README and FAQ</h2>
	
	<p>This is an example implementation of NTIS Subscriber Service that uses the Spring web-services framework. 
	You could import the project into eclipse and tailor it accordingly for your needs.</p>

	<h4>DEPLOYING PRE-BUILT WAR FILE MANUALLY TO A TOMCAT 6.X INSTALLATION:</h4>
	<p>Simply copy the SubscriberService.war file from build/libs folder to %CATALINA_HOME%/webapps folder. 
	Once deployed you could invoke the WSDL from  <i><b><a href="http://localhost:8080/SubscriberService/services/subscriber.wsdl">http://localhost:8080/SubscriberService/services/subscriber.wsdl</a></b></i></p>
	
	<h4>BUILD THE WAR FROM THE SOURCE:</h4>
	<p> NOTE: For all of the following steps Gradle 1.x and JDK 1.6.x needs to be on your path.<i><b> (e.g. PATH=%PATH%;c:\gradle\bin;c:\jdk1.6.0_26\bin)</b></i> <br/>
	From the project root directory simply run <i><b>gradle build</b></i> and the script will build the SubscriberService.war file under <i><b>build/libs</b></i>.</p>

	<h4>JETTY APPLICATION DEPLOYMENT FROM THE SCRIPT:</h4>
	<p> To deploy the application war file on jetty server with gradle, simply run <i><b>gradle -i jettyRunWar</b></i> 
	It will build and deploy the SubscriberService.war on jetty and the wsdl could be accessed from <i><b><a href="http://localhost:8080/SubscriberService/services/subscriber.wsdl">http://localhost:8080/SubscriberService/services/subscriber.wsdl</a></b></i></p>

	<h4>TOMCAT APPLICATION DEPLOYMENT FROM THE SCRIPT:</h4>
	<p> Ensure CATALINA_HOME points to a valid Tomcat 6.x installation. <br> From the project root directory simply run <i><b>gradle -i deployTomcat</b></i> 
	and the script will build and deploy the SubscriberService.war file on Tomcat and the wsdl could be accessed from <i><b><a href="http://localhost:8080/SubscriberService/services/subscriber.wsdl">http://localhost:8080/SubscriberService/services/subscriber.wsdl</a></b></i></p>
</html>
