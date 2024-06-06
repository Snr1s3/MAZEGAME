import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {

    private static final String name_DB = "animals.bd";
    private static final String conn_string = "jdbc:sqlite:" + name_DB;
    private Connection conn = null;
    public void connecta() throws SQLException {
        if (conn != null) return;
        conn = DriverManager.getConnection(conn_string);
    }
    public void desconnecta() throws SQLException {
        if (conn == null) return; 
        conn.close();
        conn = null;
    }

    public void createTable() throws SQLException{
        String sql="CREATE TABLE IF NOT EXISTS MAZES("+
                    " maze VARCHAR(30),"+
                    " record VARCHAR(30),"+
                    " attempts INTEGER))";
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
}