package yuutube;

import java.util.Scanner;

public class ResetPassword {
	private boolean success = false;
	private String confirmCode = "";//
	private String userInputCode = null;
	private String newPassword = null;

	public ResetPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("To reset your password, please write your email");
		while (true) {
			System.out.print("Enter e-mail: ");
			String email = sc.nextLine();
			System.out.println("Wait a minute. We are sending you the code...");
			confirmCode = Integer.toString(TwoFA.ResetPassword(email));
			System.out.println("We have sent the confirmation code to you email, please check");
			
			System.out.print("Enter code: ");
			
			userInputCode = sc.nextLine();

			if (confirmCode.equals(userInputCode)) {
				success = true;
				// method to reset password
				System.out.print("Please enter your new password: ");
				newPassword = sc.nextLine();
				MySQL.resetPassword(email, newPassword);
				System.out.println("Congratulations! You have successfully reset your password.");
				break;
			} else {
				System.out.println("Wrong code! Please try again.");
			}
		}
	}

	public boolean isSuccess() {
		return success;
	}

}
