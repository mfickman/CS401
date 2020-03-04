/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moreclasspractice;

/**
 *
 * @author markfickman
 */
public class food {
    private String name;
    private int amount;
    private double price;
    
    
    //set methods
    public void setName(String s) throws NullPointerException{
        if (s == null){
            throw new NullPointerException("Name can't be blank");
        }
        this.name = new String(s);
    }
    
    public void setAmount(int a) throws IllegalArgumentException{
        if (a <= 0){
            throw new IllegalArgumentException("The amount must be positive");
        }
        this.amount = a;
        }
    
    public void setPrice(double p) throws IllegalArgumentException{
        if(p <= 0){
            throw new IllegalArgumentException("The price must be positive");
        }
        this.price = p;
    }
    
    //get methods
    public String getName(){
        return new String (name);
    }
    
    public int getAmount(){
        return amount;
    }
    
    public double getPrice(){
        return price;
    }
    
    //initalizer constructor, uses the set methods
    public food(String myName, int myAmount, double myPrice){
        setName(myName);
        setAmount(myAmount);
        setPrice(myPrice);
    }
    
    //no args constructor
    public food(){
        name = "Food name";
        amount = 5034;
        price = 1.23;
    }
    
    //copy constructor
    public food(food f){
        name = f.name;
        amount = f.amount;
        price = f.price;
    }
}

