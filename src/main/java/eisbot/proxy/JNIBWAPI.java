package eisbot.proxy;

import eisbot.proxy.model.*;
import eisbot.proxy.model.Map;
import eisbot.proxy.types.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * JNI interface for the Brood War API.
 * <p/>
 * This focus of this interface is to provide the callback and game state query
 * functionality in BWAPI. Some utility functions have not been implemented yet,
 * but are on the way.
 * <p/>
 * Note: for thread safety and game state sanity, all native calls should be invoked from the callback methods.
 * <p/>
 * For BWAPI documentation see: http://code.google.com/p/bwapi/
 * <p/>
 * API Pages
 * Game: http://code.google.com/p/bwapi/wiki/Game
 * Unit: http://code.google.com/p/bwapi/wiki/Unit
 */
public class JNIBWAPI {

    // load the BWAPI client library
    static {
        try {
            System.loadLibrary("ExampleAIClient");
            System.out.println("Load ExampelAI");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            String libPath = System.getProperty("java.library.path");
            System.err.println("java.library.path is " + libPath);
        }
    }

    /**
     * callback listener for BWAPI events
     */
    private BWAPIEventListener listener;

    // game state
    private int gameFrame = 0;
    private Map map;
    private HashMap<Integer, Unit> units = new HashMap<Integer, Unit>();
    private ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private ArrayList<Unit> alliedUnits = new ArrayList<Unit>();
    private ArrayList<Unit> enemyUnits = new ArrayList<Unit>();
    private ArrayList<Unit> neutralUnits = new ArrayList<Unit>();

    // player lists
    private Player self;
    private HashSet<Integer> allyIDs = new HashSet<Integer>();
    private HashSet<Integer> enemyIDs = new HashSet<Integer>();
    private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
    private ArrayList<Player> allies = new ArrayList<Player>();
    private ArrayList<Player> enemies = new ArrayList<Player>();

    // invokes the main native method
    private native void startClient(JNIBWAPI jniBWAPI);

    // query methods
    private native int getGameFrame();

    private native int[] getPlayerInfo();

    private native int[] getPlayerUpdate(int playerID);

    private native int[] getResearchStatus(int playerID);

    private native int[] getUpgradeStatus(int playerID);

    private native int[] getUnits();

    private native int[] getUnitTypes();

    private native String getUnitTypeName(int typeID);

    private native int[] getTechTypes();

    private native String getTechTypeName(int techID);

    private native int[] getUpgradeTypes();

    private native String getUpgradeTypeName(int upgradeID);

    private native int[] getWeaponTypes();

    private native String getWeaponTypeName(int weaponID);

    private native int[] getUnitSizeTypes();

    private native String getUnitSizeTypeName(int sizeID);

    private native int[] getBulletTypes();

    private native String getBulletTypeName(int bulletID);

    private native int[] getDamageTypes();

    private native String getDamageTypeName(int damageID);

    private native int[] getExplosionTypes();

    private native String getExplosionTypeName(int explosionID);

    private native int[] getUnitCommandTypes();

    private native String getUnitCommandTypeName(int unitCommandID);

    private native int[] getOrderTypes();

    private native String getOrderTypeName(int unitCommandID);

    // map data
    private native void analyzeTerrain();

    private native int getMapWidth();

    private native int getMapHeight();

    private native String getMapName();

    private native String getMapHash();

    private native int[] getHeightData();

    private native int[] getWalkableData();

    private native int[] getBuildableData();

    private native int[] getChokePoints();

    private native int[] getRegions();

    private native int[] getPolygon(int regionID);

    private native int[] getBaseLocations();

    // unit commands: http://code.google.com/p/bwapi/wiki/Unit


    private native void attack(int unitID, int x, int y);

    public native void attack(int unitID, int targetID);

    public native void build(int unitID, int tx, int ty, int typeID);

    public native void buildAddon(int unitID, int typeID);

    public native void train(int unitID, int typeID);

    public native void morph(int unitID, int typeID);

