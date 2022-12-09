import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-09 08:51:54
 */
public class SubjectDAOimp extends DAOBase implements SubjectDAO{

    private static final String SUBJECT_INSERT_SQL = "INSERT INTO Subject(subid,name) VALUES(?,?)";
    @Override
    public void addSubject(Subject subject) {
        Connection con = null;
        con = getConnection();
        try {
            PreparedStatement psmt = con.prepareStatement(SUBJECT_INSERT_SQL);
            psmt.setString(1, subject.subid);
            psmt.setString(2, subject.name);

            psmt.executeUpdate();
            psmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubject(Subject subject) {

    }

    @Override
    public void deleteSubject(Subject subject) {

    }

    @Override
    public Subject getSubject(String id) {
        return null;
    }
}
