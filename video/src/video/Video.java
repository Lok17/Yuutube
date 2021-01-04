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
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Video {

    public static void main(String[] args) {
        
        
        final String destPath = "C:\\Users\\eedre\\IdeaProjects\\playVideo\\VideoStorage\\";
        
        
        Videos v1 = new Videos("The Most Insane 900 IQ Among Us Outplay!");
        String videoName = v1.getTitle();
        
        v1.setViewsCount(39000000);
        int viewCount = v1.getViewsCount();
        v1.setLikeCount(1000000);
        int likeCount = v1.getLikeCount();
        v1.setDislikeCount(17000);
        int dislikeCount = v1.getDislikeCount();
        String v1SrcPath = "D:\\Downloads\\WIX1002\\The Most Insane 900 IQ Among Us Outplay!.mp4";
        Path v1Src = Paths.get(v1SrcPath);
        String[] splitter = v1SrcPath.split("\\\\");
        String v1DestPath = destPath+splitter[splitter.length-1];
        String videoAddress = v1DestPath;
        Path v1Dest = Paths.get(v1DestPath);
        upload(v1Src, v1Dest, videoName,viewCount,likeCount,dislikeCount,videoAddress);
        play(v1DestPath);

//        Videos v2 = new Videos("Java Tutorial for Beginners [2020]");
//        v2.setViewsCount(3222388);
//        v2.setLikeCount(87000);
//        v2.setDislikeCount(999);
//        String v2SrcPath = "D:\\Downloads\\WIX1002\\Java Tutorial for Beginners [2020].mp4";
//        Path v2Src = Paths.get(v2SrcPath);
//        String[] splitter2 = v2SrcPath.split("\\\\");
//        String v2DestPath = destPath+splitter2[splitter2.length-1];
//        Path v2Dest = Paths.get(v2DestPath);
//
//        Videos v3 = new Videos("TWICE 'FANCY' M-V");
//        v3.setViewsCount(373606501);
//        v3.setLikeCount(4300000);
//        v3.setDislikeCount(228000);
//        String v3SrcPath = "D:\\Downloads\\WIX1002\\TWICE 'FANCY' M-V.webm";
//        Path v3Src = Paths.get(v3SrcPath);
//        String[] splitter3 = v3SrcPath.split("\\\\");
//        String v3DestPath = destPath+splitter3[splitter3.length-1];
//        Path v3Dest = Paths.get(v3DestPath);
//        System.out.println(v3.toString());
//
//        Videos v4 = new Videos("BLACKPINK - 'How You Like That' M-V");
//        v4.setViewsCount(715596328);
//        v4.setLikeCount(18000000);
//        v4.setDislikeCount(1000000);
//        String v4SrcPath = "D:\\Downloads\\WIX1002\\BLACKPINK - 'How You Like That' M-V.webm";
//        Path v4Src = Paths.get(v4SrcPath);
//        String[] splitter4 = v4SrcPath.split("\\\\");
//        String v4DestPath = destPath+splitter4[splitter4.length-1];
//        Path v4Dest = Paths.get(v4DestPath);
//        System.out.println(v4.toString());
//
//        Videos v5 = new Videos("BTS (방탄소년단) 'Dynamite' Official MV");
//        v5.setViewsCount(735612109);
//        v5.setLikeCount(23000000);
//        v5.setDislikeCount(1200000);
//        String v5SrcPath = "D:\\Downloads\\WIX1002\\BTS (방탄소년단) 'Dynamite' Official MV.webm";
//        Path v5Src = Paths.get(v5SrcPath);
//        String[] splitter5 = v5SrcPath.split("\\\\");
//        String v5DestPath = destPath+splitter5[splitter5.length-1];
//        Path v5Dest = Paths.get(v5DestPath);
//        System.out.println(v5.toString());
        
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
            stmt.executeUpdate(sql);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
  
    //to upload video
    public static void upload(Path src, Path dest, String videoName, int viewCount, int likeCount, int dislikeCount, String videoAddress){
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
            stmt = (Statement) connect.createStatement();
            String sql = "insert into videos " + " (video_name,user_id,view_count,like_count,dislike_count,address)" + " values(videoName, '?' ,viewCount,likeCount,dislikeCount,videoAddress)";
            stmt.executeUpdate(sql);
            
        }catch(SQLException e){
            e.printStackTrace();
        }   
    }
    
}
