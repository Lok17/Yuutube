package video;

import java.util.ArrayList;

public class Videos {
    private String  title, videoID, videoPath;
    private int likeCount, dislikeCount, viewsCount;
    private ArrayList<String> comments;
    private boolean trendingNow;
    private static int ID=1;

    public Videos(String title){
        this.title = title;
        videoID = Integer.toString(ID);
        IdCount();
    }

    public static void IdCount(){
        ID++;
    }

    public String getTitle() {
        return title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }


    public String toString() {
        return String.format("Title: "+ title +
                            "\nID: " + videoID +
                            "\nViews: "+viewsCount +
                            "\nLikes: " + likeCount +
                            "\nDislikes: " + dislikeCount
                            );
    }
}
