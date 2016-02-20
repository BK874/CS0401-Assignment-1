public class Player{
    private double money;
    private int handsPlayed;
    private int handsWon;

    public Player(){
	money = 100.00;
	handsPlayed = 0;
	handsWon = 0;
    }

    public double getMoney(){
	return money;
    }
    
    public int getHandsPlayed(){
	return handsPlayed;
    }
   
    public int getHandsWon(){
	return handsWon;
    }
    
    public void changeMoney(double amount){
	money += amount;
    }

    public void changeHandsPlayed(int amount){
	handsPlayed += amount;
    }

    public void changeHandsWon(int amount){
	handsWon += amount;
    }
    
    //Need methods for loading and saving information to a file
}

