package DAOS;

import Entity.paper;

public interface paperDAO {
    void submitpaper(paper paper);

    paper getPaper(String master_sid);
}
