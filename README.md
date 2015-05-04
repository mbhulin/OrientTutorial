# OrientDB Tutorial
## World-Model for Autonomous Service Robots using the Graph-Database OrientDB
In this tutorial you will build a database for a service robot. A service robot should help elderly or disabled persons in their household. To do so it must have knowledge about rooms and objects in its environment. A graph database like OrientDB is ideally suited for this task because the database is a highly connected graph.
##Autonomous Service Robots
At the University of Applied Sciences Ravensburg-Weingarten a service robot is developed that helps elderly or disabled persons in their household. It is not a nursing robot but a service robot. This service is not possible with an industrial robot that performs the same task at the same position very accurately again and again because the environment changes permanently: the equipment like cups, glasses, fruit etc. is located at different positions every time the robot looks for it, one time a yellow cup has to be used another time a blue one, the usual way to the kitchen is blocked by a chair, ...

Usual tasks of such a service robot are to search for objects and bring them to the owner, serve the dinner, clean up the kitchen etc.

Find more information about this project at [Univesity Ravensburg-Weingarten, Institute for Artificial Intelligence](http://iki.hs-weingarten.de/?lang=eng&page=aktuelles)
###World Model
The service robot can perform a lot of tasks in a permanently changing environment using its sensors: find its way avoiding obstacles at changing positions, detecting and grasping objects, understanding commands given in natural language etc. However to plan its activities the robot needs some information about its environment: Where are other rooms? Where are doors to get from one room to another one? Where could certain objects be? To which object class does an object belong? How can similar objects be distinguished?

In his **Master-Theses Benjamin Stähle** suggested a database structure to store the information about the robot's environment.This structure is briefly described here.

####Locations and Location Concepts
Usually the robot works in an apartment or flat. However sometimes the robot may have to do some work outside of the apartment e.g. bring something to a neighbour. The apartment consists of rooms connected by doors. The rooms may be on different floors. Thus the locations build a hierarchy connected by the "is part" relationship: A certain room e.g. the kitchen **is part** of the 1st floor which is part of an apartment which is part of a certain house.

There may be more than one kitchen or bathroom in one apartment. Therefore it is necessary to store the type of a location e.g. the kitchen on the 1st floor **is a** kitchen. The abstract information about location concepts forms an extra hierchy.

####Positions
Inside of a room an autonomous robot can move using its sensors. Therefore it does not need certain positions with connections as navigation paths inside of rooms. However positions are necessary to indicate doors as connections between different rooms. Thus the robot knows where to navigate to enter another room. Also other connections between rooms like elevators are stored as positions.

Fig. 1 shows the topological hierarchy of real locations together with the conceptual location hierarchy.
![Figure 1](file:///C:/mh/GitHub/OrientTutorial/ThesisStaehleLocations.pn)
Figure 1: Conceptual hierarchy of location concepts, location instances and place instances
(example concept: place 1 in lab kitchen)

####Objects and Object Concepts
The robot has to use and interact with a lot of objects. In contrast to the locations the objects are not fixed at one position but can be moved, consumed, substituted by new objects etc. Similar to locations real objects belong to one or more object classes. However we will not use the "is part of" relationship between objects. The reason is that assembling or disassembling of objects are not tasks of our service robot.
![Figure 2](file:///C:/mh/GitHub/OrientTutorial/ThesisStaehleObjects.png)

####Properties of Objects
Each object has several properties that may be relevant for the service robot e.g. to distinguish similar objects by color or by size. The robot might get the instruction: "Bring me my hat!" and ask in response: "Which hat? The black one or the blue one?" Each property type like color has some possible values like Yellow, Green or Blue. Of course 


