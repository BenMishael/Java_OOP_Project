package view;

import java.util.Scanner;

import model.Database;
import listeners.ExamEventListener;
import listeners.Exam_UI_EventListener;

public interface AbstractExamView {
	void registerListener(Exam_UI_EventListener listener);
//	void printMenu();
	void printDatabaseToUI(Database initExam);
//	void addNewQuest(Database initExam,Scanner s);
//	void editQuest(Database initExam,Scanner s);
//	void editAnswer(Database initExam,Scanner s);
//	void deleteAnswer(Database initExam,Scanner s);
//	void createManualExam(Database initExam,Scanner s);
//	void createAutoExam(Database initExam,Scanner s);
//	void createExamClone(Database initExam,Scanner s);
//	void printTestsInMem(Database initExam);
//	void exitAndSave(Database initExam);
//	public void addNewQuestToExistTest(Database initExam,Scanner s);
}