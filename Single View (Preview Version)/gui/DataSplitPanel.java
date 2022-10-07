package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import guiComponent.AbstractPreview;

class DataSplitPanel extends JPanel {
	private static final long serialVersionUID = -835851681402783283L;
	private OptionsPanel optionsPanel = new OptionsPanel();
	private AbstractPreview trainPreview = AbstractPreview.newSheetWithoutImport(),
			testPreview = AbstractPreview.newSheetWithoutImport();

	public DataSplitPanel() {
		setLayout(new BorderLayout(10, 10));
		add(optionsPanel, BorderLayout.NORTH);
		
		trainPreview.setTableTitle("拆分結果(訓練集)");
		testPreview.setTableTitle("拆分結果(驗證集)");
		JSplitPane previewPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trainPreview, testPreview);
		previewPanel.setBorder(null);
		previewPanel.setDividerLocation(previewPanel.getPreferredSize().width / 2);
		add(previewPanel, BorderLayout.CENTER);
	}
	
	private class OptionsPanel extends JPanel implements ItemListener {
		private static final long serialVersionUID = -1626768545221257752L;
		private JCheckBox randomCheck = new JCheckBox("打亂資料集");
		private JRadioButton percentRadio = new JRadioButton("百分比", true),
				numberRadio = new JRadioButton("筆數", false);
		private JTextField[] splitText = new JTextField[2];
		private JTextField randomText = new JTextField();
		private JLabel dataCount = new JLabel("尚無資料");
		private JLabel[] splitLabel = new JLabel[5];
		private JButton importData = new JButton("匯入資料集"),
				lookup = new JButton("檢視資料集"),
				confirm = new JButton("確定產生");
		
		public OptionsPanel() {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			Box buttonUpper = Box.createHorizontalBox();
			buttonUpper.add(importData);
			buttonUpper.add(Box.createHorizontalStrut(5));
			buttonUpper.add(lookup);
			
			Box buttonLower = Box.createHorizontalBox();
			buttonLower.add(new JLabel("資料總筆數："));
			buttonLower.add(dataCount);
			buttonLower.add(Box.createHorizontalGlue());
			
			Box buttonBox = Box.createVerticalBox();
			buttonBox.add(buttonUpper);
			buttonBox.add(buttonLower);
			add(buttonBox);
			add(Box.createHorizontalStrut(30));
			//
			randomCheck.addItemListener(this);
			randomText.setEnabled(false);

			Box randomUpper = Box.createHorizontalBox();
			randomUpper.add(randomCheck);
			randomUpper.add(Box.createHorizontalGlue());

			Box randomLower = Box.createHorizontalBox();
			randomLower.add(new JLabel("種子碼：", JLabel.CENTER));
			randomLower.add(randomText);
			Box randomBox = Box.createVerticalBox();
			randomBox.add(randomUpper);
			randomBox.add(randomLower);
			add(randomBox);
			add(Box.createHorizontalStrut(30));
			//
			splitLabel[0] = new JLabel("使用單位：", JLabel.CENTER);
			splitLabel[1] = new JLabel("設定驗證集為：", JLabel.CENTER);
			splitLabel[2] = new JLabel("%", JLabel.CENTER);
			splitLabel[3] = new JLabel("~", JLabel.CENTER);
			splitLabel[4] = new JLabel("%的資料", JLabel.CENTER);
			
			percentRadio.addItemListener(this);

			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(percentRadio);
			radioGroup.add(numberRadio);

			Box splitUpper = Box.createHorizontalBox();
			splitUpper.add(splitLabel[0]); // JLabel 使用單位:
			splitUpper.add(percentRadio);
			splitUpper.add(numberRadio);
			//
			splitText[0] = new JTextField(); // 於資料拆分選項輸入數字
			splitText[0].setColumns(15);
			splitText[1] = new JTextField(); // 於資料拆分選項輸入數字
			splitText[1].setColumns(15);

			Box splitLower = Box.createHorizontalBox();
			splitLower.add(splitLabel[1]); 	// JLabel 設定驗證集為：
			splitLower.add(splitText[0]); 	// 輸入數字 左
			splitLower.add(splitLabel[2]); 	// JLabel % | 筆
			splitLower.add(splitLabel[3]);	// JLabel ~
			splitLower.add(splitText[1]); 	// 輸入數字 右
			splitLower.add(splitLabel[4]); 	// JLabel %的資料 | 筆的資料
			
			JPanel splitPanel = new JPanel(new GridLayout(2, 1));
			splitPanel.add(splitUpper);
			splitPanel.add(splitLower);
			add(splitPanel);
			add(Box.createHorizontalStrut(30));
			//
			add(confirm);
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == randomCheck) {	//打亂資料集，輸入種子碼
				if(ItemEvent.SELECTED == e.getStateChange()) {
					randomText.setEnabled(true);
				}else {
					randomText.setEnabled(false);
					randomText.setText("");
				}
			}else if(e.getSource() == percentRadio) {	// percentRadio	| 針對百分比RadioButton
				if (ItemEvent.SELECTED == e.getStateChange()) {
					splitLabel[2].setText("%");
					splitLabel[4].setText("%的資料");
				} else { 	// 筆數
					splitLabel[2].setText("筆");
					splitLabel[4].setText("筆的資料");
				}
			}
		}
	}
}
