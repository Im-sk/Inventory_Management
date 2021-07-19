package com.shop.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; 

public class ShopKeeper {
	
		private String name;
		private String company;
		private int price;
		private int stock;
		private static int id = 0;
		
		File file = new File("Inventory.txt");
		
		public ShopKeeper() throws IOException {
				//use tree set to add values of id and increment the last id
			
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			String nameNumberString;
			
			while (raf.getFilePointer() < raf.length()) {
			     nameNumberString = raf.readLine();
		           
		            List<String> lineSplit =  new ArrayList<>();
		            lineSplit = Arrays.asList(nameNumberString.split(","));
		            if(lineSplit.size() == 5) {
		            		id++;
		            	}
			 }
		}
		
	
	
	
	public void createFile() {
//		     
	        try {
				if (file.createNewFile()) {  
				           System.out.println("File " + file.getName() + " is created successfully.");
//				           System.out.println("Is file writeable?: " + f0.canWrite()); 
				}
//				           else {  
//				           System.out.println("File is already exist in the directory.");  
////				           System.out.println("Is file writeable?: " + f0.canWrite()); 
//				}
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
	}
	
	
	public void takeInput() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter product name :");
		name = scn.next();
		
		System.out.println("Enter product company Name :");
		company = scn.next();
		
		System.out.println("Enter product price :");
		price = scn.nextInt();
		
		System.out.println("Enter total product stock :");
		stock = scn.nextInt();
		
	}
	
	
	public void addProduct() throws IOException {
		
		createFile();
		takeInput();
		++id;	
		insert(id,name,price,stock,company);	
	}
	
	
	
	public void insert(int id,String name, int price, int stock,String company) throws IOException {
		// use this pointer to insert data
		if(!file.exists()) {
            file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
        boolean found = false;
        String nameNumberString,tempname;
        int tempid;
        
        
        while (raf.getFilePointer() < raf.length()) {
     	   
            // reading line from the file.
            nameNumberString = raf.readLine();

            // splitting the string to get name and
            // number
            String[] lineSplit
                = nameNumberString.split(",");

            // separating name and number.
            tempid = Integer.valueOf(lineSplit[0])   ;
            tempname =lineSplit[1];

            // if condition to find existence of record.
            if (tempname == name || tempid == id) {
                found = true;
                break;
            }
        }
        
       if(found == false) {
     	   nameNumberString = id +","+ name +","+ company +","+ price +","+ stock;
           raf.writeBytes(nameNumberString);
           raf.writeBytes(System.lineSeparator());
           System.out.println("Product added.");
           raf.close();
       }else {
     	  raf.close();   	  
          
     	  System.out.println("Product already exist.");
       }
    
	}
	
	
	public void removeProduct() throws IOException {
		if(!file.exists()) {
            file.createNewFile();
		}
		
		Scanner scn = new Scanner(System.in);
		int deleteid = 0;
		System.out.println("Enter the Product ID to be deleted : ");
		deleteid = scn.nextInt();
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		String nameNumberString;
        int tempid = 0;
		boolean found = false;
		
        while (raf.getFilePointer() < raf.length()) {
      	   
            
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
//          String[] lineSplit = nameNumberString.split(",");

            if(lineSplit.size() == 5) {
            
            	tempid = Integer.parseInt(lineSplit.get(0))   ;            	          
            	}
            
            if(tempid == deleteid) {
            		found = true;
            		break;
            	}
            
        	}
        	
        	if(found == true) {
        		
        		 File tmpFile = new File("temp.txt");
        		 RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        		 raf.seek(0);
        		 
        		 while (raf.getFilePointer() < raf.length()) {
        			 nameNumberString = raf.readLine();
        			 List<String> lineSplit =  new ArrayList<>();
        	         lineSplit = Arrays.asList(nameNumberString.split(","));
        			 
        	         if(lineSplit.size() == 5) {
        	        	 tempid = Integer.parseInt(lineSplit.get(0))   ;
        	         }
        	         
        	         if(tempid == deleteid) {
        	        	 continue;
        	         }
        	         tmpraf.writeBytes(nameNumberString);
        	         tmpraf.writeBytes(System.lineSeparator());
        	         
        		 }
        		 
        		 raf.seek(0);
                 tmpraf.seek(0);
                 while (tmpraf.getFilePointer()< tmpraf.length()) {
                      raf.writeBytes(tmpraf.readLine());
                      raf.writeBytes(System.lineSeparator());
                  }
   
                  // Set the length of the original file to that of temporary.
                  raf.setLength(tmpraf.length());
 
                  // Closing the resources.
                  tmpraf.close();
                  raf.close();
                  tmpFile.delete();
                  System.out.println("Product deleted.");
        	}else {
        		 System.out.println("Product does not exist.");
        	}
  	}
	
	
	public void listAllProduct() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");       
        String nameNumberString,tempname,tempcompany;
        int tempid,tempprice,tempstock;
        		
	     
        while (raf.getFilePointer() < raf.length()) {
     	   
           
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
//          String[] lineSplit = nameNumberString.split(",");

            if(lineSplit.size() == 5) {
            
            	tempid = Integer.parseInt(lineSplit.get(0))   ;
            	tempname =lineSplit.get(1);
            	tempcompany =lineSplit.get(2);
            	tempprice = Integer.parseInt(lineSplit.get(3))   ;
            	tempstock = Integer.parseInt(lineSplit.get(4))   ;
            	
                System.out.println(
                        "ID: " + tempid + "\n"
                       +"Name: " +tempname  + "\n"
                        +"Company Name: " +tempcompany  + "\n"
                       +"Price: " +tempprice  + "\n"
                       +"Total Stock: " +tempstock  + "\n"
                       );
            	}
            
        	}
		
        raf.close();
		}
	
	
	public void searchProduct() throws IOException {

		Scanner scn = new Scanner(System.in); 
		System.out.println("Enter the name to search for : ");
		
		String n = scn.nextLine();
		RandomAccessFile raf = new RandomAccessFile(file, "rw");       
        String nameNumberString,tempname,tempcompany;
        int tempid,tempprice,tempstock;
        		
	     
        while (raf.getFilePointer() < raf.length()) {
     	   
           
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));

