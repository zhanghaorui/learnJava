package BigWork1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnection {
    private Connection connection = null;
    private Properties pros = null;
    public Connection connect() throws Exception{
        pros = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("J2SE/src/BigWork1/conn.properties"));
        pros.load(bufferedReader);
        String driver = pros.getProperty("driver");
        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
}
