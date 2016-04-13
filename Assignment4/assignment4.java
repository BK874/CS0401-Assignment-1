/*
Assignment 4 - Voting
Brian Knotten
*/


/* Instead of two new JPanels for Login and Cast Vote, just two buttons w/ActionListeners that 
   trigger JOptionPanes? Could potentially simplify things but may not be very "OO"
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class assignment4{
    
    public static void main(String[] args) throws IOException{
	new eVote(args);
    }
}
