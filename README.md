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
