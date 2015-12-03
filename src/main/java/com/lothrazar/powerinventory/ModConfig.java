package com.lothrazar.powerinventory;
 
import com.lothrazar.powerinventory.inventory.slot.*;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
 
public class ModConfig
{  
	public static boolean showSortButtons;
	public static boolean showCornerButtons;
	public static boolean showCharacter;
	public static boolean showMergeDeposit;
	public static boolean enderPearl64;
	
	public static String smallMedLarge;

	public static int filterRange; 
	public static int expPerBottle;
	  
	public static boolean enableCompatMode;
	
	public static Configuration config;
	public static boolean enableSlotOutlines;
	public static boolean minecart64;
	public static boolean boat64;
	public static boolean doors64;
	public static boolean snowballs64;
	public static boolean food64;
	public static boolean bucket64;
	public static boolean alwaysShowHungerbar;
	public static boolean smallerMergeDep;
	public static String[] blacklist_in;
	public static String[] blacklist_out;
	public static final String categoryHighlander = "can_change_ingame";

	public static void loadConfig(Configuration c) 
	{
		config = c;
    	config.load();
    	syncConfig();
	}
	
	public static void syncConfig()
	{
    	// decide which ones can be altered in game - there can be only one
    	String category = categoryHighlander;
		
    	ModConfig.filterRange = config.getInt("button_filter_range", category, 12, 1, 32, "Range of the filter button to reach nearby chests");
		ModConfig.showCharacter = config.getBoolean("show_character",category,true,"Show or hide the animated character text in the inventory");
		ModConfig.showSortButtons = config.getBoolean("move_inventory_buttons",category,true,"Show or hide the inventory shifting buttons << >>");
		ModConfig.showCornerButtons = config.getBoolean("show_corner_buttons",category,true,"Show or hide the corner inventory buttons in other GUI's");
		
		ModConfig.showMergeDeposit = config.getBoolean("merge_deposit_buttons", category, true, "Show or hide the merge deposit buttons in upper right corner.");
		ModConfig.smallerMergeDep = config.getBoolean("smaller_merge_deposit", category, true, "Show the merge deposit buttons as squares with a single letter.");
		
		
		ModConfig.expPerBottle = config.getInt("exp_per_bottle", category, 10, 1, 11, "The exp cost of filling a single bottle.  Remember, the Bottle 'o Enchanting gives 3-11 experience when used, so it is never an exact two-way conversion.  ");
		
		ModConfig.enableSlotOutlines = config.getBoolean("slot_outlines",category,true,"Normally the special slots have outlines to indicate the allowed items, but you can disable them here.  ");

		ModConfig.alwaysShowHungerbar = config.getBoolean("always_show_hunger",category,true,"Always show hunger bar - even while mounted.  Horse health will show above the hunger bar.");

		
		ModConfig.smallMedLarge = config.getString("main_size", category, "normal", "Valid values are only exactly 'normal', 'small', 'large'.    Changes your inventory size, for use if your GUI Scale requirements are different.  normal = regular 15x25 inventory size, small = 6x18.  WARNING: EMPTY YOUR PLAYERS INVENTORY IN A CHEST before changing this.  And to be safe, BACKUP YOUR WORLD!");
		
		
		// TODO exp cost for uncrafting added (default zero)  

		//TODO enable/disable 3x3 crafting
		
		
		category = "stack_to_64";

		//these dont seem to work without restarting the game
		ModConfig.enderPearl64 = config.get( category,"ender_pearl", true).getBoolean();
		ModConfig.minecart64 = config.get(category,"minecarts",  true).getBoolean();
		ModConfig.boat64 = config.get(category,"boats",  true).getBoolean();
		ModConfig.doors64 = config.get(category,"doors",  true).getBoolean();
		ModConfig.snowballs64 = config.get(category,"snowballs",  true).getBoolean();
		ModConfig.food64 = config.get(category,"allfood_cake_eggs_stew",  true).getBoolean();//cookie, stews, cakes
		ModConfig.bucket64 = config.get(category,"empty_bucket",  true).getBoolean();
		//?? maybe? record and horse armor?saddle?
		
		
		//category = "warning_advanced";
		
		//config.addCustomCategoryComment(category, "This section is for disabling and changes major features.  Always empty your inventory before changing these.");
		
		
		
		//category = Configuration.CATEGORY_GENERAL;//"warning_advanced";
 
		category = "uncrafting";
		
		ModConfig.blacklist_in = config.getString("blacklist_in", category, "", "You can block items from being uncrafted here.  Put a comma (,) between each item, and make sure you use the syntax with minecraft:item or modid:item.  For example:  minecraft:stone,minecraft:dirt")
				.split(",");
		ModConfig.blacklist_out = config.getString("blacklist_out", category, "minecraft:milk_bucket", "Items that cannot be given as output to uncrafted recipes.  However, items containing these recipes will still be uncrafted.  For example, milk is the default here so you can still uncraft cake to the other ingredients.")
				.split(",");
		
		
		
		
		category = "warning_compatibility";
		config.addCustomCategoryComment(category, "Compatibility mode is intended for advanced users and modpack creators. "
				+ "It is intended to be turned on if you are using other mods that crash the game or conflict with this mod in some way.  For example, mods that alter the vanilla inventory using ASM techniques, or mods that add tabs (such as Tinkers construct or Custom NPCs).");
		
		
		ModConfig.enableCompatMode =  config.getBoolean("compatibility_mode",category,false,"False is the regular mod with everything normal.  "
				+ "True will give you the regular vanilla inventory, not replaced or changed in any way for compatibility reasons.  Instead, push the upper right button to use the mini version.");
	 
	 
		
		enableCompatMode = true;

		if(config.hasChanged()){config.save();}
		
		
		
		
		setupGuiFromConfig();
	}

