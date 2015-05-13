# Develop the Schema for Objects

While the *location* class stores all immobile objects we need an **object class** to store mobile objects. Of course the border between immobile locations and mobile objects is fluent. A chair will often be moved to other positions, a table may be moved a little bit after cleaning the floor and a heavy wardrobe normally isn't moved at all. If you want to read again about the details of objects go back to the chapter [Motivation](motivation.md#)  

The objects build a hierarchy. At the leafs we have real objects which the robot can fetch. These real objects belong to abstract object classes or object concepts. 