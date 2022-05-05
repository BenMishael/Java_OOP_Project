import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Database implements Serializable {
	public Vector<Question> allQ;
	private String databaseName;
	
	public Database() {
		allQ = new Vector<Question>();
		this.databaseName= "N/A";
	}
	
	public Database(String name) {
		allQ = new Vector<Question>();
		this.databaseName = name;
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
		StringBuffer str = new StringBuffer("The Exam: \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i).toStringNoAns() + "\n");

		}
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("The Exam: \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i) + "\n");

		}
		return str.toString();

	}
}
