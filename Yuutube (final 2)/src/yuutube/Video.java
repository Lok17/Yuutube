package yuutube;

import java.util.ArrayList;

public class Video {
    private int like_count, dislike_count, view_count;
    private String video_id, video_name, user_id, address;

    public String getUser_id() {
        return user_id;
    }

    public Video(String video_id, String video_name, String user_id, int view_count, int like_count, int dislike_count, String address) {
        this.like_count = like_count;
        this.user_id = user_id;
        this.dislike_count = dislike_count;
        this.video_id = video_id;
        this.video_name = video_name;
        this.view_count = view_count;
        this.address = address;
    }

    public String getVideo_id() {
        return video_id;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getDislike_count() {
        return dislike_count;
    }
  //**  
    public void interaction(){
    }
    
    public int getView_count() {
        return view_count;
    }
    
    public void upviewCount(){
        this.view_count++;
    }

    public void upLike(){
        this.like_count++;
    }

    public void upDislike(){
        this.dislike_count++;
    }
    
    public void downLike(){
        this.like_count--;
    }

    public void downDislike(){
        this.dislike_count--;
    }

    public String getVideo_name() {
        return video_name;
    }

    public String getAddress() {
        return address;
    }
    
    
}