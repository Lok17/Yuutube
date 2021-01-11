package yuutube;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;


public class VideoOperation {

    public static void main(String[] args) throws IOException{
        
            
        System.out.println("Command");
        System.out.println("1. Upload");
        System.out.println("2. Delete");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your command: ");
        int operation = Integer.parseInt(sc.nextLine());
       
        final String destPath = "C:\\\\Users\\\\Lok\\\\Videos\\\\VideoStoring\\\\";
        
//        play("C:\\Users\\LokVideos\\VideoStoring\\HappyVideo.mp4");
        
        //sample file path: D:\\Downloads\\WIX1002\\TWICE'FANCY'MV.webm
        if(operation == 1){
            System.out.print("Please enter file path: ");
            String userFilePath = sc.nextLine();
            System.out.print("Please enter video title: "); //TWICE'FANCY'MV
            String videoTitle = sc.nextLine();
            Path vSrc = Paths.get(userFilePath);
            String[] splitter = userFilePath.split("\\\\");
            String vDestPath = destPath+splitter[splitter.length-1];
            Path vDest = Paths.get(vDestPath);
            System.out.println(vDestPath);
            upload(vSrc, vDest, videoTitle,vDestPath,"1"); 
            System.out.print("Do you want to play? [Y/N] : ");
            String yn = sc.nextLine();
            if(yn.equalsIgnoreCase("Y")){
                play(vDestPath);            
            }
        }else if(operation == 2){
            System.out.print("Please enter video title you wish to delete: "); //TWICE'FANCY'MV
            String videoTitle = sc.nextLine();
            deleteEntry(videoTitle);
        }  

//
//            //File uploading
//            String videoTitle = "HappyVideo";
//            final String destPath = "C:\\\\Users\\\\Lok\\\\Videos\\\\VideoStoring\\\\";
//            System.out.println(destPath);
//            String userFilePath = "C:\\Users\\Lok\\Videos\\HappyVideo.mp4";
//            Path vSrc = Paths.get(userFilePath);
//            String[] splitter = userFilePath.split("\\\\");
//            String vDestPath = destPath+splitter[splitter.length-1];
//            System.out.println(vDestPath);
//            String temp = destPath;
//            System.out.println(temp);
//            Path vDest = Paths.get(vDestPath);
//            upload(vSrc, vDest, videoTitle,vDestPath);           
//            play(vDestPath);
//
//            Video playing from databases
//            play("C:\\Users\\Lok\\Videos\\VideoStoring\\HappyVideo.mp4");

    }
    
        //to play video
    public static void play(String dest) {
        try{
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe",dest);
            pb.start();
        }catch  (IOException e){
            e.printStackTrace();
        }
    }   
    
    //to delete video
    public static void delete(String vdestPath) {
        File myObj = new File(vdestPath);
        if (myObj.delete()) {
            System.out.println("Deleted successfully: " + myObj.getName());
        } 
//          else if (!myObj.delete()) {
//            System.out.println("Failed to delete the file.");
//        }
    }
        
        
    
    
    //to delete video entry
    public static void deleteEntry(String videoName) {
        String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "12345678";
        Statement stmt = null;
        java.sql.Statement state=null;
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            stmt = connect.createStatement();
            PreparedStatement stt = connect.prepareStatement("DELETE FROM videos WHERE video_name='"+videoName+"'");
            ResultSet rs = stt.executeQuery("SELECT * FROM videos");
            while(rs.next()){
                if(videoName.equals(rs.getString("video_name"))){
                    String addr = rs.getString("address");
                    delete(addr);
                    break;
                }
            }       
            stt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
  
    //to upload video
    public static void upload(Path src, Path dest, String videoName, String videoAddress,String user_id){
        //upload video
        try {
            Path temp = Files.copy(src, dest);
            if(temp != null)
            {
                src = dest;
                System.out.println("File Uploaded Successfully");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to upload");
        }

        //upload entry
        String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "12345678";
        Statement stmt = null;
        java.sql.Statement state=null;
        
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            PreparedStatement stt = connect.prepareStatement("INSERT INTO videos(video_name,user_id, view_count, like_count, dislike_count, address)VALUES('"+videoName+"','"+user_id+"',0,0,0, '"+videoAddress+"')");
            stt.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }   
    
    } 
    
    public static void updateViewCount(int viewCount, String videoId){
        String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "12345678";
        Statement stmt = null;
        java.sql.Statement state=null;
        
        viewCount++;
        
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            PreparedStatement stt = connect.prepareStatement("UPDATE videos SET view_count = '"+viewCount+"' where video_id = '"+videoId+"'");
            stt.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }   
    }

}
