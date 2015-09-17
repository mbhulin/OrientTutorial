# Additional Tasks and Quiz
In this chapter you have learned how to create an OrientDB graph database using the Java API of OrientDB which is based on Tinkerpop Blueprints, a general API for graph databases. Now you should do some programming tasks yourself.

* Add some more unit tests to ``LocationTests.java``
.
* Create a Java class ``ObjectTests.java`` with unit tests for the OrientDB classes *MobileObject*, *ObjectConcept*, *IS_A*, *IS_PART_OF* and *PROB_IS_AT*.
* Develop the Schema for *Property*, *PropertyConcept* and *HAS_PROPERTY* as described in [Motivation](motivation.md). Then add intructions to ``CreateDBSchema.java`` which create these OrientDB classes.

<quiz name="Quiz: Database Schema for 'Robot World Model'">
    <question multiple>
        <p>Which alternatives does OrientDB offer regarding a database schema?</p>
        <answer correct>Schema-less mode: Each vertex or edge may have different properties</answer>
        <answer>Automatic-schema mode: OrientDB creates and updates a schema automatically when new vertices are inserted</answer>
        <answer correct>Schema-full mode: All vertices of a vertex- or edge-class must have the same predefined structure</answer>
        <answer correct>Schema-mixed mode: Some properties of a vertex- or edge-class are predifined, others may be added arbitrarily for each object</answer>
        <answer>OrientDB offers a set of predefined schemas, e. g. Business, Routing, Finance or WaterSupply. You can choose one that fits for your problem.</answer>
        <explanation>OrientDB supports schema-less, schema-full and schema-mixed mode (see <a href="http://orientdb.com/docs/last/Graph-Schema.html"> documentation</a> for details)</explanation>
    </question>
    <question multiple>
        <p>Which tools or languages can you use to define a schema for OrientDB?</p>
        <answer correct>OrientDB Studio with a graphical user interface</answer>
        <answer>OrientDB OXML Schema Creator</answer>
        <answer correct>OrientDB Studio with SQL</answer>
        <answer correct>OrientDB Console</answer>
        <answer correct>Java with Tinkerpop Blueprints</answer>
        <answer>Oracle designer</answer>
        <explanation>To define a schema for OrientDB you can use <a href="http://orientdb.com/docs/last/Studio-Schema.html"> Studio with the Schema Manager</a> or Studio with SQL <a href="http://orientdb.com/docs/last/SQL-Create-Class.html"> 'Create Class'</a> and <a href="http://orientdb.com/docs/last/SQL-Create-Property.html"> 'Create Property'</a> commands, OrientDB Console or in Java the <a href="http://orientdb.com/docs/last/Graph-Schema.html#working-with-custom-vertex-and-edge-types"> createVertexType() and createEdgeType() methods.</a></explanation>
    </question>
    <question>
    <p>If you want to create a database and define a database schema, should you open the database in</p>
    <answer>transactional mode: OrientGraph db = factory.getTx();</answer>
    <answer correct>non transactional mode: OrientGraphNoTx db = factory.getNoTx();</answer>
    <explanation>Transactions with the possibility to rollback a transaction are useful if you insert, update, or delete data. A schema is usually defined before the application is used. Transaction are not necessary in this case. If the schema is not correct you can delete it correct the errors in your script and rebuild the schema.</explanation>
    </question>
    <question>
    <p>You want to create a database schema using db.createVertexType() and db.createEdgeType(). In which mode do you connect to the database?</p>
    <answer>remote connection to an OrientDB server</answer>
    <answer correct>plocal mode</answer>
    <explanation>If you want to use Tinkerpop Blueprints API you have to open the database locally on the database server in plocal mode. If you want to use a remote connection you have to use the underlying document database. </explanation>
    </question>
    <question>
    <p>Which is the correct syntax if you want to create a new class "Account" using the Java API with SQL?</p>
    <answer>db.executeSQL(new OCommandSQL ("create class Account extends V"));</answer>
    <answer correct>db.command(new OCommandSQL ("create class Account extends V")).execute();</answer>
    <answer>db.command(new OSQLSynchQuery ("create class Account extends V")).execute();</answer>
    <answer>db.command("create class Account extends V").execute();</answer>
    <explanation><a href="http://orientdb.com/docs/last/Graph-Database-Tinkerpop.html#sql-commands"> See documentation for db.command()</a></explanation>
    </question>
    <question>
    <p>You want to create the property "accountNr" of the OrientDB class "Account" which should exist for every record of the class and must not be empty (null). Which constraint(s) is/are necessary?</p>
    <answer>mandatory constraint</answer>
    <answer>not null constraint</answer>
    <answer>obligate constraint</answer>
    <answer correct>both constraints: mandatory and not null</answer>
    <explanation>The mandatory constraint ensures that every record has a field accountNr but this field need not have a value. Not null means that if a field accountNr is present it must have a value other than NULL but there may be records without this field. Only both constraints together guarantee that each record has an accountNr with a value. There is no constraint "obligate".</explanation>
    </question>
</quiz>

