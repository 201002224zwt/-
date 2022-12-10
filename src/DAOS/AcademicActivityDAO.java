package DAOS;

import Entity.AcademicActivity;
import Entity.Master;

public interface AcademicActivityDAO {
    //增加
    public void addAcademicActivity(AcademicActivity activity);
    //删除
    public void deleteAcademicActivity(AcademicActivity activity);
    //修改
    public void updateAcademicActivity(AcademicActivity activity);
    //查找
    public Master getAcademicActivity(String MasterId);
}
