package eisbot.proxy;

import java.util.HashSet;

import eisbot.proxy.model.Unit;
import eisbot.proxy.types.UnitType;

/**
 * Example Java AI Client using JNI-BWAPI. 
 * 
 * Executes a 5-pool rush and cheats using perfect information.
 * 
 * Note: the agent often gets stuck when attempting to build the spawning pool. 
 * It works best on maps where the overlord spawns with plenty of free space 
 * around it.
 */
public class ExampleAIClient implements BWAPIEventListener {

	/** reference to JNI-BWAPI */
	private JNIBWAPI bwapi;

	/** used for mineral splits */
	private HashSet<Integer> claimed = new HashSet<Integer>();

	/** has drone 5 been morphed */
	private boolean morphedDrone = false;
	
	/** has a drone been assigned to building a pool? */
	private int poolDrone = -1;

	/** when should the next overlord be spawned? */
	private int supplyCap = 0;

	/**
	 * Create a Java AI.
	 */
	public static void main(String[] args) {
		new ExampleAIClient();
	}

	/**
	 * Instantiates the JNI-BWAPI interface and connects to BWAPI.
	 */
	public ExampleAIClient() {
		bwapi = new JNIBWAPI(this);
		bwapi.start();
	} 

	/**
	 * Connection to BWAPI established.
	 */
	public void connected() {
		bwapi.loadTypeData();
	}
	
	/**
	 * Called at the beginning of a game.
	 */
	public void gameStarted() {		
		System.out.println("Game Started");
		
		bwapi.enableUserInput();
		bwapi.enablePerfectInformation();
		bwapi.setGameSpeed(0);
		bwapi.loadMapData(true);

		// reset agent state
		claimed.clear();
		morphedDrone = false;
		poolDrone = -1;
		supplyCap = 0;
	}
	
	/**
	 * Called each game cycle.
	 */
	public void gameUpdate() {
	
		// spawn a drone
		for (Unit unit : bwapi.getMyUnits()) {
			if (unit.getType() == UnitType.Zerg_Larva) {
				if (bwapi.getSelf().getMinerals() >= 50 && !morphedDrone) {
					bwapi.morph(unit.getID(), UnitType.Zerg_Drone);
					morphedDrone = true;
				}
			}
		}
				
		// collect minerals
		for (Unit unit : bwapi.getMyUnits()) {
			if (unit.getTypeID() == UnitType.Zerg_Drone.ordinal()) {
				if (unit.isIdle() && unit.getID() != poolDrone) {
					
					for (Unit minerals : bwapi.getNeutralUnits()) {
						if (minerals.getTypeID() == UnitType.Resource_Mineral_Field.ordinal() && !claimed.contains(minerals.getID())) {
							double distance = Math.sqrt(Math.pow(minerals.getPosition().getX() - unit.getPosition().getX(), 2) + Math.pow(minerals.getPosition().getY() - unit.getPosition().getY(), 2));
							
							if (distance < 300) {
								bwapi.rightClick(unit.getID(), minerals.getID());
								claimed.add(minerals.getID());
								break;
							}
						}
					}					
				}
			}
		}
		
		// build a spawning pool
		if (bwapi.getSelf().getMinerals() >= 200 && poolDrone < 0) {
			for (Unit unit : bwapi.getMyUnits()) {
				if (unit.getTypeID() == UnitType.Zerg_Drone.ordinal()) {
					poolDrone = unit.getID();
					break;
				}
			}
			
			// build the pool under the overlord
			for (Unit unit : bwapi.getMyUnits()) {
				if (unit.getTypeID() == UnitType.Zerg_Overlord.ordinal()) {
					bwapi.build(poolDrone, unit.getTilePosition().getX(), unit.getTilePosition().getY(), UnitType.Zerg_Spawning_Pool.ordinal());
				}				
			}
		}
		
		// spawn overlords
		if (bwapi.getSelf().getSupplyUsed() + 2 >= bwapi.getSelf().getSupplyTotal() && bwapi.getSelf().getSupplyTotal() > supplyCap) {			
			if (bwapi.getSelf().getMinerals() >= 100) {
				for (Unit larva : bwapi.getMyUnits()) {
					if (larva.getTypeID() == UnitType.Zerg_Larva.ordinal()) {
						bwapi.morph(larva.getID(), UnitType.Zerg_Overlord);
						supplyCap = bwapi.getSelf().getSupplyTotal();
					}
				}									
			}
		}
		// spawn zerglings
		else if (bwapi.getSelf().getMinerals() >= 50) {
			for (Unit unit : bwapi.getMyUnits()) {
				if (unit.getTypeID() == UnitType.Zerg_Spawning_Pool.ordinal() && unit.isCompleted()) {
					for (Unit larva : bwapi.getMyUnits()) {
						if (larva.getTypeID() == UnitType.Zerg_Larva.ordinal()) {
							bwapi.morph(larva.getID(), UnitType.Zerg_Zergling);
						}
					}					
				}
			}
		}

		// attack
		for (Unit unit : bwapi.getMyUnits()) {
			if (unit.getTypeID() == UnitType.Zerg_Zergling.ordinal() && unit.isIdle()) {
				for (Unit enemy : bwapi.getEnemyUnits()) {
                                        bwapi.attackMove(unit, enemy.getPosition());
					break;
				}
			}
		}
	}

    @Override
	public void gameEnded() {}
    @Override
	public void keyPressed(int keyCode) {}
    @Override
	public void matchEnded(boolean winner) {}
    @Override
	public void nukeDetect(int x, int y) {}
    @Override
	public void nukeDetect() {}
    @Override
	public void playerLeft(int id) {}
    @Override
	public void unitCreate(int unitID) {}
    @Override
	public void unitDestroy(int unitID) {}
    @Override
	public void unitDiscover(int unitID) {}
    @Override
	public void unitEvade(int unitID) {}
    @Override
	public void unitHide(int unitID) {}
    @Override
	public void unitMorph(int unitID) {}
    @Override
	public void unitShow(int unitID) {}

    @Override
    public void unitComplete(int unitID) {
    }
}
