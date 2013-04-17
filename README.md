wikianalyzer
============

web application for analyzing wikipedia

1. How to build?

You must have installed Apache Maven.
Navigate to "wikianalyzer/wawebapp" and run "mvn clean package"

2. How to deploy?

We recommend you to deploy on jetty.
After step 1 is done copy file "wikinalyzer/wawebapp/target/wawebapp-0.0.1-SNAPSHOT.war or folder" "wawebapp/target/wawebapp-0.0.1-SNAPSHOT" to "<your-jetty>/webapps".
Start jetty by executing command "java -jar start.jar" in your jetty directory.
Default URL: http://localhost:8080/wawebapp-0.0.1-SNAPSHOT/

3. How to work with Eclipse?

You'll need to have Google Web Toolkit plugin and Maven Plugin installed in Eclipse.
Import project as existing Maven Project in Eclipse.
Before you are able to continue you'll need to finish step 1.
Now run project as Web Application. You'll be asked where to find the WAR folder. Set it as target/wawebapp-0.0.1-SNAPSHOT 
You are now in Development Mode
