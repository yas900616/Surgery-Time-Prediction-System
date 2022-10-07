package guiComponent;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.Info;
import guiFunction.LoadFile;

/**資料顯示表格*/
public abstract class AbstractPreview extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1998540816189543580L;
	private enum Mode {WITHOUT_IMPORT, NOTHING, ALL};
	private JFileChooser chooser;
	private JLabel title;
	private JButton importButton, popUpButton, storeButton;
	private ViewTable viewTable;
	private static String[] imageName = {"\\import.png", "\\popUp.png", "\\store.png"};
	private static ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 30, 30);
	
	/**建立無任何按鈕的預覽表格*/
	public static AbstractPreview newNothingSheet() {
		return new AbstractPreview(Mode.NOTHING) {
			private static final long serialVersionUID = 2668494334011211477L;
			@Override
			public void actionPerformed(ActionEvent e) {}//按下匯入按鈕要做的動作，不需實作
		};
	}
	
	/**建立包含匯出及彈窗的預覽表格*/
	public static AbstractPreview newSheetWithoutImport() {
		return new AbstractPreview(Mode.WITHOUT_IMPORT) {
			private static final long serialVersionUID = -7704785758861677448L;
			@Override
			public void actionPerformed(ActionEvent e) {}//按下匯入按鈕要做的動作，不需實作
		};
	}
	
	/**建立包含匯入、匯出及彈窗的預覽表格*/
	public AbstractPreview() {
		this(Mode.ALL);
	}
	
	private AbstractPreview(Mode mode) {
		viewTable = new ViewTable();
		title = new JLabel("無資料可預覽", JLabel.CENTER);
		
		// 只有表格
		switch(mode) {
		case NOTHING:
			setLayout(new BorderLayout());
			add(title, BorderLayout.NORTH);
			add(viewTable, BorderLayout.CENTER);
			break;
		case ALL:
			// 包含匯入資料集彈窗、儲存按鈕及表格
			importButton = new JButton(icon[0]);
			importButton.setToolTipText("匯入檔案");
			importButton.addActionListener(this);
		case WITHOUT_IMPORT:
			setLayout(new GridBagLayout());
			chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
			
			popUpButton = new JButton(icon[1]);
			popUpButton.setToolTipText("彈出新視窗，以完整檢視該表格");
			popUpButton.addActionListener(listener);
			
			storeButton = new JButton(icon[2]);
			storeButton.setToolTipText("儲存該表格");
			storeButton.addActionListener(listener);

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.anchor = GridBagConstraints.CENTER;
			add(title, gbc);
			
			if(importButton != null) {
				gbc = new GridBagConstraints();
				add(importButton, gbc);
			}

			gbc = new GridBagConstraints();
			add(popUpButton, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(storeButton, gbc);

			gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.BOTH;
			add(viewTable, gbc);
			break;
		}
	}

	/**設定表格標題*/
	public void setTableTitle(String text) {
		title.setText(text);
	}
	
	/**按下彈窗按鈕 | 按下儲存按鈕*/
	private ActionListener listener = e -> {
		if (e.getSource() == popUpButton) {			// 按下彈窗按鈕
		} else if (e.getSource() == storeButton) {	// 按下儲存按鈕
			chooser.showSaveDialog(this);
		}
	};

	private class ViewTable extends JScrollPane { // 為相容於java所有版本，內部類有函式宣告為靜態，所屬類別也須同為靜態
		private static final long serialVersionUID = 129864934755437301L;

		public ViewTable() {
			JTable table = new JTable();
			table.setToolTipText("雙擊可自動調整欄位大小");
			setViewportView(table);
		}
	}
}
