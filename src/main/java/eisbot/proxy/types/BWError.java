package eisbot.proxy.types;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Marcus McCurdy <marcus.mccurdy@gmail.com>
 * Date: 3/5/12
 * Time: 8:13 AM
 */
public enum BWError {


    Connection_Failure(21),
    Unit_Does_Not_Exist(0),
    Unit_Not_Visible(1),
    Unit_Not_Owned(2),
    Unit_Busy(3),
    Incompatible_UnitType(4),
    Incompatible_TechType(5),
    Already_Researched(6),
    Fully_Upgraded(7),
    Insufficient_Minerals(8),
    Insufficient_Gas(9),
    Insufficient_Supply(10),
    Insufficient_Energy(11),
    Insufficient_Tech(12),
    Insufficient_Ammo(13),
    Insufficient_Space(14),
    Unbuildable_Location(15),
    Out_Of_Range(16),
    Unable_To_Hit(17),
    Access_Denied(18),
    None(19),
    Unknown(20);

    private final int cppOrd;

    private BWError(int cppOrd) {
        this.cppOrd = cppOrd;
    }

    private static final Map<Integer, BWError> lookup = new HashMap<Integer, BWError>(BWError.values().length);

    static {
        for (BWError err : BWError.values()) {
            lookup.put(err.cppOrd, err);
        }
    }

    public static BWError fromId(int id) {
        final BWError err = lookup.get(id);
        if (err == null) {
            String message = String.format("No mapping for id %d", id);
            throw new IllegalArgumentException(message);
        }
        return err;
    }
}
