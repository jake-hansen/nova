<h1>NOVA Project README</h1>
<h3>Network of Verified Accountability</h3>

<h4>Directory Structure:</h4>

**novaweb** - Main project directory</br>
novaweb**/src** - Directory for all source code</br>
novaweb/src/main**/java** - Directory for all Java source code</br>
novaweb/src/main**/webapp** - Directory for all web source code

**docker** - Directory for docker files

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
10. Click *Finished*</br></br>
And now you've configured your Tomcat server. All that is left is to build the
project using Maven, and making sure it runs correctly on Tomcat!


11. Right click on the project directory, choose *Maven* -> *Update Project* -> *OK*
12. Now you can build the project. Right click on the project directory,
choose *Run As* -> *Maven build...*
13. In the 'Goals' field type **clean install** then click *Run*</br></br>
You should see some lines scroll through the console at this point. This is
the project being auto built and pulling in all necessary dependencies for you.

At this point, you should be able to run the project on your local Tomcat
server you configured earlier. In the *Run As* menu, choose **Run on Server**.
Then choose *Finish*.


<h5>Files you need to change</h5>
It's important that after importing the project, you change some specific files. The requirements are below:

1. *java/src/main/config.properites.example* - You'll need to copy this file to the same directory and rename it to **config.properties.private**. Then, change the required lines in order to connect to the database.

2. *java/WebContent/WEB-INF/lib* - You'll need to add the **mysql-connector-java-8.0.12.jar file**. Make sure to add this jar to your build path.

<h4>How to use Docker</h4>
Docker can be used to setup a local HTTP server seamlessly without any manual install of a traditional HTTP server. To begin, you'll need to download the latest version of Docker for your platform.

[Docker Downlaod](https://www.docker.com/get-started)

Next, you'll need to either copy the provided docker/*startnovanginx.sh.example* file to *docker/startnovanginx.sh.private*
and edit the copied file to include the source path to your local repo where necessary. Or, you can run the following commands to start the docker container:

`docker build -t "nova-dev:1.0" . && \
docker run -it --rm -d --name "nova" -p 80:80 -v \
"LOCAL/PATH/TO/WWW/REPO/DIRECTORY":/usr/share/nginx/html:ro nova-dev:1.0`

If you've done everything correctly, you should be able to navigate to http://localhost and be greeted with the website.
