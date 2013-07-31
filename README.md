NTIS Subscriber Service example - README
========================================
	
This is an example implementation of an NTIS Subscriber Service that uses the Spring web-services framework. 
You can import the project into Eclipse and tailor it to suit your needs.
	
Prerequisites	
------------------

1. Softwares to install :
	Java JDK v1.6

	Gradle v1.4

	Eclipse J2EE IDE v3.6.2

	SoapUI v4.5.2 (for testing)

	git v1.7.10

  Note:  
    Versions listed above are those used to test the install/build process, earlier/later compatible versions should also work)

2. Set System environment variable 
     PATH=${PATH}:<gradle home>/bin:<java home>/bin

  Note:  
    All other Java libraries are downloaded and installed during the gradle-based build process.


Importing Project in to eclipse:
---------------------------------

1. Open your terminal or command prompt

2. Traverse to the directory where you want to clone example application

3. To clone SubscriberService project from github enter below command in your terminal. 
   
   git clone https://github.com/ntisservices/ntis-java-web-services.git

4. Open eclipse and Import project into Eclipse workspace

 a. File->Import

 b. Select the option 'General->Existing Project into Workspace'

 c. Select the option 'Select root directory' and locate the downloaded project root directory: <download dir>/SubscriberService

 d. Click Finish to import the project


Eclipse Project Configuration:
---------------------------------

Eclipse needs to be confugred to run the gradle build/run commands.  This is achievied through Eclipse's External Tools Configurations;

built into the two build/run configurations created to start and stop the SubscriberService.

1. Start Service configuration:

 a. From Eclipse, select Run->External Tool–>External Tools Configurations...

 b. Create a new configuration by right-clicking Program and selecting New.

 c. In the Main tab:

   - Specify the Name 'Start Subscriber Service'

   - Set the Location to: <gradle home>/bin/gradle

   - Set the working directory to: ${workspace_loc:/SubscriberService}

   - Specify arguments: -i clean eC jettyRunWar   

	where: 

         -i is used to set the log level to info

         eC is used to generate eclipse specific project artifacts.

         jettyRunWar - is a gradle tasks which Assembles the webapp into a war and deploys it to Jetty server.    

 d. In the Environment tab:

   - Set the JAVA_HOME variable to the installed JDK root directory

 e. Apply to save the configuration

2. Stop Service configuration:

 a. Create a new configuration by right-clicking Program and selecting New.

 b. In the Main tab:

   - Specify the Name 'Stop Subscriber Service'

   - Set the Location to: <gradle home>/bin/gradle

   - Set the working directory to: ${workspace_loc:/SubscriberService}

   - Specify arguments: -i jettyStop           
    
	Where:
             -i is used to set the log level to info             

             jettyStop  - Stops Jetty.

 d. In the Environment tab:

   - Set the JAVA_HOME variable to the installed JDK root directory

 e. Apply to save the configuration


Build/Run:
-----------

Follow Steps 1 & 2 under 'Eclipse Project Configuration' to setup the start and stop configuration in eclipse. 

To run your new External Tools Configuration select Run –> External Tool –> 'Start Subscriber Service'. 

It will execute the Gradle command and output the result in the Eclipse console view.

wsdl could be accessed from http://localhost:8880/SubscriberService/services/push.wsdl 

  Note: 

   Build procedure is managed by Gradle - refer to the build.gradle file, supplied with the project.


Testing application with SoapUI
--------------------------------

 First deploy application and run Jetty server. 

 Open SoapUI Application and click on menu File -> New SoapUI Project. This should bring up a New SoapUI Project dialog box.

 Enter Project Name as 'Subscriber Service'.  

 In Initial WSDL/WADL field copy and paste path 'http://localhost:8880/SubscriberService/services/push.wsdl' 

 Click 'Ok' button. Now it should create SoapUI project and you should able to see puDatex2Data operation. 
  
 Under puDatex2Data double click on Request1 element. This will open up a request dialog window in SoapUI.

 Now from your downloaded project folder open DeliverANPRTrafficDataRequest.xml in to your favourite text editor (i.e. {workspace_loc}/SubscriberService/src/test/resources/exampleRequests/
  
 DeliverANPRTrafficDataRequest.xml)

 Copy and replace Soap request XML from  DeliverANPRTrafficDataRequest.xml in to the SoapUI request window .

 In SoapUi click on Green Solid arrow Icon button to send this request to the specified endpoint. If everything is all right then you should able to see some logs printed on the
  
 Similarly you should able to test all other Notification by using requests from folder 'exampleRequests'.

