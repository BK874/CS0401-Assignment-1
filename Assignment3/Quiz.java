/*
Assignment 3 - Quiz
Brian Knotten
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Quiz{

    public static void main(String[] args) throws IOException{

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

	if (args.length > 0){
	    System.out.println("Welcome to the Quiz Program! Good luck!");
	    quiz = args[0];
	}else{
	    System.out.println("No Quiz given!");
	}

	File file = new File(quiz);
	Scanner inputFile = new Scanner(file);

	while (inputFile.hasNext()){

	    question = inputFile.nextLine();
	    numAnswers = inputFile.nextInt();
	    Question x = new Question(question, numAnswers);

	    inputFile.nextLine();
	    for (int j = 0; j < numAnswers; j++){
		loopAnswer = inputFile.nextLine();
		x.addAnswer(loopAnswer, j);
	    }
	    
	    filler = inputFile.nextInt();
	    x.setCorrectAnswer(filler);
	    filler = inputFile.nextInt();
	    x.setNumTried(filler);
	    filler = inputFile.nextInt();
	    x.setNumCorrect(filler);
	    
	    questions.add(x);
	    
	    inputFile.nextLine();
	}
	
	inputFile.close();
	
	userAns = new int[questions.size()];

	for (int i = 0; i < questions.size(); i++){
	    System.out.println("");
	    System.out.println("Question " + i + ":");
	    System.out.println(questions.get(i).getQuestion());
	    System.out.println("Answers: ");
	    
	    questions.get(i).printAnswers();
	    
	    System.out.println("");
	    userAns[i] = getAns(questions.get(i).getNumAns());
	    questions.get(i).setNumTried(1);
	}


	System.out.println("Thanks for your answers!");
	System.out.println("Here are your results:");
	
	for (int l = 0; l < questions.size(); l++){
	    System.out.println("");
	    System.out.println("Question: " + questions.get(l).getQuestion());
	    System.out.println("Answer: " + questions.get(l).getCorrectAns());
	    System.out.println("Player Guess: " + userAns[l]);
	    
	    if (userAns[l] == questions.get(l).getCorrectAnsNum()){
		System.out.println("\tResult: CORRECT! Great Work!");
		right++;
		questions.get(l).setNumCorrect(1);
	    }else{
		System.out.println("\tResult: INCORRECT! Remember the answer for next time!");
		wrong++;
	    }   

	}
	
	System.out.println("");
	System.out.println("Your overall performance was: ");
	System.out.println("\tRight:\t" + right);
	System.out.println("\tWrong:\t" + wrong);
	System.out.printf("\tPct:\t%.0f%%\n", 100*((float)right/questions.size()));
	
	System.out.println("Here are some cumulative statistics: ");
	
	for (int n = 0; n < questions.size(); n++){
	    System.out.println("");
	    System.out.println("Question: " + questions.get(n).getQuestion());
	    System.out.println("\tTimes Tried: " + questions.get(n).getNumTried());
	    System.out.println("\tTimes Correct: " + questions.get(n).getNumCorrect());
	    System.out.printf("\tPercent Correct: %.1f%%\n", questions.get(n).getPercent());
	}
	
	System.out.println("Easiest Question:");
	easiest = getEasiest(questions);
	printOneQuestion(questions, easiest);
	
	System.out.println("");
	
	System.out.println("Hardest Question: ");
	hardest = getHardest(questions);
	printOneQuestion(questions, hardest);
    
	PrintWriter outputFile = new PrintWriter(quiz);
	for (int w = 0; w < questions.size(); w++){
	    outputFile.println(questions.get(w).getQuestion());
	    outputFile.println(questions.get(w).getNumAns());
	    for (int v = 0; v < questions.get(w).getNumAns(); v++){
		outputFile.println(questions.get(w).getAnswers()[v]);
	    }
	    outputFile.println(questions.get(w).getCorrectAnsNum());
	    outputFile.println(questions.get(w).getNumTried());
	    outputFile.println(questions.get(w).getNumCorrect());
	}
	outputFile.close();
	
	System.out.println("Thanks for taking the Quiz!\n");
        
    }

    private static int getAns(int max){
	
	Scanner keyboard = new Scanner(System.in);
	int ans;

	do{
	    System.out.print("Your answer? (enter a number): ");
	    while (!keyboard.hasNextInt()){
		keyboard.next();
		System.out.print("Your answer? (enter a number): ");
	    }
	    ans = keyboard.nextInt();
	} while (ans < 0 || ans > max-1);
	
	return ans;
    }
    
    private static int getEasiest(ArrayList<Question> questions){

	int easiest = 0;
	
	for (int m = 0; m < questions.size(); m++){
	    
	    if (questions.get(m).getPercent() > questions.get(easiest).getPercent()){
		easiest = m;
	    }
	}
	
	return easiest;
    }
    
     private static int getHardest(ArrayList<Question> questions){

	int hardest = 0;
	
	for (int m = 0; m < questions.size(); m++){
	    
	    if (questions.get(m).getPercent() < questions.get(hardest).getPercent()){
		hardest = m;
	    }
	}
	
	return hardest;
    }
    
    private static void printOneQuestion(ArrayList<Question> questions, int a){

	System.out.println("");
	System.out.println("Question: " + questions.get(a).getQuestion());
	System.out.println("\tTimes Tried: " + questions.get(a).getNumTried());
	System.out.println("\tTimes Correct: " + questions.get(a).getNumCorrect());
	System.out.printf("\tPercent Correct: %.1f%%\n", questions.get(a).getPercent());
    }
	
}

	    
