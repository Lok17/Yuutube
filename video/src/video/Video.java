package video;
import com.mysql.cj.xdevapi.Statement;
import video.Videos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;

public class Video {

    public static void main(String[] args) {
        
            
        System.out.println("Command");
        System.out.println("1. Upload");
        System.out.println("2. Delete");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your command: ");
        int operation = sc.nextInt();
       
        final String destPath = "C:\\Users\\eedre\\IdeaProjects\\playVideo\\VideoStorage\\";
        
        
        if(operation == 1){
            System.out.print("Please enter file path: ");
            String userFilePath = sc.next();
            System.out.print("Please enter video title: ");
            String videoTitle = sc.next();
            Videos v = new Videos(videoTitle);
            v.setViewsCount(0);
            v.setLikeCount(0);
            v.setDislikeCount(0);
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
            System.out.print("Please enter video title you wish to delete: ");
            String videoTitle = sc.next();
            String deleteDest = destPath + videoTitle;
//            delete(deleteDest);
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
    public static void delete(String dest) {
        File myObj = new File(dest);
        if (myObj.delete()) {
            deleteEntry(myObj.getName());
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
        java.sql.Statement state=null;
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            stmt = (Statement) connect.createStatement();
            String sql = "DELETE FROM videos WHERE video_name="+videoName;
            
            
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
        Statement stmt = null;
        java.sql.Statement state=null;
        
        try{
            Connection connect = DriverManager.getConnection(url,username,password);
            PreparedStatement stt = connect.prepareStatement("INSERT INTO videos(video_name,user_id, view_count, like_count, dislike_count, address)VALUES(videoName,1,0,0,0, videoAddress)");
            stt.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }   
    
    } 
}
