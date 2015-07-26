# Create the Search Method

The special task of a service robot we will simulate in this tutorial is to search for a special object. Imagine that a person instructs the robot to bring a glass of apple juice. The robot has to understand the spoken instruction, parse it into simple actions, plan the actions and finally perform the task. In this example the robot has to search for a juice glass then to search for apple juice, bring the juice to the glass, fill the glass with the juice and finally bring the glass to the person.

In this tutorial we will consider one frequent task: **search for an object**

This search algorithm consists of the following parts:

1. Get the the parameters for the search using a simple GUI: 
    * the search Object
    * and for the simulation the current position of the robot
    * and the current position of the search object
* [Retrieve all positions from the database where the search object could be](fill_database_with_test_data.md#create-vertex-using-sql) together with the probability (score) that the object is really there.
* Calculate the path to each possible position
* Decide to which position the robot should go first
* Go to this position and look for the search object there
* If the object is really there or if this is the last possible search position show the robot's search path
else continue the search with the remaining positions at step 3.

To implement the search algorithm create a new package in Eclipse: **operations**

Then create a new JAVA class **Operations** in this package.

### Retrieve Positions of Search Object
