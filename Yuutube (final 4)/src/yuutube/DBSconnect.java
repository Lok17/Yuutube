package yuutube;

import java.sql.*;  
import java.util.ArrayList;

public class DBSconnect {

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    private static String path = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
    
    public DBSconnect(){
        try{

            con = DriverManager.getConnection(path,"root","12345678");
            st = con.createStatement();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    //creating user with name, pw,email(user_id is auto generated)
    public void createDBUser(String user_name, String password, String email){
        try{
            String query = "INSERT INTO users (user_name,password, email) values (\'" + user_name + "\', \'" + password + "\', \'" + email + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void newVideo(String video_id, String user_id, int view_count, int like_count, int dislike_count, String address){
        try{
            String query = "INSERT INTO videos (video_id ,user_id, view_count, like_count, dislike_count, address) values (\'" + video_id + "\', \'" + user_id + "\', \'" + view_count + "\', \'" + like_count + "\', \'" + dislike_count + "\', \'" + address + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    //SELECT EXISTS(SELECT * FROM yourTableName WHERE yourCondition);
    public void Interaction(String user_id, int interactions, String video_id){
        try{
            String query = "INSERT INTO videointeraction (video_id ,user_id, interactions) values (\'" + video_id + "\', \'" + user_id + "\', \'" + interactions + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    //checking if the user has previously interacted with the video
    public int checkInteraction (String user_id, String video_id){   
        int result = -1;
        try{
            String query = "SELECT interactions FROM videointeraction WHERE (video_id, user_id) =(\'" + video_id + "\', \'" + user_id + "\')";
            rs = st.executeQuery(query);
            while ( rs.next() ) {
                result = rs.getInt("interactions");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }        
        return result;    
    } 
    
    
    public void updateLike(int like_count, String video_id, String user_id, int interactions){
        try{
            String query = "UPDATE videos SET like_count = \'" +like_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "INSERT INTO videointeraction (video_id, user_id, interactions) values (\'" + video_id + "\', \'" + user_id + "\', \'" + interactions + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void removeLike(int like_count, String video_id, String user_id){        
        try{
            String query = "DELETE FROM videointeraction WHERE user_id = \'" + user_id + "\' AND video_id = \'" + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "UPDATE videos SET like_count = \'" +like_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void removeDislike(int dislike_count, String video_id, String user_id){        
        try{
            String query = "DELETE FROM videointeraction WHERE user_id = \'" + user_id + "\' AND video_id = \'" + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "UPDATE videos SET dislike_count = \'" +dislike_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateDislike(int dislike_count, String video_id, String user_id, int interactions){
        try{
            String query = "UPDATE videos SET dislike_count = \'" +dislike_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "INSERT INTO videointeraction (video_id, user_id, interactions) values (\'" + video_id + "\', \'" + user_id + "\', \'" + interactions + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateviewCount(int view_count, String video_id){
        try{
            String query = "UPDATE videos SET view_count = \'" +view_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void newComment(String video_id, String user_id, String user_name, String comments){
        try{
            String query = "INSERT INTO comments (video_id ,user_id, user_name, comments) values (\'" + video_id + "\', \'" + user_id + "\', \'" + user_name + "\', \'" + comments + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updatePassword(String newPassword, String user_id){
        try{
            String query = "UPDATE users SET password = \'" +newPassword+ "\' WHERE user_id = \' " + user_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateEmail(String newEmail, String user_id){
        try{
            String query = "UPDATE users SET email = \'" +newEmail+ "\' WHERE user_id = \' " + user_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateSubscribe(int subscribe_count){
        try{
            String query = "UPDATE users SET subscriber_count = \'" +subscribe_count+ "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void deleteUser(String user_id){
        try{
            String query = "DELETE FROM users WHERE user_id = \'" + user_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public static void getComments(String video_id){
        int order_num = 1;
        try{
            String query = "SELECT * FROM comments WHERE video_id = \'" + video_id + "\'"; 
            rs = st.executeQuery(query);
            System.out.println("Comments");
            while (rs.next()){
                String name = rs.getString("user_name");
                String comments = rs.getString("comments");
                System.out.println(order_num + ". " +name + ": " +comments);    
                order_num++;
            }
            if (order_num ==1)
                System.out.println("No comment");
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public String getOwner(String user_id){
        String name = "";
        try{
            String query = "SELECT * FROM users WHERE user_id = \'" + user_id + "\'"; 
            rs = st.executeQuery(query);
            while (rs.next()){
                name = rs.getString("user_name");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return name;
    }
    
    public int ownerSub(String user_id){
        int subscribe_count = 0;
        try{
            String query = "SELECT * FROM users WHERE user_id = \'" + user_id + "\'"; 
            rs = st.executeQuery(query);
            while (rs.next()){
                subscribe_count = rs.getInt("subscriber_count");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return subscribe_count;
    }
    
    public void videoList(String user_id){
        int order_num = 1;
        try{
            String query = "SELECT * FROM videos WHERE user_id = \'" + user_id + "\'"; 
            rs = st.executeQuery(query);
            System.out.println("\nAll video belong to this channel");
            System.out.printf("%-5s %-13s %-30s %-30s\n","#","video id","video name","total view counts");
            
            while (rs.next()){
                System.out.printf("%-5d %-13s %-30s %-30s\n",order_num,rs.getString("video_id"),rs.getString("video_name"),rs.getString("view_count"));
                order_num++;
//                System.out.println(video_name + "\n");                
            }
            if (order_num ==  1){
                System.out.println("No video");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public int checkSubscription (String subscriber, String subscribedTo){   
        try{
            String query = "SELECT subscribedTo FROM subscription WHERE subscriber =(\'" + subscriber + "\')";
            rs = st.executeQuery(query);
            while ( rs.next() ) {
                if (subscribedTo.equals(rs.getString("subscribedTo"))){
                    return 1;
                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }        
        return -1;    
    } 
    
    public void subscribe(String subscriber, String subscribedTo){
        int subscribe_count = 0;
        try{
            String query = "SELECT subscriber_count FROM users WHERE user_id =(\'" + subscribedTo + "\')";
            rs = st.executeQuery(query);
            while ( rs.next() ) {
                subscribe_count =rs.getInt("subscriber_count")+1;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "UPDATE users SET subscriber_count = \'" +subscribe_count+ "\' WHERE user_id = \' " + subscribedTo + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "INSERT INTO subscription (subscriber, subscribedTo) values (\'" + subscriber + "\', \'" + subscribedTo + "\')";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public void unsubscribe(String subscriber, String subscribedTo){
        int subscribe_count = 0;
        try{
            String query = "SELECT subscriber_count FROM users WHERE user_id =(\'" + subscribedTo + "\')";
            rs = st.executeQuery(query);
            while ( rs.next() ) {
                subscribe_count =rs.getInt("subscriber_count")-1;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "UPDATE users SET subscriber_count = \'" +subscribe_count+ "\' WHERE user_id = \' " + subscribedTo + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
        try{
            String query = "DELETE FROM subscription WHERE subscriber = \'" + subscriber + "\' AND subscribedTo = \'" + subscribedTo + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}        