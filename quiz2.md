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
    <question>
    <p>Which is the correct syntax if you want to find all mobile objects which may be at the position with @rid #20:15 and a score of 5 or more?</p>
    <answer>db.executeSQL(new OSQLSynchQuery ("select * from MobileObject where out = #20:15 and PROB_IS_AT.Score >= 5"));</answer>
    <answer correct>db.command(new OSQLSynchQuery ("select out from PROB_IS_AT where in.@rid = #20:15 and Score >= 5 and out.@class = 'MobileObject'")).execute();</answer>
    <answer>db.command(new OSQLSynchQuery ("select * from MobileObject where out = #20:15 and Score >= 5")).execute();</answer>
    <answer>db.command("select out('MobileObject) from PROB_IS_AT where in.@rid = #20:15 and Score >= 5").execute();</answer>
    <explanation>db.command(<OSQLSynchQuery>).execute() is the correct syntax. "select out from PROB_IS_AT where in.@rid = #20:15 and Score >= 5 and out.@class = 'MobileObject'" is the correct SQL query: Start at an edge because an edge has only one start vertex (out) and one destination vertex (in). So searh for all PROB_IS_AT edges with destination #20:15 and a Score of 5 or more which start at a MobileObject vertex; then return these MobileObject vertices.</explanation>
    </question>
</quiz>