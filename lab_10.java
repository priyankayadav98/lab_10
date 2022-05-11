import java.sql.Time;
import java.util.*;
import java.lang.*;
import java.util.List;
import java.util.Scanner;

class Lab_10{
    public static void main(String args[]) throws InterruptedException {

          // implementing  creating thread using runnable interface and setting priority.
        customer cust1 = new customer(16,"Divya","1234567890",12);
        customer cust2 = new customer(17,"kumar","5234567890",13);
        Thread t2 = new Thread(cust1);
        Thread t3 = new Thread(cust2);

        System.out.println("Priority of the thread t2 is : " + t2.getPriority());  
        System.out.println("Priority of the thread t3 is : " + t3.getPriority());  
        t2.setPriority(6);  
        System.out.println("Priority of the thread thread 2 is : " + t2.getPriority());  
        t2.start();
        // implementing notify() and notifyAll() 
        login l = new login();
        l.start();
        // System.out.println("total attempts:"+user3.attempts);
        synchronized(l){
        l.wait(); 
            System.out.println("Actual total attempts:" +l.attempts);
    }
    //synchronized thread implementation

    // Company obj = new Company();
    // customer cust3 = new customer(obj);
    // customer cust4 = new customer(obj);
    // Thread t4 = new Thread(cust3);
    // Thread t5 = new Thread(cust4);
    // t4.start();
    // t5.start();
    
       // implementing join() method
        drug drug1 = new drug(1, "Paracetamol", "Antibody drug.", 10);
        drug1.start();
        try {
           drug1.join(); // calling join() method
         } catch(Exception e) {
            System.out.println(e);
         }
        
        drug drug2 = new drug(2, "Sinerest", "Antibody drug(fever).", 20, 90);
        //  drug2.generate_bill();
        drug2.start();

        //creating thread through extends threads.
        Branches bt = new Branches(1,"piu",1998,2,500,200);
        Thread t = new Thread(bt);
        t.start();
       
    }
}
class login extends Thread
 {
    public String userName;
    private String password;
    public String type;
    public int i;
    public int attempts = 0;

    

    public login(){
        System.out.println("Enter Username : ");
        Scanner sc=new Scanner(System.in);
        this.userName = sc.next();
        setUserName(this.userName);
        System.out.println("Create Password : ");
        this.password = sc.next();
        setPassword(this.password);
        System.out.println("Enter Type(User or Admin) : ");
        this.type = sc.next();
        setType(this.type);
        sc.close();
    }

	  public login(String userName, String password, String type,int attempts)
    {
      this.userName=userName;
      this.password=password;
      this.type=type;
	}
    public  void run(){
     synchronized(this){
         for(i = 1; i<3; i++){
             attempts = attempts + 1;
         }
        this.notify();
        //will notify all the waiting threads
        // this.notifyAll();
     }
    }
    public void setUserName(String userName){
		  this.userName=userName;
	  }
    public void setPassword(String password){
      this.password=password;
    }
    public void setType(String type){
      this.type=type;
    }

    public String getUserName(){
      return userName;
    }
    public String getPassword(){
      return password;
    }
    public String getType(){
      return type;
    }
}

class drug extends Thread
{
    public int d_id;
    public String name;
    public String description;
    public int quantity;
    public float cost;
    public int drug_rating;
  
    

    static String drug_type1;
    static String drug_type2;
    static String drug_type3;

    static{
        drug_type1 = "minors";
        drug_type2 = "adults";
        drug_type3 = "seniors";
    }

    //constructor overloading and chaining
    
    public drug(int d_id, String name,String description,int quantity)
    {
        this.d_id=d_id;
        this.name=name;
        this.description=description;
        this.quantity=quantity;
    }
    public drug(int d_id, String name,String description,int quantity, float cost)
    {
        this(d_id, name, description, quantity);
        this.cost = cost;
    }
    public drug(drug obj)
    {
        this(obj.d_id, obj.name, obj.description, obj.quantity, obj.cost);
    }

    public static void printDrugTypes()
    {
        System.out.println("Drug Type 1: "+drug_type1);
        System.out.println("Drug Type 2: "+drug_type2);
        System.out.println("Drug Type 3: "+drug_type3);
    }
    public static void printDrugTypes(String drug_type)
    {
        if(drug_type == "type1")
        {
            System.out.println("Drug Type 1: "+drug_type1);
        }
        else if(drug_type == "type2")
        {
            System.out.println("Drug Type 2: "+drug_type2);
        }
        else if(drug_type == "type3")
        {
            System.out.println("Drug Type 3: "+drug_type3);
        }
        else
        {
            System.out.println("Drug type not found");
        }
    }

    static class DrugCountry
    {
        void IndiaDrug()
        {
            System.out.println("Drug manufactured from India.");
        }
    }

    public void run(){
        float new_price;
        Scanner sc=new Scanner(System.in);
        new_price = sc.nextFloat();
        this.cost=new_price;
        sc.close();
        float total_cost;
        total_cost = this.cost * this.quantity;
        System.out.println("This is the thread calculating on new price");
        System.out.println("Amount paid: "+total_cost);
      
    }

    private float calculate_cost(int d_id){
        float total_cost;
        total_cost = this.cost * this.quantity;
        System.out.println("Amount paid: "+ this.cost);
        return total_cost;
    }

