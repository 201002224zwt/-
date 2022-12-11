package DAOS;

import Entity.hs_platform;

public interface hs_platformDAO {
    void submiths_platform(hs_platform platform);

    hs_platform geths_platform(String master_sid);
}
