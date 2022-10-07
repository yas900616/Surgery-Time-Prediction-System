package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;

class FeatureSelectPanel extends JPanel {
	private static final long serialVersionUID = 2449969085578476145L;
	private JButton confirmButton = new JButton("確認並選取");
	private BeforeSheet beforeSheet = new BeforeSheet();
	private AbstractPreview afterSheet = AbstractPreview.newSheetWithoutImport();

	public FeatureSelectPanel() {
		setLayout(new BorderLayout(5, 5));
		
		beforeSheet.setTableTitle("資料內容(選取前)");
		afterSheet.setTableTitle("資料內容(選取後)");
		JSplitPane previewPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, beforeSheet, afterSheet);
		previewPanel.setDividerLocation(previewPanel.getPreferredSize().height / 2);
		add(previewPanel, BorderLayout.CENTER);
		
		JLabel tipLabel = new JLabel("請選取要用於預測的特徵：");
		
		JPanel eastPanel = new JPanel(new GridBagLayout());
		add(eastPanel, BorderLayout.EAST);
		Insets inset = new Insets(5, 0, 5, 0);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 0.5;
		gbc.insets = inset;
		gbc.anchor = GridBagConstraints.LINE_START;
		eastPanel.add(tipLabel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 0.5;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = inset;
		eastPanel.add(new JPanel(), gbc);

		gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = inset;
		eastPanel.add(confirmButton, gbc);
	}
	
	private class BeforeSheet extends AbstractPreview {
		private static final long serialVersionUID = -7287660204910605281L;
		private JFileChooser chooser = new JFileChooser(Info.getDesktop());
		public BeforeSheet() {
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
