import java.util.Scanner;

public interface Menuable {
	
	void printMenu();
	void printDatabase(Database initExam);
	void addNewQuest(Database initExam,Scanner s);
	void editQuest(Database initExam,Scanner s);
	void editAnswer(Database initExam,Scanner s);
	void deleteAnswer(Database initExam,Scanner s);
	void createManualExam(Database initExam,Scanner s);
	void createAutoExam(Database initExam,Scanner s);
	void createExamClone(Database initExam,Scanner s);
	void printTestsInMem(Database initExam);
	void exitAndSave(Database initExam);
	public void addNewQuestToExistTest(Database initExam,Scanner s);
}
