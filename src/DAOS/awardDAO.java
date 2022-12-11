package DAOS;

import Entity.award;
import Entity.paper;

public interface awardDAO {
    void submitaward(award award);

    award getAward(String master_sid);
}
