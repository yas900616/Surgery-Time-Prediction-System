package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;

import guiComponent.AbstractFileTree;
import guiComponent.AbstractPreview;
import guiFunction.LoadFile;

class SelectFolderPanel extends JPanel {
	private static final long serialVersionUID = -6332451822625149595L;
	private JButton importButton = new JButton(LoadFile.fromIcon(Info.getIconPath(), "\\folder.png", 35, 35));
	private JTextField showPath = new JTextField();
	private AbstractPreview sheet = AbstractPreview.newNothingSheet();
	private FileTree fileTree = new FileTree();

	public SelectFolderPanel() {
		setLayout(new BorderLayout(10, 10));
		importButton.setToolTipText("選擇資料夾路徑");
		
		showPath.setEditable(false);
		Box northBox = Box.createHorizontalBox();
		northBox.add(importButton);
		northBox.add(Box.createHorizontalStrut(10));
		northBox.add(showPath);
		add(northBox, BorderLayout.NORTH);
		
		fileTree.setViewportBorder(new TitledBorder(LineBorder.createBlackLineBorder(), "目錄", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		fileTree.setPreferredSize(new Dimension(300, getHeight()));
		add(fileTree, BorderLayout.WEST);
		add(sheet, BorderLayout.CENTER);
	}
	
	private class FileTree extends AbstractFileTree {
		private static final long serialVersionUID = 5974961810914587782L;

		@Override
		public void valueChanged(TreeSelectionEvent e) {
		}
	}
}