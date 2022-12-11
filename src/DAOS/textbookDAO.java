package DAOS;

import Entity.textbook;

public interface textbookDAO {
    void submittextbook(textbook textbook);

    textbook gettextbook(String master_sid);
}
