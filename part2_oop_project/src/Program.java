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
		int select = 0;
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

		while (select != 9) {
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
				prog.exitAndSave(initExam);
				break;
			}
		}
		s.close();
	}
	
	@Override
	public void printMenu() {
		System.out.println("Hello..please select from the next options: " + "\n1.Show me all questions and answers."
				+ "\n2.Add question and answer." + "\n3.Update an existing question."
				+ "\n4.Update an existing answer." + "\n5.Delete an existing answer." + "\n6.Create a manual exam."
				+ "\n7.Create an automatic exam." + "\n8.Create an exam clone" + "\n9.Exit.");
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
						throw new IntNotInRange("Please enter a number between 1 - 4");
					s.nextLine();
					initExam.deleteAmericanAns(id, answerId);
					System.out.println(
							"***************\nThe question:\n" + initExam.allQ.get(id) + "\n***************");
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
		Database exam = null;
		try {
			exam = new Database("exam");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found Error");
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IOException File not found");
		} catch (Exception e) {
			System.out.println("General Error");
		}
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
				Database autoExam = null;
				try {
					autoExam = new Database("exam");
				} catch (ClassNotFoundException e) {
					System.out.println("Class not found Error");
				} catch (FileNotFoundException e) {
					System.out.println("File not found error");
				} catch (IOException e) {
					System.out.println("IOException File not found");
				} catch (Exception e) {
					System.out.println("General Error");
				}
				int autoCount = 0;
				while (autoCount != size) {
					id = (int) (Math.random() * initExam.allQ.size());
					if (!(autoExam.allQ.contains(initExam.allQ.get(id)))) {
						autoExam.allQ.add(initExam.allQ.get(id));
						autoCount++;
					}
				}
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
		// // System.out.println("Please chose the exam id you wish to clone:\n" +
		// exam.toString());
		// id = s.nextInt();
		
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

}
