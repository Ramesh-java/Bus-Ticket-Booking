import java.sql.*;
public class DbConfig {
    Connection connection;
    public DbConfig(){
        try {
            String url = "jdbc:mysql://localhost:3306/myDB";
            String username = "root";
            String password = "1357";
            connection=DriverManager.getConnection(url,username,password);
            System.out.println("connected successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public void destroy(Connection connection){
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
