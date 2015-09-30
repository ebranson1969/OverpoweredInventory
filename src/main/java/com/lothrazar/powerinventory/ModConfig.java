package com.lothrazar.powerinventory;

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
	

	public static boolean enableUncrafting;
	public static boolean enableEnchantBottles;
	
	public static boolean enableCompatMode;
	

	private static Configuration config;

	public static void loadConfig(Configuration c) 
	{
		config = c;
    	config.load();
    	 
    	String category = Configuration.CATEGORY_GENERAL;
		
    	ModConfig.filterRange = config.getInt("button_filter_range", category, 12, 1, 32, "Range of the filter button to reach nearby chests");
		ModConfig.showCharacter = config.getBoolean("show_character",category,true,"Show or hide the animated character text in the inventory");
		ModConfig.showSortButtons = config.getBoolean("move_inventory_buttons",category,true,"Show or hide the inventory shifting buttons << >>");
		ModConfig.showCornerButtons = config.getBoolean("show_corner_buttons",category,true,"Show or hide the corner inventory buttons in other GUI's");
		ModConfig.enderPearl64 = config.getBoolean("ender_pearl_64", category, true, "Stack to 64 instead of 16");
		
		ModConfig.showMergeDeposit = config.getBoolean("merge_deposit_buttons", category, true, "Show or hide the merge deposit buttons in upper right corner.");
		ModConfig.expPerBottle = config.getInt("exp_per_bottle", category, 10, 1, 11, "The exp cost of filling a single bottle.  Remember, the Bottle 'o Enchanting gives 3-11 experience when used, so it is never an exact two-way conversion.  ");
		  
		ModConfig.enableUncrafting = config.getBoolean("enable_uncrafting",category,true,"Lets you disable the uncrafting slot and button");
		ModConfig.enableEnchantBottles =  config.getBoolean("enable_enchantbottles",category,true,"Lets you disable the enchanting bottle filling slot and button");
		
		
		
		ModConfig.enableCompatMode =  config.getBoolean("compatibility_mode",category,false,"!!!");
		
		
		ModConfig.smallMedLarge = config.getString("normal_small", category, "normal", "Valid values are only exactly normal/small.    Changes your inventory size, for use if your GUI Scale requirements are different.  normal = regular 15x25 inventory size, small = 6x18.  WARNING: EMPTY YOUR PLAYERS INVENTORY IN A CHEST before changing this.  And to be safe, BACKUP YOUR WORLD!");
		
		if(ModConfig.smallMedLarge.equalsIgnoreCase("normal"))
		{

			Const.MORE_ROWS = 12;//texture 15x25
		 
			Const.MORE_COLS = 16;

			Const.texture_width = 464;
			Const.texture_height = 382;
		    Const.INVENTORY_TEXTURE = "textures/gui/inventory_15x25.png";//375 total
		}
		else//assume its small
		{
			Const.MORE_ROWS = 3;
		 
			Const.MORE_COLS = 9;

			Const.texture_width = 338;
			Const.texture_height = 221;
		    Const.INVENTORY_TEXTURE = "textures/gui/inventory_6x18.png";//6*18 is 108..so yeah?
		}

		Const.ALL_COLS = 9 + Const.MORE_COLS;
		Const.ALL_ROWS = 3 + Const.MORE_ROWS;
		Const.INVOSIZE  = Const.ALL_COLS * Const.ALL_ROWS;
		
		
		if(ModConfig.enableCompatMode)
		{
			Const.texture_width = 176;
			Const.texture_height = 166;
			
			int charSpace = 54 + 18;// moving stuff left
			
			//TODO: these get set twice, or more, we should fix this whole setup but for now just get it working
			Const.compassX -= charSpace;
			Const.clockX -= charSpace;
			Const.pearlX -= charSpace;
			Const.echestX -= charSpace;
			
			Const.bottleX = Const.texture_width - Const.square - Const.padding - 1;
			 
			Const.uncraftX = Const.bottleX;
		}
		
		
		
		
		if(config.hasChanged()){config.save();}
	}
    
	//2: add in/fix the armor background images
	
	
	//3: option to turn off background images (including armor)
	
	
	//4 more 64 stacking
	
	
	
	
	
	//turn onn/off the uncrafting and ehcnat bottling
	
	
	//turn off version checker 
	
	
	
	// exp cost for uncrafting added (default zero)  
	
	


	//?? enable/disable 3x3 crafting
	// ???: make the left 9 columns actually go vertically down so it matches player invo
}
