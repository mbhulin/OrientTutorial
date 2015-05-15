# Create a Graph Database using the Document API
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

Go back to ["Create new Database"](create_new_database.md).