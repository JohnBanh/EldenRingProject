package game;

import java.util.Arrays;
import java.util.List;

import engine.displays.Display;
import engine.positions.FancyGroundFactory;
import engine.positions.GameMap;
import engine.positions.World;
import game.actors.Traders.Enia;
import game.actors.Traders.MerchantKale;
import game.actors.Player;

import game.environments.*;
import game.items.consumables.GodGodFruit;
import game.items.consumables.GoldenRunes;
import game.items.consumables.GoldenSeeds;
import game.items.consumables.SugonMushroom;
import game.positions.*;
import game.utils.FancyMessage;
import game.utils.MenuManagerAdmin;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

/**
 * The main class to start the game.
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new SiteOfLostGrace(), new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Barrack(), new Cage()
		, new Cliff(), new SummonSign());

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				".........&&&..........#..___....____#.........................+++++........",
				".........&&&..........#...........__#..........nnnnn.............++........",
				".........&&&..........#_____........#..........nnnnn..............+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"..................................................................~~~~~~~~~",
				"...............................................................~~~~~~~~~~~~",
				"........++++......................###___###.....................~~~~~~~~~~~",
				"........+++++++...................________#..........................~~~~~~",
				"~~~~~~~~~~+++.....................#___U____......=................~~~~~~~~~",
				"~~~~~~~~~~~~+++...................#_______#................................",
				"~~~~~~~~~~~~~+....................###___###................................",
				"~~~~~~~~~~~~++......................#___#........................&&&.......",
				"~~~~~~~~......+..................................................&&&.......",
				"..............++.................................................&&&.......",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++.........nnnnn.............+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++...nnnnn................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____..............<..............<..............................",
				".........____..............................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		GameMap limgraveMap = new GameMap(groundFactory, limgrave);
		GameMap stormveilCastleMap = new GameMap(groundFactory, stormveilCastle);
		GameMap roundtableHoldMap = new GameMap(groundFactory, roundtableHold);
		GameMap bossRoomMap = new GameMap(groundFactory, bossRoom);
		world.addGameMap(limgraveMap);
		world.addGameMap(stormveilCastleMap);
		world.addGameMap(roundtableHoldMap);
		world.addGameMap(bossRoomMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}


		// HINT: what does it mean to prefer composition to inheritance?
		Player player; //This is the player instance, the player will become whatever the user chooses (e.g. Samurai or Bandit)
		MenuManagerAdmin menuManager = MenuManagerAdmin.getInstance();
		limgraveMap.at(39,12).addActor(new MerchantKale());
		limgraveMap.at(37,12).addActor(new Enia());
		int selection;
			selection = menuManager.menuItem();
			switch (selection) {
				//Samurai
				case 1:
					player = Player.getInstance("Tarnished", '@', 455);
					player.addWeaponToInventory(new Uchigatana());//Add weapon
					player.setSpawn(limgraveMap.at(38, 14));
					world.addPlayer(player, player.getSpawn());
					//for testing purposes player spawns with newly implemented consumable items
//					player.addItemToInventory(new GoldenSeeds());
//					player.addItemToInventory(new GoldenRunes());
//					player.addItemToInventory(new GodGodFruit());
//					player.addItemToInventory(new SugonMushroom());
//					player.addItemToInventory(new RemembranceOfTheGrafted());
					break;
				//Bandit
				case 2:

					player = Player.getInstance("Tarnished", '@', 414);
					player.addWeaponToInventory(new GreatKnife());
					player.setSpawn(limgraveMap.at(38, 14));
					world.addPlayer(player, player.getSpawn());
					break;
				//Wretch
				case 3:

					player = Player.getInstance("Tarnished", '@', 414);
					player.addWeaponToInventory(new Club());
					player.setSpawn(limgraveMap.at(38, 14));
					world.addPlayer(player, player.getSpawn());
					break;
				//Astrologer
				case 4:
					player = Player.getInstance("Tarnished", '@', 396);
					player.addWeaponToInventory(new Club());//Didn't implement optional weapon so added club
					player.setSpawn(limgraveMap.at(38, 14));
					world.addPlayer(player, player.getSpawn());
					break;
				default:
					System.out.println("Not a correct input");
			}

		//limgraveMap.at(38,15).addActor(new Ally(1000, new Uchigatana()));
		//Scattering of consumable items on across maps
		limgraveMap.at(25,13).addItem(new GoldenRunes());
		limgraveMap.at(60,15).addItem(new GoldenRunes());
		limgraveMap.at(15,4).addItem(new GoldenRunes());
		stormveilCastleMap.at(25,13).addItem(new GoldenRunes());
		stormveilCastleMap.at(60,16).addItem(new GoldenRunes());
		stormveilCastleMap.at(15,5).addItem(new GoldenRunes());
		limgraveMap.at(53,10).addItem(new GoldenSeeds());
		limgraveMap.at(65,2).addItem(new GoldenSeeds());
		limgraveMap.at(35,8).addItem(new GoldenSeeds());
		limgraveMap.at(57,12).addItem(new GodGodFruit());
		limgraveMap.at(65,5).addItem(new GodGodFruit());
		limgraveMap.at(29,10).addItem(new GodGodFruit());
		limgraveMap.at(20,11).addItem(new SugonMushroom());
		limgraveMap.at(40,9).addItem(new SugonMushroom());
		limgraveMap.at(26,18).addItem(new SugonMushroom());

		//Set Grounds
		//locations.add(new LocationStringPair(limgraveMap.at(5, 5), "Meow 2"));
		limgraveMap.at(5, 22).setGround(new FogDoor(new LocationStringPair(roundtableHoldMap.at(5, 5), "Round Table Hold")));
		limgraveMap.at(29, 2).setGround(new FogDoor(new LocationStringPair(stormveilCastleMap.at(38, 21), "Stormveil Castle")));
		stormveilCastleMap.at(38, 21).setGround(new FogDoor(new LocationStringPair(limgraveMap.at(38, 12), "Limgrave Spawn")));
		stormveilCastleMap.at(38, 6).setGround(new FogDoor(new LocationStringPair(bossRoomMap.at(5, 5), "Boss room")));
		roundtableHoldMap.at(5, 5).setGround(new FogDoor(new LocationStringPair(limgraveMap.at(38, 12), "Limgrave Spawn")));
		world.run();
	}
}
