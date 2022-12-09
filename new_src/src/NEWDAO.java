import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NEWDAO {
    //设置数据库名称
    private final static String URL = "jdbc:sqlserver://82.157.243.176:1433";
    //登录名
    private static final String USER="cqk";
    //登录密码
    private static final String PASSWORD="123456";
    //设置连接对象
    private static Connection conn=null;
    //静态代码块（连接数据库）
    public static void main(String[] args){
        try {
            //加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //获得数据库的连接
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
