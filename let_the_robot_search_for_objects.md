
# Let the Robot Search for Objects
If you prefer you can watch a screencast video:
<a href="RWM-Search.mp4
" target="_blank"><img src="StartScreencastVideo.jpg"
alt="Eclipse Video" width="200" height="30" border="10" /></a>

One of the robot's tasks is to search for some object and bring it to the disabled person. The disabled person starts the search process using a vocal interface, e. g. he gives the robot the instruction: "Bring me a glass of apple juice". The robot has to analyze this instruction and split it into several tasks: Search for a glass, search for a bottle of apple juice, fill the glass with apple juice and bring the glass to the person.

In this tutorial we want to simulate only the task to search for a single object. Since we have no real robot available we use a graphical user interface to specify the current position of the robot as start position for the search, the search object, and the current position of this object.

This chapter consists of three sections:
1. Before we can start to develop the search algorithm we need some [test data](fill_database_with_test_data.md).
2. The main part of this chapter is the [search algorithm](create_the_search_method.md).
3. To start the simulation of the robot's search you can [download a simple GUI](download_a_simple_gui.md).

