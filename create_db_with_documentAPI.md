# Create a Graph Database using the Document API
Since a graph database is a special document database in OrientDB with the special classes V for Vertices and E for Edges you can create a graph database using the document API.

``` java
try {
    new OServerAdmin("remote:MyOrientServer").connect("root", "rootpw").createDatabase("RobotWorld","graph","local").close();
} catch (IOException e) {
    e.printStackTrace ();
}
```

