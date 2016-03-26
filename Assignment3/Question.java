public class Question{

    // Needed:
    // - a String for the actual question 
    // - an array of Strings storing possible answers that is sized in the constructor
    // - an int indicating the correct answer
    // - two ints to store number of times correct and times tried
    // - Various methods for access

    private String question;
    private int correct;
    private String[] answers;
    private int numCorrect = 0;
    private int numTried = 0;

    public Question(String ques, int numAns){
	question = ques;
	answers = new String[numAns];
    }    

    public void addAnswer(String ans, int num){
	answers[num] = ans;
    }

    public void setCorrectAnswer(int cor){
	correct = cor;
    }
    
    public void setNumTried(int tri){
	numTried += tri;
    }
    
    public void setNumCorrect(int numCor){
	numCorrect += numCor;
    }

    public String getQuestion(){
	return question;
    }
    
    public void printAnswers(){
	for (int k = 0; k < answers.length; k++){
	    System.out.println(k + ": " + answers[k]);

	}
    }
    
    public String[] getAnswers(){
	return answers;
    }
    
    public int getNumAns(){
	return answers.length;
    }
    
    public String getCorrectAns(){
	return answers[correct];
    }
    
    public int getCorrectAnsNum(){
	return correct;
    }
    
    public int getNumTried(){
	return numTried;
    }
    
    public int getNumCorrect(){
	return numCorrect;
    }
    
    public double getPercent(){
	double percent = 100 * ((float)numCorrect/numTried);
	return percent;
    }
}
