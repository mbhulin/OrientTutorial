<quiz name="Using an OrientDB Database with the Java API">
    <question>
    <p>You want to insert a new vertex into your graph database. Which method can you use?</p>
        <answer correct><code>db.addVertex()</code></answer>
        <answer><code>db.createVertex()</code></answer>
        <answer><code>db.createVertexType()</code></answer>
        <answer><code>db.insertVertex()</code></answer>
        <answer><code>db.insert()</code></answer>
        <answer><code>db.add()</code></answer>
        <explanation><a href="http://orientdb.com/docs/last/Graph-Database-Tinkerpop.html#create-a-vertex"> See documentation</a></explanation>
    </question>
    <question>
    <p>To create an edge two vertices which are connected by the edge must already exist.</p>
    <answer correct>True</answer>
    <answer>False</answer>
    <explanation>In the <code>db.addEdge(Object id, Vertex outVertex, Vertex inVertex, String label)</code> method the second (start vertex) and third parameter (destination vertex of the edge) are mandatory. The last parameter is the subclass of E if needed. The first parameter may be null because the id is set automatically. </explanation>
    </question>
    <question>
    <p>Can edges have properties?</p>
    <answer correct>Yes</answer>
    <answer>No</answer>
    <explanation>Edges may have properties. Only lightweight edges cannot have properties. Find more information about <a href="http://orientdb.com/docs/last/Lightweight-Edges.html"> lightweight edges in the documentation.</a>
    </explanation>
    
    </question>
    <question>
    <p>Which is the correct syntax if you want to find all mobile objects which may be at the position with @rid #20:15 and a score of 5?</p>
    <answer><code>db.executeSQL(new OSQLSynchQuery ("select * from MobileObject where out = #20:15 and PROB_IS_AT.Score = 5"));</code>
    </answer>
    <answer correct>
        <code>db.command(new OSQLSynchQuery (
            "select out from PROB_IS_AT where in.@rid = #20:15 and Score = 5 and out.@class = 'MobileObject'"
        )).execute();
        </code>
    </answer>
    <answer>
        <code>db.command(new OSQLSynchQuery (<br>"select * from MobileObject where out = #20:15 and Score = 5"<br>)).execute();</code>
    </answer>
    <answer><code>db.command("select out('MobileObject) from PROB_IS_AT where in.@rid = #20:15 and Score = 5").execute();</code>
    </answer>
    <explanation><code>db.command(&LT;OSQLSynchQuery&GT;).execute()</code> is the correct syntax. <code>select out from PROB_IS_AT where in.@rid = #20:15 and Score = 5 and out.@class = 'MobileObject'</code> is the correct SQL query: Start at an edge because an edge has only one start vertex (out) and one destination vertex (in). So search for all PROB_IS_AT edges with destination #20:15 and a Score of 5 or more which start at a MobileObject vertex; then return these MobileObject vertices.</explanation>
    </question>
    
    <question>
    <p>Which is the result of <code>db.command(<OSQLSynchQuery>).execute()?</code></p>
    <answer>A table of records</answer>
    <answer>Always <code>Iterable &LT;Vertex&GT;</code></answer>
    <answer>It depends. If you query a vertex class e.g. <code>select * from MobileObject</code> you get a result of type <code>Iterable &LT;Vertex&GT;</code>. If you query an edge class e.g. <code>select * from PROB_IS_AT</code> you get a result of type <code>Iterable &LT;Edge&GT;</code>. If you query a function with integer results e.g. <code>select count(*) from MobileObject</code> you get a result of type <code>Iterable &LT;Integer&GT;</code></answer>
<answer correct>It depends. If you query a vertex class e.g. <code>select * from MobileObject</code> you get a result of type <code>Iterable &LT;Vertex&GT;</code>. If you query an edge class e.g. <code>select * from PROB_IS_AT</code> you get a result of type <code>Iterable &LT;Edge&GT;</code>. If you query anything else e.g. <code>select Score from PROB_IS_AT</code> you get a result of type <code>Iterable &LT;Vertex&GT;</code></answer>
    <explanation>In most cases you get a result of type <code>Iterable &LT;Vertex&GT;</code>. Only if you explicitly query edges you get a result of type <code>Iterable &LT;Edge&GT;</code></explanation>
    </question>
</quiz>