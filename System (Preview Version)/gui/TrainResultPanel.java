package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;

class TrainResultPanel extends JPanel {
	private static final long serialVersionUID = 7225862284829363231L;
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private JButton save = new JButton("�x�s�ҫ�"),
			lookUp = new JButton("�˵����Ҷ�"),
			done = new JButton("�����إ�");
	private JTextArea resultText = new JTextArea();
	private AbstractPreview featureSheet = AbstractPreview.newNothingSheet(),
			showPandA = AbstractPreview.newNothingSheet(),
			popTable = AbstractPreview.newNothingSheet();
	
	public TrainResultPanel() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Model File", "model"));
		
		setLayout(new GridLayout(1, 2, 10, 10));
		resultText.setEditable(false);
		
		featureSheet.setTableTitle("�S�x�Ψ䭫�n��");
		showPandA.setTableTitle("��ڭȻP�w���Ȫ����");
		popTable.setTableTitle("�˵����Ҷ�");
		
		JScrollPane resultScroll = new JScrollPane(resultText);
		resultScroll.setViewportBorder(new TitledBorder(null, "�V�m�ҫ����Ӹ`", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		
		JSplitPane infoSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, resultScroll, featureSheet);
		infoSplit.setBorder(null);
		infoSplit.setDividerLocation(infoSplit.getPreferredSize().height / 2);
		add(infoSplit);
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(lookUp);
		buttonBox.add(Box.createHorizontalStrut(5));
		buttonBox.add(save);
		buttonBox.add(Box.createHorizontalStrut(5));
		buttonBox.add(done);
		
		JPanel viewPanel = new JPanel(new BorderLayout(10, 10));
		viewPanel.add(showPandA, BorderLayout.CENTER);
		viewPanel.add(buttonBox, BorderLayout.SOUTH);
		add(viewPanel);
	}
}
