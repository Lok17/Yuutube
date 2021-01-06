package video;
import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;

public class Video {

    public static void main(String[] args) throws IOException{
        System.out.println("Command");
        System.out.println("1. Upload");
        System.out.println("2. Delete");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your command: ");
        int operation = sc.nextInt();
       
        final String destPath = "C:\\Users\\eedre\\IdeaProjects\\playVideo\\VideoStorage\\";
        
        //sample file path: D:\\Downloads\\WIX1002\\TWICE'FANCY'MV.webm
        if(operation == 1){
            System.out.print("Please enter file path: ");
            String userFilePath = sc.next();
            System.out.print("Please enter video title: "); //TWICE'FANCY'MV
            String videoTitle = sc.next();
            Path vSrc = Paths.get(userFilePath);
            String[] splitter = userFilePath.split("\\\\");
            String vDestPath = destPath+splitter[splitter.length-1];
            Path vDest = Paths.get(vDestPath);
            upload(vSrc, vDest, videoTitle,vDestPath); 
            System.out.print("Do you want to play? [Y/N] : ");
            String yn = sc.next();
            if(yn.equalsIgnoreCase("Y")){
                play(vDestPath);            
            }
        }else if(operation == 2){
            System.out.print("Please enter video title you wish to delete: "); //TWICE'FANCY'MV
            String videoTitle = sc.next();
            deleteEntry(videoTitle);
        }  
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
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
        
        
    
    
    //to delete video entry
    public static void deleteEntry(String videoName) {
        String url = "jdbc:mysql://localhost:3306/videos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "elgene2000";
        Statement stmt = null;
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            stmt = (Statement) connect.createStatement();
            PreparedStatement stt = connect.prepareStatement("DELETE FROM videos WHERE video_name="+videoName);
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
    public static void upload(Path src, Path dest, String videoName, String videoAddress){
        //upload video
        try {
            Path temp = Files.move(src, dest);
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
        String url = "jdbc:mysql://localhost:3306/videos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "elgene2000";
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            String query = "INSERT INTO videos(video_id,video_name,user_id, view_count, like_count, dislike_count, address)VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(query);
            stt.setInt(1,12);
            stt.setString(2,videoName);
            stt.setInt(3,0);
            stt.setInt(4,0);
            stt.setInt(5,0);
            stt.setInt(6,0);
            stt.setString(7,videoAddress);
            stt.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }   
    
    } 
}
