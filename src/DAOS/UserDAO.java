package DAOS;

import Entity.Course;
import Entity.Teacher;
import User.User;

import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-12 09:05:51
 */
public interface UserDAO {
    //����
    public void addUser(User user);
    //ɾ��
    public void deleteUser(User user);
    //�޸�
    public void updateUser(User user);
    //���� --�����û���
    public User getUser(String name);


    //����ȫ���û���Ϣ
    public LinkedList<User> getAllUsers();

}
