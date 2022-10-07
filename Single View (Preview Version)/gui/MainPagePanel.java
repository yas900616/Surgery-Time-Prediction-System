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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import guiComponent.AbstractPreview;
import guiFunction.LoadFile;

class MainPagePanel extends JPanel {
	private static final long serialVersionUID = 1180611674900194495L;
	private String[] imageName = {"\\single.png", "\\multiple.png", "\\folder.png"};
	private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 30, 30);
	private JFileChooser chooser = new JFileChooser(Info.getDesktop());
	private JTextArea info = new JTextArea();
	private JButton startPredict = new JButton("�}�l�w��"),
			loadModel = new JButton("���J�ҫ�");
	private JTabbedPane optionPane = new JTabbedPane();	//�S�x�ﶵ��������
	private MultiplePreview multiplePreview = new MultiplePreview();
	
	public MainPagePanel() {// �}�l�إ߼ҫ����s����ť��
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Model File", "model"));
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane singleScroll = new JScrollPane();
		singleScroll.setViewportBorder(new TitledBorder(null, "��ܱ��w�����ﶵ", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		optionPane.addTab("�浧��ƹw��", icon[0], singleScroll, "��ܦU�S�x���ﶵ�H�w���浧��N���");
		optionPane.addTab("�h����ƹw��", icon[1], multiplePreview, "�פJ�ɮץH�w���h����N���");
		
		info.setLineWrap(true);
		info.setEditable(false);
		JScrollPane infoScroll = new JScrollPane(info);
		infoScroll.setViewportBorder(new TitledBorder(null, "�ϥμҫ����ԲӸ�T", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		centerPanel.add(optionPane);
		centerPanel.add(infoScroll);
		add(centerPanel, BorderLayout.CENTER);
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(startPredict);
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(loadModel);
		add(buttonBox, BorderLayout.SOUTH);
	}
	
	/**�h����ƹw�� �פJ��ƹw��*/
	private class MultiplePreview extends AbstractPreview {
		private static final long serialVersionUID = 1705315329772510531L;
		private JFileChooser chooser = new JFileChooser(Info.getDesktop());
		private MultiplePreview() {
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
			setTableTitle("�h����ƹw�����e");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.showOpenDialog(this);
		}
	}
}
