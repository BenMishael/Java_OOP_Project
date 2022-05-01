import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
