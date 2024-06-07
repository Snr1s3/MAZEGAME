import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBase {

    private static final String name_DB = "maze.db";
    private static final String conn_string = "jdbc:sqlite:" + name_DB;
    private static Connection conn = null;
    public static void connecta() throws SQLException {
        if (conn != null) return;
        conn = DriverManager.getConnection(conn_string);
    }
    public static void desconnecta() throws SQLException {
        if (conn == null) return; 
        conn.close();
        conn = null;
    }

    public static void createTableMazes() throws SQLException{
        String sql="CREATE TABLE IF NOT EXISTS PLAYERS("+
                    " maze VARCHAR(15),"+
                    " attempts INTEGER,"+
                    " player VARCHAR(50))";
        Statement st=null;
        try{
            st= conn.createStatement();
            st.executeUpdate(sql);
        }finally{
            if(st !=null){
                st.close();
            }
        }
    }
    public static String[] getMazeRecord(String mazeName) throws SQLException{
        String sql="SELECT * FROM PLAYERS WHERE maze='"+mazeName+"' ORDER BY attempts ASC LIMIT 1";
        Statement st=null;
        try{
            st= conn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            String str[] = new String[3];
            while (rs.next()) {
                str[0] = rs.getString("maze");
                str[1] = rs.getString("attempts");
                str[2]= rs.getString("player");
                //System.out.println("Maze: "+str[0]);
                //System.out.println("Attempts: "+str[1]);
                //System.out.println("Player: "+str[2]);
            }
            rs.close();
            return str;
        }finally{
            if(st !=null){
                st.close();
            }
        }
    }
    public static void  dropDB() throws SQLException{
        String sql="DROP TABLE PLAYERS";
        Statement st=null;
        try{
            st= conn.createStatement();
            st.executeUpdate(sql);
        }finally{
            if(st !=null){
                st.close();
            }
        }
    }
    public static void insertRecord(String mazeName, int attempts, String player) throws SQLException{
        String sql="INSERT INTO PLAYERS(maze, attempts, player) VALUES('"+mazeName+"',"+attempts+",'"+player+"')";
        Statement st=null;
        try{
            st= conn.createStatement();
            st.executeUpdate(sql);
        }finally{
            if(st !=null){
                st.close();
            }
        }
    }
    public static void main(String[] args) {
        try{
            connecta();
            createTableMazes();
            getMazeRecord("maze1");
            insertRecord("maze1", 234, "player1");
            getMazeRecord("maze1");
            desconnecta();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}