	private static void setupGuiFromConfig()
	{
		/*
		if(ModConfig.smallMedLarge.equalsIgnoreCase("normal"))
		{
			BigContainerPlayer.ALL_ROWS = 15;
			BigContainerPlayer.ALL_COLS = 25;

			
			GuiBigInventory.texture_width = 464;
			GuiBigInventory.texture_height = 382;
		}
		
		else if(ModConfig.smallMedLarge.equalsIgnoreCase("small"))
		{ 
			ModConfig.smallMedLarge = "small";
			
			BigContainerPlayer.ALL_ROWS = 6; 
			BigContainerPlayer.ALL_COLS = 18; 

			GuiBigInventory.texture_width = 338;
			GuiBigInventory.texture_height = 221;
 
		}
		else //if(ModConfig.smallMedLarge.equalsIgnoreCase("large"))
		{
			//default to large BECAUSE< if they have a typo, we do not wan
			// to make it super small and cause data loss
			BigContainerPlayer.ALL_ROWS = 16; 
			BigContainerPlayer.ALL_COLS = 27; 

			GuiBigInventory.texture_width = 500;
			GuiBigInventory.texture_height = 400;

		}
		
		GuiBigInventory.backgroundTexture = new ResourceLocation(Const.MODID,"textures/gui/inventory_" 
						+ BigContainerPlayer.ALL_ROWS + "x" + BigContainerPlayer.ALL_COLS + ".png");
		BigInventoryPlayer.INVOSIZE  = BigContainerPlayer.ALL_COLS * BigContainerPlayer.ALL_ROWS;
*/
		int xSlotColumn;
		int xSlotRightSide;
		
	
			int texture_width = 176;//width of vanilla inventory.png same number as in source code
			
			xSlotColumn = Const.paddingLrg;
			
			xSlotRightSide = texture_width - Const.SQ - Const.padding - 1;
	
		/*
		else
		{
		    xSlotColumn = Const.WIDTH_CHARARMOR + Const.paddingLrg; 

		    xSlotRightSide = GuiBigInventory.texture_width - Const.SQ - Const.padding - 1;
		}*/

		//for each column we set the first slot, and then the rest relative to that one
		
		//all our special slots are Singletons.
		//that is, only one appears per inventory
		
		//four slots on left column
		SlotEnderPearl.posY = Const.paddingLrg; 	
		SlotEnderChest.posY = Const.paddingLrg + 3 * Const.SQ;  

		SlotEnderPearl.posX = xSlotColumn; 
		SlotEnderChest.posX = xSlotColumn; 
		 
	}
}
