package controller;
import java.util.Scanner;

import listeners.ExamEventListener;
import listeners.Exam_UI_EventListener;
import model.Database;
import view.AbstractExamView;

public class ExamController implements ExamEventListener, Exam_UI_EventListener{
	private Database databaseModel;
	private AbstractExamView examView;
	
	public ExamController(Database model,AbstractExamView view) {
		databaseModel = model;
		examView = view;

        databaseModel.registerListener(this);
        examView.registerListener(this);
		
	}
	
	@Override
	public void printDatabaseToModelEvent(Database initExam) {
		examView.printDatabaseToUI(initExam);
		
	}

	@Override
	public void printDatabaseToUI(Database initExam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewQuest(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editQuest(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editAnswer(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnswer(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createManualExam(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAutoExam(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createExamClone(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printTestsInMem(Database initExam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAndSave(Database initExam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewQuestToExistTest(Database initExam, Scanner s) {
		// TODO Auto-generated method stub
		
	}
	
	
	


	
}
