package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;

class MethodSelectPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = -8033801386610992572L;
	private String wekaTip = "Weka是由紐西蘭懷卡托大學使用Java開發的資料探勘軟體，被廣為接受。";
	private String bpnnTip = "自主研發的倒傳遞神經網路。請注意！所有欄位內容均須為數值。";	
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private JComboBox<String> combo = new JComboBox<>();
	private JTextArea result = new JTextArea();
	private JTextField info = new JTextField(wekaTip);
	private JButton confirm = new JButton("確定");
	private TrainPreview trainPreview = new TrainPreview();
	private TestPreview testPreview = new TestPreview();
	
	public MethodSelectPanel() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		setLayout(new BorderLayout(10, 10));
		
		JLabel label = new JLabel("請選擇欲使用的預測系統：");
		combo.addItemListener(this);
		Box chooseBox = Box.createHorizontalBox();
		chooseBox.add(label);
		chooseBox.add(combo);
		
		result.setEditable(false);
		result.setLineWrap(true);
		JScrollPane resultScroll = new JScrollPane(result);
		resultScroll.setViewportBorder(new TitledBorder(null, "訓練模型時的詳細資訊", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		
		//==========頁面左上半==========
		JPanel propertyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0, 0, 10, 0);
		propertyPanel.add(chooseBox, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		JSplitPane classifierSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JPanel(), resultScroll);
		propertyPanel.add(classifierSplit, gbc);
		classifierSplit.setDividerLocation(classifierSplit.getPreferredSize().width / 2);
		
		//==========頁面右上半==========
		trainPreview.setTableTitle("訓練集");
		testPreview.setTableTitle("驗證集");
		JSplitPane previewSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, trainPreview, testPreview);
		previewSplit.setBorder(LineBorder.createBlackLineBorder());
		previewSplit.setDividerLocation(previewSplit.getPreferredSize().height / 2);
		
		//==========主頁面==========
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		mainPanel.add(propertyPanel);
		mainPanel.add(previewSplit);
		add(mainPanel, BorderLayout.CENTER);
		
		//==========頁面最下面==========
		info.setEditable(false);
		
		Box confirmBox = Box.createHorizontalBox();
		confirmBox.add(info);
		confirmBox.add(Box.createHorizontalStrut(5));
		confirmBox.add(confirm);
		add(confirmBox, BorderLayout.SOUTH);
	}
	
	
	/**點選演算法列表*/
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (ItemEvent.SELECTED == e.getStateChange()) {
			int index = combo.getSelectedIndex();
			if(index == 3) {	// 若選擇BPNN
				info.setText(bpnnTip);
			}else {
				info.setText(wekaTip);
			}
		}
	}
	
	
	/**顯示訓練集*/
	private class TrainPreview extends AbstractPreview {
		private static final long serialVersionUID = -3161485467044773055L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
	
	/**顯示驗證集*/
	private class TestPreview extends AbstractPreview {
		private static final long serialVersionUID = -8733890928778564081L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
