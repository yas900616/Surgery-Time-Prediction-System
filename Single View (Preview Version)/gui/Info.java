package gui;

public class Info {
	
	/**�ϥλ�����U���|*/
	private static String manualPath ="Manual";
	static String getManualPath() {
		return manualPath;
	}
	/**icon���|*/
	private static String iconPath = "icon";
	public static String getIconPath() {
		return iconPath;
	}
	
	// ============================================
	private static String desktop = System.getProperty("user.home") + "\\Desktop";	//�ୱ���|
	/**���o�ϥΪ̪��ୱ���|*/
	static String getDesktop() {
		return desktop;
	}
}