package com.shop.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer {
	
	
	
	public void listProduct() throws IOException {
		File file = new File("Inventory.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "rw");       
        String nameNumberString,tempname,tempcompany;
        int tempprice;
        		
	     
        while (raf.getFilePointer() < raf.length()) {
     	   
           
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
//          String[] lineSplit = nameNumberString.split(",");

            if(lineSplit.size() == 5) {
            
            	tempname =lineSplit.get(1);
            	tempcompany =lineSplit.get(2);
            	tempprice = Integer.parseInt(lineSplit.get(3));
            	System.out.println(
                       "Name: " +tempname  + "\n"
                       +"Company Name: " +tempcompany  + "\n"
                       +"Price: " +tempprice  + "\n"                      
                       );
            	}
            
        	}
		
        raf.close();
		
		
		}
		
	public void searchProduct() throws IOException {
		
		File file = new File("Inventory.txt");
		Scanner scn = new Scanner(System.in); 
		System.out.println("Enter the name to search for : ");		
		String n = scn.nextLine();
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");       
        String nameNumberString,tempname,tempcompany;
        int tempprice;
        		
	     
        while (raf.getFilePointer() < raf.length()) {
     	   
           
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));

            if(lineSplit.size() == 5) {
            
            	tempname =lineSplit.get(1);
//            	System.out.println(tempname);
            	tempcompany =lineSplit.get(2);
            	tempprice = Integer.parseInt(lineSplit.get(3))   ;
            	
            	if(tempname.equals(n)) {
                System.out.println(
                        "Name: " +tempname  + "\n"
                        +"Company Name: " +tempcompany  + "\n"
                        +"Price: " +tempprice  + "\n"
                       );
            	}
            }
        	}
		
        raf.close();
		}
		
	public void buyProduct() throws IOException {
		File file = new File("Order.txt"); 
			if (file.createNewFile()) {  
	           System.out.println("File " + file.getName() + " is created successfully.");
			}
		Scanner sc = new Scanner(System.in);	
		int productid;
		String productname;
		String creditcard;
		String cvv;
		
		System.out.println("Enter product ID :");
		productid = sc.nextInt();
		
		System.out.println("Enter product name :");
		productname = sc.next();
		
		System.out.println("Enter 16 digit card number :");
		creditcard = sc.next();
		
		Pattern pattern = Pattern.compile("[+-]?[0-9]+");
		
		while(creditcard.length() != 16 || !pattern.matcher(creditcard).matches()) {
			System.out.println("Enter a valid 16 digit card number :");
			creditcard = sc.next();
		}
		
		System.out.println("Enter 3 digit cvv number :");
		cvv = sc.next();
		
		while(cvv.length() != 3 || !pattern.matcher(cvv).matches()) {
			System.out.println("Enter a valid cvv number :");
			cvv = sc.next();
		}
		
		File fileInvent = new File("Inventory.txt"); 
		if(!fileInvent.exists()) {
            file.createNewFile();
		}
		
		
		RandomAccessFile raf = new RandomAccessFile(fileInvent, "rw");
        boolean found = false;
        String nameNumberString,tempname = null;
        int tempid = 0,tempstock=0;
        
        while (raf.getFilePointer() < raf.length()) {
        	
        	nameNumberString = raf.readLine();
        	
        	List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
        	
            if(lineSplit.size() == 5) {
            	tempid = Integer.parseInt(lineSplit.get(0))   ;
            	tempname = lineSplit.get(1);   
            	tempstock = Integer.parseInt(lineSplit.get(4))   ;
            	}
        
            
            if (tempname.equals(productname) && productid == tempid && tempstock >0 ) {
                found = true;
                break;
            }
               
        }
        
        String productString;
        if(found == true) {
        	RandomAccessFile raforder = new RandomAccessFile(file, "rw");
        	productString = productid +","+ productname +","+ creditcard +","+ cvv ;
            raforder.writeBytes(productString);
            raforder.writeBytes(System.lineSeparator());
            System.out.println("Order placed.");
            updateInventory(productid,productname);
            raforder.close();
        }else {
        	System.out.println("Product details did not match or stock is empty");
        	
        }
        
      
			
		}
	public void updateInventory(int id, String name) throws IOException {
		
		File file = new File("Inventory.txt");
		if(!file.exists()) {
            file.createNewFile();
		}
		
		Scanner scn = new Scanner(System.in);
		int updateid = id;
		String updatename = name;
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		String nameNumberString,tempname = null,tempcompany = null;
        int tempid = 0,tempprice = 0,tempstock = 0;
		boolean found = false;
		
		 
	        
		
        while (raf.getFilePointer() < raf.length()) {
      	   
            
            nameNumberString = raf.readLine();
           
            List<String> lineSplit =  new ArrayList<>();
            lineSplit = Arrays.asList(nameNumberString.split(","));
//          String[] lineSplit = nameNumberString.split(",");

            if(lineSplit.size() == 5) {
            
            	tempid = Integer.parseInt(lineSplit.get(0))   ;  
            	tempname = lineSplit.get(1);
            	}
            
            if(tempid == updateid && name.equals(tempname)) {
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
        	            	tempname =lineSplit.get(1);
        	            	tempcompany =lineSplit.get(2);
        	            	tempprice = Integer.parseInt(lineSplit.get(3))   ;
        	            	tempstock = Integer.parseInt(lineSplit.get(4))   ;
        	         }
        	         
        	         if(tempid == updateid && tempname.equals(updatename)) {
        	        	        	        	
        	        	tempstock = tempstock -1;
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
                  System.out.println(" Inventory Updated. ");
        	}
        	
        		
		}
		

}
