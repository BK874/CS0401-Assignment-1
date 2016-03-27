/*
Assignment 3 - Quiz
Brian Knotten
*/

public class Question{

    // Declare variables for storing the question, the number of the 
    // correct answer, the number of times the question was attempted,
    // the number of times it was answered correctly, and an array to 
    // hold the possible answers.

    private String question;
    private int correct;
    private String[] answers;
    private int numCorrect = 0;
    private int numTried = 0;
    
    // The constructor accepts the question and the number of possible
    // answers as arguments, assigns the question to the proper variable,
    // and uses the number size the array.

    public Question(String ques, int numAns){
	question = ques;
	answers = new String[numAns];
    }    
    
    // Method used to add a potential answer to the answers array
    // - accepts the answer and the answers number as arguments

    public void addAnswer(String ans, int num){
	answers[num] = ans;
    }

    // Method for setting the correct answer's number - accepts the number
    // as an argument

    public void setCorrectAnswer(int cor){
	correct = cor;
    }
    
    // Method for setting the number of tries and incrementing it - accepts 
    // the number of tries as an argument
    
    public void setNumTried(int tri){
	numTried += tri;
    }
    
    // Method for setting the number of correct tries and incrementing it -
    // accepts the number of tries as an argument
    
    public void setNumCorrect(int numCor){
	numCorrect += numCor;
    }
    
    // Method for returning the question

    public String getQuestion(){
	return question;
    }
    
    // Method for printing all the possible answers - loops over the
    // answers array
    
    public void printAnswers(){
	for (int k = 0; k < answers.length; k++){
	    System.out.println(k + ": " + answers[k]);

	}
    }
    
    // Method for returning the array containing possible answers
    
    public String[] getAnswers(){
	return answers;
    }

    // Method for returning the number of possible answers - returns the
    // length of the answers array
    
    public int getNumAns(){
	return answers.length;
    }
    
    public String getCorrectAns(){
	return answers[correct];
    }
    
    // Method for returning the number of the correct answer

    public int getCorrectAnsNum(){
	return correct;
    }

    // Method for returning the number of times the question has been attempted
    
    public int getNumTried(){
	return numTried;
    }

    // Method for returning the number of times the question has been
    // correctly answered
    
    public int getNumCorrect(){
	return numCorrect;
    }

    // Method for calculating and returning the percent of times the answer
    // has been answered correctly
    
    public double getPercent(){
	double percent = 100 * ((float)numCorrect/numTried);
	return percent;
    }
}
