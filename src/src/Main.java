import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author caoqike
 * @date 2022-12-03 20:15:23
 */
public class Main {
    public static void main(String[] args) {
        DAOBase daoBase=new DAOBase();
        Connection conn=daoBase.getConnection();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select name from Master");
            System.out.println("111");
            while (rs.next()){
                String SN = rs.getString("name");
//                String id = rs.getString("id");
//                String mid=rs.getString("mid");
                System.out.println(SN);
            }

            conn.close();//นุม๗
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
