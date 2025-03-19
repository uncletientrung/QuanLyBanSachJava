package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBCUtil {

    private static final Logger logger = Logger.getLogger(JDBCUtil.class.getName());
    
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection result = null;
        try {
            String url = "jdbc:mysql://localhost:3306/quanlibansach";
            String userName = "root";
            String password = "";
            result = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            logger.severe("Khong the ket noi den du lieu: " + e.getMessage());
        }
        return result;
    }

    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                logger.severe("Loi khi dong ket noi: " + e.getMessage());
            }
        }
    }
}
