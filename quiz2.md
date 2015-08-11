<quiz name="Using an OrientDB Database with the Java API">
    <question>
    <p>You want to insert a new vertex into your graph database. Which method can you use?</p>
        <answer correct>db.addVertex()</answer>
        <answer>db.createVertex()</answer>
        <answer>db.createVertexType()</answer>
        <answer>db.insertVertex()</answer>
        <answer>db.insert()</answer>
        <answer>db.add()</answer>
        <explanation><a href="http://orientdb.com/docs/last/Graph-Database-Tinkerpop.html#create-a-vertex"> See documentation</a></explanation>
    </question>
    <question>
    <p>To create an edge two vertices which are connected by the edge must already exist.</p>
    <answer correct>True</answer>
    <answer>False</answer>
    <explanation>In the db.addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) method the second and third parameter are mandatory. The last parameter is the subclass of E if needed. </explanation>
    </question>
</quiz>