    public native void research(int unitID, int techID);

    public native void upgrade(int unitID, int updateID);

    public native void setRallyPoint(int unitID, int x, int y);

    public native void setRallyPoint(int unitID, int targetID);

    /**
     * @param unitID
     * @param x
     * @param y
     * @see #move(eisbot.proxy.model.Unit, eisbot.proxy.model.Position)
     */
    @Deprecated
    public native void move(int unitID, int x, int y);

    public native void patrol(int unitID, int x, int y);

    public native void holdPosition(int unitID);

    public native void stop(int unitID);

    public native void follow(int unitID, int targetID);

    public native void gather(int unitID, int trargetID);

    public native void returnCargo(int unitID);

    public native void repair(int unitID, int targetID);

    public native void burrow(int unitID);

    public native void unburrow(int unitID);

    public native void cloak(int unitID);

    public native void decloak(int unitID);

    public native void siege(int unitID);

    public native void unsiege(int unitID);

    public native void lift(int unitID);

    public native void land(int unitID, int tx, int ty);

    public native void load(int unitID, int targetID);

    public native void unload(int unitID, int targetID);

    public native void unloadAll(int unitID);

    public native void unloadAll(int unitID, int x, int y);

    public native void rightClick(int unitID, int x, int y);

    public native void rightClick(int unitID, int targetID);

    public native void haltConstruction(int unitID);

    public native void cancelConstruction(int unitID);

    public native void cancelAddon(int unitID);

    public native void cancelTrain(int unitID, int slot);

    public native void cancelMorph(int unitID);

    public native void cancelResearch(int unitID);

    public native void cancelUpgrade(int unitID);

    public native void useTech(int unitID, int typeID);

    public native void useTech(int unitID, int typeID, int x, int y);

    public native void useTech(int unitID, int typeID, int targetID);

    // utility commands
    public native void drawHealth(boolean enable);

    public native void drawTargets(boolean enable);

    public native void drawIDs(boolean enable);

    public native void enableUserInput();

    public native void enablePerfectInformation();

    public native void setGameSpeed(int speed);

    private native int getLastErrorBWAPI();

    // draw commands
    public native void drawBox(int left, int top, int right, int bottom, int color, boolean fill, boolean screenCoords);

    public native void drawCircle(int x, int y, int radius, int color, boolean fill, boolean screenCoords);

    public native void drawLine(int x1, int y1, int x2, int y2, int color, boolean screenCoords);

    public void drawLine(Point a, Point b, int color, boolean screenCoords) {
        drawLine(a.x, a.y, b.x, b.y, color, screenCoords);
    }

    public native void drawDot(int x, int y, int color, boolean screenCoords);

    public native void drawText(int x, int y, String msg, boolean screenCoords);

    public void drawText(Point a, String msg, boolean screenCoords) {
        drawText(a.x, a.y, msg, screenCoords);
    }

    // Extended Commands (Fobbah)
    public native boolean hasCreep(int tx, int ty);

    public native boolean canBuildHere(int unitID, int tx, int ty, int utypeID, boolean checkExplored);

    public native void printText(String message);

    public native void sendText(String message);

    public native void setCommandOptimizationLevel(int level);

    private native boolean isReplay();

    //added by Marcus McCurdy
    private native int[] getUnitsOnTile(int tileX, int tileY);

    private native int[] getStartLocationsBWAPI();

    private native int[] getNearestChokePointBWAPI(int x, int y);
    
    private native int[] getUnitsInRectangleBWAPI(int top, int left, int bottom, int right);
    
    private native int[] getUnitsInRadiusBWAPI(int centerX, int centerY, int radius);

