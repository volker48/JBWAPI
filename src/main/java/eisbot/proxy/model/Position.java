package eisbot.proxy.model;

/**
 * A Position is a pixel level point on the map.
 * @author Marcus McCurdy <marcus.mccurdy@gmail.com>
 */
public class Position extends AbstractPosition {

    public Position(int x, int y) {
        super(x, y);
    }
    
    /**
     * Creates a new Position from TilePosition tilePosion taking into account
     * the difference in size between the two.
     * @param tilePosition 
     */
    public Position(TilePosition tilePosition) {
        this(tilePosition.getX() * 32, tilePosition.getY() * 32);
    }
}
