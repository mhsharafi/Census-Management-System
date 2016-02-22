import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
	public Parser () throws FileNotFoundException{
		File file = new File("....");
		Scanner sc = new Scanner(file);
		String c = sc.nextLine();
		String[] years = c.split(",");


		 while(sc.hasNext()){
			String s = sc.nextLine();
			String[] popul = s.split(",");
			Cell cell = new Cell();
		
		 }
	}
}