    // type data
    private HashMap<Integer, UnitType> unitTypes = new HashMap<Integer, UnitType>();
    private HashMap<Integer, TechType> techTypes = new HashMap<Integer, TechType>();
    private HashMap<Integer, UpgradeType> upgradeTypes = new HashMap<Integer, UpgradeType>();
    private HashMap<Integer, WeaponType> weaponTypes = new HashMap<Integer, WeaponType>();
    private HashMap<Integer, UnitSizeType> unitSizeTypes = new HashMap<Integer, UnitSizeType>();
    private HashMap<Integer, BulletType> bulletTypes = new HashMap<Integer, BulletType>();
    private HashMap<Integer, DamageType> damageTypes = new HashMap<Integer, DamageType>();
    private HashMap<Integer, ExplosionType> explosionTypes = new HashMap<Integer, ExplosionType>();
    private HashMap<Integer, UnitCommandType> unitCommandTypes = new HashMap<Integer, UnitCommandType>();
    private HashMap<Integer, OrderType> orderTypes = new HashMap<Integer, OrderType>();

    // type data accessors
    public UnitType getUnitType(int unitID) {
        return unitTypes.get(unitID);
    }

    public TechType getTechType(int techID) {
        return techTypes.get(techID);
    }

    public UpgradeType getUpgradeType(int upgradeID) {
        return upgradeTypes.get(upgradeID);
    }

    public WeaponType getWeaponType(int weaponID) {
        return weaponTypes.get(weaponID);
    }

    public UnitSizeType getUnitSizeType(int sizeID) {
        return unitSizeTypes.get(sizeID);
    }

    public BulletType getBulletType(int bulletID) {
        return bulletTypes.get(bulletID);
    }

    public DamageType getDamageType(int damageID) {
        return damageTypes.get(damageID);
    }

    public ExplosionType getExplosionType(int explosionID) {
        return explosionTypes.get(explosionID);
    }

    public UnitCommandType getUnitCommandType(int unitCommandID) {
        return unitCommandTypes.get(unitCommandID);
    }

    public OrderType getOrderType(int orderID) {
        return orderTypes.get(orderID);
    }

    public Collection<UnitType> unitTypes() {
        return unitTypes.values();
    }

    public Collection<TechType> techTypes() {
        return techTypes.values();
    }

    public Collection<UpgradeType> upgradeTypes() {
        return upgradeTypes.values();
    }

    public Collection<WeaponType> weaponTypes() {
        return weaponTypes.values();
    }

    public Collection<UnitSizeType> unitSizeTypes() {
        return unitSizeTypes.values();
    }

    public Collection<BulletType> bulletTypes() {
        return bulletTypes.values();
    }

    public Collection<DamageType> damageTypes() {
        return damageTypes.values();
    }

    public Collection<ExplosionType> explosionTypes() {
        return explosionTypes.values();
    }

    public Collection<UnitCommandType> unitCommandTypes() {
        return unitCommandTypes.values();
    }

    public Collection<OrderType> orderTypes() {
        return orderTypes.values();
    }

    /**
     * Issues the attack move command to unit.
     *
     * @param unit the Unit recipient of the order.
     * @param p    the Position that is the destination of the attack move.
     */
    public void attackMove(Unit unit, Position p) {
        this.attack(unit.getID(), p.getX(), p.getY());
    }

    // game state accessors
    public int getFrameCount() {
        return gameFrame;
    }


    public Player getSelf() {
        return self;
    }

    public Player getPlayer(int playerID) {
        return players.get(playerID);
    }

    public ArrayList<Player> getAllies() {
        return allies;
    }

    public ArrayList<Player> getEnemies() {
        return allies;
    }

    public Unit getUnit(int unitID) {
        return units.get(unitID);
    }

    public Collection<Unit> getAllUnits() {
        return units.values();
    }

    public ArrayList<Unit> getMyUnits() {
        return playerUnits;
    }

    public ArrayList<Unit> getAlliedUnits() {
        return alliedUnits;
    }

    public ArrayList<Unit> getEnemyUnits() {
        return enemyUnits;
    }

    public ArrayList<Unit> getNeutralUnits() {
        return neutralUnits;
    }

