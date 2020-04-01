<h1>NOVA Project README</h1>
<h3>Network of Verified Accountability</h3>

<h4>Directory Structure:</h4>

**novaweb** - Main project directory</br>
novaweb**/src** - Directory for all source code</br>
novaweb/src/main**/java** - Directory for all Java source code</br>
novaweb/src/main**/webapp** - Directory for all web source code

<h4>How to import to your IDE</h4>
This project uses a dependency manager tool known as Maven. Maven allows you to
define configurations within an XML file that can automatically be shared with
other developers, ensuring that each developer's environment is setup just like
every other developer's environment. Maven takes care of importing all JARs for
you automatically (such as JDBC Connector).</br></br>

Maven also allows you to use any IDE that supports importing Maven based projects.
Eclipse and IntelliJ allow you to import Maven projects.

To setup your local development environment for NOVA, you'll only need to configure
your Tomcat server after you import the project. That's it. The instructions
below are for Eclipse, but could be extended to IntelliJ.

1. Clone this repo to your local machine.
2. Open a new workspace in Eclipse. Ensure you create a workspace folder
in a folder different from the repo
3. Choose *Import projects...*
4. Choose *Maven* -> *Existing Maven Projects*
5. Browse for the cloned repo. Make sure it is selected and click *Finish*</br></br>
And you're done. Now you just need to setup your local Tomcat server.


6. Choose *File* -> *New* -> *Other*
7. Search for *Server*
8. Under server type, select *Tomcat v8.5 Server*
9. Browse for the directory where Tomcat v8.5 is located
10. Click *Finished*
11. Right click on the project directory, choose *Maven* -> *Update Project* -> *OK*</br></br>

<h4>Files you need to change</h4>
It's important that after importing the project, you change some specific files. The requirements are below:

1. *novaweb/src/main/resources/config/hibernate.cfg.xml.example* - You'll need to copy this file to the same directory and rename it to **hibernate.cfg.xml.private**. Then, change the required lines in order to connect to the database.

At this point, you should be able to run the project on your local Tomcat
server you configured earlier. In the *Run As* menu, choose **Run on Server**.
Then choose *Finish*.
