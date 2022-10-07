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
	private String wekaTip = "Weka�O�ѯæ����h�d���j�Ǩϥ�Java�}�o����Ʊ��ɳn��A�Q�s�������C";
	private String bpnnTip = "�ۥD��o���˶ǻ����g�����C�Ъ`�N�I�Ҧ���줺�e�������ƭȡC";	
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private JComboBox<String> combo = new JComboBox<>();
	private JTextArea result = new JTextArea();
	private JTextField info = new JTextField(wekaTip);
	private JButton confirm = new JButton("�T�w");
	private TrainPreview trainPreview = new TrainPreview();
	private TestPreview testPreview = new TestPreview();
	
	public MethodSelectPanel() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		setLayout(new BorderLayout(10, 10));
		
		JLabel label = new JLabel("�п�ܱ��ϥΪ��w���t�ΡG");
		combo.addItemListener(this);
		Box chooseBox = Box.createHorizontalBox();
		chooseBox.add(label);
		chooseBox.add(combo);
		
		result.setEditable(false);
		result.setLineWrap(true);
		JScrollPane resultScroll = new JScrollPane(result);
		resultScroll.setViewportBorder(new TitledBorder(null, "�V�m�ҫ��ɪ��ԲӸ�T", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		
		//==========�������W�b==========
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
		
		//==========�����k�W�b==========
		trainPreview.setTableTitle("�V�m��");
		testPreview.setTableTitle("���Ҷ�");
		JSplitPane previewSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, trainPreview, testPreview);
		previewSplit.setBorder(LineBorder.createBlackLineBorder());
		previewSplit.setDividerLocation(previewSplit.getPreferredSize().height / 2);
		
		//==========�D����==========
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		mainPanel.add(propertyPanel);
		mainPanel.add(previewSplit);
		add(mainPanel, BorderLayout.CENTER);
		
		//==========�����̤U��==========
		info.setEditable(false);
		
		Box confirmBox = Box.createHorizontalBox();
		confirmBox.add(info);
		confirmBox.add(Box.createHorizontalStrut(5));
		confirmBox.add(confirm);
		add(confirmBox, BorderLayout.SOUTH);
	}
	
	
	/**�I��t��k�C��*/
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (ItemEvent.SELECTED == e.getStateChange()) {
			int index = combo.getSelectedIndex();
			if(index == 3) {	// �Y���BPNN
				info.setText(bpnnTip);
			}else {
				info.setText(wekaTip);
			}
		}
	}
	
	
	/**��ܰV�m��*/
	private class TrainPreview extends AbstractPreview {
		private static final long serialVersionUID = -3161485467044773055L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
	
	/**������Ҷ�*/
	private class TestPreview extends AbstractPreview {
		private static final long serialVersionUID = -8733890928778564081L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
