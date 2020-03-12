<h1>NOVA Project README</h1>
<h3>Network of Verified Accountability</h3>

<h4>Directory Structure:</h4>

java: Eclipse project directory for Tomcat. When cloning repo, choose the option
      to import existing project to Eclipse. Ensure not to add any project settings files
      to repo.

www: Web root directory. This directory will serve all web based files expect for Tomcat.

<h4>How to import to Eclipse</h4>

1. Clone repo to local machine
2. Open a new workspace in Eclipse. Ensure you create a workspace folder
in a folder different from the repo
3. Choose *Import projects...*
4. Choose *Git* -> *Projects from Git (with smart import)*
5. Choose *Existing local repository*
6. Add and browse for your local repo, make sure the checkbox next to it is selected
7. Only import *YOUR_REPO_NAME/java*. You'll need to deselect the folder that is *YOUR_REPO_NAME*
8. Choose *Finish*

You might get some errors at this point. That's okay. You need to edit your build path to include the Tomcat Server for local development.

9. Choose *File* -> *New* -> *Other*
10. Search for *Server*
11. Under server type, select *Tomcat v8.5 Server*
12. Browse for the directory where Tomcat v8.5 is located
13. Move *nova* from *Available* to *Configured*

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
