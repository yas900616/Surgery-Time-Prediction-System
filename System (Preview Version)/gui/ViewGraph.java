package gui;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import guiComponent.AbstractPreview;

class ViewGraph extends JFrame implements ItemListener {
	private static final long serialVersionUID = 890470383572123962L;
	private JTextField[] rangeText = new JTextField[2];
	private JButton checkButton = new JButton("�T�w");
	private AbstractPreview sheet = AbstractPreview.newNothingSheet();
	private String[] items = {"������", "����", "��u��"};
	private JComboBox<String> fileCombo = new JComboBox<>(),
			graphCombo = new JComboBox<>(items);

	public ViewGraph() {
		setLayout(new BorderLayout(10, 10));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("��ܲέp�Ϫ�");
		setSize(1024, 768);
		setLocationRelativeTo(null);

		JLabel[] text = new JLabel[2];
		text[0] = new JLabel("��ܪ��ɮ׬��G");
		text[1] = new JLabel("�����ͪ��Ϫ��G");
		
		fileCombo.addItemListener(this);
		graphCombo.addItemListener(this);

		JLabel[] rangeLabel = new JLabel[4];
		rangeLabel[0] = new JLabel("�]�w�d��G��", JLabel.CENTER);
		rangeLabel[1] = new JLabel("��", JLabel.CENTER);
		rangeLabel[2] = new JLabel(" ~ ��", JLabel.CENTER);
		rangeLabel[3] = new JLabel("��", JLabel.CENTER);
		
		rangeText[0] = new JTextField(); // �ﶵ��J�Ʀr
		rangeText[1] = new JTextField();
		
		sheet.setBorder(LineBorder.createBlackLineBorder());
		
		Box chooseBox = Box.createHorizontalBox();
		chooseBox.add(text[0]);
		chooseBox.add(fileCombo);
		chooseBox.add(Box.createHorizontalStrut(15));
		chooseBox.add(text[1]);
		chooseBox.add(graphCombo);
		chooseBox.add(Box.createHorizontalStrut(15));
		
		Box setBox = Box.createHorizontalBox();
		setBox.add(rangeLabel[0]);	// JLabel �]�w���ն����G
		setBox.add(rangeText[0]);	// ��J�Ʀr ��
		setBox.add(rangeLabel[1]);	// JLabel % | ��
		setBox.add(rangeLabel[2]);	// JLabel ~
		setBox.add(rangeText[1]);	// ��J�Ʀr �k
		setBox.add(rangeLabel[3]);	// JLabel %����� | �������
		setBox.add(checkButton);
		
		Box northBox = Box.createVerticalBox();
		northBox.add(chooseBox);
		northBox.add(Box.createVerticalStrut(10));
		northBox.add(setBox);
		add(northBox, BorderLayout.NORTH);

		JSplitPane previewPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sheet, new JPanel());
		previewPanel.setBorder(null);
		previewPanel.setDividerLocation(previewPanel.getPreferredSize().width / 2);
		add(previewPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(e.getSource() == fileCombo) {
				
			}else if(e.getSource() == graphCombo) {
				rangeText[0].setText("");
				rangeText[1].setText("");
			}
		}
	}
}