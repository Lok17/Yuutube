package yuutube;

import java.util.ArrayList;
import java.util.Scanner;
public class User {
    private int subscribe_count;
    private String user_id, user_name, email, password;
    Scanner sc = new Scanner(System.in);
    
    public User(String user_id, String user_name, String password, String email, int subscribe_count) {
        this.subscribe_count = subscribe_count;
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }

    public String getUserID() {
        return user_id;
    }
    
    public String getName() {
        return user_name;
    }

    public void setName(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int upSubscribeCount(String user_id){
        return subscribe_count++;
    }
    
    public int downSubscribeCount(String user_id){
        return subscribe_count--;
    }
   
    public int getsubscribeCnt(){
        return subscribe_count;
    }
    
}
