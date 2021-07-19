package com.shop.user;

import com.shop.domain.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int num,userchoice,shopchoice,customerchoice;	
		
		int cnum,snum;
		
		do{
			System.out.println("*****Welcome***** \nSelect the User :\n1.Customer\n2.Shopkeeper\n3.Exit");
			num = sc.nextInt();
		switch(num) {
		
		case 1: //System.out.println("create customer object");
				Customer cu = new Customer();
				do {
					System.out.println("Customer Menu : \n1.List \n2.Search \n3.Buy");
					
					cnum = sc.nextInt();
					switch(cnum) {
					case 1:cu.listProduct();
						   break;
					case 2: cu.searchProduct();
						   break;
					case 3:cu.buyProduct();
						   break;
						   
					default: System.out.println("Incorrect Choice !");
							
					
					}
					
					System.out.println("Enter 1 to continue, 0 for main menu ");
					
					customerchoice = sc.nextInt();					
				}while(customerchoice == 1);
				
			    break;
		case 2: //System.out.println("create shopkeeper object");
				ShopKeeper sh = new ShopKeeper();
				do {
					System.out.println("Shopkeeper Menu : \n1.Add \n2.Remove \n3.List \n4.Search \n5. Edit");
					
					//switch case
					snum = sc.nextInt();
					switch(snum) {
					case 1:sh.addProduct();
						   break;
					case 2:sh.removeProduct();
						   break;
					case 3:sh.listAllProduct();
						   break;
					case 4:sh.searchProduct();
						   break;
					case 5:sh.editProduct();
						   break;
						   
					default: System.out.println("Incorrect Choice !");
					}
					System.out.println("Want to continue 1/0");
					shopchoice = sc.nextInt();					
				}while(shopchoice == 1);
			    break;
		case 3: 
				System.out.println("*****Good Bye*****");
				System.exit(0);
				break;
		default: System.out.println("Incorrect choice");
			
		}
		
	
		}while(1 == 1);
		
//		sc.close();
	}



}
