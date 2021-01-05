package yuutube;

import java.sql.*;  
import java.util.ArrayList;

public class DBSconnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String path = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    public DBSconnect(){
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");

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
    
    public void updateLike(int like_count, String video_id){
        try{
            String query = "UPDATE videos SET like_count = \'" +like_count+ "\' WHERE video_id = \' " + video_id + "\'";
            st.executeUpdate(query);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    
    public void updateDislike(int dislike_count, String video_id){
        try{
            String query = "UPDATE videos SET dislike_count = \'" +dislike_count+ "\' WHERE video_id = \' " + video_id + "\'";
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
    
    public void newComment(String video_id, String user_name, String comments){
        try{
            String query = "INSERT INTO videos (video_id ,user_name, comment) values (\'" + video_id + "\', \'" + user_name + "\', \'" + comments + "\')";
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
            String query = "UPDATE users SET subscribe_count = \'" +subscribe_count+ "\'";
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
    //to obtain user details
    public ArrayList<String> getUser(String email, String password){
        ArrayList<String> userDetails = new ArrayList<String>();
        
        try{
            String query = "SELECT * FROM users WHERE email = \'" + email + "\' AND password = \'" + password + "\'";
            
            rs = st.executeQuery(query);
            while (rs.next()){
                String name = rs.getString("user_name");
                userDetails.add(name);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return userDetails;
    }

    
//to search for videos from db
    public ArrayList<String> getVideo (String searchString){
        ArrayList<String> videoList = new ArrayList<String>();
        
        try{//use "like" to bring up searches with similar names
            String query = "select * from videos where video_name like \'%" + searchString + "%\'";
            rs = st.executeQuery(query);
            while ( rs.next() ) {
                String videoName = rs.getString("video_name");
                videoList.add(videoName);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return videoList;
        
        
    }
}
