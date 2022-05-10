import java.io.Serializable;

public class Answer implements Comparable<Answer>,Serializable{
	public String answer;
	public boolean trueORfalse;

// answer for open question
	public Answer(String answer) {
		this.answer = answer;
		this.trueORfalse = true;
	}

// answer for American question	
	public Answer(String answer, boolean trueORFalse) {
		this.answer = answer;
		this.trueORfalse = trueORFalse;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean setAnswer(String answer) {
		this.answer = answer;
		return true;
	}

//	public boolean getTrueORfalse() {
//		return trueORfalse;
//	}
//	
//	public boolean setTrueORfalse(boolean trueORfalse) {
//		this.trueORfalse = trueORfalse;
//		return true;
//	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Answer))
			return false;

		if (!(super.equals(other)))
			return false;

		Answer a = (Answer) other;
		return a.answer == a.answer;
	}
	
	@Override
	public int compareTo(Answer a) {
		if (getAnswer().equals(a.getAnswer()))
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(answer +" - " + trueORfalse);
		return str.toString();
	}
	
	public String toStringNoAns() {
		StringBuffer str = new StringBuffer(answer);
		return str.toString();
	}

}
