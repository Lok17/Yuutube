package a1.trending;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Trending {

    private String[][] trending;
    private int numOfVideo = 0;
    private String hold;

    public Trending() {
    }
    
    public void getTop5Trending () {
        try {
            Scanner in = new Scanner(new FileInputStream("viewCount.txt"));
            
            while(in.hasNextLine()) { 
                numOfVideo++;
                in.nextLine();
            }
            
            
            trending = new String[numOfVideo][2];
            
            in = new Scanner(new FileInputStream("viewCount.txt"));
            
            for (int i = 0; i < numOfVideo; i++) {
                
                hold = in.nextLine();
                trending[i] = hold.split(",", 2);
                
            }

            for (int i = 0; i < trending.length - 1; i++) {
                for (int j = 0; j < trending.length - 1; j++) {
                    
                    if (Integer.parseInt(trending[j][1]) < Integer.parseInt(trending[j+1][1])) {
                        
                        hold = trending[j][1];
                        trending[j][1] = trending[j+1][1];
                        trending[j+1][1] = hold;
                        
                    }
         
                }
            }
            
            String output = "";
            
            System.out.println("\nCurrent top 5 trnding: ");
            if (trending.length <= 4) {
                for (int i = 0; i < trending.length; i++) {
                System.out.println(Arrays.toString(trending[i]));
                }
                
            }
            
            else {
                for (int i = 0; i < 5; i++) {
                System.out.println(Arrays.toString(trending[i]));
                }
            }
            
            in.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }   
    
}
