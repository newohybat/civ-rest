civ-rest
========

Project with RESTful API for civ-game project

**requirements:**

* civ-game dependency
* Maven 3 or higher
* JBoss AS 7.1.1.Final with JBOSS_HOME set properly

**purpose:**
Provides RESTful API for access to game component defined in civ-game.
Also contains index.html file with sample page accessing the API by AJAX calls.

**usage:**

mvn install

or

mvn jboss-as:deploy / mvn jboss-as:redeploy / mvn jboss-as:undeploy
