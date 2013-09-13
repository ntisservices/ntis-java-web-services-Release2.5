NTIS Subscriber Service example - README
========================================
	
This is an example implementation of an NTIS Subscriber Service that uses the Spring web-services framework. 
You can import the project into Eclipse and tailor it to suit your needs.
	
Prerequisites	
------------------

1. Softwares to install :
	JDK v1.6

	Gradle v1.4

	Eclipse J2EE IDE v3.6.2

	SoapUI v4.5.2 (for testing)

Note: Versions listed above are those used to test the install/build process, earlier/later compatible versions should also work.

2. Set System environment variable

JAVA_HOME=<INSTALLED JDK Directory>

Note: All other Java libraries are downloaded and installed during the gradle-based build process.


Importing Project in to eclipse:
---------------------------------

1. Download Subscriber Service example application from https://github.com/ntisservices/ntis-java-web-services/archive/master.zip

2. Extract zip file in some location on your local file system.

3. Open eclipse and Import project into Eclipse workspace.

 a. File->Import

 b. Select the option 'General->Existing Project into Workspace'

 c. Select the option 'Select root directory' and locate the downloaded project root directory: <download dir>/SubscriberService

 d. Click Finish to import the project.


Eclipse Project Configuration:
---------------------------------

Eclipse needs to be confugred to run the gradle build/run commands. This is achievied through Eclipse's External Tools Configurations.

Following are three configurations to start and stop the Jetty server which runs the SubscriberService application.

1. Start Service configuration:

 a. From Eclipse, select Run->External Tool–>External Tools Configurations.

 b. Create a new configuration by right-clicking Program and selecting New.

 c. In the Main tab:

   - Specify the Name 'Start Subscriber Service'

   - Set the Location to: <gradle home>/bin/gradle

   - Set the working directory to: ${workspace_loc}/SubscriberService

   - Specify arguments: -i ec jettyRunWar
	Where:
	     -i is used to set the log level to info.
	     eC is used to generate eclipse specific project artifacts.
	     jettyRunWar - is a gradle tasks which assembles the webapp into a war and deploys it to Jetty server.

 d. In the Environment tab:

   - Set the JAVA_HOME variable to the installed JDK root directory

 e. Apply to save the configuration.

2. Stop Service configuration:

 a. Create a new configuration by right-clicking Program and selecting New.

 b. In the Main tab:

   - Specify the Name 'Stop Subscriber Service'

   - Set the Location to: <gradle home>/bin/gradle

   - Set the working directory to: ${workspace_loc}/SubscriberService

   - Specify arguments: -i jettyStop

	Where:
	  -i is used to set the log level to info
	  jettyStop  - Stops Jetty.

 d. In the Environment tab:

   - Set the JAVA_HOME variable to the installed JDK root directory

 e. Apply to save the configuration.


3. Build War from Source Configuration.

 a. Create a new configuration by right-clicking Program and selecting New.

 b. In the Main tab:

   - Specify the Name 'Build War- Subscriber Service'

   - Set the Location to: <gradle home>/bin/gradle

   - Set the working directory to: ${workspace_loc}/SubscriberService

   - Specify arguments: -i build

	Where:
	  -i is used to set the log level to info
	  build  - builds projects and packages it to war.

 d. In the Environment tab:

   - Set the JAVA_HOME variable to the installed JDK root directory

 e. Apply to save the configuration.

Build/Run Jetty Server:
------------------------

Select Run –> External Tool –> 'Start Subscriber Service'.

When you run 'Start Subscriber Service' command it will execute the Gradle  command and output the result in the Eclipse console view.

wsdl could be accessed from http://localhost:8880/SubscriberService/services/push.wsdl 


Building a WAR from source
------------------------------

Select Run –> External Tool –> 'Build War- Subscriber Service'.

This will build the SubscriberService.war file under build/libs.

Note:Build procedure is managed by Gradle - refer to the build.gradle file, supplied with the project.

Deploy on Other servers
---------------------------

Simply copy the SubscriberService.war file from build/libs folder to your fauvorite server's deploy folder.

Once deployed you could invoke the WSDL from http://localhost:8880/SubscriberService/services/subscriber.wsdl.

Note: Above Url may change depending on your sever configuration.

Testing application with SoapUI
--------------------------------

1. First deploy application and run Jetty server.

2. Open SoapUI Application and click on menu File -> New SoapUI Project. This should bring up a New SoapUI Project dialog box.

3. Enter Project Name as 'Subscriber Service'.

4. In Initial WSDL/WADL field copy and paste path 'http://localhost:8880/SubscriberService/services/push.wsdl'

5. Click 'Ok' button. Now it will create SoapUI project and you should able to see puDatex2Data operation.
  
6. Under puDatex2Data double click on Request1 element. This will open up a request dialog window in SoapUI.

7. Now from your downloaded project folder open DeliverANPRTrafficDataRequest.xml in to your favourite text editor (i.e. {workspace_loc}/SubscriberService/src/test/resources/exampleRequests/
   DeliverANPRTrafficDataRequest.xml)

8. Copy and replace Soap request XML from  DeliverANPRTrafficDataRequest.xml in to the SoapUI request window .

9. In SoapUi click on Green Solid arrow Icon button to send this request to the specified endpoint. If everything is all right then you should able to see some logs printed on the
  
10. Similarly you should able to test all other Notification by using requests from folder 'exampleRequests'.
