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

public class Test implements Serializable {
	public Vector <Question> allQ;
	private String testName;
	
	public Test() {
		allQ = new Vector<Question>();
		this.testName = "The Exam";
	}
	
	public Test (String testName) {
		allQ = new Vector<Question>();
		this.testName = testName;
	}
	
	public String getName() {
		return testName;
	}
	
	public String toStringNoAns() {
		StringBuffer str = new StringBuffer(this.testName+": \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i).toStringNoAns() + "\n");

		}
		return str.toString();
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
	public String toString() {
		StringBuffer str = new StringBuffer(this.testName+"-Solution: \n\n");
		for (int i = 0; i < allQ.size(); i++) {
			str.append((i+1)+") "+ allQ.get(i) + "\n");

		}
		return str.toString();

	}
}
