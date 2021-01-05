package login;

import java.util.Scanner;

public class HomePage {
	String id;
	String name;
	String email;
	
	public HomePage(){
		System.out.println("Welcome to Yuutube");
		System.out.println("*Trending display method to be completed*");
		Scanner sc = new Scanner(System.in);
		// trending method to be completed
		while (true) {
			System.out.println("To enjoy our service, please log in first.");
			System.out.println("Please select:");
			System.out.println("1-login\n2-register\n3-forget password");
			String select = sc.nextLine();
			if (select.equals("1")) {
				Login log = new Login();
				if (log.isIn() == true) {
					id = log.getId();
					name = log.getName();
					email = log.getEmail();
					new GeneralMenu(id);
					break;
					// can get user info here from here
//					System.out.println(id+name+email);
				}
			} else if (select.equals("2")) {
				Reg reg = new Reg();
				
			} else if (select.equals("3")) {
				Rst resetpass = new Rst();
				if (resetpass.equals(true)) {
				ResetPassword rst = new ResetPassword();
				}

			} 
			else {
				System.out.println("Invalid selection, please try again");
			}
		}
	}

}
