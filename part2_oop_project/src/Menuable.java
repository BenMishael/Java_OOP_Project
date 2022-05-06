import java.util.Scanner;

public interface Menuable {
	
	void printMenu();
	void printDatabase(Database db);
	void addNewQuest(Database db,Scanner s);
	void editQuest(Database db,Scanner s);
	void editAnswer(Database db,Scanner s);
	void createManualExam(Database db,Scanner s);
	void createAutoExam(Database db,Scanner s);
	void createExamClone(Database db,Scanner s);
	void exitAndSave(Database db);
}
