/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markfickman_java_project_2;


import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.lang.*;        

/**
 *
 * @author markfickman
 */
public class MarkFickman_java_project_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        // TODO code application logic here
        Scanner keyboard = new Scanner(System.in);
        int [][] data = new int[5][336];
        int i = 0, j = 0;
        int hour = 0, minute = 0, second = 0, prediction_minute = 0, prediction_second = 0;
        double prediction_hour = 0.0;
        String station;
        int station_key = 0;
        int hour_marker = 0, minute_marker = 0, second_marker = 0;
        int station_dwell = 0;
        double predict_1 = 0.0, predict_2 = 0.0, predict_3 = 0.0, prediction_total = 0.0;
        
        
        //prompt user for file to read in
        
    System.out.println("please enter the name of the file:");
    String filename = keyboard.nextLine();

//decaler File
    File nofile = new File(filename);
    
    //check to see if the file exists, prompt user until file is found
    while (!nofile.exists()){
            System.out.println("file could not be found, please try again");
            System.out.println("please enter the filename:");
            filename = keyboard.nextLine();
            nofile = new File(filename);
            }	
    //scanner to read in data from file
    Scanner inFile = new Scanner(nofile);
    //System.out.println(inFile.next());

    //populate array with data from file
    for (j = 0; j < 336; j++){
       data[0][j] = inFile.nextInt();
       data[1][j] = inFile.nextInt();
       data[2][j] = inFile.nextInt();
       data[3][j] = inFile.nextInt();
       data[4][j] = inFile.nextInt();
       inFile.nextLine();
    }
   
    inFile.close();
    
    //prompt the user for the time
    System.out.println("Now we are going to need you to provide the current time(note, hour is in miltary time (9-22)");
    System.out.println("What is the hour(number only please)?");
    hour = validate_time("Hour");
    while(hour>= 23 || hour < 9){
        System.out.println("There are only 24 hours in a day.");
        System.out.println("The trains start running at 9, and the last run of the day starts at hour 22, please enter a new time.");
        System.out.println("2nd chance, What is the hour(number only please)?");
        hour = validate_time("Hour");
    }
    System.out.println("What is the minute (0-59 and numbers only please)?");
    minute = validate_time("Minute");
    while(minute >=60 || minute < 0){
        System.out.println("There are 60 minutes in an hour, try again");
        System.out.println("2nd chance, What is the current minute?");
        minute = validate_time("Minute");
    }
    System.out.println("To get the best estimate, we need to know the second");
    System.out.println("What is your best estimate of the current second (0-59 and numbers only please)?");
    second = validate_time("Second");
    while(second >= 60 || second < 0){
        System.out.println("There are 60 seconds in a minute");
        System.out.println("2nd chance, what is your estimate of the current second?");
        second = validate_time("Second");
    }
    
    //prompt user for station 
    while(station_key == 0){
        System.out.println("The following is a map for your convenience");
        System.out.println("Station 1: North");
        System.out.println("Station 2: East");
        System.out.println("Station 3: South");
        System.out.println("Station 4: West");
        System.out.println("Please enter the name of the station or the number(e.g east or 2): ");
        station = keyboard.next();
        if(station.equalsIgnoreCase("North") || station.equals("1")){
            station_key = 1;
        }
        else if(station.equalsIgnoreCase("East") || station.equals("2")){
            station_key = 2;
        }
        else if(station.equalsIgnoreCase("South") || station.equals("3")){
            station_key = 3;
        }
        else if(station.equalsIgnoreCase("West") || station.equals("4")){
            station_key = 4;
        }
        else{
            station_key = 0;
            System.out.println("Please try again and enter a valid station");
            System.out.println("2nd chance");
        }
    }
    
    //algorithim to finding matching norming point location and time to station
    hour_marker = hour_finder(hour, data);
    minute_marker = minute_finder(minute, hour_marker, hour, data);
   
    //if exact minute was found, search for exact second, if exact second not found, use next avaiable norming point
    if(minute == data[2][minute_marker] && second == data[3][minute_marker]){
        //search to see if seconds match
        second_marker = minute_marker;
    }
    else{
        second_marker = minute_marker+1;
    }
 
    //check to see if train is at station 
    station_dwell = station_time(station_key, second_marker + 1, minute, second, data);
    
    if(station_dwell == -1000){
        int snpid = 0;
        System.out.println("Train is not at your station.");
        System.out.println("Train is on the move.");
        //display the remaining travel time until train arrives at specified station
        //algorithim to compute median travel time
        int norming_point_id = 0, station_id = 0;
        int station_marker = second_marker;
        norming_point_id = data[0][second_marker];
        
        //loop to locate location of station_id data
        while (station_id != station_key){
            station_marker = station_marker + 1;
            station_id = data[4][station_marker];
            snpid = data[0][station_marker];  
        }
        
        //if hour is 9 or less, only use one prevous run data
        //if hour is 10 or 11, use median of 2 previous run data
        // if hour is 12 oe greater, use median of 3 previous runs
        
  
        if (hour == 9 || hour == 10){
            //use only one run to predict arrival time
            int np = 0, st = 0;
            
            //find norming point and station
            while (data[0][np] != norming_point_id  && data[1][np] != hour){
                np = np +1;
            }
            st = np;
            while (data[4][st] != station_id){
                st = st + 1;
            }
            
            prediction_hour = data[1][st] - data[1][np];
            prediction_minute = data[2][st] - data[2][np];
            if (prediction_minute < 0){
                prediction_minute = 60 + prediction_minute;
                prediction_hour = prediction_hour - 1;
            }
            prediction_second = data[3][st] - data[3][np];
            if (prediction_second < 0){
                prediction_second = 60 + prediction_minute;
                prediction_minute = prediction_minute - 1;
            }
  
            System.out.println("the train is predicted to be at " + station_key + " in " + prediction_hour + " hour(s), " + prediction_minute + " minute(s), and " + prediction_second + " second(s).");
        }
        
        if(hour == 11 || hour == 12){
            //have to take average of last two runs
            int np = second_marker - 3, st = station_marker - 3;
            int ph2 = 0, pm2 = 0, ps2 = 0;
            
            while (data[0][np] != norming_point_id){
                np = np - 1;
            }
            while (data[0][st] != snpid){
                st = st - 1;
            }
            if(data[0][st] == snpid){
                st = st - 1;
            }
            
            int prediction_np = data[0][np] + 1;
            int prediction_st = data[0][st] + 1;
            
            prediction_hour = data[1][st] - data[1][np];
            prediction_minute = data[2][st] - data[2][np];
            if (prediction_minute < 0){
                prediction_minute = -1 * prediction_minute;
                prediction_hour = prediction_hour - 1;
            }
            prediction_second = data[3][st] - data[3][np];
            if (prediction_second < 0){
                prediction_second = -1* prediction_second;
                prediction_minute = prediction_minute - 1;
            }
            
            np = np - 3;
            st = st - 3;
            
            //find next prediciton time 
            while (data[0][np] != prediction_np){
                np = np - 1;
            }
            while (data[0][st] != prediction_st){
                st = st - 1;
            }
            
            ph2 = data[1][st] - data[1][np];
            pm2 = data[2][st] - data[2][np];
            if (pm2 < 0){
                pm2 = 60 + pm2;
                ph2 = ph2 - 1;
            }
            ps2 = data[3][st] - data[3][np];
            if (ps2 < 0){
                ps2 = 60 + ps2;
                pm2 = pm2 - 1;
            }
            
            prediction_hour = (prediction_hour + ph2) / 2;
            prediction_minute = (prediction_minute + pm2) / 2;
            prediction_second = (prediction_second + ps2) / 2;
            
            if(prediction_hour == 0.5){
                prediction_minute = prediction_minute + 30;
                prediction_hour = 0.0;
            }
      
            System.out.println("the train is predicted to be at " + station_key + " in " + prediction_hour + " hour(s), " + prediction_minute + " minute(s), and " + prediction_second + " second(s).");
        }
        if(hour >= 12){
            //median of three 
            //have to take average of last two runs
            int np = second_marker - 3, st = station_marker - 3;
            int ph2 = 0, pm2 = 0, ps2 = 0;
            int ph3 = 0, pm3 = 0, ps3 = 0;
            
            while (data[0][np] != norming_point_id){
                np = np - 1;
            }
            while (data[0][st] != snpid){
                st = st - 1;
            }
            if(data[0][st] == snpid){
                st = st - 1;
            }
            
            int prediction_np = data[0][np] + 1;
            if (prediction_np == 29){
                prediction_np = 28;
            }
            int prediction_st = data[0][st] + 1;
            if (prediction_st == 29){
                prediction_st = 28;
            }
            
            prediction_hour = data[1][st] - data[1][np];
            prediction_minute = data[2][st] - data[2][np];
            if (prediction_minute < 0){
                prediction_minute = -1 * prediction_minute;
                prediction_hour = prediction_hour - 1;
            }
            prediction_second = data[3][st] - data[3][np];
            if (prediction_second < 0){
                prediction_second = -1* prediction_second;
                prediction_minute = prediction_minute - 1;
            }
               
            np = np - 3;
            st = st - 3;
            //find next prediciton time 
            while (data[0][np] != prediction_np){
                np = np - 1;
            }
            while (data[0][st] != prediction_st){
                st = st - 1;
            }
       
            ph2 = data[1][st] - data[1][np];
            pm2 = data[2][st] - data[2][np];
            if (pm2 < 0){
                pm2 = 60 + pm2;
                ph2 = ph2 - 1;
            }
            ps2 = data[3][st] - data[3][np];
            if (ps2 < 0){
                ps2 = 60 + ps2;
                pm2 = pm2 - 1;
            }
            
            //find next travel time 
            np = np - 3;
            st = st - 3;
            while (data[0][np] != prediction_np){
                np = np - 1;
            }
            while (data[0][st] != prediction_st){
                st = st - 1;
            }
            ph3 = data[1][st] - data[1][np];
            pm3 = data[2][st] - data[2][np];
            if (pm3 < 0){
                pm3 = 60 + pm3;
                ph3 = ph3 - 1;
            }
            ps3 = data[3][st] - data[3][np];
            if (ps3 < 0){
                ps3 = 60 + ps3;
                pm3 = pm3 - 1;
            }
            
            predict_1 = (prediction_hour * 3600) + (prediction_minute * 60) + prediction_second;
            predict_2 = (ph2 * 3600) + (pm2 * 60) + ps2;
            predict_3 = (ph3 * 3600) + (pm3 * 60) + ps3;
            prediction_total = compute_median(predict_1, predict_2, predict_3);
            prediction_hour = (int) (prediction_total/3600);
            prediction_total = prediction_total - (prediction_hour * 3600);
            prediction_minute = (int) (prediction_total/60);
            prediction_total = prediction_total - (prediction_minute * 60);
            prediction_second = (int) prediction_total;
            
            
            
            //double prediction_hour_f = compute_medianhour(prediction_hour, ph2, ph3);
            //int prediction_minute_f = compute_median(prediction_minute, pm2, pm3);
            //int prediction_second_f = compute_median(prediction_second, ps2, ps3);
            //double prediction_hour_f = (prediction_hour + ph2 + ph3)/ 3;
            //int prediction_minute_f = (prediction_minute + pm2 + pm3)/3;
            //int prediction_second_f = (prediction_second + ps2 + ps3)/3;
            
            System.out.println("the current time is " + hour + ":" + minute + ":" + second);
            System.out.println("the train is predicted to be at " + station_key + " in " + prediction_hour + " hour(s), " + prediction_minute + " minute(s), and " + prediction_second + " second(s).");
        }
    }
    else{
        System.out.println("the current time is " + hour + ":" + minute + ":" + second);
        System.out.println("The train will be at your station for another " + station_dwell + " seconds.");        
    }
}    
    
    /**
     * validate_time takes in the time input from the user and checks to ensure it is in proper number form
     * @param my_time, time input from user
     * @return valid_time, checked numerical input
     */
    public static int validate_time(String my_time){
        boolean check = true;
        int valid_time = 0;
        Scanner kb = new Scanner(System.in);
        while(check){
            check = false;
            try{
                valid_time = kb.nextInt();
            }
            catch(InputMismatchException ime){
                check = true;
                System.out.println("The " + my_time + " must be a postive integer");
                kb.next();
                System.out.println("Please enter a valid "+ my_time); 
            }
        }
        return valid_time;
    }
    
    /**
     * hour_find searches trough the array containing the norming point data and locates the id that matches the specified hour
     * @param my_hour, hour specified by the user
     * @param my_data, array containing all the times and locations of the norming point data
     * @return k, the location in the array that contains the first instance of the specified hour
     */
    public static int hour_finder(int my_hour, int [][] my_data){
        boolean loop_continue = true;
        int k = 0;
        while (loop_continue){
            if(my_hour == my_data[1][k]){
                loop_continue = false;
            }
            else{
                k = k+1;
            }
            if(k > 335){
                System.out.println("We've got a problem finding the hour");
                System.out.println("We've got a problem finding the hour");
                System.out.println("We've got a problem finding the hour");
                loop_continue = false;
            }
        }
        return k;
    }
    
    /**
     * minute_finder searches through the norming point array and finds the norming point that is closest to the time specified by the user
     * @param my_minute, the minute specified by the user
     * @param my_hour_marker, the location of the first instance matching the hour specified by the user
     * @param my_hour, the hour specified by the user
     * @param my_data, the array containing the norming point information
     * @return l, the location in the array containing the information of the norming point that is closest to the time specified by the user
     */
    public static int minute_finder(int my_minute,int my_hour_marker, int my_hour, int [][] my_data){
        boolean loop_continue = true;
        int l = my_hour_marker;
        while (loop_continue){
            if(my_minute == my_data[2][l] && my_hour == my_data[1][l]){
                loop_continue = false;
            }
            else{
                l = l+1;
            }
            if(l == 334){
                loop_continue = false;
                l = my_hour_marker;
            }
        }
        loop_continue = true;
        while (loop_continue){
            if( ((my_data[2][l] <= my_minute && my_hour == my_data[1][l]) && (my_data[2][l+1] > my_minute && my_hour == my_data[1][l+1])) || my_hour + 1 == my_data[1][l] ){
            //no exact time exists, use next available norming point 
                loop_continue=false;
            }
            else{
                l = l+1;
                if(l == 335){
                    loop_continue=false;
                }
            }
        }
        return l;
    }
    
   /**
    * station_time checks to see if the train is currently at the station entered by the user,
    * if it is at the station, it computes the remaining amount of time the train will be at the staion
    * 
    * @param my_station_key, the label of the station specified by the user
    * @param my_second_marker, the location of the norming point that is closest to the time entered by the user
    * @param my_minute, the minute entered by the user
    * @param my_second, the second entered by the user
    * @param my_data, the array containing the norming point information
    * @return dwell_time, the remaining time that the train is at the station, or -1000 to indicate the train is not at the station specified by the user
    */
    public static int station_time(int my_station_key, int my_second_marker, int my_minute, int my_second, int[][] my_data){
        int dwell_minute = 0, dwell_second = 0, dwell_time = 0;
        if(my_data[4][my_second_marker] == my_station_key){
            dwell_minute = my_data[2][my_second_marker] - my_minute;
            dwell_second = my_data[3][my_second_marker] - my_second;
            dwell_time = ((60*dwell_minute) + dwell_second);
            return dwell_time;
        }
        else{
            dwell_time = -1000;
        }
        return dwell_time;
    }
    
    /**
     * compute_median computes the median travel time of the three most recent travel times
     *
     * 
     * @param a, the hour associated with the first travel time
     * @param b, the hour associated with the second travel time
     * @param c, the hour associated with the third travel time
     * @return a,b, or c, whichever is the median travel hour
     */
    public static double compute_median(double a, double b, double c){
        if (a >= b && a >= c){
            if (b >= c){
                return b;
            }
            else{
                return c;
            }
        }
            if (b >= a && b >= c){
                if (a >= c){
                    return a;
                }
                else{
                    return c;
                }
            }
            if (c >= a && c >= b){
                if (a >= b){
                    return a;
                }
                else{
                    return b;
                }
            }
        return 0;
        }
    
    
    /*
     * compute_median is identical to compute_medianhour, except it takes in int minutes and int seconds
     * initially the travel time was computed by calculating the average, so hour needed to be a double in case we had a fraction
     * this meant I needed a second method to compute 
     * @param a
     * @param b
     * @param c
     * @return 
     */
    /*public static int compute_median(int a, int b, int c){
        if (a >= b && a >= c){
            if (b >= c){
                return b;
            }
            else{
                return c;
            }
        }
            if (b >= a && b >= c){
                if (a >= c){
                    return a;
                }
                else{
                    return c;
                }
            }
            if (c >= a && c >= b){
                if (a >= b){
                    return a;
                }
                else{
                    return b;
                }
            }
        return 0;
        }
    */
}
    
    






