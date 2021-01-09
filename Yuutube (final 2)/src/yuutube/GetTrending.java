package yuutube;

import java.sql.*;  

public class GetTrending {
    
    private int cnt = 0;
    private int order_num = 1;
    
    private Connection myConn = null;
    private Statement myStmt = null;
    private ResultSet myRs = null;

    private static final String user = "root";
    private static final String pass = "12345678";
    private static final String path = "jdbc:mysql://localhost:3306/test?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
    
    public void getTop5Trending () throws SQLException{



        try {
            myConn = DriverManager.getConnection(path, user, pass);
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery("SELECT video_id,video_name,view_count FROM videos ORDER BY view_count DESC;");
            System.out.println("Top 5 Trending Video In Yuutube");
            System.out.printf("%-5s %-13s %-30s %-30s\n","#","video id","video name","total view counts");
            while (myRs.next() && cnt < 5) {
                System.out.printf("%-5d %-13s %-30s %-30s\n",order_num,myRs.getString("video_id"),myRs.getString("video_name"),myRs.getString("view_count"));
                cnt++;
                order_num++;
            }
            System.out.println("");

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
