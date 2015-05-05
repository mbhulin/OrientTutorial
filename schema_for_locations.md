# Develop Schema for Locations
[Location Hierarchy](LocationHierarchy.JPG)

Locations relevant for the robot are rooms, floors, houses, later perhaps also a yard or even a street outdoor. Hence we have to define a vertex class **Location**! as subclass of V.

Each location needs a **name**. The individuals attended by the service robot use the name to refer to the location. The name need not be unique because a location can be identified by the containing location. A command to the robot could e.g. be: "Bring this bottle of juice to the *kitchen of Mr. Millers apartement*".

To move inside of a room a robot must get the information about the **shape** of the room. A shape can be stored as a list of positions where each position is a pair of x- and y-coordinates.
