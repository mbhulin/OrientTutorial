# Create the Search Method

The special task of a service robot we will simulate in this tutorial is to search for a special object. Imagine that a person instructs the robot to bring a glass of apple juice. The robot has to understand the spoken instruction, parse it into simple actions, plan the actions and finally perform the task. In this example the robot has to search for a juice glass then to search for apple juice, bring the juice to the glass, fill the glass with the juice and finally bring the glass to the person.

In this tutorial we will consider one frequent task: **search for an object**

This search algorithm consists of the following parts:

1. Get the the parameters for the search using a simple GUI: 
    * the search Object
    * and for the simulation the current position of the robot
    * and the current position of the search object
1. [Retrieve all positions from the database where the search object could be](create_the_search_method.md#retrieve-positions-of-search-object) together with the probability (score) that the object is really there.
1. Calculate the path to each possible position
1. Decide to which position the robot should go first
1. Go to this position and look for the search object there
1. If the object is really there or if this is the last possible search position show the robot's search path
else continue the search with the remaining positions at step 3.

To implement the search algorithm create a new package in Eclipse: **operations**

Then create a new JAVA class **Operations** in this package.

### Retrieve Positions of Search Object

Inside of the class Operations write a new method ``createPosList()``. As the name says the result should be a list of positions and positions are vertices. To determine possible positions of the search Object the method needs the search object as a parameter which is also a vertex. So we get:

```java
public ArrayList<Vertex> createPosList(Vertex obj) {
```

To retrieve possible positions of the search object we can use SQL and query the PROB_IS_AT subclass of E. There are two alternatives for each PROB_IS_AT edge:

* Either we have a direct connection from an object to a position
* Or we we have a connection from one object to another object, e. g. a bottle of milk is inside the refrigerator.

Hence our method consists of two steps