    /**
     * Returns the units on the build tile located at (tileX, tileY)
     *
     * @param tileX the int x coordinate of the left side of the build tile
     * @param tileY the int y coordinate of the top of the build tile
     * @return A Set of Units that are on the tile or an empty Set if
     *         there are no units on the build tile defined by (tileX, tileY)
     */
    public Set<Unit> getUnitsOnBuildTile(int tileX, int tileY) {
        final int[] unitIds = getUnitsOnTile(tileX, tileY);
        if (unitIds.length == 0) {
            return Collections.emptySet();
        }
        final Set<Unit> onTile = new HashSet<Unit>(unitIds.length);
        for (int id : unitIds) {
            onTile.add(getUnit(id));
        }
        return onTile;
    }

    public Set<Unit> getUnitsOnBuildTile(TilePosition tp) {
        return getUnitsOnBuildTile(tp.getX(), tp.getY());
    }

    /**
     * Returns all the start locations on the map.
     *
     * @return a List of TilePositions each one corresponding to a start
     *         location on the map. Note that if there are less players than the
     *         maximum for the map not every start location will have a player at
     *         it.
     */
    public List<TilePosition> getStartLocations() {
        final int[] xyArray = getStartLocationsBWAPI();
        final List<TilePosition> locations = new ArrayList<TilePosition>();
        for (int i = 0; i < xyArray.length; i += 2) {
            locations.add(new TilePosition(xyArray[i], xyArray[i + 1]));
        }
        return locations;
    }

    /**
     * Moves the screen to the given position on the map. The position specified where the top left corner of the
     * screen will be.
     *
     * @param x the int pixel of the x coordinate of the top left corner
     * @param y the int pixel of the y coordinate of the top left corner
     */
    public native void setScreenPosition(int x, int y);


    /**
     * Finds the center Position of the nearest ChokePoint to start.
     *
     * @param start
     * @return
     */
    public ChokePoint getNearestChokePoint(Position start) {
        int[] res = getNearestChokePointBWAPI(start.getX(), start.getY());
        if (res.length == 1) {
            return null;
        }
        final ChokePoint cp = new ChokePoint(res, 0);
        return cp;
    }

    /**
     * Returns the map.
     * <p/>
     * Note: return null if loadMapData has not been called.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Orders Unit unit to move to Position destination.
     * @param unit the Unit recipient of the command
     * @param destination the Position destination
     */
    public void move(Unit unit, Position destination) {
        move(unit.getID(), destination.getX(), destination.getY());
    }

