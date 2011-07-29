/**
*	Created to simplify my life.
*		false = no, true = yes
*/

public class Config {

	public static int portONID = 43594; //Clients port.
	public static int Revision = 525; //Client's revision.

	//public static String serverName = "Project Fatality"; //Server's IP adress. EX: pkscape.no-ip.biz
public static String serverName = "0"; //Server's IP adress. EX: pkscape.no-ip.biz
	public static String ClientName = "Project Fatality"; //Client's Name.
	public static String ClientWebsite = "www.project-fatality.com"; //You're servers forums/website.
	public static String FrameTitle = "Project Fatality"; //Title after you client name.
	public static String cacheLink = 
		"http://www.project-fatality.net/projectfatalityv6.zip"; //Website were your cache is stored.
	public static boolean //Everything that can be toggled.
		detail525 = true, //Model's used, 525 or 474 w/ some 525 models.
		is474 = true, //Gameframe style 474 revision.
		is525 = false, //Gameframe style 525 revision.
		idToggle = false, //Enable/Disabled the Child IDs on Interfaces.
		newHitBar = false, //Newer hitpoint bar, or the old hitpoint bar.
		menuToggle = false, //Newer Context menu, or the old Context menu.
		namesToggle = false, //Names above a player/npcs head.
		allowSky = false, //Allow the FOG, or the BLACK.
		hitSplat = false,//Hitmarks - New expand, Old regular, Old Expand
		displayHP = false;//Hitpoints above a player/npcs head
	public static boolean //Everything to do with the login screen.
		loadBackground = false, //Allow the usage of a custom background. Currently set to 'Don't load the original 377 background'.
		loadLogo = true, //Allow the usage of a custom logo. Currently set to 'Don't load the logo'.
		loadFlames = false; //Allow the loading of flames. Currently set to 'Don't load the flames'.
	public static boolean //Everything to do with Webclient.
		loadAsWebclient = false; //Sets the client as a webclient. (Unused)
	
	//Misc.
	public static boolean hotKeysEn = false; //Disabled / Enable the use of hotkeys.
}