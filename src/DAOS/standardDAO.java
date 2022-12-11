package DAOS;

import Entity.standard;

public interface standardDAO {
    void submitstandard(standard standard);

    standard getstandard(String master_sid);
}
