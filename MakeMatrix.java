import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;


public class MakeMatrix{

	public static void main(String[] argv){
		try{
			Scanner scan = new Scanner(System.in);
			System.out.print("How big would you like the Matrix: ");
			int test = scan.nextInt();
			final int size = test;
			int[][] map = new int[size][size];
			Random x = new Random();
			for (int i = 0; i < size; ++i){
				for (int j = 0; j < size; ++j){
					map[i][j] = 0;
					int chance = x.nextInt(100);
					if (chance < 24){
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