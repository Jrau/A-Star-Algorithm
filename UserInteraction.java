import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class UserInteraction{
	private static String readFile;
	private String writeFile;
	private Scanner input;

	public UserInteraction(){
		if (readFile == null){
			readFile = ""; //static
		}
		writeFile = "";
		input = new Scanner(System.in);
	}

	public UserInteraction(String readfile){
		this.readFile = readfile;
		this.writeFile = "";
		this.input = new Scanner(System.in);
	}

	public String getReadFile(){
		if (readFile == ""){
			getUserInput('r');
		}
		return readFile;
	}

	public void setReadFile(String read){
		System.out.println("Readfile is being changed to: " + read);
		this.readFile = read;
	}

	public String getWriteFile(){
		if (writeFile == ""){
			getUserInput('w');
		}
		return writeFile;
	}
	public void setWriteFile(String write){
		System.out.println("Writefile is being changed to: " + write);
		this.writeFile = write;
	}

	public void getUserInput(char choice){
		switch(choice){
			case 'r':
				System.out.println("Readfile has not been found or is invalid..");
				System.out.print("Please enter a file to read from: ");
				readFile = input.nextLine();
				System.out.println("Using file: " + readFile);
				break;
			case 'w':
				System.out.println("Writefile is required to continue..");
				System.out.print("Please enter a file to write into: ");
				writeFile = input.nextLine();
				System.out.println("Using file: " + writeFile);
				break;
			default:
				System.out.println("GetUserInput default called.. Unknown Error");
				break;
		}
	}
	public void writeToFile(String output){
		if (writeFile == ""){
			getWriteFile();
		}
		try{
			PrintWriter write = new PrintWriter(writeFile, "UTF-8");
			write.println(output);
			write.close();
		}catch(FileNotFoundException e){
			System.out.println("Error: " + e.getMessage());
		}catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
	}
}