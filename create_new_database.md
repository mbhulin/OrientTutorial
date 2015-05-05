# Create a new Database
OrientDB offers several possibilities to create new databases:
* Use Studio to [create a new database interactively](http://orientdb.com/docs/last/orientdb-studio.wiki/Home-page.html#create-a-new-database)
* Use Console and the [create database command](http://orientdb.com/docs/last/orientdb.wiki/Console-Command-Create-Database.html)
* Use the Java API to create a database in a Java program.

## Create a Database using the Java API
In this tutorial we will use the last method. To do so we will first create a Java application.

Start Eclipse on your computer. In the main menu of eclipse click on *File* > *New* > *Java Project*

A "New Java Project"-dialog opens. Type in a name for the project e.g. *RobotWorldModel* and choose the default JRE (Java Runtime Environment). Click *Next* to continue.

![](EclipseNewProjectDialog.JPG)

In the following "Java Settings"-dialog click on the *Libraries* tab and then on *Add External JARs...*

![](EclipseAddLibraries.JPG)

A file dialog opens. Navigate to your orient root directory and choose the following JAR-files:


