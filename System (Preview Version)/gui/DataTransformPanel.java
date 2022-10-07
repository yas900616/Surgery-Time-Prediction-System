package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import guiComponent.AbstractPreview;
import guiFunction.LoadFile;

class DataTransformPanel extends JPanel {
	private static final long serialVersionUID = -4528788971375791834L;
	private String[] imageName = {"\\chart.png", "\\Off.png", "\\On.png"};
	private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 50, 50);
	private ChooserPanel chooser = new ChooserPanel();
	private MergeStpAtpPanel mergeStpAtp = new MergeStpAtpPanel();
	private AbstractPreview sheet = AbstractPreview.newSheetWithoutImport();
	private CompoundBorder normalBorder = BorderFactory.createCompoundBorder(LineBorder.createBlackLineBorder(),
			BorderFactory.createEmptyBorder(5, 5, 5, 5));
	private JButton chartButton = new JButton(icon[0]),
			confirmButton = new JButton("確認");

	public DataTransformPanel() {
		setLayout(new BorderLayout());
		
		JPanel westPanel = new JPanel(new GridBagLayout());
		Insets inset = new Insets(0, 5, 0, 5);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.ipady = 200;
		gbc.insets = inset;
		gbc.fill = GridBagConstraints.BOTH;
		westPanel.add(chooser, gbc);
		
		mergeStpAtp.setBorder(normalBorder);
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.insets = inset;
		gbc.fill = GridBagConstraints.BOTH;
		westPanel.add(mergeStpAtp, gbc);
		
		chartButton.setToolTipText("顯示資料圖表");
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = inset;
		westPanel.add(chartButton, gbc);
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = inset;
		westPanel.add(confirmButton, gbc);
		add(westPanel, BorderLayout.WEST);
		
		sheet.setTableTitle("資料轉檔完的結果");
		add(sheet, BorderLayout.CENTER);
	}
	
	private class ChooserPanel extends JPanel {
		private static final long serialVersionUID = -470229501144606243L;
		private DefaultListModel<JCheckBox> listModel = new DefaultListModel<>();
		private JList<JCheckBox> list = new JList<JCheckBox>(listModel);
		
		public ChooserPanel() {
			setLayout(new BorderLayout());
			setBorder(new TitledBorder(null, "年報表清單", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			setToolTipText("請選擇一個以上的年報表以合併");
			add(new JScrollPane(list), BorderLayout.CENTER);
		}
	}

	private class MergeStpAtpPanel extends JPanel{
		private static final long serialVersionUID = 248221410459070138L;
		private JToggleButton mergeMonthToggle = new JToggleButton(icon[1]),
				mergeDRGToggle = new JToggleButton(icon[1]);;
		private JRadioButton stp = new JRadioButton("手術時間"),
				atp = new JRadioButton("麻醉時間");

		public MergeStpAtpPanel() {
			setLayout(new GridBagLayout());
			//======================
			mergeMonthToggle.setSelectedIcon(icon[2]);
			mergeMonthToggle.setBorderPainted(false);
			mergeMonthToggle.setContentAreaFilled(false);
			mergeMonthToggle.setFocusPainted(false);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.anchor = GridBagConstraints.WEST;
			add(new JLabel("是否合併月報表資料："), gbc);

			gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			add(mergeMonthToggle, gbc);
			//======================
			mergeDRGToggle.setSelectedIcon(icon[2]);
			mergeDRGToggle.setBorderPainted(false);
			mergeDRGToggle.setContentAreaFilled(false);
			mergeDRGToggle.setFocusPainted(false);
			
			gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.anchor = GridBagConstraints.WEST;
			add(new JLabel("是否合併DRG編號："), gbc);

			gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			add(mergeDRGToggle, gbc);
			//======================
			stp.setSelected(true);
			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(stp);
			radioGroup.add(atp);
			
			gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.weighty = 0.5;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.WEST;
			add(new JLabel("要預測何種資料："), gbc);

			gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.weighty = 0.5;
			add(stp, gbc);

			gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.weighty = 0.7;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(atp, gbc);
		}
	}
}
