package yuutube;
  
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import static yuutube.SearchFinal.*;

public class Ytbmain {
    private static boolean conditionA = true;
    private static boolean condition2 = true;
    private static boolean condition3 = true;
    
    public static int userInput = 0;
    public static Scanner input = new Scanner(System.in);
    public static DBSconnect connection = new DBSconnect();
    
    public static int subscriberCount;
    public static User user ;
    public static String userId, userName, userEmail, userPassword;
    public static Video vid ;
    public static String videoId,video_userId,videoAddress,videoName;
    public static int viewCount,likeCount,dislikeCount;
    public static String videoselect;
    public final static String destPath = "C:\\\\\\Users\\\\\\Lok\\\\Videos\\\\\\VideoStoring\\\\\\";
    
    
	public static void main(String[] args) throws SQLException{
		Scanner sc = new Scanner(System.in);
		boolean condition0 = true;
		program: {
			while (condition0) {
				boolean condition1 = true;
				System.out.println("\nWelcome to Yuutube\n");
				GetTrending trending = new GetTrending();
                                trending.getTop5Trending();   
				// trending method to be completed
				while (condition1) {
                                        condition2 = true;
					System.out.println("To enjoy our service, please log in first.");
					System.out.println("Please select:");
					System.out.println("1-login\n2-register\n3-forget password\nE-end program");
                                        System.out.print("Enter Your Command: ");
					String select = sc.nextLine();
					if (select.equals("1")) {
						Login log = new Login();
						if (log.isIn() == true) {
							condition1 = false;
							userId = log.getId();
							userName = log.getName();
                                                        userPassword = log.getPassword();
                                                        subscriberCount = Integer.parseInt(log.getSubscriberCount());
							userEmail = log.getEmail();
                                                        user = new User(userId, userName, userEmail, userPassword,subscriberCount);
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

				while (condition2) {
					System.out.println("\nPlease select your operation");
					System.out.println("1-video\n2-account\n3-log out");
                                        System.out.print("Enter Your Command: ");
					String select = sc.nextLine();
                                        
					boolean condition3 = true;
					if (select.equals("1")) {
						while (condition3) {
							System.out.println("\nvideo operation");
							System.out.println("Please select:\n1-upload video\n2-search video\n3-search channel\n4-watch video\n5-show top 5 trending\n6-delete video\n0-return last menu");
                                                        System.out.print("Enter Your Command: ");
							videoselect = sc.nextLine();
							if (videoselect.equals("1")) {
                                                                System.out.println("Enter 'exit' to exit");
								System.out.print("Please enter file path: ");
                                                                String userFilePath = sc.nextLine();
                                                                if (userFilePath.equalsIgnoreCase("exit")){
                                                                    continue;
                                                                }
                                                                System.out.print("Please enter video title: "); 
                                                                String videoTitle = sc.nextLine();
                                                                if (videoTitle.equalsIgnoreCase("exit")) {
                                                                    continue;
                                                                }
                                                                Path vSrc = Paths.get(userFilePath);
                                                                String[] splitter = userFilePath.split("\\\\");
                                                                String vDestPath = destPath+splitter[splitter.length-1];
                                                                Path vDest = Paths.get(vDestPath);
                                                                VideoOperation.upload(vSrc, vDest, videoTitle,vDestPath,user.getUserID()); 
							} else if (videoselect.equals("2")) {
								
                                                                System.out.print("Enter to search : ");
                                                                    String video = sc.nextLine();
                                                                    SearchFinal.search(1, video);
                                                                
                                                        } else if (videoselect.equals("3")) {     
                                                                System.out.print("Enter to search : ");
                                                                    String channel = sc.nextLine();
                                                                    SearchFinal.search(2, channel);

																																	// video info
							} else if (videoselect.equals("4")) {
							
                                                        stop:{	
                                                                System.out.println("\nEnter 'exit' to quit\n");
                                                                System.out.print("Enter Video Name: ");
                                                                String input = sc.nextLine();
                                                                if (input.equalsIgnoreCase("exit")){
                                                                    break stop;
                                                                }
                                                                SearchFinal.search(1,input);
                                                                
                                                                
                                                                
                                                                System.out.print("\nEnter Video ID: ");
                                                                input = sc.nextLine();
                                                                if (input.equalsIgnoreCase("exit")){
                                                                    break stop;
                                                                }
                                                                
                                                                directSearchVideoID(input);
                                                                System.out.print("\nDo you want to play? [Y/N] : ");
                                                                String yn = sc.nextLine();
                                                                if (input.equalsIgnoreCase("exit")){
                                                                    break stop;
                                                                }
                                                                else if(yn.equalsIgnoreCase("Y")){
                                                                    VideoOperation.play(vid.getAddress());     
                                                                    VideoOperation.updateViewCount(vid.getView_count(), vid.getVideo_id());
                                                                    vid.upviewCount();
                                                                }
                                                                else if (yn.equalsIgnoreCase("N")){
                                                                    System.out.print("");
                                                                }
                                                                else{
                                                                    System.out.println("Invalid Operation");
                                                                }
								
                                                                showSampleVideo("Yes");
								
                                                        }    
                                                        } else if (videoselect.equals("5")) {
								trending = new GetTrending();
                                                                trending.getTop5Trending();   
                                                        }else if (videoselect.equals("6")){
                                                            stop:{	
                                                                System.out.println("\nEnter 'exit' to quit\n");
                                                                System.out.print("Enter Video Name: ");
                                                                String input = sc.nextLine();
                                                                if (input.equalsIgnoreCase("exit")){
                                                                    break stop;
                                                                }
                                                                SearchFinal.search(1,input);
                                                                
                                                                
                                                                
                                                                System.out.print("\nEnter Video ID: ");
                                                                input = sc.nextLine();
                                                                if (input.equalsIgnoreCase("exit")){
                                                                    break stop;
                                                                }
                                                                directSearchVideoID(input);
                                                            
                                                                if (!vid.getUser_id().equalsIgnoreCase(userId)){
                                                                    System.out.println("\nThis video is not belong to you, you are not allow to delete it!");
                                                                    break stop;
                                                                }
                                                                else if (vid.getUser_id().equalsIgnoreCase(userId)){
                                                                    VideoOperation.delete(vid.getAddress());
                                                                    VideoOperation.deleteEntry(vid.getVideo_name());
                                                                    System.out.println("Done");
                                                                }
                                                            }
							} else if (videoselect.equals("0")) {
								break;
							} else {
								System.out.println("Invalid Selection!");
							}

						}
					} else if (select.equals("2")) {
//						System.out.println("*manage account method to be completed*");// manage account
                                                //please create user
                                                    conditionA = true;
                                                    while(conditionA){
                                                        showAccountMenu();
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
     
    public static void showSampleVideo(String newVisit){
        int x = connection.checkInteraction(user.getUserID(), vid.getVideo_id());
        if (newVisit.equals("yes")){
            vid.upviewCount();
            connection.updateviewCount(vid.getView_count(), vid.getVideo_id());  
        }
        //x is for the likes/dislikes of the user with the video stored in the table
        //-1 means there is no interaction, 1 means like and 0 indicates dislike
        if (x == -1){
            System.out.println("\n1-Like?");
            System.out.println("2-Dislike?");
            System.out.println("3-Leave a comment.");
            System.out.println("4-View comments.");
            System.out.println("5-Visit video owner channel.");
            System.out.println("6-Play video");
            System.out.println("0-Go back to last menu.");
            System.out.print("Enter Your Command: ");
            userInput = input.nextInt();
            //check if the user has interacted with the video before
            //if no, does the user want to like or dislike?
            if (userInput == 1){
                vid.upLike();
                connection.updateLike(vid.getLike_count(), vid.getVideo_id(), user.getUserID(), 1);
                showSampleVideo("no");
            }
            else if (userInput == 2){
                vid.upDislike();
                connection.updateDislike(vid.getDislike_count(), vid.getVideo_id(), user.getUserID(), 0);
                showSampleVideo("no");
            }
            else if (userInput == 3){
                inputComment();
            }
            else if (userInput == 4){
                viewComment();  
            }
            else if (userInput == 5){
                visitOwner();
            }
            else if (userInput == 6){
                VideoOperation.play(vid.getAddress()); 
                VideoOperation.updateViewCount(vid.getView_count(), vid.getVideo_id());
                vid.upviewCount();
            }
            if(userInput == 0){
                if (videoselect.equalsIgnoreCase("4")){
                    condition3 = false;
                }
            }
        }        
        else if (x == 1){                
            System.out.println("\nYou have already liked the video. ");
            System.out.println("1-Remove like?");
            System.out.println("2-Dislike?");
            System.out.println("3-Leave a comment.");
            System.out.println("4-View comments.");
            System.out.println("5-Visit video owner channel.");
            System.out.println("6-Play video");
            System.out.println("0-Go back to last menu.");
            System.out.print("Enter Your Command: ");
            userInput = input.nextInt();
            if (userInput == 1){
                vid.downLike();
                connection.removeLike(vid.getLike_count(), vid.getVideo_id(), user.getUserID());
                showSampleVideo("no");
            }
            else if (userInput == 2){
                vid.downLike();
                connection.removeLike(vid.getLike_count(), vid.getVideo_id(), user.getUserID());
                vid.upDislike();
                connection.updateDislike(vid.getDislike_count(), vid.getVideo_id(), user.getUserID(), 0);
                showSampleVideo("no");
            }
            else if (userInput == 3){
                inputComment();
            }
            else if (userInput == 4){
            viewComment();  
            }
            else if (userInput == 5){
            visitOwner();
            }
            else if (userInput == 6){
            VideoOperation.play(vid.getAddress()); 
            VideoOperation.updateViewCount(vid.getView_count(), vid.getVideo_id());
            vid.upviewCount();
            }
            else if(userInput == 0){
                if (videoselect.equalsIgnoreCase("4")){
                    condition3 = false;
                }   
            }
        }
        else if (x == 0){
            System.out.println("\nYou have already disliked the video.");
            System.out.println("1-Remove dislike?");
            System.out.println("2-Like?"); 
            System.out.println("3-Leave a comment.");
            System.out.println("4-View comments.");
            System.out.println("5-Visit video owner channel.");
            System.out.println("6-Play video");
            System.out.println("0-Go back to lain menu.");
            System.out.print("Enter Your Command: ");
            userInput = input.nextInt();
            if (userInput == 1){
                vid.downDislike();
                connection.removeDislike(vid.getDislike_count(), vid.getVideo_id(), user.getUserID());
                showSampleVideo("no");
            }
            else if (userInput == 2){
                vid.downDislike();
                connection.removeDislike(vid.getDislike_count(), vid.getVideo_id(), user.getUserID());
                vid.upLike();
                connection.updateLike(vid.getLike_count(), vid.getVideo_id(), user.getUserID(), 1);
                showSampleVideo("no");
            }
            else if (userInput == 3){
                inputComment();
            }
            else if (userInput == 4){
                viewComment();     
            }
            else if (userInput == 5){
                visitOwner();
            }
            else if (userInput == 6){
                VideoOperation.play(vid.getAddress()); 
                VideoOperation.updateViewCount(vid.getView_count(), vid.getVideo_id());
                vid.upviewCount();
            }
            else if (userInput == 0)
                if (videoselect.equalsIgnoreCase("4")){
                    condition3 = false;
                }
        }  
    }
    
    public static void visitOwner(){
        System.out.println("\n~~~Welcome to " +connection.getOwner(vid.getUser_id()) + "'s channel!~~~");
        System.out.println("Subcriber count: " + connection.ownerSub(vid.getUser_id()));
        if (vid.getUser_id() == user.getUserID()){
            System.out.println("1. View owned videos.");
            System.out.println("0. Go back.");
            System.out.print("Enter Your Command: ");
            userInput = input.nextInt();
            if (userInput == 1){
                viewVideoList();
            }
            else if (userInput == 0){
                showSampleVideo("no");
            }
        }
        else {
            if (connection.checkSubscription(user.getUserID(), vid.getUser_id()) == 1){
                System.out.println("You are currently subscribed.");
                System.out.println("1. Unsubscribe?");
                System.out.println("2. View owned videos.");
                System.out.println("0. Go back.");
                System.out.print("Enter Your Command: ");
                userInput = input.nextInt();
                if (userInput == 1){
                    connection.unsubscribe(user.getUserID(), vid.getUser_id());
                    visitOwner();
                }
                else if (userInput == 2){
                    viewVideoList();
                }
                else if (userInput == 0){
                    showSampleVideo("no");
                }
            }
            else{
                System.out.println("You are currently unsubscribed.");
                System.out.println("1. Subscribe?");
                System.out.println("2. View owned videos.");
                System.out.println("0. Go back.");
                System.out.print("Enter Your Command: ");
                userInput = input.nextInt();
                if (userInput == 1){
                    connection.subscribe(user.getUserID(), vid.getUser_id());
                    visitOwner();
                }
                else if (userInput == 2){
                    viewVideoList();
                }
                else if (userInput == 0){
                    showSampleVideo("no");
                }
            }
        }
        
    }
    public static void viewVideoList(){
        connection.videoList(vid.getUser_id());
        System.out.println("Input something to leave video section.");
        input.nextLine();
        input.nextLine();
        visitOwner();
    }
    
    public static void inputComment(){
        input.nextLine();
        System.out.println("\nEnter: ");
        String comment = input.nextLine();
        connection.newComment(vid.getVideo_id(), user.getUserID(), user.getName(), comment);
        System.out.println("Comment saved.");
        showSampleVideo("no");
    }
    
    public static void viewComment(){
        System.out.println("");
        connection.getComments(vid.getVideo_id());
        System.out.println("Input something to leave comment section.");
        input.nextLine();
        input.nextLine();
        showSampleVideo("no");
    }
    
    public static void showAccountMenu(){
        System.out.println("\nHi, " +user.getName()+ "! What would you like to do today?");
        System.out.println("1-Change Email");
        System.out.println("2-Change Password");
        System.out.println("3-Close Account");
        System.out.println("0-Cancel");
        userInput = input.nextInt();
        input.nextLine();
        
        if (userInput == 1){
            
            System.out.print("\nPlease type new email : ");
            String newEmail = input.nextLine();
            user.setEmail(newEmail);//update in obj
            connection.updateEmail(newEmail, user.getUserID());
            System.out.println("Complete! Email has been successfully changed to " + user.getEmail());
            showAccountMenu();
        }
        
        if (userInput == 2){
            System.out.print("\nPlease type in new password : ");
            String newPassword = input.nextLine();
            user.setPassword(newPassword);
            connection.updatePassword(newPassword, user.getUserID());
            //DBSconnect set pw in table
            System.out.println("Complete! New password is " + user.getPassword());
            showAccountMenu();
        }
        
        if (userInput == 3){
            System.out.println("\nAre you sure you want to close this account?");
            System.out.println("1-Yes \n2-No");
            System.out.print("Enter Your Command: ");
            userInput = input.nextInt();
            if (userInput == 1){
                //delete acc
                connection.deleteUser(user.getUserID());
                conditionA = false;
                condition2 = false;
                condition3 = false;
                //redirect to login page
                //LogIn();
            }
            else
                System.out.println("Invalid operation!");
        }
        if (userInput == 0)
            conditionA = false;
    }    
        
    public static void directSearchVideoID (String searchContent) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int count = 0;

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","12345678");
            //search video
            String sql_video = "select video_id,video_name,user_id,view_count,like_count,dislike_count,address from videos where video_id = ? order by videos.view_count desc";
            //compile sql
            ps = conn.prepareStatement(sql_video);
            //assign value to ?
            ps.setString(1, searchContent);
            //execute sql
            rs = ps.executeQuery();
            //output
            while(rs.next()){
                videoId = rs.getString("video_id");
                System.out.println(videoId);
                videoName = rs.getString("video_name");
                video_userId = rs.getString("user_id");
                viewCount = rs.getInt("view_count");
                likeCount = rs.getInt("like_count");
                dislikeCount = rs.getInt("dislike_count");
                videoAddress = rs.getString("address");
                vid = new Video(videoId, videoName, video_userId, viewCount, likeCount, dislikeCount, videoAddress);
             
                
                count++;
                
            }
            if(count == 0){
                System.out.println("No content was found!");
            }
                        
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try{
                    ps.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        }
    
    public static String keywordSQLSearch(String str){
        String[] keywords = str.split(" ");
        if(keywords.length==1) return str;
        
        String strSqlSearch = "";
        for (int i = 0; i < keywords.length; i++) {
            strSqlSearch += ("^"+keywords[i]+"|");
        }
        strSqlSearch = strSqlSearch+"^"+keywords[0];
        return "^("+strSqlSearch+")";
    }
}