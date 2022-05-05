import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

public class FileManager implements Serializable{
	
	
	public FileManager() throws FileNotFoundException, IOException {}
	
	public Database OpenDatabase(String name) throws FileNotFoundException, IOException, ClassNotFoundException {
		File chosenFile = new File(name+".dat");
		if(chosenFile.exists()) {
			ObjectInputStream inFile = new ObjectInputStream (new FileInputStream(name+".dat"));
			Database tempDB = (Database)inFile.readObject();
			inFile.close();
			return tempDB;
		}
		else {
			Database tempDB = new Database(name);
			return tempDB;
		}
	}
	
	public boolean UpdateDatabase(Database db) throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(db.getName()+".dat"));
		outFile.writeObject(db);
		outFile.close();
		return true;
	}
	
	public boolean saveTest(Database test) throws FileNotFoundException, IOException {
		saveTestSol(test);
		saveTestNoSol(test);
		return true;
	}
	
	
	public boolean saveTestSol(Database test) throws FileNotFoundException {
		LocalDate date = LocalDate.now();
		PrintWriter pw = new PrintWriter(new File("solution_"+date+".txt"));
		pw.println(test);
		pw.close();
		return true;
	}
	
	public boolean saveTestNoSol(Database test) throws FileNotFoundException {
		LocalDate date = LocalDate.now();
		PrintWriter pw = new PrintWriter(new File("exam_"+date+".txt"));
		pw.println(test.toStringNoAns());
		pw.close();
		return true;
	}
	
	
}
