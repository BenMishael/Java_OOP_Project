package application;
	
import controller.ExamController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import view.AbstractExamView;
import view.ViewMenu;
import model.Database;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Database theModel = new Database("initExam");
		AbstractExamView theView1 = new ViewMenu(primaryStage);
		ExamController controller1 = new ExamController(theModel, theView1);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
