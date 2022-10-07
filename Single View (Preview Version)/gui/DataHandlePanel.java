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
	private BeforeSheet beforeSheet = new BeforeSheet();// ��l���(������ɸ��)
	private AbstractPreview afterSheet = AbstractPreview.newSheetWithoutImport(); 	// �w�����(�u����n��ܪ���ƶ�)
	private AbstractPreview confirmSheet = AbstractPreview.newNothingSheet();	// �P�ϥΪ̽T�{�B�J���浲�G
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private HandlePanel handlePanel = new HandlePanel();
	private ToolPanel toolPanel = new ToolPanel();
	
	public DataHandlePanel() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		setLayout(new BorderLayout());
		
		add(toolPanel, BorderLayout.WEST); 		// �s�@�u����
		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.add(handlePanel);	// �s�@�B�J��
		centerPanel.add(new PreviewPanel());// �s�@�w����
		add(centerPanel, BorderLayout.CENTER);
		//
		confirmSheet.setTableTitle("�B�J����᪺���G");
	}
	
	private class ToolPanel extends JScrollPane {
		private static final long serialVersionUID = 4936944520744858424L;
		private String[] imageName = {"\\RemoveOutlier.png", "\\RemoveOutlierBySD.png", "\\RemoveRecord.png", "\\RemoveFeature.png",
				"\\ExtractRecord.png", "\\TargetEncoding.png", "\\NormalizeFeature.png", "\\StandardizeFeature.png"};
		private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 80, 80); 	// Button���Ϯ�
		private JButton erButton, nfButton, sfButton, roButton, rosdButton, rrButton, rfButton, teButton;// �u���檺���s

		public ToolPanel() {
			setViewportBorder(new TitledBorder(null, "�u����", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JPanel content = new JPanel(new GridLayout(8, 1, 5, 5));
			setViewportView(content);
			
			roButton = new JButton(icon[0]);
			roButton.setToolTipText("�������w������s��(�ȭ��ƭ����)");
			content.add(roButton);
			
			rosdButton = new JButton(icon[1]);
			rosdButton.setToolTipText("�H�зǮt����ǡA�������w������s��(�ȭ��ƭ����)");
			content.add(rosdButton);

			rrButton = new JButton(icon[2]);
			rrButton.setToolTipText("�������w��줧�S�w���e��");
			content.add(rrButton);

			rfButton = new JButton(icon[3]);
			rfButton.setToolTipText("�������w���");
			content.add(rfButton);

			erButton = new JButton(icon[4]);
			erButton.setToolTipText("�������w��줧�S�w���e��");
			content.add(erButton);

			teButton = new JButton(icon[5]);
			teButton.setToolTipText("���w���ƭȤ�(�A�ΫD�ƭ����)");
			content.add(teButton);

			nfButton = new JButton(icon[6]);
			nfButton.setToolTipText("���w���зǤ�(�A�μƭ����)");
			content.add(nfButton);

			sfButton = new JButton(icon[7]);
			sfButton.setToolTipText("���w���зǤ�(�ȭ��ƭ����)");
			content.add(sfButton);
		}
	}

	private class HandlePanel extends JPanel {
		private static final long serialVersionUID = 6451689933054716323L;
		private JButton loadStep, saveStep, confirm; // �B�J�檺���s�P���J��ƫ��s
		
		public HandlePanel() {
			setLayout(new BorderLayout());
			setBorder(new TitledBorder(null, "��ƳB�z�y�{", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			add(new JScrollPane(new JPanel()), BorderLayout.CENTER);

			saveStep = new JButton("�x�s�B�J");
			saveStep.setToolTipText("�x�s���e����ñĥΪ��B�J");

			loadStep = new JButton("���J�B�J");
			loadStep.setToolTipText("���J���e�x�s���B�J");

			confirm = new JButton("�w�����G");
			
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

	/**�w������*/
	private class PreviewPanel extends JPanel {
		private static final long serialVersionUID = -8552488333943233617L;
		public PreviewPanel() {
			setLayout(new BorderLayout());
			setBorder(new TitledBorder(null, "��ƹw��", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
			
			beforeSheet.setTableTitle("��Ƶ��G(�B�z�e)");
			afterSheet.setTableTitle("��Ƶ��G(�B�z��)");
			JSplitPane previewSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, beforeSheet, afterSheet);
			previewSplit.setBorder(null);
			previewSplit.setDividerLocation(previewSplit.getPreferredSize().height / 2);
			add(previewSplit, BorderLayout.CENTER);
		}
	}
	
	/**
	 * ��Ƶ��G(�B�z�e) �w�����<br>
	 * �~��{@link AbstractPreview}
	 */
	private class BeforeSheet extends AbstractPreview {
		private static final long serialVersionUID = -7287660204910605281L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
