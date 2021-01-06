package login;

import java.util.Scanner;

public class GeneralMenu {
	public GeneralMenu(String id) {
		while(true) {
			Scanner sc = new Scanner(System.in);
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
				new HomePage();
			}
		}
		
	}
}
