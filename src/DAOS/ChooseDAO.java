package DAOS;

import Entity.Choose;

import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-10 15:40:48
 */
public interface ChooseDAO {
    void addChoose(Choose choose);
    void updateChoose(Choose choose);
    void deleteChoose(Choose choose);
    Choose getChoose(String couid,String mid);
    LinkedList<Choose> getAllChooses();
}
