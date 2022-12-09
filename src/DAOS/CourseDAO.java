package DAOS;

import Entity.Course;
import Entity.Teacher;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/9 21:01
 */
public interface CourseDAO {

        //增加
        public void addCourse(Course course);
        //删除
        public void deleteCourse(Course course);
        //修改
        public void updateCourse(Course course);
        //查找
        public Teacher getCourse(String courseid);

}
