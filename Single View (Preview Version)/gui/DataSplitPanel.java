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
		
		trainPreview.setTableTitle("������G(�V�m��)");
		testPreview.setTableTitle("������G(���Ҷ�)");
		JSplitPane previewPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trainPreview, testPreview);
		previewPanel.setBorder(null);
		previewPanel.setDividerLocation(previewPanel.getPreferredSize().width / 2);
		add(previewPanel, BorderLayout.CENTER);
	}
	
	private class OptionsPanel extends JPanel implements ItemListener {
		private static final long serialVersionUID = -1626768545221257752L;
		private JCheckBox randomCheck = new JCheckBox("���ø�ƶ�");
		private JRadioButton percentRadio = new JRadioButton("�ʤ���", true),
				numberRadio = new JRadioButton("����", false);
		private JTextField[] splitText = new JTextField[2];
		private JTextField randomText = new JTextField();
		private JLabel dataCount = new JLabel("�|�L���");
		private JLabel[] splitLabel = new JLabel[5];
		private JButton importData = new JButton("�פJ��ƶ�"),
				lookup = new JButton("�˵���ƶ�"),
				confirm = new JButton("�T�w����");
		
		public OptionsPanel() {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			Box buttonUpper = Box.createHorizontalBox();
			buttonUpper.add(importData);
			buttonUpper.add(Box.createHorizontalStrut(5));
			buttonUpper.add(lookup);
			
			Box buttonLower = Box.createHorizontalBox();
			buttonLower.add(new JLabel("����`���ơG"));
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
			randomLower.add(new JLabel("�ؤl�X�G", JLabel.CENTER));
			randomLower.add(randomText);
			Box randomBox = Box.createVerticalBox();
			randomBox.add(randomUpper);
			randomBox.add(randomLower);
			add(randomBox);
			add(Box.createHorizontalStrut(30));
			//
			splitLabel[0] = new JLabel("�ϥγ��G", JLabel.CENTER);
			splitLabel[1] = new JLabel("�]�w���Ҷ����G", JLabel.CENTER);
			splitLabel[2] = new JLabel("%", JLabel.CENTER);
			splitLabel[3] = new JLabel("~", JLabel.CENTER);
			splitLabel[4] = new JLabel("%�����", JLabel.CENTER);
			
			percentRadio.addItemListener(this);

			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(percentRadio);
			radioGroup.add(numberRadio);

			Box splitUpper = Box.createHorizontalBox();
			splitUpper.add(splitLabel[0]); // JLabel �ϥγ��:
			splitUpper.add(percentRadio);
			splitUpper.add(numberRadio);
			//
			splitText[0] = new JTextField(); // ���Ʃ���ﶵ��J�Ʀr
			splitText[0].setColumns(15);
			splitText[1] = new JTextField(); // ���Ʃ���ﶵ��J�Ʀr
			splitText[1].setColumns(15);

			Box splitLower = Box.createHorizontalBox();
			splitLower.add(splitLabel[1]); 	// JLabel �]�w���Ҷ����G
			splitLower.add(splitText[0]); 	// ��J�Ʀr ��
			splitLower.add(splitLabel[2]); 	// JLabel % | ��
			splitLower.add(splitLabel[3]);	// JLabel ~
			splitLower.add(splitText[1]); 	// ��J�Ʀr �k
			splitLower.add(splitLabel[4]); 	// JLabel %����� | �������
			
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
			if(e.getSource() == randomCheck) {	//���ø�ƶ��A��J�ؤl�X
				if(ItemEvent.SELECTED == e.getStateChange()) {
					randomText.setEnabled(true);
				}else {
					randomText.setEnabled(false);
					randomText.setText("");
				}
			}else if(e.getSource() == percentRadio) {	// percentRadio	| �w��ʤ���RadioButton
				if (ItemEvent.SELECTED == e.getStateChange()) {
					splitLabel[2].setText("%");
					splitLabel[4].setText("%�����");
				} else { 	// ����
					splitLabel[2].setText("��");
					splitLabel[4].setText("�������");
				}
			}
		}
	}
}
