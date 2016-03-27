/*
Assignment 3 - Quiz
Brian Knotten
*/

// Import the scanner, io, and ArrayList

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Quiz{

    public static void main(String[] args) throws IOException{

	// Create variables to hold the quiz file's name, individual questions,
	// the number of potential answers for a question, the number of right
	// and wrong answers the user gives, the easiest and hardest question
	// numbers, an array to hold the user's answers, and an ArrayList to
	// hold the quiz's questions

	String quiz = "";
	ArrayList <Question> questions = new ArrayList<Question>(); 
	int[] userAns;
	String question;
	int numAnswers;
	String loopAnswer;
	int filler;
	int right = 0;
	int wrong = 0;
	int easiest;
	int hardest;
	
	// Check to make sure the user passes a quiz file as an argument and
	// assign the file's name to the quiz variable

	if (args.length > 0){
	    System.out.println("Welcome to the Quiz Program! Good luck!");
	    quiz = args[0];
	}else{
	    System.out.println("No Quiz given!");
	}

	// Create a new file object with the quiz file's name and a Scanner to read it

	File file = new File(quiz);
	Scanner inputFile = new Scanner(file);

	// While loop to read the file

	while (inputFile.hasNext()){

	    // Assuming the file is in the proper format, the Scanner reads the first
	    // line (the question) as a String and the second (the number of potential
	    // answers) as an int. A new Question object is then created using the 
	    // read string and number.

	    question = inputFile.nextLine();
	    numAnswers = inputFile.nextInt();
	    Question x = new Question(question, numAnswers);

	    // The Scanner's .nextInt() doesn't read newline characters, so this
	    // is necessary when alternating between .nextInt() and .nextLine().

	    inputFile.nextLine();

	    // A for loop that reads in the next x lines as answers where x = the
	    // number of answers specified by the file. It uses the Question class'
	    // .addAnswer method and the loop counter to add the answer to the question
	    // object in the correct place in the object's answers array.
	    
	    for (int j = 0; j < numAnswers; j++){
		loopAnswer = inputFile.nextLine();
		x.addAnswer(loopAnswer, j);
	    }
	    
	    // Using the filler variable, the scanner reads the next three lines as ints
	    // and uses the Question class' specific methods for setting the correct 
	    // answer's number, the number of tries, and the number of times correctly
	    // answered.
	    
	    filler = inputFile.nextInt();
	    x.setCorrectAnswer(filler);
	    filler = inputFile.nextInt();
	    x.setNumTried(filler);
	    filler = inputFile.nextInt();
	    x.setNumCorrect(filler);
	    
	    // The question object is now added to the questions ArrayList

	    questions.add(x);

	    // The newline character not read by .nextInt() is read in.
	    
	    inputFile.nextLine();

	    // The loop repeats if the Scanner detects another line.
	}
	
	// The file is closed after all questions are read.

	inputFile.close();
	
	// The userAns list is sized to hold a number of answers corresponding to 
	// the number of questions.

	userAns = new int[questions.size()];

	// A for loop that iterates over the questions ArrayList

	for (int i = 0; i < questions.size(); i++){

	    // At each iteration the question is presented, the possible answers
	    // are printed, and the user is prompted for their answer using the
	    // .getAns() method. Each answer is added to the userAns array using the for
	    // loop's counter, causing the answer's position in the array to correspond with
	    // the question's position in the ArrayList. At the end of each iteration the 
	    // number of times variable for the question is incremented by one.

	    System.out.println("");
	    System.out.println("Question " + i + ":");
	    System.out.println(questions.get(i).getQuestion());
	    System.out.println("Answers: ");
	    
	    questions.get(i).printAnswers();
	    
	    System.out.println("");
	    userAns[i] = getAns(questions.get(i).getNumAns());
	    questions.get(i).setNumTried(1);
	}

	// After the last question is answered the user is show their correct
	// and incorrect answers.

	System.out.println("Thanks for your answers!");
	System.out.println("Here are your results:");
	
	// A for loop that iterates over the questions ArrayList

	for (int l = 0; l < questions.size(); l++){

	    // Each question is displayed along with the correct answer and 
	    // the user's answer.

	    System.out.println("");
	    System.out.println("Question: " + questions.get(l).getQuestion());
	    System.out.println("Answer: " + questions.get(l).getCorrectAns());
	    System.out.println("Player Guess: " + userAns[l]);
	    
	    // Simple if-else statement indicating whether the user's answer is correct

	    if (userAns[l] == questions.get(l).getCorrectAnsNum()){
		System.out.println("\tResult: CORRECT! Great Work!");
		right++;
		questions.get(l).setNumCorrect(1);
	    }else{
		System.out.println("\tResult: INCORRECT! Remember the answer for next time!");
		wrong++;
	    }   

	}

	// After displaying the user's results some overall statistics about the quiz are displayed:
	// First, the user's number of right and wrong answers are displayed, along with their
	// percentage.

	System.out.println("");
	System.out.println("Your overall performance was: ");
	System.out.println("\tRight:\t" + right);
	System.out.println("\tWrong:\t" + wrong);
	System.out.printf("\tPct:\t%.0f%%\n", 100*((float)right/questions.size()));
	
	System.out.println("Here are some cumulative statistics: ");

	// Next the questions ArrayList is again iterated over by a for loop to display
	// the number of times each question has been attempted, attempted correctly,
	// and the percent of correct attempts using the Question class' corresponding methods.
	
	for (int n = 0; n < questions.size(); n++){
	    System.out.println("");
	    System.out.println("Question: " + questions.get(n).getQuestion());
	    System.out.println("\tTimes Tried: " + questions.get(n).getNumTried());
	    System.out.println("\tTimes Correct: " + questions.get(n).getNumCorrect());
	    System.out.printf("\tPercent Correct: %.1f%%\n", questions.get(n).getPercent());
	}

	// Finally the easiest and most difficult questions are determined and displayed
	// using the getEasiest(), getHardest(), and printOneQuestion() methods.
	
	System.out.println("Easiest Question:");
	easiest = getEasiest(questions);
	printOneQuestion(questions, easiest);
	
	System.out.println("");
	
	System.out.println("Hardest Question: ");
	hardest = getHardest(questions);
	printOneQuestion(questions, hardest);
    
	// A PrintWriter object is created to write the changed statistics to the quiz file.

	PrintWriter outputFile = new PrintWriter(quiz);

	// The for loop iterates over the questions ArrayList to write each question,
	// the number of possible answers, the answers themselves, the number of the 
	// correct answer, and the statistics in the correct format.

	for (int w = 0; w < questions.size(); w++){
	    outputFile.println(questions.get(w).getQuestion());
	    outputFile.println(questions.get(w).getNumAns());
	    
	    // To write the potential answers another for loop has to be utilized to 
	    // iterate over the answers array of each Question object.
	    
	    for (int v = 0; v < questions.get(w).getNumAns(); v++){
		outputFile.println(questions.get(w).getAnswers()[v]);
	    }
	    outputFile.println(questions.get(w).getCorrectAnsNum());
	    outputFile.println(questions.get(w).getNumTried());
	    outputFile.println(questions.get(w).getNumCorrect());
	}

	// The quiz file is closed and a thank you message is displayed to the user.

	outputFile.close();
	
	System.out.println("Thanks for taking the Quiz!\n");
        
    }


    // A method to prompt for and accept the user's answer. It accepts the number of possible
    // answers as an argument and uses it to determine the range of acceptable inputs.

    private static int getAns(int max){
	
	// Create a Scanner to take in the answer and a variable to store it.

	Scanner keyboard = new Scanner(System.in);
	int ans;

	// A do-while loop for input verification; the prompt is displayed and is 
	// repeated until the user inputs an acceptable answer.

	do{
	    System.out.print("Your answer? (enter a number): ");
	    while (!keyboard.hasNextInt()){
		keyboard.next();
		System.out.print("Your answer? (enter a number): ");
	    }
	    
	    // Once an acceptable answer is input the answer is stored in the ans variable 
	    // and returned.

	    ans = keyboard.nextInt();
	} while (ans < 0 || ans > max-1);
	
	return ans;
    }

    // A method for determining the easiest question in the quiz. It accepts the ArrayList
    // of questions as an argument.
    
    private static int getEasiest(ArrayList<Question> questions){

	// Create a variable to store the number corresponding to the easiest question. It is
	// initialized to 0 to start, because the first question's number and position in the 
	// ArrayList is 0.

	int easiest = 0;
	
	// The for loop iterates over the questions ArrayList and compares the percent of 
	// right attempts to that of the easiest one. If the percent of the current question
	// is greater, it is assigned as the easiest.

	for (int m = 0; m < questions.size(); m++){
	    
	    if (questions.get(m).getPercent() > questions.get(easiest).getPercent()){
		easiest = m;
	    }
	}
	
	// After the loop completes the number corresponding the the easiest question is returned.
	
	return easiest;
    }

    // A method for determining the hardest question in the quiz. It accepts the ArrayList
    // of questions as an argument.
    
     private static int getHardest(ArrayList<Question> questions){

	// Create a variable to store the number corresponding to the hardest question. It is
	// initialized to 0 to start, because the first question's number and position in the 
	// ArrayList is 0.

	int hardest = 0;

	// The for loop iterates over the questions ArrayList and compares the percent of 
	// right attempts to that of the hardest one. If the percent of the current question
	// is smaller, it is assigned as the hardest.
	
	for (int m = 0; m < questions.size(); m++){
	    
	    if (questions.get(m).getPercent() < questions.get(hardest).getPercent()){
		hardest = m;
	    }
	}

	// After the loop completes the number corresponding the the hardest question is returned.
	
	return hardest;
    }

    // A method for printing one question and its statistics. It accepts the questions ArrayList
    // and the number of the question to be printed as arguments. 
    
    private static void printOneQuestion(ArrayList<Question> questions, int a){

	// The question and its statistics are displayed using the Question class'
	// corresponding methods.

	System.out.println("");
	System.out.println("Question: " + questions.get(a).getQuestion());
	System.out.println("\tTimes Tried: " + questions.get(a).getNumTried());
	System.out.println("\tTimes Correct: " + questions.get(a).getNumCorrect());
	System.out.printf("\tPercent Correct: %.1f%%\n", questions.get(a).getPercent());
    }
	
}

	    
