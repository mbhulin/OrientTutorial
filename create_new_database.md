# Create a new Database using the OrientDB Java API
OrientDB offers several possibilities to create new databases:
* Use **Studio** to [create a new database interactively](http://orientdb.com/docs/last/orientdb-studio.wiki/Home-page.html#create-a-new-database)
* Use **Console** and the [create database command](http://orientdb.com/docs/last/orientdb.wiki/Console-Command-Create-Database.html)
* Use the **Java API** to create a database with a Java program.

In this tutorial we will use the last method.

## Create a Database using the Java API
If you prefer to wach a screencast video click on the video start page.

<a href="EclipseRobotWorldModel.mp4
" target="_blank"><img src="ThumbnailEclipseVideo.JPG"
alt="Eclipse Video" width="240" height="180" border="10" /></a>

### Create a Java Project
In this tutorial we will use Java to define the database structure. To do so we will first create a Java application.

Start Eclipse on your computer. In the main menu of eclipse click on *File* > *New* > *Java Project*

A "New Java Project"-dialog opens. Type in a name for the project e.g. *RobotWorldModel* and choose the default JRE (Java Runtime Environment). Click *Next* to continue.

In the following "Java Settings"-dialog click on the *Libraries* tab and then on *Add External JARs...*

A file dialog opens. Navigate to your orient root directory and then to the jar-directory. Choose the following JAR-files (where x.x.x is the version number installed on your computer):
* blueprints-core-x.x.x.jar
* concurrentlinkedhashmap-lru-x.x.x.jar
* orientdb-client-x.x.x.jar
* orientdb-core-x.x.x.jar
* orientdb-enterprise-x.x.x.jar
* orientdb-graphdb-x.x.x.jar

Click Finish - a new empty Java project is created and is added to the list of your Java projects in your workspace. You can see it in the Package Explorer. If it is not visible open it with  
*Window* > *Show View* > *Package Explorer*

### Create a new Package
In the package explorer choose your new project RotWorldModel. Click on the "New Package"-icon or click on *File* > *New* > *Package* in the main menu. In the "New Package"-dialog type the package name e.g. *createDBSchema*.

### Create a new Java Class with a Main Method
In the package explorer choose the newly created package *createDBSchema*. In the main menu click on *File* > *New* > *Class* or click on the "New Java Class"-icon. In the "New Class"-dialog type in the class name e.g. *CreateDBSchema*, select the check box "Create a static main method" and click *Finish*.

### Create a New Database
The graph-API of OrientDB is based on [Tinkerpop Blueprints](https://github.com/tinkerpop/blueprints/wiki) which is a general API for graph traversal. Inside the main method we use this API to establish a connection to the database.

``` Java
OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld");
OrientGraphNoTx db = factory.getNoTx(); // For data definition instructions transactions are not relevant
```
The first instruction connects to the database. If it does not exist the database is created. This works only in **"plocal" mode** where the Java program is executed on the computer where OrientDB was installed. After "plocal:" the path to the database follows. Usually this path is ``<Orient root directory>/databases``. Be shure that no other program accesses the database when you are in "plocal" mode.  
*With a **remote connection** to a database server a new database cannot be created automatically with* ``OrientGraphFactory()``.

The second instruction gets a new connection to the database from the factory. Since we want to define the structure of the database we do not use tansactions and use ``factory.getNoTx()`` instead of ``factory.getTx()``.

If you are interested in alternatives creating a database which also work in "remote"-mode take a look at ["Create graph database using the document API"](create_db_with_documentAPI.md).

### Create a Graph Database using the Document API
Are you annoyed that you have to use *plocal mode* to create a database? This subsection explains an alternative creating a database which also works in *remote mode*. However you can very well skip this subsection and go on with the tutorial.

Since a graph database in OrientDB is a special document database with the special classes V for Vertices and E for Edges you can create a graph database using the document API. After having created the database you can then connect to it on the remote server using ``OrientGraphFactory()`` as you saw in the last section. The newly created database automatically gets three users, one of them is the admin user with the credentials "admin", "admin".

```java
try {
    new OServerAdmin("remote:MyOrientServer").connect("root", "rootpw").createDatabase("RobotWorld","graph","plocal").close();
} catch (Exception e) {
    e.printStackTrace ();
}
OrientGraphFactory factory = new OrientGraphFactory("remote:MyOrientServer/RobotWorld", "admin", "admin");
OrientGraphNoTx db = factory.getNoTx();
```

To identify yourself on the remote server you have to provide the root account and password (here "rootpw"). If you forgot the root user credentials you can find them in ``<Orient root directory>/config/orient-server-config.xml`` at the &lt;users&gt; tag.


