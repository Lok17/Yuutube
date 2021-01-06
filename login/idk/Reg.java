package login;

import java.util.Scanner;

public class Reg {
	private boolean done = false;
	public Reg() {
		String email = null,name = null,password = null;
		boolean emailcheck=true, namecheck=true,passwordcheck=true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter email, name and password to register");
		
		while(emailcheck) {
			System.out.print("E-mail: ");
			email=sc.nextLine();
			
			if(email=="") {
				System.out.println("Please enter your email!");
			}
			else if (!Legit.check(email)) {
				System.out.println("Invalid address format! Please enter the right email!");
				}
				

			else {
				if(MySQL.emailExist(email)) {
					System.out.println("This email has been registered!");
				} else if(email.length()>45) {
					System.out.println("Name length should be less than 45");
				}
				else break;
			}	
		
		}
		while(namecheck) {
			System.out.print("Name: ");
			name=sc.nextLine();
			if(name.length()>40||name.length()==0) {
				System.out.println("Name length should be 1 - 40");
			}
			else break;
		}
		while(passwordcheck) {
			System.out.print("Password: ");
			password=sc.nextLine();
			if(password.length()<6||password.length()>20) {
				System.out.println("Password length should be between 6 - 20");
			}
			else break;
		}
		System.out.println("Wait a minute, we are sending you the confirmation code...");
		int code = TwoFA.Auth(email);
		System.out.println("We have sent the code, please check your email.");
		System.out.print("Code: ");
		if(sc.nextLine().equals(Integer.toString(code))) {
			MySQL.registerUser(email, name, password);
			System.out.println("Congratulations! Register success!");
		}
		else System.out.println("Sorry! Wrong Code!");
		System.out.println("Enter e to enter previous menu; press enter to try again");
		if(sc.nextLine().equalsIgnoreCase("e")) {
			new HomePage();
		}
	}
}
