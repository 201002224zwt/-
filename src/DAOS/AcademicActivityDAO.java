package DAOS;

import Entity.AcademicActivity;
import Entity.Master;

public interface AcademicActivityDAO {
    //增加
    public void addAcademicActivity(AcademicActivity activity);
    //删除
    public void deleteAcademicActivity(String ActivityId);
    //修改
    public void updateAcademicActivity(AcademicActivity activity);

    //修改导师意见
    public void updateTutorView(boolean permitted);

    //修改学院意见
    public void updateSubjectMasterView(boolean permitted);

    //导入图片


    //修改演讲题目

    //查找
    public Master getAcademicActivity(String MasterId);
}
