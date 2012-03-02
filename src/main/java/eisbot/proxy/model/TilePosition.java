package eisbot.proxy.model;

/**
 * Class that represents a build tile on the map.
 *
 * @author Marcus McCurdy <marcus.mccurdy@gmail.com>
 */
public class TilePosition extends AbstractPosition {

    public static final TilePosition INVALID = new TilePosition(1000, 1000);
    public static final TilePosition NONE = new TilePosition(1000, 1001);
    public static final TilePosition UNKNOWN = new TilePosition(1000, 1002);

    public TilePosition(int x, int y) {
        super(x, y);
    }

}
