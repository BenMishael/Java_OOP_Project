package view;


import javafx.stage.*;
import model.Menuable;
import model.Database;

import java.util.ArrayList;
import java.util.Vector;
import listeners.ExamEventListener;
import listeners.Exam_UI_EventListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.io.InvalidClassException;
///*extends Application*/


public class ViewMenu implements AbstractExamView{
		private Button btPrintDatabase;
		private Button btAddNewQuest;
		private Button btEditQuest;
		private Button btDeleteAnswer;
		private Button btCreateManualExam;
		private Button btCreateAutoExam;
		private Button btCreateExamClone;
		private Button btPrintTestsInMem;
		private Button btExitAndSave;
		private Button btAddNewQuestToExistTest;
		private Database initExam;
		
		private Vector<listeners.Exam_UI_EventListener> allListeners = new Vector<listeners.Exam_UI_EventListener>();
		private ComboBox<String> cmbDatabase = new ComboBox<String>();
		public ViewMenu(Stage primaryStage){
            primaryStage.setTitle("Mishael & Shaked EXAM");
            GridPane gpRoot = new GridPane();
            gpRoot.setPadding(new Insets(10));
            gpRoot.setHgap(10);
            gpRoot.setVgap(10);
            
            try {
				initExam = new Database("initExam");
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            btPrintDatabase = new Button("Print Database");
            TextField tfName = new TextField();
            Text text = new Text();
            text.setX(50); 
            text.setY(50);
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            
            btPrintDatabase.setOnAction(new EventHandler<ActionEvent>(){
            	@Override
            	public void handle(ActionEvent action) {
	            		for(listeners.Exam_UI_EventListener l : allListeners) {
	            			l.printDatabaseToUI(initExam);
	            		}
	            		cmbDatabase.setVisible(true);
	            		text.setText(initExam.toString());
            		}
            	}
			);
            
            gpRoot.add(btPrintDatabase, 0, 0);
            gpRoot.add(text,2,0);
            primaryStage.setScene(new Scene(gpRoot,600,600));
            primaryStage.show();
		
        }

		@Override
		public void registerListener(Exam_UI_EventListener listener) {
			allListeners.add(listener);
			
		}

		@Override
		public void printDatabaseToUI(Database initExam) {
			cmbDatabase.getItems().add(initExam.toString());
			
		}
		
		
}
