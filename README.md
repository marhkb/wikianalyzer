wikianalyzer
============

web application for analyzing wikipedia

(1) How to build?

Checkout the source code.
You must have installed Apache Maven.
Run "mvn clean package" in working directory.

(2) How to deploy?

We recommend you to deploy on jetty.
After (1) is done copy file "wikinalyzer/wawebapp/target/wawebapp-0.0.1-SNAPSHOT.war or folder" "wawebapp/target/wawebapp-0.0.1-SNAPSHOT" to "<your-jetty>/webapps".
Start jetty by executing command "java -jar start.jar" in your jetty directory.
Default URL: http://localhost:8080/wawebapp-0.0.1-SNAPSHOT/

(3) How to use Development Mode with Eclipse?

Before you are able to continue with this step you'll need to finish (1).
You'll need to have Google Web Toolkit plugin and Maven Plugin installed in Eclipse and the GWT Plugin in your webbrowser.
Import "Existing Maven Project" in Eclipse and point to the working directory. Maven now should list up all projects. Import them all.
Now run project "wawebapp" as Web Application. You'll be asked where to find the WAR folder. Set it as target/wawebapp-0.0.1-SNAPSHOT 
You are now in Development Mode.
