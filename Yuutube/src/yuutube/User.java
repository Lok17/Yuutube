package yuutube;

import java.util.ArrayList;
import java.util.Scanner;
public class User {
    private int subscriber_count, videoCnt, totalView;
    private String user_id, user_name, email, password, videoID;
    private ArrayList<String> subscribedTo = new ArrayList<>();//Array that stored lists of users that the user subscribed to
    private ArrayList<String> videos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    public User(String user_id, String user_name, String email, String password) {
        this.subscriber_count = 0;
        this.videoCnt = 0;
        this.totalView = 0;
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
        return subscriber_count++;
    }
    
    public int downSubscribeCount(String user_id){
        return subscriber_count--;
    }
   
    public int getsubscribeCnt(){
        return subscriber_count;
    }
    
    public boolean checkSubscription(String user_id){
        if (subscribedTo.contains(user_id))
             return true;
        else
            return false;
    }
       
    public int subscribe(String user_id){
        //for (int i = 0; i < subscribedTo.size() ; i++){
            //while (checkSubscription(userID) == false){
                subscribedTo.add(user_id);
                //increase the subscribeCount of the user who this user subscribed to by one
                //userID is the owner of the channel
                upSubscribeCount(user_id);
                //update in db
                //DBSconnect.updateSubscribe(subscriber_count);
                System.out.println("Subscription is successful");   
            //}
        return 1; //subscription added successfully
    }
    
    public int unsubscribe(String user_id){
        //for (int i = 0; i < subscribedTo.size() ; i++){
            //if (subscribedTo.contains(userID)){
            //while (checkSubscription(userID) == true){
                System.out.println("You are already subscribed. Unsubscribe?");
                System.out.println("1. Yes   \n2. No");
                sc.nextInt();
                if (sc.nextInt() == 1){
                    subscribedTo.remove(subscribedTo.indexOf(user_id));
                    downSubscribeCount(user_id);
                    //DBSconnect.updateSubscribe(subscriber_count);
            }
            return 0;
        }
    
    public int upVideo(String video_id){
        //for (int i = 0; i < videos.size; i++){
            if (!(videos.contains(video_id))){
                videos.add(video_id);
                System.out.println("Upload success!");
                
            }    
        return 1; //video added successfully
    }
   
    public String toString() {
        return "User \t: " +user_name+ "\nEmail \t: " +email+ "\nPassword \t: " +password+ "Subcribers \t: " +subscriber_count+ "\nTotal Views \t: " + totalView;
    }
    
}
