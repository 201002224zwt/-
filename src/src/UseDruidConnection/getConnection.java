package UseDruidConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getConnection {

    public static void main(String [] args) throws SQLException {
        Druid d = new Druid();
        Connection con = null;
        con = d.getDataSource().getConnection();
        PreparedStatement psmt = null;
    }

}
