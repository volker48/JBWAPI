package eisbot.proxy.types;

import java.util.HashMap;

/**
 * Contains information about specific units that does not change e.g. build time.
 * 
 * For a description of fields see: http://code.google.com/p/bwapi/wiki/UnitInfo
 */
public class UnitInfo {

	public static final int numAttributes = 56;
	public static final double fixedScale = 100.0;

	private String name;
	private int ID;
	private int raceID;
	private int whatBuildID;
	private int armorUpgradeID;
	private int maxHitPoints;
	private int maxShields;
	private int maxEnergy;
	private int armor;
	private int mineralPrice;
	private int gasPrice;
	private int buildTime;
	private int supplyRequired;
	private int supplyProvided;
	private int spaceRequired;
	private int spaceProvided;
	private int buildScore;
	private int destroyScore;
	private int sizeID;
	private int tileWidth;
	private int tileHeight;
	private int dimensionLeft;
	private int dimensionUp;
	private int dimensionRight;
	private int dimensionDown;
	private int seekRange;
	private int sightRange;
	private int groundWeaponID;
	private int maxGroundHits;
	private int airWeaponID;
	private int maxAirHits;
	private double topSpeed;
	private int acceleration;
	private int haltDistance;
	private int turnRadius;
	private boolean produceCapable;
	private boolean attackCapable;
	private boolean canMove;
	private boolean flyer;
	private boolean regenerates;
	private boolean spellcaster;
	private boolean invincible;
	private boolean organic;
	private boolean mechanical;
	private boolean robotic;
	private boolean detector;
	private boolean resourceContainer;
	private boolean refinery;
	private boolean worker;
	private boolean requiresPsi;
	private boolean requiresCreep;
	private boolean burrowable;
	private boolean cloakable;
	private boolean building;
	private boolean addon;
	private boolean flyingBuilding;
	private boolean spell;

    public UnitInfo(int[] data, int index) {
		ID = data[index++];
		raceID = data[index++];
		whatBuildID = data[index++];
		armorUpgradeID = data[index++];
		maxHitPoints = data[index++];
		maxShields = data[index++];
		maxEnergy = data[index++];
		armor = data[index++];
		mineralPrice = data[index++];
		gasPrice = data[index++];
		buildTime = data[index++];
		supplyRequired = data[index++];
		supplyProvided = data[index++];
		spaceRequired = data[index++];
		spaceProvided = data[index++];
		buildScore = data[index++];
		destroyScore = data[index++];
		sizeID = data[index++];
		tileWidth = data[index++];
		tileHeight = data[index++];
		dimensionLeft = data[index++];
		dimensionUp = data[index++];
		dimensionRight = data[index++];
		dimensionDown = data[index++];
		seekRange = data[index++];
		sightRange = data[index++];
		groundWeaponID = data[index++];
		maxGroundHits = data[index++];
		airWeaponID = data[index++];
		maxAirHits = data[index++];
		topSpeed = ((double)data[index++])/fixedScale;
		acceleration = data[index++];
		haltDistance = data[index++];
		turnRadius = data[index++];
		produceCapable = data[index++] == 1;
		attackCapable = data[index++] == 1;
		canMove = data[index++] == 1;
		flyer = data[index++] == 1;
		regenerates = data[index++] == 1;
		spellcaster = data[index++] == 1;
		invincible = data[index++] == 1;
		organic = data[index++] == 1;
		mechanical = data[index++] == 1;
		robotic = data[index++] == 1;
		detector = data[index++] == 1;
		resourceContainer = data[index++] == 1;
		refinery = data[index++] == 1;
		worker = data[index++] == 1;
		requiresPsi = data[index++] == 1;
		requiresCreep = data[index++] == 1;
		burrowable = data[index++] == 1;
		cloakable = data[index++] == 1;
		building = data[index++] == 1;
		addon = data[index++] == 1;
		flyingBuilding = data[index++] == 1;
		spell = data[index++] == 1;
	};
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public int getRaceID() {
		return raceID;
	}

	public int getWhatBuildID() {
		return whatBuildID;
	}

	public int getArmorUpgradeID() {
		return armorUpgradeID;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getArmor() {
		return armor;
	}

	public int getMineralPrice() {
		return mineralPrice;
	}

	public int getGasPrice() {
		return gasPrice;
	}

	public int getBuildTime() {
		return buildTime;
	}

	public int getSupplyRequired() {
		return supplyRequired;
	}

	public int getSupplyProvided() {
		return supplyProvided;
	}

	public int getSpaceRequired() {
		return spaceRequired;
	}

	public int getSpaceProvided() {
		return spaceProvided;
	}

	public int getBuildScore() {
		return buildScore;
	}

	public int getDestroyScore() {
		return destroyScore;
	}

	public int getSizeID() {
		return sizeID;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getDimensionLeft() {
		return dimensionLeft;
	}

	public int getDimensionUp() {
		return dimensionUp;
	}

	public int getDimensionRight() {
		return dimensionRight;
	}

	public int getDimensionDown() {
		return dimensionDown;
	}

	public int getSeekRange() {
		return seekRange;
	}

	public int getSightRange() {
		return sightRange;
	}

	public int getGroundWeaponID() {
		return groundWeaponID;
	}

	public int getMaxGroundHits() {
		return maxGroundHits;
	}

	public int getAirWeaponID() {
		return airWeaponID;
	}

	public int getMaxAirHits() {
		return maxAirHits;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public int getHaltDistance() {
		return haltDistance;
	}

	public int getTurnRadius() {
		return turnRadius;
	}

	public boolean isProduceCapable() {
		return produceCapable;
	}

	public boolean isAttackCapable() {
		return attackCapable;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public boolean isFlyer() {
		return flyer;
	}

	public boolean isRegenerates() {
		return regenerates;
	}

	public boolean isSpellcaster() {
		return spellcaster;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public boolean isOrganic() {
		return organic;
	}

	public boolean isMechanical() {
		return mechanical;
	}

	public boolean isRobotic() {
		return robotic;
	}

	public boolean isDetector() {
		return detector;
	}

	public boolean isResourceContainer() {
		return resourceContainer;
	}

	public boolean isRefinery() {
		return refinery;
	}

	public boolean isWorker() {
		return worker;
	}

	public boolean isRequiresPsi() {
		return requiresPsi;
	}

	public boolean isRequiresCreep() {
		return requiresCreep;
	}

	public boolean isBurrowable() {
		return burrowable;
	}

	public boolean isCloakable() {
		return cloakable;
	}

	public boolean isBuilding() {
		return building;
	}

	public boolean isAddon() {
		return addon;
	}

	public boolean isFlyingBuilding() {
		return flyingBuilding;
	}

	public boolean isSpell() {
		return spell;
	}
}
