package a1.trending;

import java.sql.*;  

public class GetTrending {
    
    private int cnt = 0;
    private int order_num = 1;
    
    public void getTop5Trending () throws SQLException{

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "12345678";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL", user, pass);

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("SELECT video_id,video_name,view_count FROM videos ORDER BY view_count DESC;");

            System.out.printf("%-5s %-13s %-30s %-30s\n","#","video id","video name","total view counts");
            while (myRs.next() && cnt < 5) {
                System.out.printf("%-5d %-13s %-30s %-30s\n",order_num,myRs.getString("video_id"),myRs.getString("video_name"),myRs.getString("view_count"));
                cnt++;
                order_num++;
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
