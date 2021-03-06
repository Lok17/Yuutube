package yuutube;

import java.util.Arrays;
import java.util.Scanner;

public class Login {
	private String email;
	private String password;
	private String name;
	private String id;
        private String subscriberCount;
	private boolean in = false;
	Scanner sc = new Scanner(System.in);

	public Login() {
		System.out.print("Email: ");
		email = sc.nextLine();
		System.out.print("Password: ");
		password = sc.nextLine();
		// 2fa method
		// check password method
		if (MySQL.checkPassword(email, password)) {
			System.out.println("Wait a minute, we are sending you the confirmation code...");
			String code = TwoFA.ConfirmLogin(email);
			System.out.println("We have sent you the confirmation code, please check your email");
			System.out.print("Code: ");
			if (code.equals(sc.nextLine())) {
				System.out.println("Login success!");
				String[] s = MySQL.getUserInfo(email);
				id = s[0];
				name = s[1];
                                password = s[2];
                                subscriberCount = s[3];
                                email = s[4];
                                
				in = true;


				// name,id
			} else {
				System.out.println("Login failed. Please try again.");
			}

		} else {
			System.out.println("Sorry! Invalid login email or password.");
		}
		

	}

	public boolean isIn() {
		return in;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

        public String getPassword() {
            return password;
        }

        public String getSubscriberCount() {
            return subscriberCount;
        }
        
        

}