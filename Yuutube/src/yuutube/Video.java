package yuutube;

import java.util.ArrayList;

public class Video {
    private int like_count, dislike_count, view_count;
    private String video_id, video_name, user_id, address;
    private ArrayList <String> comment = new ArrayList<>();

    public Video(String video_id, String video_name, String user_id, int view_count, int like_count, int dislike_count, String address) {
        this.like_count = like_count;
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

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getDislike_count() {
        return dislike_count;
    }

    public void setDislike_count(int dislike_count) {
        this.dislike_count = dislike_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
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
        
/*   public int upCommentCnt(String newComment){
        comment.add(newComment);
            commentCount++;
            return 1; //comment added successfully

    public String toString() {
        return "Video{" + "\nLikes \t: " + like_count + "\nDislike \t: " + dislike_count + "\nSubscribers \t: " + subscriber_count + "\nComments \t: " + commentCount;
    }
        }
*/  
}