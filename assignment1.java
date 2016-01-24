/*
Assignment 1 - bookstore
Items: 
1. Books $5.00 each
2. Bookmarks $1.00 each or a pack of six for $5.00 - always give a pack when possible 
3. Paintings of Books $100.00 each
7% tax on all sales
Every third customer gets a discount - give message when receiving discount and different message when not receiving discount
All values rounded to nearest cent
*/

import java.util.Scanner;

public class assignment1{

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
	
		System.out.print("Are there more customers in line? (1 = Yes, 2 = No): ");
		
		int custInt = sc.nextInt(); 
		int custNum = 1;
		boolean discount = false;
		boolean bookDiscount = false;
		int cartBooks = 0;
		int cartMarks = 0;
		int cartPaint = 0;

		while (custInt == 1){
			
			if (custNum % 3 == 0){
				discount = true;
			}
			else{
				discount = false;
			}

			System.out.println("1 - Buy Books - $5.00 each ");
			System.out.println("2 - Buy Bookmarks - $1.00 each, $5.00 for a six-pack ");
			System.out.println("3 - Buy Paintings of Books - $100.00 each ");
			System.out.println("4 - See current order ");
			System.out.println("5 - Checkout ");
			System.out.print("Please enter a valid option (1 through 5): ");
			int optInt = sc.nextInt();

			if (optInt == 1){
				System.out.println("Currently in cart: " + cartBooks + " books");
				System.out.print("How many books do you want to buy? ");
				int buyBook = sc.nextInt();
				while (buyBook < 0){	
					System.out.print("Please enter a valid number of books - 0 or more: ");
					buyBook = sc.nextInt();
				}
				cartBooks += buyBook;
				
			}
			
			else if (optInt == 2){
				System.out.println("Currently in cart: " + cartMarks + " bookmarks");
				System.out.print("How many bookmarks do you want to buy? " );
				int buyMarks = sc.nextInt();
				while (buyMarks < 0){
					System.out.print("Please enter a valid number of bookmarks - 0 or more: ");
					buyMarks = sc.nextInt();
				}
				cartMarks += buyMarks;
			}
			
			else if (optInt == 3){
				System.out.println("Currently in cart: " + cartPaint + " paintings");
				System.out.print("How many paintings do you want to buy? ");
				int buyPaint = sc.nextInt();
				while (buyPaint < 0){
					System.out.print("Please enter a valid number of paintings - 0 or more: ");
					buyPaint = sc.nextInt();
				}
				cartPaint += buyPaint;
			}
			
			else if (optInt == 4){
				System.out.println("Currently in cart:");
				System.out.println("Books: " + cartBooks);
				System.out.println("Bookmarks: " + cartMarks);
				System.out.println("Paintings of books: " + cartPaint);
			}
			
			else if (optInt == 5){
								
				double booksCost, marksCost, packsCost, paintCost, subsubtotal, discountTotal, subtotal;
				booksCost = marksCost = packsCost = paintCost = subsubtotal = discountTotal = subtotal = 0;

				if (cartBooks > 0){
					System.out.print("Do you have a Bookworm Card? (1 = yes 2 = no) ");
					int bookWorm = sc.nextInt();
				
					if (bookWorm == 1){
						bookDiscount = true;
					}
					else{
						bookDiscount = false;
					}
				}

				System.out.println("----------------------------------------------------");
				if (discount == true){
					System.out.println("You won a 10% discount!");
				}

				else {
					System.out.println("You did not get a discount! Better luck next time!");
				}
					
				if (cartBooks > 0){
					booksCost = cartBooks * 5.0;
					System.out.printf("\t" + cartBooks + " Book(s):\t\t$%.2f\n", booksCost);
				}
				
				if (cartMarks > 0){
					if (cartMarks < 6){
						marksCost = (double)cartMarks;
						System.out.printf("\t" + cartMarks + " Single Bookmark(s):\t$%.2f\n", marksCost);
					}
					
					else if (cartMarks >= 6){
						int cartPacks = cartMarks / 6;
						packsCost = cartPacks * 5.0;
						cartMarks = cartMarks % 6;
						marksCost = (double)cartMarks; 
						System.out.printf("\t" + cartPacks + " Bookmark Pack(s):\t$%.2f\n", packsCost);
						System.out.printf("\t" + cartMarks + " Single Bookmark(s):\t$%.2f\n", marksCost);
					}
				}
				
				if (cartPaint > 0){
					paintCost = cartPaint * 100.0;
					System.out.printf("\t" + cartPaint + " Painting(s):\t\t$%.2f\n", paintCost);
				}

				if (discount == true){
					subsubtotal = booksCost + packsCost + marksCost + paintCost;
					
					if (bookDiscount == true){
						discountTotal = subsubtotal * 0.1 + (booksCost * 0.25);
						subtotal = subsubtotal - discountTotal;
						System.out.printf("10%% Discount! Saved:\t\t-$%.2f\n", (subsubtotal * 0.1));
						System.out.printf("Bookworm Discount! Saved:\t-$%.2f\n", (booksCost * 0.25));
					}
					else{
						discountTotal = subsubtotal * 0.1; 
						subtotal = subsubtotal - discountTotal;
						System.out.printf("10%% Discount! Saved:\t\t-$%.2f\n", discountTotal);
					}
				}
				
				else {
					subtotal = booksCost + packsCost + marksCost + paintCost;
					
					if (bookDiscount == true){
						discountTotal = (booksCost * 0.25);
						subtotal -= discountTotal;
						System.out.printf("Bookworm Discount! Saved: \t-$%.2f\n", discountTotal);
					}
				}
					
				System.out.printf("\nSubtotal:\t\t\t$%.2f\n", subtotal);

				double tax = subtotal * 0.07;
				double total = subtotal + tax;
				System.out.printf("Tax:\t\t\t\t$%.2f\n", tax);
				System.out.printf("\nTotal:\t\t\t\t$%.2f\n", total);
				System.out.println("\n----------------------------------------------------");
				
				System.out.print("\nEnter amount paid (no dollar sign): ");
				double payment = sc.nextDouble();
				while (payment < total){
					System.out.print("Not enough money, please re-enter: ");
					payment = sc.nextDouble();
				}
				double change = payment - total;
				System.out.printf("\nYour change is: $%.2f\n", change);
				System.out.println("Thanks for shopping!\n");
				
				cartBooks = cartMarks = cartPaint = 0;
				discount = false;
				custNum ++;
			
				System.out.print("More customers in line? (1 = Yes, 2 = No): ");
				custInt = sc.nextInt();
			
			}
			
			else {
				System.out.println("Please enter a valid option (1 through 5) ");
			}
		
		}
	System.out.println("No more customers! Closing!");
	}
}
