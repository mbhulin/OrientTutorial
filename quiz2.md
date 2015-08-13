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
    <answer><code>db.executeSQL(new OSQLSynchQuery (<br><tab indent=20>"select * from MobileObject where out = #20:15 and PROB_IS_AT.Score = 5"<br>));</code>
    </answer>
    <answer correct>
        <code>db.command(new OSQLSynchQuery (<br><tab indent=20>
            "select out from PROB_IS_AT where in.@rid = #20:15 and Score = 5 and out.@class = 'MobileObject'"
        <br>)).execute();
        </code>
    </answer>
    <answer>
        <code>db.command(new OSQLSynchQuery (<br><tab indent=20>"select * from MobileObject where out = #20:15 and Score = 5"<br>)).execute();</code>
    </answer>
    <answer><code>db.command(<br>"select out('MobileObject) from PROB_IS_AT where in.@rid = #20:15 and Score = 5"<br>).execute();</code>
    </answer>
    <explanation><code>db.command(&LT;OSQLSynchQuery&GT;).execute()</code> is the correct syntax. <br><code>select out from PROB_IS_AT where in.@rid = #20:15 and Score = 5 and out.@class = 'MobileObject'</code> is the correct SQL query: Start at an edge because an edge has only one start vertex (out) and one destination vertex (in). So search for all PROB_IS_AT edges with destination #20:15 and a Score of 5 or more which start at a MobileObject vertex; then return these MobileObject vertices.</explanation>
    </question>
    
    <question>
    <p>Which is the result of <code>db.command(<OSQLSynchQuery>).execute()?</code></p>
    <answer>A table of records</answer>
    <answer>Always <code>Iterable &LT;Vertex&GT;</code></answer>
    <answer>It depends. <ul><li>If you query a vertex class e.g. <code>select * from MobileObject</code> <br>you get a result of type <code>Iterable &LT;Vertex&GT;</code>.</li> <li>If you query an edge class e.g. <code>select * from PROB_IS_AT</code> <br>you get a result of type <code>Iterable &LT;Edge&GT;</code>.</li> <li>If you query a function with integer results e.g. <code>select count(*) from MobileObject</code> <br>you get a result of type <code>Iterable &LT;Integer&GT;</code></li> </ul>
    </answer>
    <answer correct>It depends. <ul><li>If you query a vertex class e.g. <code>select * from MobileObject</code> <br>you get a result of type <code>Iterable &LT;Vertex&GT;</code>.</li> <li>If you query an edge class e.g. <code>select * from PROB_IS_AT</code> <br>you get a result of type <code>Iterable &LT;Edge&GT;</code>.</li> <li>If you query anything else e.g. <code>select Score from PROB_IS_AT</code> <br>you get a result of type <code>Iterable &LT;Vertex&GT;</code></li> </ul>
    </answer>
    <explanation>Only if you explicitly query edges you get a result of type <code>Iterable &LT;Edge&GT;</code>. In most cases you get a result of type <code>Iterable &LT;Vertex&GT;</code>: If you select a subclass of V you get persistend vertices, if you select anything else you get temporary vertices. </explanation>
    </question>
    
    <question multiple>
    <p>Suppose you have a certain vertex in your code <code>Vertex myVertex;</code><br>You want to get all outgoing edges from myVertex of edge type MyEdge. Which of the following code lines produce this result?
    </p>
    <answer correct><code>Iterable <Edge> edges =<br>myVertex.getEdges(Direction.OUT, "MyEdge");
    </answer>
    <answer><code>Iterable <Edge> edges =<br>db.getEdges(myVertex, Direction.OUT, "MyEdge");</code></answer>
    <answer correct><code>Vertex v = db.command(new OSQLSynchQuery("select outE('MyEdge') from V where @rid = ?")).execute(myVertex);
    <br>Iterable <Edge> edges = (Iterable <Edge>) v.getProperty("outE");</code>
    </answer>
    <answer correct><code>Iterable <Edge> edges = <br>db.command(new OSQLSynchQuery("select from MyEdge where out = ?")).execute(myVertex);</code>
    </answer>
    <explanation><code>db.getEdges(myVertex, Direction.OUT, "MyEdge");</code> is wrong. <code>db.getEdges()</code> is only suitable if you want to retrieve all edges of an edge class.<br>Attention: If you use SQL and refer to a vertex class in the FROM part you have to use <code>outE()<c/ode> or <code>inE()</code> with parantheses to get the connected edges. If you refer to an edge class in the FROM part you have to use <code>in</code> or <code>out</code> without parantheses to get the connected vertices. </explanation>
    </question>
</quiz>