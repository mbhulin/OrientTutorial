# Develop Schema for Locations
![Location Hierarchy](LocationHierarchy.JPG)

Locations relevant for the robot are rooms, floors, houses, even outdoor locations like a yard or a street. Hence we have to define a vertex class **Location** as subclass of V.

Each location needs a **name**. The persons attended by the service robot use the name to refer to the location. The name need not be unique because a location can be identified by the containing location. A command to the robot could e.g. be: "Bring this bottle of juice to the *kitchen of Mr. Millers apartement*".

To move inside of a room a robot must get the information about the **shape** of the room. A shape can be stored as a list of positions where each position is a pair of x- and y-coordinates.

To store a shape we have several options in OrientDB:
* We can connect a location vertex with some position vertices by an edge type "is_corner_of". We need an ordered list of edges for a location vertex because the sequence of the positions is relevant for the shape. Otherwise the same set of corners could define different shapes: ![two shapes](twoShapes.png)
* We can connect a location vertex with one position vertex 
