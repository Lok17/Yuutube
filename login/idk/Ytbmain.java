package login;

import java.util.Scanner;

public class Ytbmain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean condition0 = true;
		program: {
			String id = null;
			String name = null;
			String email = null;
			while (condition0) {
				boolean condition1 = true;
				System.out.println("Welcome to Yuutube");
				System.out.println("*Trending display method to be completed*");
				// trending method to be completed
				while (condition1) {
					System.out.println("To enjoy our service, please log in first.");
					System.out.println("Please select:");
					System.out.println("1-login\n2-register\n3-forget password\nE-end program");
					String select = sc.nextLine();
					if (select.equals("1")) {
						Login log = new Login();
						if (log.isIn() == true) {
							condition1 = false;
							id = log.getId();
							name = log.getName();
							email = log.getEmail();
							// can get user info here from here
//							System.out.println(id+name+email);
						}
					} else if (select.equals("2")) {
						Register reg = new Register();
						
					} else if (select.equals("3")) {
						ResetPassword resetpass = new ResetPassword();
						if (resetpass.equals(true)) {
						ResetPassword rst = new ResetPassword();
						}

					} else if (select.equalsIgnoreCase("E")) {
						System.out.println("Bye-Bye");
						break program;
					} else {
						System.out.println("Invalid selection, please try again");
					}
				}

//				System.out.println("Hello! " + username);

				boolean condition2 = true;
				while (condition2) {
					System.out.println("Please select your operation");
					System.out.println("1-video\n2-account\n3-log out");
					String select = sc.nextLine();

					boolean condition3 = true;
					if (select.equals("1")) {
						while (condition3) {
							System.out.println("video operation");
							System.out.println(
									"Please select:\n1-upload video\n2-search video\n3-watch video\n4-return last menu");
							String videoselect = sc.nextLine();
							if (videoselect.equals("1")) {
								System.out.println("*uploading method to be completed*");
								// upload method to be completed - upload and write data into file
							} else if (videoselect.equals("2")) {
								System.out.println("*search method to be completed*");// e.g.--ask search info and print
																						// search result along with
																						// video info
							} else if (videoselect.equals("3")) {
								System.out.println("*watch method to be completed*");
								// e.g.--ask input of video name if exist - print video info and play method
								// print user operation instruction
								// other user operation Like & Dislike video - Comment on video - Subscribe to a
								// user written here
							} else if (videoselect.equals("4")) {
								break;
							} else {
								System.out.println("Invalid Selection!");
							}

						}
					} else if (select.equals("2")) {
						System.out.println("*manage account method to be completed*");// manage account
					} else if (select.equals("3")) {
						condition2 = false;
					}
				}
			}
		}
	}
}
