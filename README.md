# OrientDB Tutorial
## World-Model for Autonomous Service Robots using the Graph-Database OrientDB
In this tutorial you will build a database for a service robot. A service robot should help elderly or disabled persons in their household. To do so it must have knowledge about rooms and objects in its environment. A graph database like OrientDB is ideally suited for this task because the database is a highly connected graph.
##Autonomous Service Robots
At the University of Applied Sciences Ravensburg-Weingarten a service robot is developed that helps elderly or disabled persons in their household. It is not a nursing robot but a service robot. This service is not possible with an industrial robot that performs the same task at the same position very accurately again and again because the environment changes permanently: the equipment like cups, glasses, fruit etc. is located at different positions every time the robot looks for it, one time a yellow cup has to be used another time a blue one, the usual way to the kitchen is blocked by a chair, ...

Usual tasks of such a service robot is to search for objects and bring them to the owner, serve the dinner, clean up the kitchen etc.

Find more information about this project at the [Institute for Artificial Intelligence of the Univesity Ravensburg-Weingarten](http://iki.hs-weingarten.de/?lang=eng&page=aktuelles)
###World Model
The service robot can perform a lot of tasks in a permanently changing environment: find its way avoiding obstacles at changing positions, detecting and grasping objects, understanding commands given in natural language etc.