    public int update_quantity(int purchased){
        int new_quantity = this.quantity;
        new_quantity -= purchased;
        return new_quantity;
    }

    public void generate_bill(){
        float amount = this.calculate_cost(this.d_id);
        System.out.println("\n");
        System.out.println("Drug id: "+this.d_id);
        System.out.println("Drug name: "+this.name);
        System.out.println("Quantity purchased: "+this.quantity);
        System.out.println("Amount paid: "+ amount);
    }
}

class customer implements Runnable {
    public int cust_id;
    public String cust_name;
    public String phone_no;
    public int presc_id;
    // Company company;

    // customer(Company company){
    //     this.company = company;
    // }

    public customer(int cust_id, String cust_name, String phone_no, int presc_id)
    {
        this.cust_id = cust_id;
        this.cust_name= cust_name;
        this.phone_no = phone_no;
        this.presc_id = presc_id;
    }
   

    //function overloading
    public void search_drug(String drug_name)
    {
        drug d = new drug(2,"dolo","painkiller",2,90);
        if(d.name == drug_name){
            System.out.println("You searched for drug name: "+drug_name+". dose  = "+d.cost);
        }
        else{
            System.out.println("no drug with that name");
        }
    }
//datatype overloading

    public void search_drug(int drug_id)
    {
        drug d = new drug(2,"dolo","painkiller",2,90);
        if(d.d_id == drug_id){
            System.out.println("You searched for drug id: "+drug_id+". dose  = "+d.cost);
        }
        else{
            System.out.println("no drug with that name");
        }
    }
    public void search_drug(int drug_id, String drug_name)
    {
        drug d = new drug(2,"dolo","painkiller",2,90);
        if(d.name == drug_name && d.d_id == drug_id){
            System.out.println("You searched for drug id: "+drug_id+" and drug name: "+drug_name+". dose  = "+d.cost);
        }
        else{
            System.out.println("no drug with that name");
        }
    }

//number of arguments
    public void search_doctor(String name, String desc){
        //object by reference variable
        drug d = new drug(2,"dolo","painkiller",2,90);
        if(d.name == name && d.description == desc){
            System.out.println("You searched for : "+d.d_id+". dose  = "+d.quantity);
        }
        else{
            System.out.println("Doctor id NOT FOUND");
        }
    }

    public void order_drug(int drug_id){
        drug d = new drug(2,"dolo","painkiller",2,90);
        d.generate_bill();
    }

    public void rate_drug(int d_id, int rating){
        drug drug1 = new drug(2,"dolo","painkiller",2,90);
        if(drug1.d_id == d_id){
            drug1.drug_rating = rating;
        }
        else{
            System.out.println("Drug id NOT FOUND");
        }
        
    }
    public void run(){
      System.out.println("this is second thread running");
    //   company.generate_alert();

    }

  
}

class Branches implements Runnable{
    public int b_id;
    public String name ;
    public String address;
    public int year;
    public int times_visited;
    public float total_revenue;
    public float total_expense;

    
    public Branches(){}
    public Branches(int b_id, String name,int year,int times_visited, float total_revenue, float total_expense)
    {
        this.b_id= b_id;
        this.name = name;
        this.year = year;
        this.times_visited = times_visited;
        this.total_revenue = total_revenue;
        this.total_expense = total_expense;
    }

    public void visited(int min)
    {
        if(this.times_visited > min)
        {
            System.out.println("The Branch is visited by Enough: "+name);
        }
        else
        {
            System.out.println("The Branch is not Visited much: "+name);
        }
    }

    private float yearly_profit(int b_id){
        float profit;
        profit = (total_revenue-total_expense);
        return profit;
    }

    public void run(){
        float yearly_profit = this.yearly_profit(this.b_id);
        System.out.println("Branch id: "+this.b_id);
        System.out.println("Branch name: "+this.name);
        System.out.println("Year: "+this.year);
        // System.out.println("The Yearly profit is: "+ yearly_profit);
        try{Thread.sleep(10000);}
        catch(InterruptedException e)
        {System.out.println(e);
        }  
        System.out.println("The Yearly profit is: "+ yearly_profit);
    }
}
// class Company {
//     public int comp_id;
//     public String name;
//     public String address;
//     public int years = 5;
//     public int sales;
//     public double profit;
    

    

//     public Company(){
//         Scanner sc= new Scanner(System.in);
//         this.comp_id = sc.nextInt();
//         sc.close();
//     }

//     public Company(String name,String address){
//         this.name = name;
//         this.address = address;
//          System.out.println("Enter your sales:");
//          this.sales = sales;
//          Scanner sc = new Scanner(System.in);
//         this.sales = sc.nextInt();
      
//     }

  
//     synchronized public void generate_alert(){
//            System.out.println("The company id:"+this.comp_id);
//             if(this.sales > 0 && this.sales < 30 ){
//                 // System.out.println("Enter your sales:");
//                 profit = ((this.sales/30.0)* 100);
//                 System.out.println("Profit monthly:"+profit);
               
//             }
//             try
//             {
//                 Thread.sleep(400);
//             }
//             catch (Exception e)
//             {
//                 System.out.println(e);
//             }
            
            
//         } 

//  }