    /**
     * Loads type data from BWAPI.
     */
    public void loadTypeData() {
        // unit types
        int[] unitTypeData = getUnitTypes();
        for (int index = 0; index < unitTypeData.length; index += UnitType.numAttributes) {
            UnitType type = new UnitType(unitTypeData, index);
            type.setName(getUnitTypeName(type.getID()));
            unitTypes.put(type.getID(), type);
        }

        // tech types
        int[] techTypeData = getTechTypes();
        for (int index = 0; index < techTypeData.length; index += TechType.numAttributes) {
            TechType type = new TechType(techTypeData, index);
            type.setName(getTechTypeName(type.getID()));
            techTypes.put(type.getID(), type);
        }

        // upgrade types
        int[] upgradeTypeData = getUpgradeTypes();
        for (int index = 0; index < upgradeTypeData.length; index += UpgradeType.numAttributes) {
            UpgradeType type = new UpgradeType(upgradeTypeData, index);
            type.setName(getUpgradeTypeName(type.getID()));
            upgradeTypes.put(type.getID(), type);
        }

        // weapon types
        int[] weaponTypeData = getWeaponTypes();
        for (int index = 0; index < weaponTypeData.length; index += WeaponType.numAttributes) {
            WeaponType type = new WeaponType(weaponTypeData, index);
            type.setName(getWeaponTypeName(type.getID()));
            weaponTypes.put(type.getID(), type);
        }

        // unit size types
        int[] unitSizeTypeData = getUnitSizeTypes();
        for (int index = 0; index < unitSizeTypeData.length; index += UnitSizeType.numAttributes) {
            UnitSizeType type = new UnitSizeType(unitSizeTypeData, index);
            type.setName(getUnitSizeTypeName(type.getID()));
            unitSizeTypes.put(type.getID(), type);
        }

        // bullet types
        int[] bulletTypeData = getBulletTypes();
        for (int index = 0; index < bulletTypeData.length; index += BulletType.numAttributes) {
            BulletType type = new BulletType(bulletTypeData, index);
            type.setName(getBulletTypeName(type.getID()));
            bulletTypes.put(type.getID(), type);
        }

        // damage types
        int[] damageTypeData = getDamageTypes();
        for (int index = 0; index < damageTypeData.length; index += DamageType.numAttributes) {
            DamageType type = new DamageType(damageTypeData, index);
            type.setName(getDamageTypeName(type.getID()));
            damageTypes.put(type.getID(), type);
        }

        // explosion types
        int[] explosionTypeData = getExplosionTypes();
        for (int index = 0; index < explosionTypeData.length; index += ExplosionType.numAttributes) {
            ExplosionType type = new ExplosionType(explosionTypeData, index);
            type.setName(getExplosionTypeName(type.getID()));
            explosionTypes.put(type.getID(), type);
        }

        // unitCommand types
        int[] unitCommandTypeData = getUnitCommandTypes();
        for (int index = 0; index < unitCommandTypeData.length; index += UnitCommandType.numAttributes) {
            UnitCommandType type = new UnitCommandType(unitCommandTypeData, index);
            type.setName(getUnitCommandTypeName(type.getID()));
            unitCommandTypes.put(type.getID(), type);
        }

        // order types
        int[] orderTypeData = getOrderTypes();
        for (int index = 0; index < orderTypeData.length; index += OrderType.numAttributes) {
            OrderType type = new OrderType(orderTypeData, index);
            type.setName(getOrderTypeName(type.getID()));
//			System.out.println("ID: "+ type.getID()+" Name: "+ type.getName());
            orderTypes.put(type.getID(), type);
        }
    }

    /**
     * Loads data for the current map e.g. width and also runs BWTA analysis on the map.
     * You only need to call this method once in the gameStarted call back.
     * <b>WARNING:</b> This will take several seconds to analize the map, but will save a
     * file of the analysis so subsequent loads of the same map will not encounter this
     * delay. You must have a bwapi-data folder in the same folder where you are running
     * the command to launch your AI.
     * @param enableBWTA
     */
    public void loadMapData(boolean enableBWTA) {
        map = new Map(getMapWidth(), getMapHeight(), getMapName(), getMapHash(), getHeightData(), getBuildableData(), getWalkableData());
        if (!enableBWTA) {
            return;
        }

        // get region and choke point data
        int[] regionData = null;
        int[] chokePointData = null;
        int[] baseLocationData = null;
        HashMap<Integer, int[]> polygons = new HashMap<Integer, int[]>();

        // run BWTA
        analyzeTerrain();
        regionData = getRegions();
        chokePointData = getChokePoints();
        baseLocationData = getBaseLocations();
        for (int index = 0; index < regionData.length; index += Region.numAttributes) {
            int id = regionData[index];
            polygons.put(id, getPolygon(id));
        }

        // regions
        HashMap<Integer, Region> regionMap = new HashMap<Integer, Region>();
        for (int index = 0; index < regionData.length; index += Region.numAttributes) {
            Region region = new Region(regionData, index);
            region.setCoordinates(polygons.get(region.getID()));
            map.getRegions().add(region);
            regionMap.put(region.getID(), region);
        }

        // choke points
        if (chokePointData != null) {
            for (int index = 0; index < chokePointData.length; index += ChokePoint.numAttributes) {
                ChokePoint chokePoint = new ChokePoint(chokePointData, index);
                chokePoint.setFirstRegion(regionMap.get(chokePoint.getFirstRegionID()));
                chokePoint.setSecondRegion(regionMap.get(chokePoint.getSecondRegionID()));
                map.getChokePoints().add(chokePoint);
            }
        }

        // base locations
        if (baseLocationData != null) {
            for (int index = 0; index < baseLocationData.length; index += BaseLocation.numAttributes) {
                BaseLocation baseLocation = new BaseLocation(baseLocationData, index);
                map.getBaseLocations().add(baseLocation);
            }
        }

        // connect the region graph
        for (Region region : map.getRegions()) {
            for (ChokePoint chokePoint : map.getChokePoints()) {
                if (chokePoint.getFirstRegion().equals(region) || chokePoint.getSecondRegion().equals(region)) {
                    region.getChokePoints().add(chokePoint);
                    region.getConnectedRegions().add(chokePoint.getOtherRegion(region));
                }
            }
        }
    }

