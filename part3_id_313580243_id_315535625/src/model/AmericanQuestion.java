package model;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;
import java.io.Serializable;

public class AmericanQuestion extends Question implements Serializable,Cloneable{
	MySet<Answer> ansList = new MySet<Answer>();

	public AmericanQuestion(String question, MySet<Answer> ansList) {
		super(question, null);
		this.ansList = ansList;
	}

	public MySet<Answer> getAnsList() {
		return ansList;
	}

	public void setAnsList(MySet<Answer> ansList) {
		this.ansList = ansList;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof AmericanQuestion))
			return false;

		AmericanQuestion AQ = (AmericanQuestion) other;
		return (getQuestion().equals(AQ.getQuestion()));
	}
	
	@Override
	public int answerLength() {
		int counter = 0;
		for (int i = 0; i < ansList.getSize(); i++) {
			counter += ansList.get(i).answer.length();
		}
		return counter;
	}
	
	public String toStringNoAns() {
		StringBuffer str = new StringBuffer(super.getQuestion() +"\n");		//changed to super.getQuestion
		for (int i = 0; i < ansList.getSize(); i++) {
			str.append(" " + (i + 1) + ". " + ansList.get(i).toStringNoAns() + "\n");
		}
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(super.getQuestion() +"\n");		//changed to super.getQuestion
		for (int i = 0; i < ansList.getSize(); i++) {
			str.append(" " + (i + 1) + ". " + ansList.get(i) + "\n");
		}
		return str.toString();
	}

}
