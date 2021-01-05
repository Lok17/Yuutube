
package yuutube;
import java.sql.*;
import java.util.*;
/**
 *
 * @author lenovo
 */
public class SearchOld {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter : ");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("Search video")){
            System.out.println("Enter to search : ");
                String video = in.nextLine();
                SearchOld.search(1, video);
        }
        if(input.equalsIgnoreCase("Search channel")){
            System.out.println("Enter to search : ");
                String channel = in.nextLine();
                SearchOld.search(2, channel);
        }
    }
    //if number == 1, search video; 
    //if number == 2, search channel(user_name)
    public static void search(int number, String searchContent) {
        if(number!=1&&number!=2) return;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //used in sql statement
        String keywordSearch = "&"+searchContent+"&";
        try{
            //select v.* from videos v left join users u on u.user_id=v.user_id where u.user_name like ? order by view_count desc;
            int count = 0;
            if(number==1){
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yuutube","root","654312");
                //search video
                String sql_video = "select video_id,video_name,user_id,view_count,like_count,dislike_count from videos where video_name = ? order by view_count desc";
                //compile sql
                ps = conn.prepareStatement(sql_video);
                //assign value to ?
                ps.setString(1, searchContent);
                //execute sql
                rs = ps.executeQuery();
                //output
                System.out.println("Video search result : ");
                outputFormat();
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
                if(count == 0){
                    System.out.println("No content was found!");
                }
                
                count = 0;
                //search video keywords
                String sql_keyword = "select video_id,video_name,user_id,view_count,like_count,dislike_count from videos where video_name like ? order by view_count desc";
                //compile sql_keyword
                ps = conn.prepareStatement(sql_keyword);
                //assign value to ?
                ps.setString(1, keywordSearch);
                //execute sql_keyword
                rs = ps.executeQuery();
                //ourput
                System.out.println("Relative result : ");
                outputFormat();
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
                if(count == 0){
                    System.out.println("No content was found!");
                }
                
                
            }else if(number==2){
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","12345678");
                count = 0;
                //search channel
                String sql_channel = "select v.video_id,v.video_name,v.user_id,v.view_count,v.like_count,v.dislike_count from videos v left join users u on u.user_id=v.user_id where u.user_name = ? order by view_count desc";
                //compile sql
                ps = conn.prepareStatement(sql_channel);
                //assign value to ?
                ps.setString(1, keywordSearch);
                //execute sql
                rs = ps.executeQuery();
                //output
                System.out.println("Channel search result : ");
                outputFormat();
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
                if(count == 0){
                    System.out.println("No content was found!");
                }
                
                count = 0;
                //search channel keywords
                String sql_keyword = "select v.video_id,v.video_name,v.user_id,v.view_count,v.like_count,v.dislike_count from videos v left join users u on u.user_id=v.user_id where u.user_name like ? order by view_count desc";
                //compile sql_keyword
                ps = conn.prepareStatement(sql_keyword);
                //assign value to ?
                ps.setString(1, keywordSearch);
                //execute sql_keyword
                rs = ps.executeQuery();
                //ourput
                System.out.println("Relative result : ");
                outputFormat();
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
                if(count == 0){
                    System.out.println("No content was found!");
                }
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
    public static void outputFormat(){
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n","videoID","title","userID","view","like","dislike");
    }
}
