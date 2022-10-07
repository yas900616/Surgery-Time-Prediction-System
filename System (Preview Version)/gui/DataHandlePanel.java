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
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;
import guiFunction.LoadFile;

class DataHandlePanel extends JPanel {
	private static final long serialVersionUID = 7437323078040768459L;
	private BeforeSheet beforeSheet = new BeforeSheet();// 原始資料(資料轉檔資料)
	private AbstractPreview afterSheet = AbstractPreview.newSheetWithoutImport(); 	// 預覽資料(彈窗後要顯示的資料集)
	private AbstractPreview confirmSheet = AbstractPreview.newNothingSheet();	// 與使用者確認步驟執行結果
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private HandlePanel handlePanel = new HandlePanel();
	private ToolPanel toolPanel = new ToolPanel();
	
	public DataHandlePanel() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		setLayout(new BorderLayout());
		
		add(toolPanel, BorderLayout.WEST); 		// 製作工具欄
		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.add(handlePanel);	// 製作步驟欄
		centerPanel.add(new PreviewPanel());// 製作預覽欄
		add(centerPanel, BorderLayout.CENTER);
		//
		confirmSheet.setTableTitle("步驟執行後的結果");
	}
	
	private class ToolPanel extends JScrollPane {
		private static final long serialVersionUID = 4936944520744858424L;
		private String[] imageName = {"\\RemoveOutlier.png", "\\RemoveOutlierBySD.png", "\\RemoveRecord.png", "\\RemoveFeature.png",
				"\\ExtractRecord.png", "\\TargetEncoding.png", "\\NormalizeFeature.png", "\\StandardizeFeature.png"};
		private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 80, 80); 	// Button的圖案
		private JButton erButton, nfButton, sfButton, roButton, rosdButton, rrButton, rfButton, teButton;// 工具欄的按鈕

		public ToolPanel() {
			setViewportBorder(new TitledBorder(null, "工具欄", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JPanel content = new JPanel(new GridLayout(8, 1, 5, 5));
			setViewportView(content);
			
			roButton = new JButton(icon[0]);
			roButton.setToolTipText("移除指定欄位離群值(僅限數值欄位)");
			content.add(roButton);
			
			rosdButton = new JButton(icon[1]);
			rosdButton.setToolTipText("以標準差為基準，移除指定欄位離群值(僅限數值欄位)");
			content.add(rosdButton);

			rrButton = new JButton(icon[2]);
			rrButton.setToolTipText("移除指定欄位之特定內容值");
			content.add(rrButton);

			rfButton = new JButton(icon[3]);
			rfButton.setToolTipText("移除指定欄位");
			content.add(rfButton);

			erButton = new JButton(icon[4]);
			erButton.setToolTipText("提取指定欄位之特定內容值");
			content.add(erButton);

			teButton = new JButton(icon[5]);
			teButton.setToolTipText("指定欄位數值化(適用非數值欄位)");
			content.add(teButton);

			nfButton = new JButton(icon[6]);
			nfButton.setToolTipText("指定欄位標準化(適用數值欄位)");
			content.add(nfButton);

			sfButton = new JButton(icon[7]);
			sfButton.setToolTipText("指定欄位標準化(僅限數值欄位)");
			content.add(sfButton);
		}
	}

	private class HandlePanel extends JPanel {
		private static final long serialVersionUID = 6451689933054716323L;
		private JButton loadStep, saveStep, confirm; // 步驟欄的按鈕與載入資料按鈕
		
		public HandlePanel() {
			setLayout(new BorderLayout());
			setBorder(new TitledBorder(null, "資料處理流程", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			add(new JScrollPane(new JPanel()), BorderLayout.CENTER);

			saveStep = new JButton("儲存步驟");
			saveStep.setToolTipText("儲存先前執行並採用的步驟");

			loadStep = new JButton("載入步驟");
			loadStep.setToolTipText("載入先前儲存的步驟");

			confirm = new JButton("預覽結果");
			
			Box buttonBox = Box.createHorizontalBox();
			buttonBox.add(Box.createHorizontalGlue());
			buttonBox.add(saveStep);
			buttonBox.add(Box.createHorizontalStrut(5));
			buttonBox.add(loadStep);
			buttonBox.add(Box.createHorizontalStrut(5));
			buttonBox.add(confirm);
			add(buttonBox, BorderLayout.SOUTH);			
		}
	}

	/**預覽版面*/
	private class PreviewPanel extends JPanel {
		private static final long serialVersionUID = -8552488333943233617L;
		public PreviewPanel() {
			setLayout(new BorderLayout());
			setBorder(new TitledBorder(null, "資料預覽", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			
			beforeSheet.setTableTitle("資料結果(處理前)");
			afterSheet.setTableTitle("資料結果(處理後)");
			JSplitPane previewSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, beforeSheet, afterSheet);
			previewSplit.setBorder(null);
			previewSplit.setDividerLocation(previewSplit.getPreferredSize().height / 2);
			add(previewSplit, BorderLayout.CENTER);
		}
	}
	
	/**
	 * 資料結果(處理前) 預覽表格<br>
	 * 繼承{@link AbstractPreview}
	 */
	private class BeforeSheet extends AbstractPreview {
		private static final long serialVersionUID = -7287660204910605281L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
