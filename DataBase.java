import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static final String name_DB = "maze.bd";
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
        String sql="CREATE TABLE IF NOT EXISTS MAZES("+
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " maze VARCHAR(15))";
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
    public static void createTablePlayers() throws SQLException{
        String sql="CREATE TABLE IF NOT EXISTS PLAYERS("+
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " player VARCHAR(15),"+
                    " attempts INTEGER," +
                    " id_maze INTEGER))";
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
     public void eliminaMazes(Categoria categoria) throws SQLException {
        if (categoria != null && categoria.getId2() != -1) {
            String sqlPlayers = String.format("DELETE FROM PLAYERS WHERE id_maze = %d", categoria.getId());
            String sqlMazes = String.format("DELETE FROM CATEGORIES WHERE id = %d", categoria.getId());
            
            Statement st = null;
            try {
                st = conn.createStatement();
                st.executeUpdate(sqlAnimals);
                st.executeUpdate(sqlCategoria);
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } 
    }
}