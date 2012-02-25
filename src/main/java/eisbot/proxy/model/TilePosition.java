package eisbot.proxy.model;

/**
 * Class that represents a build tile on the map.
 * @author Marcus McCurdy <marcus.mccurdy@gmail.com>
 */
public class TilePosition {
    public static final TilePosition INVALID = new TilePosition(1000, 1000);
    public static final TilePosition NONE = new TilePosition(1000, 1001);
    public static final TilePosition UNKNOWN = new TilePosition(1000,1002);
    private final int x;
    private final int y;

    public TilePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TilePosition other = (TilePosition) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        return hash;
    }
}
