/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markfickman_javalab5_maf176;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;


/**
 *
 * @author markfickman
 */
public class MarkFickman_JavaLab5_MAF176 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String input;
        String plan;
        String plan_check;
        double amount = 0.0;
        double hours = 0.0;
        boolean check = true;
        double total_hours = 0.0, new_hours = 0.0, new_paid = 0.0, total_paid = 0.0;
        double avg_hours = 0.0, avg_paid = 0.0;
        int loop_count = 0;
        
        Scanner keyboard = new Scanner(System.in);
        // TODO code application logic here
        
        //define number format
        NumberFormat fmtCurr = NumberFormat.getCurrencyInstance();
        
        //define decimal format
        DecimalFormat formatted = new DecimalFormat("0.000");
        
        //prompt user for file that contains the previous information
        System.out.println("please enter the name of the file:");
        String filename = keyboard.nextLine();
        
        //declare file
        File nofile = new File(filename);
 
        
        //if no file exists, create it, if it exists, read in the information
        if (!nofile.exists()){
            //no file found, create new file
            System.out.println("No such file exists, creating it now");
            FileWriter fl = new FileWriter(filename, true);
            PrintWriter pw = new PrintWriter(fl);
        }
        else{
            //file found, read in data from it to get average and total usage
            Scanner inFile = new Scanner(nofile);
            // read in data until no more data is found in file
            
                
                //while(inFile.nextLine().equalsIgnoreCase("A")  || inFile.nextLine().equalsIgnoreCase("B") || inFile.nextLine().equalsIgnoreCase("C")) {
                    //System.out.println(inFile.nextDouble());
                    //inFile.nextLine();
                    //if (plan_check.equals("A")  || plan_check.equals("B"))
            //
            //inFile.nextDouble();
            while(inFile.hasNext()){
                if (!inFile.hasNextDouble()){
                    inFile.nextLine();
                }
                else{
                    new_hours = inFile.nextDouble();
                    total_hours = new_hours + total_hours;
                    new_paid = inFile.nextDouble();
                    total_paid = new_paid + total_paid;
                    loop_count = loop_count + 1; 
                }
            }
            // close infile
            inFile.close();
            avg_hours = total_hours/loop_count;
            avg_paid = total_paid/loop_count;
            //System.out.println("loop_count = " + loop_count);
            System.out.println("total hours used is " + total_hours);
            System.out.println("total paid is " + fmtCurr.format(total_paid));
            System.out.println("average hours used is " + formatted.format(avg_hours));
            System.out.println("average amount paid is " + fmtCurr.format(avg_paid));
        }
        
        // prompt the user for their plan, take first character and convert to upper case
        //if plan is not A - C, re-prompt user
        System.out.println();
        System.out.println("Please enter the letter of your monthly plan ( A, B, or C)"); 
        input = keyboard.nextLine(); 
        plan = input.toUpperCase();
        while ( !(plan.equals("A")) && !(plan.equals("B")) && !(plan.equals("C"))){
            System.out.println ("the plan must be A, B, or C.  Please try again");
            input = keyboard.nextLine();
            plan = input.toUpperCase();
        }
       
       //prompt the user for the number of hours, if negative or word, re-prompt
       System.out.println ("please enter the number of hours");
       
       //check that the type matches
       check = true;
       while (check){
            check = false;
            try {
                hours = keyboard.nextDouble();   
            }
            catch (InputMismatchException ime){
               System.out.println("Must enter an number!");
                keyboard.next();
                System.out.println("Please enter the number of hours: ");
                check = true;
            }
        }
	
        while (hours <= 0){
            System.out.println("the hours entered must be a positive amount");
            System.out.println("please enter the hour amount: ");
            check = true;
            while (check){
                check = false;
                try {
                    hours = keyboard.nextDouble();   
                }
                catch (InputMismatchException ime){
                    System.out.println("Must enter an number!");
                    keyboard.next();
                    System.out.println("Please enter the number of hours: ");
                    check = true;
                }
            }
	}
        
        //comput the bill total based upon the hours and plan
        if(plan.equals("A")){
            if(hours <= 10){
                    amount = 9.95;
               }
            else{
		amount = 9.95 + ((hours - 10) * 2.00);
            }
        }
        else if(plan.equals("B")){
            if(hours <= 20){
                amount = 13.95;
            }
            else{
                amount = 13.95 + (hours - 10);
            }
        }
        else if(plan.equals("C")){
		amount = 19.95;
	}
        
        //print out the bill total with the plan to 2 decimal places
        System.out.printf("the total cost with plan %s is %.2f", plan, amount);
        
        //write the information to the file without overwriting anything
        FileWriter fl = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter(fl);
        pw.println(plan);
        pw.println(hours);
        pw.println(amount);
        
        //close files
        pw.close();
        fl.close();
        
    }
}
