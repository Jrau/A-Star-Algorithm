import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;

/**
* Asks for User input of size and density of barriers then creates a matrix 
* to test using the A-Star Algorithm implemented in this repository. It first
* randomly places barriers by choosing a number between 0 and 100 and then based 
* on the density and size creates a matrix of ints. The ints represent a grid of 
* a start position, end position, barriers, and open paths. The grid is then written 
* to the file "matrixFile.txt". This file can be run through the A-Star algorithm 
* to test the algorithm. 
*/
public class MakeMatrix{

	public static void main(String[] argv){
		try{
			Scanner scan = new Scanner(System.in);
			System.out.print("How big would you like the Matrix: ");
			int test = scan.nextInt();
			System.out.print("What density would you like the barriers to be (Between [0,100]): ");
			int density = scan.nextInt();
			final int size = test;
			int[][] map = new int[size][size];
			Random x = new Random();
			for (int i = 0; i < size; ++i){
				for (int j = 0; j < size; ++j){
					map[i][j] = 0;
					int chance = x.nextInt(100);
					if (chance < density){
						map[i][j] = 1;
					}
				}
			}
			map[x.nextInt(size-1)][x.nextInt(size-1)] = 2;
			map[x.nextInt(size-1)][x.nextInt(size-1)] = 3;
			PrintWriter write = new PrintWriter("matrixFile.txt", "UTF-8");
			write.println(size);
			for (int i = 0; i < size; ++i){
				String output = "";
				for (int j = 0; j < size; ++j){
					int val = map[i][j];
					switch(val){
						case 0:
							output += '.';
							break;
						case 1:
							output += '+';
							break;
						case 2:
							output += 'i';
							break;
						case 3:
							output += 'g';
							break;
						default:
							System.out.println("Unknown Bug. Hit Default Case in MakeMatrix");
							break;
					}
				}
				write.println(output);
			}
			write.close();
		}catch(FileNotFoundException e){
			System.out.println("Error: " + e.getMessage());
		}catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
	}
}