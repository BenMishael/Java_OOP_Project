import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.io.Serializable;

public class Program implements Menuable{

	public static void main(String[] args) {
		Program prog = new Program();
		Scanner s = new Scanner(System.in);
		int select = 10;
		final String DB_NAME = "initExam";
		Database initExam = null;
		try {
			initExam = new Database(DB_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found Error");
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IOException File not found");
		} catch (Exception e) {
			System.out.println("General Error");
		}

		while (select != 0) {
			prog.printMenu();
			try {
				select = s.nextInt();
			} catch (InputMismatchException e1) {
				System.out.println("Wrong input only number please! \n");
				s.nextLine();
			}
			switch (select) {
			case 1:
				prog.printDatabase(initExam);
				break;
			case 2:
				prog.addNewQuest(initExam, s);
				break;
			case 3:
				prog.editQuest(initExam, s);
				break;
			case 4:
				prog.editAnswer(initExam, s);
				break;
			case 5:
				prog.deleteAnswer(initExam, s);
				break;
			case 6:
				prog.createManualExam(initExam, s);
				break;
			case 7:
				prog.createAutoExam(initExam, s);
				break;
			case 8:
				prog.createExamClone(initExam, s);
				break;
			case 9:
				prog.printTestsInMem(initExam);
				break;
			case 0:
				prog.exitAndSave(initExam);
				break;
			case 13:
				prog.addNewQuestToExistTest(initExam, s);
				break;
			}
		}
		s.close();
	}
	
	@Override
	public void printMenu() {
		System.out.println("#########################################");
		System.out.println("### Welcome To Our Questions Database ###");
		System.out.println("######################################### \n");
		System.out.println("Please Enter Your Choice: \n");
		System.out.println("<1> Show All Questions In Database");
		System.out.println("<2> Add New Question To Database");
		System.out.println("<3> Edit Exist Question from Database");
		System.out.println("<4> Edit Exist Answer from Database");
		System.out.println("<5> Delete Exist Answer from Database");
		System.out.println("<6> Create New Exam Manually");
		System.out.println("<7> Create New Exam Automatically");
		System.out.println("<8> Create Clone From Existing Test");
		System.out.println("<9> Show All Test In Memory");
		System.out.println("<0> Exit And Save");
	}

	@Override
	public void printDatabase(Database db) {
		System.out.println(db);
	}

	@Override
	public void addNewQuest(Database initExam, Scanner s) {
		boolean flag = false;
		String question,answer;
		int americanORopen = 0;
		int size = 0;
		int trueORfalse = 0;
		while (!flag) {
			try {
				s.nextLine();
				System.out.println("Please enter the new question: ");
				question = s.nextLine();
				System.out.println("Please enter the type off the new question \n 1 - open\n 2 - American: ");
				americanORopen = s.nextInt();
				flag = true;
				if (americanORopen == 1) {
					s.nextLine();
					System.out.println("Please enter the answer: ");
					answer = s.nextLine();
					Question newQuestion = new Question(question, answer);
					if (initExam.allQ.add(newQuestion)) {
						System.out.println("**\nThe new question:\n" + newQuestion + "\n**");
					} else
						System.out.println("**************\nQuestion already exist!\n**************");
				} else if (americanORopen == 2) {
					flag = false;
					while (!flag) {
						try {
							System.out.println("How many answers would you like this question to contain: ");
							size = s.nextInt();
							if (size < 0)
								throw new IntNotInRange("Please enter a positive number");
							flag = true;
						} catch (InputMismatchException | IntNotInRange e) {
							s.nextLine();
							System.out.println(e.getMessage());
							flag = false;
						}
						MySet<Answer> ansArr = new MySet<Answer>();
						for (int i = 0; i < size; i++) {
							s.nextLine();
							System.out.println("Please enter answer number " + (i + 1));
							answer = s.nextLine();
							flag = false;
							while (!flag) {
								try {
									System.out.println("Please enter:\n 1 - true \n 2 - false");
									trueORfalse = s.nextInt();
									if (trueORfalse > 2 || trueORfalse < 1)
										throw new IntNotInRange("Please enter 1 OR 2");
									flag = true;
									Answer ansTemp = new Answer(answer, (trueORfalse == 1 ? true : false));
									ansArr.add(ansTemp);
								} catch (InputMismatchException | IntNotInRange e) {
									s.nextLine();
									System.out.println(e.getMessage());
									flag = false;
								}
							}
						}
						AmericanQuestion newAmericanQuestion = new AmericanQuestion(question, ansArr);
						if (initExam.allQ.add(newAmericanQuestion)) {
							System.out.println("**\nThe new question:\n" + newAmericanQuestion + "\n**");
						} else
							System.out.println("**************\nQuestion already exist!\n**************");
					}
				} else
					throw new IntNotInRange("Please enter 1 OR 2");
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}

		}
		
	}

	@Override
	public void editQuest(Database initExam, Scanner s) {
		boolean flag = false;
		int id = 0;
		while (!flag) {
			try {
				System.out.println("Please enter the question ID you would like to update:");
				id = s.nextInt() - 1;
				if (id < 0 || id > (initExam.allQ.size()))
					throw new IntNotInRange("Please enter a number between 1 - " + (initExam.allQ.size()));
				s.nextLine();
				flag = true;
				System.out.println("The question you select is:\n" + initExam.allQ.get(id)
						+ "\nPlease enter your new question: ");
				initExam.allQ.get(id).setQuestion(s.nextLine());
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}
		}
		
	}

	@Override
	public void editAnswer(Database initExam, Scanner s) {
		boolean flag = false;
		int answerId = 0;
		int trueORfalse = 0;
		int id = 0;
		String answer;
		while (!flag) {
			try {
				System.out.println("Please enter the question ID for the answer you would like to update:");
				id = s.nextInt() - 1;
				if (id < 0 || id > initExam.allQ.size())
					throw new IntNotInRange("Please enter a number between 1 - " + (initExam.allQ.size()));
				flag = true;
				System.out.println("The question you select is:\n" + initExam.allQ.get(id));
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}
		}
		if (initExam.allQ.get(id).getClass() == AmericanQuestion.class) {
			flag = false;
			while (!flag) {
				try {
					System.out.println("Please enter the answer id you would like to update: ");
					answerId = s.nextInt() - 1;
					if (answerId < 0 || answerId > 3)
						throw new IntNotInRange("Please enter a number between 1 - 4");
					s.nextLine();
					flag = true;
				} catch (InputMismatchException | IntNotInRange e) {
					s.nextLine();
					System.out.println(e.getMessage());
					flag = false;
				}
			}

			System.out.println("Please enter the new answer:");
			answer = s.nextLine();
			flag = false;
			while (!flag) {
				try {
					System.out.println("Please enter:\n 1 - true \n 2 - false");
					trueORfalse = s.nextInt();
					if (trueORfalse > 2 || trueORfalse < 1)
						throw new IntNotInRange("Please enter 1 OR 2");
					initExam.setAmericanAnswer(id, answerId, answer, (trueORfalse == 1 ? true : false));
					System.out.println("***************\nThe new question and answer:\n" + initExam.allQ.get(id)
							+ "\n***************");
					flag = true;
				} catch (InputMismatchException | IntNotInRange e) {
					s.nextLine();
					System.out.println(e.getMessage());
					flag = false;
				}
			}

		} else {
			s.nextLine();
			System.out.println("Please enter your new answer: ");
			initExam.allQ.get(id).setAnswer(s.nextLine());
		}

		
	}
	
	@Override
	public void deleteAnswer(Database initExam, Scanner s) {
		boolean flag = false;
		int id = 0;
		int answerId = 0;
		while (!flag) {
			try {
				System.out.println("Please enter the question ID for the answer you would like to delete:");
				id = s.nextInt() - 1;
				if (id < 0 || id > initExam.allQ.size())
					throw new IntNotInRange("Please enter a number between 1 - " + initExam.allQ.size());
				flag = true;
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}
		}
		if (initExam.allQ.get(id).getClass() == AmericanQuestion.class) {
			System.out.println("The question you select is:\n" + initExam.allQ.get(id));
			flag = false;
			while (!flag) {
				try {
					System.out.println("Please enter the answer id you would like to delete");
					answerId = s.nextInt() - 1;
					if (answerId < 0 || answerId > 3)
						throw new IntNotInRange("Please enter a number between 1 - "
								+ ((AmericanQuestion) initExam.allQ.get(id)).getAnsList().getSize());
					s.nextLine();
					initExam.deleteAmericanAns(id, answerId);
					System.out
							.println("*****\nThe question:\n" + initExam.allQ.get(id) + "\n*******");
					flag = true;
				} catch (InputMismatchException | IntNotInRange e) {
					s.nextLine();
					System.out.println(e.getMessage());
					flag = false;
				}
			}
		} else {
			initExam.allQ.get(id).setAnswer(null);
		}

	}
	
	@Override
	public void createManualExam(Database initExam, Scanner s) {
		int size = 0;
		int americanORopen = 0;
		int ansSize = 0;
		int trueORfalse = 0;
		String question, answer;
		boolean flag = false;
		while (!flag) {
			try {
				System.out.println("How many questions would you like the exam to contain?");
				size = s.nextInt();
				if (size < 1)
					throw new IntNotInRange("Please enter a positive number");
				flag = true;
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}
		}
		Test exam = new Test("exam");
		for (int i = 0; i < size; i++) {
			flag = false;
			while (!flag) {
				try {
					System.out.println(
							"Choose the type of question number " + (i + 1) + ":\n 1 - American\n 2 - Open: ");
					americanORopen = s.nextInt();
					if (americanORopen > 2 || americanORopen < 1)
						throw new IntNotInRange("Please enter 1 OR 2");
					s.nextLine();
					flag = true;
				} catch (InputMismatchException | IntNotInRange e) {
					s.nextLine();
					System.out.println(e.getMessage());
					flag = false;
				}
			}

			System.out.println("Please enter your question: ");
			question = s.nextLine();
			switch (americanORopen) {
			case 1:
				flag = false;
				while (!flag) {
					try {
						System.out.println(
								"How many answers would you like this american question will contain:");
						ansSize = s.nextInt();
						if (ansSize < 1)
							throw new IntNotInRange("Please enter a positive number next time");

						flag = true;
					} catch (InputMismatchException | IntNotInRange e) {
						s.nextLine();
						System.out.println(e.getMessage());
						flag = false;
					}
				}
				MySet<Answer> ansArr = new MySet<Answer>();
				for (int j = 0; j < ansSize; j++) {
					s.nextLine();
					System.out.println("Please enter answer number: " + (j + 1));
					answer = s.nextLine();
					flag = false;
					while (!flag) {
						try {
							System.out.println("Please enter:\n 1 - true \n 2 - false");
							trueORfalse = s.nextInt();
							if (trueORfalse > 2 || trueORfalse < 1)
								throw new IntNotInRange("Please enter 1 OR 2");
							ansArr.add(new Answer(answer, (trueORfalse == 1 ? true : false)));
							flag = true;
						} catch (InputMismatchException | IntNotInRange e) {
							s.nextLine();
							System.out.println(e.getMessage());
							flag = false;
						}
					}

				}
				ansArr.add(new Answer("All answers are true"));
				ansArr.add(new Answer("None of the above"));
				exam.allQ.add(new AmericanQuestion(question, ansArr));
				break;
			case 2:
				System.out.println("Please enter your answer: ");
				exam.allQ.add(new Question(question, s.nextLine()));
				break;
			}
		}
		exam.sortByAnsLength();
		System.out.println(exam.toString());
		try {
			initExam.saveTest(exam);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException File not found");
		} catch (IOException e) {
			System.out.println("IOException File not found");
		} catch (Exception e) {
			System.out.println("General Error");
		}
		
	}

	@Override
	public void createAutoExam(Database initExam, Scanner s) {
		boolean flag = false;
		int size = 0;
		int id = 0;
		while (!flag) {
			try {
				System.out.println("How many question would you like the exam to contain?");
				size = s.nextInt();
				if (size < 1 || size > initExam.allQ.size())
					throw new IntNotInRange("Please enter a number between 1 - " + initExam.allQ.size());
				Test autoExam = new Test("exam");
				int autoCount = 0;
				while (autoCount != size) {
					id = (int) (Math.random() * initExam.allQ.size());
					if (!(autoExam.allQ.contains(initExam.allQ.get(id)))) {
						autoExam.allQ.add(initExam.allQ.get(id));
						autoCount++;
					}
				}
				
				autoExam.sortByAnsLength();
				System.out.println(autoExam.toString());
				try {
					initExam.saveTest(autoExam);
				} catch (FileNotFoundException e) {
					System.out.println("FileNotFoundException File not found");
				} catch (IOException e) {
					System.out.println("IOException File not found");
				} catch (Exception e) {
					System.out.println("General Error");
				}
				flag = true;
			} catch (InputMismatchException | IntNotInRange e) {
				s.nextLine();
				System.out.println(e.getMessage());
				flag = false;
			}
		}
	}

	@Override
	public void createExamClone(Database initExam, Scanner s) {
		System.out.println(initExam.showTests());
		System.out.println("Please enter the ID of the test you want to clone");
		int inx = s.nextInt();
		inx--;
		try {
			initExam.allTests.add(initExam.getTestByID(inx).clone());
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone not support Exception");
		} catch (Exception e) {
			System.out.println("General Error");
		}
		System.out.println("The Clone Exam was Created:");
		System.out.println(initExam.allTests.get(initExam.allTests.size()-1));
	}

	@Override
	public void exitAndSave(Database initExam) {
		System.out.println("Good bay!");
		try {
			initExam.UpdateDatabase();
		} catch (FileNotFoundException e1) {
			System.out.println("FileNotFoundException File not found");
		} catch (IOException e1) {
			System.out.println("IOException File not found");
		} catch (Exception e) {
			System.out.println("General Error");
		}
	}

	@Override
	public void printTestsInMem(Database initExam) {
		System.out.println(initExam.showTests());
	}
	
	public void addNewQuestToExistTest(Database initExam,Scanner s) {
		System.out.println(initExam.showTests());
		System.out.println("Please enter the ID of the test you add Quest");
		int inx = s.nextInt();
		inx--;
		s.nextLine();
		System.out.println("add quest");
		String qu = s.nextLine();
		System.out.println("add answer");
		String ans = s.nextLine();
		Question q = new Question(qu,ans);
		initExam.allTests.get(inx).addNewQuest(q);
		System.out.println("Question add sucessfully");
	}
}
