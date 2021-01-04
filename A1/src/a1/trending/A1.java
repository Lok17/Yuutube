package a1.trending;

import java.sql.*;  
import java.util.Scanner;

public class A1 {

    public static void main(String[] args) throws SQLException{
        
        Scanner sc = new Scanner(System.in);
        
        String input;
        
        System.out.print("Enter: ");
        input = sc.nextLine();
        
        if (input.equalsIgnoreCase("show top 5 trending")) {
            GetTrending trending = new GetTrending();
            trending.getTop5Trending();
        }
        
    }  
    
}
