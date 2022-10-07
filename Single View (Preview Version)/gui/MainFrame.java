package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

class MainFrame extends JFrame {
	private static final long serialVersionUID = -3121824897664058141L;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UIManager.put("OptionPane.messageDialogTitle", "提示訊息");
				Font mainFont = new Font("微軟正黑體", Font.PLAIN, 20);
				UIManager.put("Label.font", mainFont);
				UIManager.put("Button.font", mainFont);
				UIManager.put("RadioButton.font", mainFont);
				UIManager.put("ComboBox.font", mainFont);
				UIManager.put("CheckBox.font", mainFont);
				UIManager.put("TextField.font", mainFont);
				UIManager.put("TextArea.font", mainFont);
				UIManager.put("ProgressBar.font", mainFont);
				UIManager.put("TitledBorder.font", mainFont);
				UIManager.put("TabbedPane.font", mainFont);
				Color mainColor = Color.decode("#e9ebfe");
				UIManager.put("info", mainColor);
				UIManager.put("control", mainColor);
				UIManager.put("Panel.background", mainColor);
				UIManager.put("RadioButton.background", mainColor);
				UIManager.put("CheckBox.background", mainColor);
				UIManager.put("TextField.background", mainColor);
				UIManager.put("TextArea.background", mainColor);
				UIManager.put("OptionPane.background", mainColor);
				UIManager.put("ComboBox.background", mainColor);
				UIManager.put("TabbedPane.background", mainColor);
				UIManager.put("Button.background", Color.decode("#e8f4ff"));
				UIManager.put("SplitPane.dividerSize", 10);
				UIManager.put("FileChooser.readOnly", true);	// 將檔案選擇器設為唯讀
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				
				if(args[0].equals("OperatingManual")) {
					new OperatingManual().setVisible(true);
				}else {
					new MainFrame(args[0]).setVisible(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public MainFrame(String frame) {
		setTitle("「醫」刻千金 · 料「術」如神 手術時間預測系統");
		getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		switch(frame) {
		case "ChoosePanel":
			setContentPane(new ChoosePanel());
			break;
		case "SelectFolderPanel":
			setContentPane(new SelectFolderPanel());
			break;
		case "DataTransformPanel":
			setContentPane(new DataTransformPanel());
			break;
		case "DataHandlePanel":
			setContentPane(new DataHandlePanel());
			break;
		case "FeatureSelectPanel":
			setContentPane(new FeatureSelectPanel());
			break;
		case "DataSplitPanel":
			setContentPane(new DataSplitPanel());
			break;
		case "MethodSelectPanel":
			setContentPane(new MethodSelectPanel());
			break;
		case "TrainResultPanel":
			setContentPane(new TrainResultPanel());
			break;
		case "MainPagePanel":
			setContentPane(new MainPagePanel());
			break;
		}
	}
}
