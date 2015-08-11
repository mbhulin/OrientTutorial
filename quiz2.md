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
    <explanation>In the db.addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) method the second (start vertex) and third parameter (destination vertex of the edge) are mandatory. The last parameter is the subclass of E if needed. The first parameter may be null because the id is set automatically. </explanation>
    </question>
    <question>
    <p>Can edges have properties?</p>
    <answer correct>Yes</answer>
    <answer>No</answer>
    <explanation>Edges may have properties. Only lightweight edges cannot have properties. Find more information about <a href="http://orientdb.com/docs/last/Lightweight-Edges.html"> lightweight edges in the documentation.</a></explanation>
    </question>    
</quiz>