package DAOS;

import Entity.report;

public interface reportDAO {
    void submitreport(report report);
    report getreport(String master_sid);
}