            if(lineSplit.size() == 5) {
            
            	tempid = Integer.parseInt(lineSplit.get(0))   ;
            	tempname =lineSplit.get(1);
            	tempcompany =lineSplit.get(2);
            	tempprice = Integer.parseInt(lineSplit.get(3))   ;
            	tempstock = Integer.parseInt(lineSplit.get(4))   ;
            	
            	if(tempname.equals(n)) {
                System.out.println(
                        "ID: " + tempid + "\n"
                       +"Name: " +tempname  + "\n"
                        +"Company Name: " +tempcompany  + "\n"
                       +"Price: " +tempprice  + "\n"
                       +"Total Stock: " +tempstock  + "\n"
                       );
            	}
            }
        	}
		
        raf.close();
		
			
	}
	
	
	public void editProduct() throws IOException {

		if(!file.exists()) {
            file.createNewFile();
		}
		
		Scanner scn = new Scanner(System.in);
		int updateid = 0;
		System.out.println("Enter the product ID to be updated : ");
		updateid = scn.nextInt();
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		String nameNumberString,tempname,tempcompany;
        int tempid = 0,tempprice,tempstock;
		boolean found = false;
		
		 
	        
		
        while (raf.getFilePointer() < raf.length()) {
      	   
            
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
//          String[] lineSplit = nameNumberString.split(",");

            if(lineSplit.size() == 5) {
            
            	tempid = Integer.parseInt(lineSplit.get(0))   ;            	          
            	}
            
            if(tempid == updateid) {
            		found = true;
            		break;
            	}
            
        	}
        	
        	if(found == true) {
        		
        		 File tmpFile = new File("temp.txt");
        		 RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        		 raf.seek(0);
        		 
        		 while (raf.getFilePointer() < raf.length()) {
        			 nameNumberString = raf.readLine();
        			 List<String> lineSplit =  new ArrayList<>();
        	         lineSplit = Arrays.asList(nameNumberString.split(","));
        			 
        	         if(lineSplit.size() == 5) {
        	        	 tempid = Integer.parseInt(lineSplit.get(0))   ;
        	         }
        	         
        	         if(tempid == updateid) {
        	        	 // code to update record        	        	
        	     		System.out.println("Enter name :");
        	    		tempname = scn.next();
        	    		
        	    		System.out.println("Enter Company Name :");
        	    		tempcompany = scn.next();
        	    		
        	    		System.out.println("Enter price :");
        	    		tempprice = scn.nextInt();
        	    		
        	    		System.out.println("Enter stock :");
        	    		tempstock = scn.nextInt();	 
        	        	nameNumberString = tempid +","+ tempname +","+ tempcompany +","+ tempprice +","+ tempstock;
        	        	 
        	         }
        	         
        	         tmpraf.writeBytes(nameNumberString);
        	         tmpraf.writeBytes(System.lineSeparator());
        	         
        		 }
        		 
        		 raf.seek(0);
                 tmpraf.seek(0);
                 while (tmpraf.getFilePointer() < tmpraf.length()) {
                      raf.writeBytes(tmpraf.readLine());
                      raf.writeBytes(System.lineSeparator());
                  }
   
                  // Set the length of the original file
                  // to that of temporary.
                  raf.setLength(tmpraf.length());
 
                  // Closing the resources.
                  tmpraf.close();
//                  raf.close();
                  tmpFile.delete();
                  System.out.println(" Record Updated. ");
        	}else {
        		 System.out.println(" Input ID does not exists. ");
        	}
       	
        	
        		
		}
   

}
