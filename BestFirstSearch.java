import java.io.*;
import java.util.PriorityQueue;

public abstract class BestFirstSearch{
	protected int map[][];
	protected int hMap[][]; // heuristic map
	protected int visited[][];
	protected int mapSize;
	protected Node goalNode;
	protected UserInteraction user;
	protected PriorityQueue<Node> nodeQueue;
	protected int expandedNodes;
	protected String outputFile;
	BestFirstSearch(){
		user = new UserInteraction();
		mapSize = 0; //if fill map fails program quits
		nodeQueue = new PriorityQueue<Node>();
		fillMap(user.getReadFile());
		if (mapSize == 0){
			System.out.println("Fill Map Failure... Exiting Program..");
			System.exit(0);
		}
	}

	BestFirstSearch(String readFile){
		user = new UserInteraction(readFile);
		mapSize = 0; //if fill map fails program quits
		nodeQueue = new PriorityQueue<Node>();
		fillMap(user.getReadFile());
		if (mapSize == 0){
			System.out.println("Fill Map Failure... Exiting Program..");
			System.exit(0);
		}
	}

	protected void fillMap(String fileName){
		try{
			System.out.println(fileName);
			String arg;
			FileReader reader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(reader);
			int row = 0;
			while ((arg = buffReader.readLine()) != null){
				try{
					int size = Integer.parseInt(arg);
					mapSize = size;
					map = new int[mapSize][mapSize];
					hMap = new int[mapSize][mapSize];
					visited = new int[mapSize][mapSize];
				}catch(NumberFormatException ne){
					for (int i = 0; i < mapSize; ++i){
						char x = arg.charAt(i);
						if (x == 'g'){
							goalNode = new Node(row, i);
						}
						if (x == 'i'){
							nodeQueue.add(new Node(row,i, 0, 0));
						}
						map[row][i] = parseCharacterValue(x);
					}
					++row;
				}catch(Exception e){
					System.out.println("Fill Map Error: " + e.getMessage());
				}
			}
			buffReader.close();
		}catch(Exception e){
			System.out.println("Fill Map Error: " + e.getMessage());
		}
	}

	protected void expandFringe(){
		Node next = new Node(nodeQueue.poll());
		this.expandedNodes++;
		int x = next.getX();
		int y = next.getY();
		visited[x][y] = 1;
		if ((x - 1) > -1){
			//check if it is an obstacle
			if (map[(x-1)][y] != 3 && visited[(x-1)][y] == 0){
				nodeQueue.add(new Node(x-1, y, next.getStepCost()+1, hMap[x-1][y], next));
			}
		}
		if ((x + 1) < mapSize){
			if (map[(x+1)][y] != 3 && visited[(x+1)][y] == 0){
				nodeQueue.add(new Node(x+1, y, next.getStepCost()+1, hMap[x+1][y], next));
			}
		}
		if ((y - 1) > -1){
			//check if it is an obstacle
			if (map[x][(y-1)] != 3 && visited[x][(y-1)] == 0){
				nodeQueue.add(new Node(x, y-1, next.getStepCost()+1, hMap[x][y-1], next));
			}
		}
		if ((y + 1) < mapSize){
			if (map[x][(y+1)] != 3 && visited[x][(y+1)] == 0){
				nodeQueue.add(new Node(x, y+1, next.getStepCost()+1, hMap[x][(y+1)], next));
			}
		}
	}

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

	protected void outputPath(Node other){
		System.out.println("Expanded Nodes: " + expandedNodes);
		Node current = new Node(other);
		if (current.equals(goalNode)){
			current = new Node(current.getParent());
		}
		while (current.getParent() != null){
			//4 equals a path movement
			map[current.getX()][current.getY()] = 4;
			current = new Node(current.getParent());
		}
		//how many steps it takes to reach the goal
		String outputString = other.getStepCost() + "\n";
		outputString += "Expanded Nodes: " + expandedNodes + "\n";
		for (int i = 0; i < mapSize; ++i){
			String temp = "";
			for (int j = 0; j < mapSize; ++j){
				temp += parseIntegerValue(map[i][j]);
			}
			outputString += temp + "\n";
		}
		user.setWriteFile(this.outputFile);
		user.writeToFile(outputString);
	}
	protected void printMap(char arg){
		switch(arg){
			case 'r':
				printRegMap();
				break;
			case 'h':
				printHMap();
				break;
			default:
				System.out.println("Error with program.. Default Print map reached.");
				break;
		}
	}
	protected void printRegMap(){
		for (int i = 0; i < mapSize; ++i){
			for (int j = 0; j < mapSize; ++j){
				System.out.print(map[i][j]+" ");
			}
			System.out.println("");
		}
	}
	protected void printHMap(){
		for (int i = 0; i < mapSize; ++i){
			for (int j = 0; j < mapSize; ++j){
				System.out.print(hMap[i][j]+" ");
			}
			System.out.println("");
		}
	}
	protected int parseCharacterValue(char val){
		int ret = -1;
		switch(val){
			case '.':
				ret = 0;
				break;
			case 'i':
				ret = 1;
				break;
			case 'g':
				ret = 2;
				break;
			case '+':
				ret = 3;
				break;
			default:
				System.out.println("Unknown Bug. Hit Default Case in ParseCharacterValue");
				break;
		}
		return ret;
	}
	protected char parseIntegerValue(int val){
		char ret = '-';
		switch(val){
			case 0:
				ret = '.';
				break;
			case 1:
				ret = 'i';
				break;
			case 2:
				ret = 'g';
				break;
			case 3:
				ret = '+';
				break;
			case 4:
				ret = 'o';
				break;
			default:
				System.out.println("Unknown Bug. Hit Default Case in ParseIntegerValue");
				break;
		}
		return ret;
	}
	public int getExpandedNodes(){
		return this.expandedNodes;
	}
	public void changeWriteFile(String writeFile){
		//used for multiple input files
		user.setWriteFile(writeFile);
	}
	//Calculates the h(n) cost function
	abstract void calculateHeuristic();

}