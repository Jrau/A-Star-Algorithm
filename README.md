
## Synopsis

This code implements the A* Algorithm in Java using two different heauristic functions. The first calculates the Manhattan Distance, or Manhattan Length, and the second calculates the Eucildean Distance. 

## Code Example
```java
/**
	* This is the main loop the program runs. It first calls the 
	* function expand fringe which expands the start node. This initializes 
	* the fringe so nodeQueue has something to work with in the main loop. 
	* The main loop is then started. The loop makes sure that there is a node to expand.
	* If there isn't it then prints no possible path. However while there
	* is a Node to check it first sees if the node is the goal, if it is
	* it returns the path. If not it expands the fringe to include the goal
	* node. This repeats until a path is found or the program outputs no
	* path possible
	*/
protected void findBestPath(){
		expandFringe();
		while (nodeQueue.peek() != null){
			if (nodeQueue.peek().equals(goalNode)){
				// System.out.println("Found Goal Node: " + nodeQueue.peek());
				outputPath(nodeQueue.poll());
				return;
			}
			expandFringe();
		}
		System.out.println("No possible path..");
		return;
	}
```
## Motivation

This project was done as an assignment for an Artificial Intelligence class at Chapman University. 

## Installation
To build the project all you must do is run the make command in the command prompt:
	make-build
 
## API Reference

The docs are inside the code. They describe the function in plain text. Seeing as the code isn't extensive they aren't overly detailed. 

## Tests

To run the project just run the make command in the folder. The make command has four possibilities
-
1. make 
  1. This will run MakeMatrix which will create a new matrixFile to test on and then will run the program on the newly created matrixFile
2. make-run:
  1. This will run the program with matrixFile as the input
3. make-build:
  1. This will rebuild the project and recompile all java classes. When developing the project this must be done before running any other make commands
4. make-matrix:
  1. This runs MakeMatrix which creates a new matrixFile.txt and then stops. The file is not run in the program.
