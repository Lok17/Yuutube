package yuutube;

import java.util.Scanner;
import java.sql.*;  

public class Ytbmain {
    public static Scanner input = new Scanner(System.in);
    public static DBSconnect connection = new DBSconnect();
    public static User user = new User("", "Wing Yee", "wyee@gmail.com", "baconbanana");
    public static Video vid = new Video("1", "Dumb Ways To Die", "4",0,0,0, " ");    
    private static boolean conditionA = true;
    
    
	public static void main(String[] args) throws SQLException{
		Scanner sc = new Scanner(System.in);
		boolean condition0 = true;
		program: {
			String id = null;
			String name = null;
			String email = null;
			while (condition0) {
				boolean condition1 = true;
				System.out.println("Welcome to Yuutube");
				System.out.println("*Trending display method to be completed*\n");
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
									"Please select:\n1-upload video\n2-search video\n3-watch video\n4-show top 5 trending\n5-return last menu");
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
								GetTrending trending = new GetTrending();
                                                                trending.getTop5Trending();   
							} else if (videoselect.equals("5")) {
								break;
							} else {
								System.out.println("Invalid Selection!");
							}

						}
					} else if (select.equals("2")) {
						System.out.println("*manage account method to be completed*");// manage account
                                                //please create user
                                                    while(conditionA){
                                                        //insert user obj
                                                        mainMenu();
                                                    }
					} else if (select.equals("3")) {
						condition2 = false;
					}
                                        
                                        else {
                                            System.out.println("Invalid selection");
                                        }
				}
			}
		}
	}
        
        
        public static void mainMenu(){
        
        System.out.println("Hi, " + user.getName() + "!\n");
        System.out.println("1-Manage Account");
        System.out.println("2-Sample Video");
        System.out.println("3-Back to account menu");
        System.out.println("4-Back to operation menu");
        int c = input.nextInt();
        if(c == 1){
            showAccountMenu();
        }
        else if (c == 2){
            showSampleVideo("yes");
        }
        else if(c == 3){
            mainMenu();
        }
        else if (c == 4) {
            conditionA = false;
        }
        else {
            System.out.println("Invalid selection!");
        }
    }
    public static void showSampleVideo(String newVisit){
        if (newVisit.equals("yes")){
            vid.upviewCount();
            connection.updateviewCount(vid.getView_count(), vid.getVideo_id());
        }
        System.out.println("1-Like?");
        System.out.println("2-Dislike?");
        System.out.println("0-Go back to Main Menu");
        int d = input.nextInt();
        if (d == 1){
            vid.upLike();
            connection.updateLike(vid.getLike_count(), vid.getVideo_id());
            showSampleVideo("no");
        }
        else if (d == 2){
            vid.upDislike();
            connection.updateDislike(vid.getDislike_count(), vid.getVideo_id());
            showSampleVideo("no");
        }
        else if(d == 0){
            mainMenu();
        }
        else {
            System.out.println("Invalid selection");
        }
    }
    
    public static void showAccountMenu(){
        System.out.println("Hi, " +user.getName()+ "! What would you like to do today?");
        System.out.println("1. Change Email");
        System.out.println("2. Change Password");
        System.out.println("3. Close Account");
        System.out.println("0. Cancel");
        int a = input.nextInt();
        input.nextLine();
        
        if(a == 1){
            
            System.out.println("Please type new email : ");
            String newEmail = input.nextLine();
            user.setEmail(newEmail);//update in obj
            connection.updateEmail(newEmail, user.getUserID());
            System.out.println("Complete! Email has been successfully changed to " + user.getEmail());
            showAccountMenu();
        }
        
        else if (a == 2){
            System.out.println("Please type in new password : ");
            String newPassword = input.nextLine();
            user.setPassword(newPassword);
            connection.updatePassword(newPassword, user.getUserID());
            //DBSconnect set pw in table
            System.out.println("Complete! New password is " + user.getPassword());
            showAccountMenu();
        }
        
        else if (a == 3){
            System.out.println("Are you sure you want to close this account?");
            System.out.println("1. Yes \n2. No");
            int b = input.nextInt();
            if (b == 1){
                //delete acc
                connection.deleteUser(user.getUserID());     
                //redirect to login page
                //LogIn();
            }
            else
                showAccountMenu();
        }
            
        else if (a == 0)
            mainMenu();
        
        else {
            System.out.println("Invalid selection");
        }
        
        
    }}
