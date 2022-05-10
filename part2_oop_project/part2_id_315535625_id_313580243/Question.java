import java.io.Serializable;

public class Question implements Comparable<Question>,Serializable {
	protected int questionID;
	protected static int id = 1;
	protected String question;
	public Answer answer;

	public Question(String question, String answer) {
		this.question = question;
		this.questionID = this.id++;
		this.answer = new Answer(answer);
	}

	public final String getQuestion() {
		return question;
	}

	public final boolean setQuestion(String question) {
		this.question = question;
		return true;
	}

	public final int getQuestionID() {
		return questionID;
	}
	
	public int answerLength() {
		return this.answer.answer.length();
	}

	public final void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		Answer ans = new Answer(answer);
		this.answer = ans;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Question))
			return false;

		Question q = (Question) other;
		return (getQuestion().equals(q.getQuestion()));
	}

	@Override
	public int compareTo(Question q) {
		if (getQuestion().equals(q.getQuestion()))
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(question + "\nAnswer: " + answer.toString() + "\n");
		return str.toString();
	}
	
	public String toStringNoAns() {
		StringBuffer str = new StringBuffer(this.getQuestion() + "\n");
		return str.toString();
	}

}
