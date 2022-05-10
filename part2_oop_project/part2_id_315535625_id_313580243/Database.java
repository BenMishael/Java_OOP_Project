import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class Database implements Serializable {
	public Vector <Question> allQ;
	public Vector <Test> allTests;
	private String databaseName;
	
	public Database() {
		allQ = new Vector <Question>();
		allTests = new  Vector <Test>();
		this.databaseName= "N/A";
	}
	
	public Database(String name) throws IOException, ClassNotFoundException {
		File chosenFile = new File(name+".dat");
		if(chosenFile.exists()) {
			ObjectInputStream inFile = new ObjectInputStream (new FileInputStream(name+".dat"));
			Database tempDB = (Database)inFile.readObject();
			this.allQ = tempDB.allQ;
			this.databaseName = tempDB.getName();
			this.allTests = new  Vector <Test>();
			inFile.close();
		}
		else {
			this.allQ = new Vector<Question>();
			this.allTests = new  Vector <Test>();
			this.databaseName = name;
		}
	}

	public void setAmericanAnswer(int id, int ansId, String ans, boolean TorF) {
		Answer a = new Answer(ans, TorF);
		AmericanQuestion q = (AmericanQuestion) allQ.get(id);
		q.getAnsList().replace(ansId, a);
	}

	public void deleteAmericanAns(int id, int ansId) {
		AmericanQuestion q = (AmericanQuestion) allQ.get(id);
		q.getAnsList().remove(ansId);
	}
	
	public String getName() {
		return databaseName;
	}
	
	public Test getTestByID(int id) {
		return allTests.get(id);
	}
	
	public boolean saveTestSol(Test test) throws FileNotFoundException {
		LocalDate date = LocalDate.now();
		PrintWriter pw = new PrintWriter(new File("solution_"+date+".txt"));
		pw.println(test);
		pw.close();
		return true;
	}
	
	public boolean saveTestNoSol(Test test) throws FileNotFoundException {
		LocalDate date = LocalDate.now();
		PrintWriter pw = new PrintWriter(new File("exam_"+date+".txt"));
		pw.println(test.toStringNoAns());
		pw.close();
		return true;
	}
	
	public boolean saveTest(Test test) throws FileNotFoundException, IOException {
		this.allTests.add(test);
		saveTestSol(test);
		saveTestNoSol(test);
		return true;
	}
	
	public boolean UpdateDatabase() throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(this.getName()+".dat"));
		outFile.writeObject(this);
		outFile.close();
		return true;
	}
	
	public void sortByAnsLength() {
		Question temp;
		for (int i = this.allQ.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (this.allQ.get(j).answerLength() > this.allQ.get(j + 1).answerLength()) {
					temp = this.allQ.get(j);
					this.allQ.set(j, this.allQ.get(j + 1));
					this.allQ.set(j + 1, temp);
				}
			}

		}
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Database))
			return false;

		if (!(super.equals(other)))
			return false;

		Database ex = (Database) other;
		return ex.allQ == ex.allQ;
	}
	
	public String toStringNoAns() {
		StringBuffer str = new StringBuffer("The Database: \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i).toStringNoAns() + "\n");

		}
		return str.toString();
	}
	
	public String showTests() {
		StringBuffer str = new StringBuffer("All the Tests: \n\n");
		for (int i = 0; i < allTests.size(); i++) {
			str.append("Test Number : "+(i+1) +" ---- Name : "+ allTests.get(i) + " \n");

		}
		return str.toString();

	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("The Database: \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i) + "\n");

		}
		return str.toString();

	}
}