    /**
     * Instantiates a BWAPI instance, but does not connect to the bridge. To
     * connect, the start method must be invokeed.
     *
     * @param listener - listener for BWAPI callback events.
     */
    public JNIBWAPI(BWAPIEventListener listener) {
        this.listener = listener;
    }

    /**
     * Invokes the native library which will connect to the bridge and then invoke
     * callback functions.
     * <p/>
     * Note: this method never returns, it should be invoked from a separate
     * thread if concurrent java processing is needed.
     */
    public void start() {
        startClient(this);
    }

    /**
     * C++ callback function.
     * <p/>
     * Utility function for printing to the java console from C++.
     */
    public void javaPrint(String msg) {
        try {
            System.out.println("Bridge: " + msg);
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Notifies the event listener that a connection has been formed to the bridge.
     */
    public void connected() {
        try {
            listener.connected();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Notifies the event listener that a game has started.
     */
    public void gameStarted() {
        try {

            // get the players
            allies.clear();
            allyIDs.clear();
            enemies.clear();
            enemyIDs.clear();
            players.clear();

            int[] playerData = getPlayerInfo();
            for (int index = 0; index < playerData.length; index += Player.numAttributes) {
                Player player = new Player(playerData, index);
                players.put(player.getID(), player);

                if (player.isSelf()) {
                    self = player;
                } else if (player.isAlly()) {
                    allies.add(player);
                    allyIDs.add(player.getID());
                } else if (player.isEnemy()) {
                    enemies.add(player);
                    enemyIDs.add(player.getID());
                }
            }

            // get unit data
            units.clear();
            playerUnits.clear();
            alliedUnits.clear();
            enemyUnits.clear();
            neutralUnits.clear();
            int[] unitData = getUnits();

            for (int index = 0; index < unitData.length; index += Unit.numAttributes) {
                int id = unitData[index];
                Unit unit = new Unit(id);
                unit.update(unitData, index);

                units.put(id, unit);
                if (self != null) {
                    if (unit.getPlayerID() == self.getID()) {
                        playerUnits.add(unit);
                    } else if (allyIDs.contains(unit.getPlayerID())) {
                        alliedUnits.add(unit);
                    } else if (enemyIDs.contains(unit.getPlayerID())) {
                        enemyUnits.add(unit);
                    } else {
                        neutralUnits.add(unit);
                    }
                } else if (allyIDs.contains(unit.getPlayerID())) {
                    alliedUnits.add(unit);
                } else if (enemyIDs.contains(unit.getPlayerID())) {
                    enemyUnits.add(unit);
                } else {
                    neutralUnits.add(unit);
                }
            }

            listener.gameStarted();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Notifies the event listener that a game update occurred.
     */
    private void gameUpdate() {
        try {
            // update game state
            gameFrame = getGameFrame();
            if (!isReplay()) {
                self.update(getPlayerUpdate(self.getID()));
                self.updateResearch(getResearchStatus(self.getID()), getUpgradeStatus(self.getID()));
            } else {
                for (Integer playerID : players.keySet()) {
                    players.get(playerID).update(getPlayerUpdate(playerID));
                    players.get(playerID).updateResearch(getResearchStatus(playerID), getUpgradeStatus(playerID));
                }
            }
            // update units
            int[] unitData = getUnits();
            HashSet<Integer> deadUnits = new HashSet<Integer>(units.keySet());
            ArrayList<Unit> playerList = new ArrayList<Unit>();
            ArrayList<Unit> alliedList = new ArrayList<Unit>();
            ArrayList<Unit> enemyList = new ArrayList<Unit>();
            ArrayList<Unit> neutralList = new ArrayList<Unit>();

            for (int index = 0; index < unitData.length; index += Unit.numAttributes) {
                int id = unitData[index];

                // bugfix - unit list was emptying itself every second frame
                deadUnits.remove(id);

                Unit unit = units.get(id);
                if (unit == null) {
                    unit = new Unit(id);
                    units.put(id, unit);
                }

                unit.update(unitData, index);

                if (self != null) {
                    if (unit.getPlayerID() == self.getID()) {
                        playerList.add(unit);
                    } else if (allyIDs.contains(unit.getPlayerID())) {
                        alliedList.add(unit);
                    } else if (enemyIDs.contains(unit.getPlayerID())) {
                        enemyList.add(unit);
                    } else {
                        neutralList.add(unit);
                    }
                } else if (allyIDs.contains(unit.getPlayerID())) {
                    alliedList.add(unit);
                } else if (enemyIDs.contains(unit.getPlayerID())) {
                    enemyList.add(unit);
                } else {
                    neutralList.add(unit);
                }
            }

            // update the unit lists
            playerUnits = playerList;
            alliedUnits = alliedList;
            enemyUnits = enemyList;
            neutralUnits = neutralList;
            for (Integer unitID : deadUnits) {
                units.get(unitID).setDestroyed();
                units.remove(unitID);
            }

            listener.gameUpdate();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Notifies the event listener that the game has terminated.
     * <p/>
     * Note: this is always called after matchEnded(bool), and is meant as a
     * way of notifying the AI client to clear up state.
     */
    private void gameEnded() {
        try {
            listener.gameEnded();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Sends BWAPI callback events to the event listener.
     *
     * @param type - event type (should probably be an enum)
     */
    private void eventOccured(int type, int param1, int param2) {
        try {
            switch (type) {
                case 0:
                    listener.matchEnded(param1 == 1);
                    break;
                case 1:
                    listener.playerLeft(param1);
                    break;
                case 2:
                    listener.nukeDetect(param2, param2);
                    break;
                case 3:
                    listener.nukeDetect();
                    break;
                case 4:
                    listener.unitDiscover(param1);
                    break;
                case 5:
                    listener.unitEvade(param1);
                    break;
                case 6:
                    listener.unitShow(param1);
                    break;
                case 7:
                    listener.unitHide(param1);
                    break;
                case 8:
                    listener.unitCreate(param1);
                    break;
                case 9:
                    listener.unitDestroy(param1);
                    break;
                case 10:
                    listener.unitMorph(param1);
                    break;
                case 11:
                    listener.unitComplete(param1);
            }
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * C++ callback function.
     * <p/>
     * Notifies the event listener that a key was pressed.
     */
    public void keyPressed(int keyCode) {
        try {
            listener.keyPressed(keyCode);
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * Retreives the last error set by BWAPI
     * @return
     */
    public BWError getLastError() {
        int errorId = getLastErrorBWAPI();
        return BWError.fromId(errorId);
    }
    
    public Set<Unit> getUnitsInRectangle(Position topLeft, Position bottomRight) {
        int[] unitIds = getUnitsInRectangleBWAPI(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
        return unitsFromIds(unitIds);
    }
    
    public Set<Unit> getUnitsInRadius(Position center, int radius) {
        int[] unitIds = getUnitsInRadiusBWAPI(center.getX(), center.getY(), radius);
        return unitsFromIds(unitIds);
    }
    
    private Set<Unit> unitsFromIds(int[] unitIds) {
        final Set<Unit> units = new HashSet<Unit>(unitIds.length);
        for (int id : unitIds) {
            units.add(getUnit(id));
        }
        return units;
    }
}
