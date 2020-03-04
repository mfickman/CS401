/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markfickman_maf176_lab6;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
/**
 *
 * @author markfickman
 * this program demonstrates the use of methods
 * 3 different methods will be called
 * one method gets a number from the user
 * another method gets a filename from the user
 * and the last method counts the number of lines in the file
 * exception handling will be used to account for improper input
 */
public class MarkFickman_maf176_lab6 {

    /**
     * @param args the command line arguments
     * 
     */
    
    //define the method that asks the user for a number and checks if it is in a valid range
    /**
     * method prompts the user for a number and stores it in num
      * @param lowerBound is the lower bound input of 0
      * @param upperBound is the higher bound for the input of 100
      * @return num stores the result of the input
       */
    public static double getValidNumber(double lowerBound, double upperBound){
        
        boolean check;
        double hours = 0.0;
        
        Scanner keyboard = new Scanner(System.in);
        
        //prompt the user for a number, use try catch to catch invalid input
        System.out.println("Please enter a number between " + lowerBound + " and " + upperBound);
        check = true;
        while (check){
            check = false;
            try {
                hours = keyboard.nextDouble();   
            }
            catch (InputMismatchException ime){
                System.out.println("Must enter a number!");
                keyboard.next();
                System.out.println("Please enter a number between " + lowerBound + " and " + upperBound);
                check = true;
            }
        }

        //check if number falls in valid range and check for valid input
        while(hours < lowerBound || hours > upperBound){
            System.out.println("The number must fall between " + lowerBound + " and " + upperBound);
            System.out.println("Please enter a new number: ");
            check = true;
            while (check){
                check = false;
                try {
                    hours = keyboard.nextDouble();   
                }
                catch (InputMismatchException ime){
                    System.out.println("Must enter a number!");
                    keyboard.next();
                    System.out.println("Please enter a number between " + lowerBound + " and " + upperBound);
                    check = true;
                }
            }
        }
        return(hours);  
    }
    
    //create method to get filenamme
    /**call the getFileName method and store the result in the variable filename
    @return filename stores the reference variable that connects to the string object which holds the filename
    */
    public static String getFileName(){
           
        Scanner keyboard = new Scanner(System.in);
       
        System.out.println("Please enter the name of the valid file: ");
        String filename = keyboard.nextLine();
        
        ///check to see if file exits, prompt user until file is found
        File nofile = new File(filename);
        while (!nofile.exists()){
            System.out.println("No such file was found!!!");
            System.out.println("Please enter the new file name: ");
            filename = keyboard.nextLine();
            nofile = new File(filename);
        }
        return(filename);
    }
    
    //create method to count the number of lines int he file
    /**call the countFileLines method and store the result in the variable numLines
    * @param filename is read in to open and count the number of lines
    * @return numLines holds the integer value which is the number of lines in the file
    * @throws FileNotFoundException necessary because we are reading from the file and we have to ensure we read from a file that exists
     */
    public static int countFileLines (String filename) throws FileNotFoundException {
        int line_count = 0;
        File nofile = new File(filename);
        
        Scanner infile = new Scanner(nofile);
       
        while (infile.hasNextLine()){
            infile.nextLine();
            line_count++;
        }
        infile.close();
        return(line_count);
    }
        
        
    /**
     * this is the main method that form which the other three methods are called
     * @param args is the default parameter
     * @throws FileNotFoundException due to possibility of calling a file that does not exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        final int LOWER_BOUND = 0;
        final int UPPER_BOUND = 100;
     
        double num = getValidNumber(LOWER_BOUND, UPPER_BOUND);
      
        String filename = getFileName();
       
        int numLines = countFileLines(filename);
        System.out.println();
        System.out.println("Here is the main");
        System.out.println("The number entered by the user was " + num);
        System.out.println("The number of lines in " + filename + " was " + numLines);
    }
}
