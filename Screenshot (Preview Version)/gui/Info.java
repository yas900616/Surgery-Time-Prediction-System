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
	/**�ϥΪ̪��ୱ���|*/
	private static String desktop = System.getProperty("user.home") + "\\Desktop";	//�ୱ���|
	static String getDesktop() {
		return desktop;
	}
	/**�B�z�y�{���I��*/
	private static String proc = "ProcessView";
	static String getProc() {
		return proc;
	}
}