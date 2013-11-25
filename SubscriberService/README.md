NTIS Subscriber Service example - README
========================================
	
This is an example implementation of an NTIS Subscriber Service that uses the Spring web-services framework. 
You can import the project into Eclipse and tailor it to suit your needs.
	
Prerequisites	
------------------

1. Software to install :

 JDK v1.6

 Gradle v1.4

 Eclipse J2EE IDE v3.6.2
 
 SoapUI v4.5.2 (for testing)
 
 *Note: Versions listed above are those used to test the install/build process, earlier/later compatible versions should also work.*

2. Set the system environment variables:

 JAVA_HOME=\<JDK installation directory\>
	
 GRADLE_HOME=\<Gradle installation directory\>
	
 PATH=${PATH}:${JAVA_HOME}:${GRADLE_HOME}

*Note: all other required Java libraries are downloaded and installed during the gradle-based build process.*

Importing Project in to Eclipse
---------------------------------

1. Download the SubscriberService example application from https://github.com/ntisservices/ntis-java-web-services-Release2.4/archive/master.zip

2. Extract the zip file into the target location on your local file system.

3. Open Eclipse and Import the project into the Eclipse workspace.

 a. File->Import

 b. Select the option General->Existing Project into Workspace

 c. Select the option 'Select root directory' and locate the downloaded project root directory: <target dir>/SubscriberService

 d. Click Finish to import the project.

Eclipse Project Configuration
-------------------------------

Eclipse needs to be configured to run the gradle build/run commands. This is achievied through Eclipse's External Tools Configuration.

The following Eclipse configurations perform the basic build/run operations for the project:

1. Start Service configuration:

 a. From Eclipse, select Run->External Tool–>External Tools Configurations.

 b. Create a new configuration by right-clicking Program and selecting New.

 c. In the Main tab:
 
  1. Specify the Name 'Start Subscriber Service'

  1. Set the Location to: ${GRADLE_HOME}/bin/gradle

  1. Set the working directory to: ${workspace_loc:/SubscriberService}

  1. Specify the arguments '-i ec jettyRunWar', where:  
    * '-i' is used to set the log level to info.  
    * 'eC' is used to generate eclipse specific project artifacts.  
    * 'jettyRunWar' is a defined gradle task (see build.gradle) which assembles the webapp into a war and deploys it to Jetty server.

 d. In the Environment tab, set the JAVA_HOME variable to the installed JDK root directory.

 e. Apply to save the configuration.

2. Stop Service configuration:

 a. Create a new configuration by right-clicking Program and selecting New.

 b. In the Main tab:

  1. Specify the Name 'Stop Subscriber Service'

  1. Set the Location to: ${GRADLE_HOME}/bin/gradle

  1. Set the working directory to: ${workspace_loc:/SubscriberService}

  1. Specify the arguments '-i jettyStop', where:
    * '-i' is used to set the log level to info.
    * 'jettyStop' is a defined gradle task (see build.gradle) which stops Jetty.

 c. In the Environment tab, set the JAVA_HOME variable to the installed JDK root directory.

 d. Apply to save the configuration.

3. Build WAR file from Source Configuration:

 a. Create a new configuration by right-clicking Program and selecting New.

 b. In the Main tab:

  1. Specify the Name 'Build WAR - Subscriber Service'

  1. Set the Location to: ${GRADLE_HOME}/bin/gradle

  1. Set the working directory to: ${workspace_loc:/SubscriberService}

  1. Specify the arguments: '-i build', where:
    * '-i' is used to set the log level to info.
    * 'build' builds the project and packages it as a WAR file.

 c. In the Environment tab, set the JAVA_HOME variable to the installed JDK root directory.

 d. Apply to save the configuration.

Build/Run Jetty Server
------------------------

From Eclipse, select Run–>External Tool–>Start Subscriber Service.

When you run the 'Start Subscriber Service' command it will execute the Gradle command and output the result in the Eclipse console view.

Select the project in Project Explorer, right click and refresh it.

The WSDL can be accessed from http://localhost:8080/SubscriberService/services/push.wsdl 

Building a WAR from source
---------------------------

From Eclipse, select Run–>External Tool–>Build WAR - Subscriber Service.

This will build the SubscriberService.war file under build/libs.

*Note: the build procedure is managed by Gradle - refer to the build.gradle file, supplied with the project.*

Deploy on Other servers
---------------------------

Simply copy the SubscriberService.war file from build/libs folder to a target application server deployment folder.

Once deployed you could invoke the WSDL from http://localhost:8080/SubscriberService/services/subscriber.wsdl.

*Note: Above URL may change depending on your sever configuration.*

Testing application with SoapUI
--------------------------------

1. First deploy application and run Jetty server.

2. Open SoapUI Application and click on menu File -> New SoapUI Project. This should bring up a New SoapUI Project dialog box.

3. Enter a Project Name, for example 'Subscriber Service'.

4. In the 'Initial WSDL/WADL' field copy and paste the path 'http://localhost:8080/SubscriberService/services/push.wsdl'

5. Click the 'Ok' button; a SoapUI project will be created and you should be able to see the putDatex2Data operation.
  
6. Under putDatex2Data, double click on the Request1 element. This will open up a request dialog window in SoapUI.

7. From your downloaded project folder, open DeliverANPRTrafficDataRequest.xml in a text editor (i.e. {workspace_loc}/SubscriberService/src/test/resources/exampleRequests/DeliverANPRTrafficDataRequest.xml).  This file contains an example publication of ANPR travel time data, that can be received and processed by the web service.

8. Copy and replace the SOAP request XML from DeliverANPRTrafficDataRequest.xml in to the SoapUI request window.

9. In SoapUI click on the green arrow icon button to send this request to the specified endpoint.  The running code will output success or failure logs to the console.  Additionally, SoapUI also contains various monitoring windows (such as a HTTP log) that can be utilised to test the connection and service request.

10. Similarly, the other XML files in the project 'exampleRequests' directory can be used to test the web service processing of other data feed types.
