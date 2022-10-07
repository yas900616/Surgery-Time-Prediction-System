package gui;

public class Info {
	
	/**使用說明手冊路徑*/
	private static String manualPath ="Manual";
	static String getManualPath() {
		return manualPath;
	}
	/**icon路徑*/
	private static String iconPath = "icon";
	public static String getIconPath() {
		return iconPath;
	}
	/**使用者的桌面路徑*/
	private static String desktop = System.getProperty("user.home") + "\\Desktop";	//桌面路徑
	static String getDesktop() {
		return desktop;
	}
	/**處理流程的截圖*/
	private static String proc = "ProcessView";
	static String getProc() {
		return proc;
	}
}