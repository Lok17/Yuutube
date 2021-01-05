/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yuutube;

import java.sql.*;
import java.util.*;
public class Search {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter : ");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("Search video")){
            System.out.println("Enter to search : ");
            String video = in.nextLine();
            
            System.out.println("Video search result : ");
            outputFormat(1);
            String sqlSearch = "select video_id,video_name,user_id,view_count,like_count,dislike_count from videos where video_name = '"+video+"'order by view_count desc";
            Search.search(1,sqlSearch);
            
            System.out.println("Relative result : ");
            outputFormat(1);
            sqlSearch = "select video_id,video_name,user_id,view_count,like_count,dislike_count from videos where video_name like '&"+video+"&'order by view_count desc";
            Search.search(1,sqlSearch);
            
        }else if(input.equalsIgnoreCase("Search channel")){
            System.out.println("Enter to search : ");
            String channel = in.nextLine();
            
            System.out.println("Channel search result : ");
            outputFormat(2);
            String sqlSearch = "select v.video_id,v.video_name,v.user_id,v.view_count,v.like_count,v.dislike_count from videos v left join users u on u.user_id=v.user_id where u.user_name = '"+channel+"' order by view_count desc";
            Search.search(2,sqlSearch);
            
            System.out.println("Relative result : ");
            outputFormat(2);
            sqlSearch = "select v.video_id,v.video_name,v.user_id,v.view_count,v.like_count,v.dislike_count from videos v left join users u on u.user_id=v.user_id where u.user_name like '&"+channel+"&' order by view_count desc";
            Search.search(2,sqlSearch);
        }
    }
    public static void search(int number, String sqlSearch){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","12345678");
            stmt = conn.createStatement();
            String sql = sqlSearch;
            rs = stmt.executeQuery(sql);
            int count = 0;
            if(number == 1){
                while(rs.next()){
                String videoID = rs.getString("video_id");
                String title = rs.getString("video_name");
                String userID = rs.getString("user_id");
                int viewsCount = rs.getInt("view_count");
                int likeCount = rs.getInt("like_count");
                int dislikeCount = rs.getInt("dislike_count");
                System.out.printf("%-20s %-20s %-20s %-20d %-20d %-20d\n",videoID,title,userID,viewsCount,likeCount,dislikeCount);
                count++;
                }
            }else if(number == 2){
                while(rs.next()){
                String videoID = rs.getString("video_id");
                String title = rs.getString("video_name");
                String userID = rs.getString("user_id");
                String channel = rs.getString("user_name");
                int viewsCount = rs.getInt("view_count");
                int likeCount = rs.getInt("like_count");
                int dislikeCount = rs.getInt("dislike_count");
                System.out.printf("%-20s %-20s %-20s %-20s %-20d %-20d %-20d\n",videoID,title,userID,channel,viewsCount,likeCount,dislikeCount);
                count++;
                }
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
            if(stmt!=null){
                try{
                    stmt.close();
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
    public static void outputFormat(int number){
        if(number == 1)
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n","videoID","title","userID","view","like","dislike");
        else if (number == 2)
            System.out.printf("%-20s %-20s %-20s &-20s %-20s %-20s %-20s\n","videoID","title","userID","channel","view","like","dislike");
    }
}
