package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;
import guiFunction.LoadFile;

class MainPagePanel extends JPanel {
	private static final long serialVersionUID = 1180611674900194495L;
	private String[] imageName = {"\\single.png", "\\multiple.png", "\\folder.png"};
	private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 30, 30);
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private JTextArea info = new JTextArea();
	private JButton startPredict = new JButton("開始預測"),
			loadModel = new JButton("載入模型");
	private JTabbedPane optionPane = new JTabbedPane();	//特徵選項切換頁籤
	private MultiplePreview multiplePreview = new MultiplePreview();
	
	public MainPagePanel() {// 開始建立模型按鈕的監聽器
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Model File", "model"));
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane singleScroll = new JScrollPane();
		singleScroll.setViewportBorder(new TitledBorder(null, "選擇欲預測的選項", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		optionPane.addTab("單筆資料預測", icon[0], singleScroll, "選擇各特徵的選項以預測單筆手術資料");
		optionPane.addTab("多筆資料預測", icon[1], multiplePreview, "匯入檔案以預測多筆手術資料");
		
		info.setLineWrap(true);
		info.setEditable(false);
		JScrollPane infoScroll = new JScrollPane(info);
		infoScroll.setViewportBorder(new TitledBorder(null, "使用模型的詳細資訊", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		centerPanel.add(optionPane);
		centerPanel.add(infoScroll);
		add(centerPanel, BorderLayout.CENTER);
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(startPredict);
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(loadModel);
		add(buttonBox, BorderLayout.SOUTH);
	}
	
	/**多筆資料預測 匯入資料預覽*/
	private class MultiplePreview extends AbstractPreview {
		private static final long serialVersionUID = 1705315329772510531L;
		private JFileChooser chooser = new JFileChooser(Info.getDesktop());
		private MultiplePreview() {
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
			setTableTitle("多筆資料預測內容");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
