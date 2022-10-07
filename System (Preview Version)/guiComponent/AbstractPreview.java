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

/**�����ܪ��*/
public abstract class AbstractPreview extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1998540816189543580L;
	private enum Mode {WITHOUT_IMPORT, NOTHING, ALL};
	private JFileChooser chooser;
	private JLabel title;
	private JButton importButton, popUpButton, storeButton;
	private ViewTable viewTable;
	private static String[] imageName = {"\\import.png", "\\popUp.png", "\\store.png"};
	private static ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), imageName, 30, 30);
	
	/**�إߵL������s���w�����*/
	public static AbstractPreview newNothingSheet() {
		return new AbstractPreview(Mode.NOTHING) {
			private static final long serialVersionUID = 2668494334011211477L;
			@Override
			public void actionPerformed(ActionEvent e) {}//���U�פJ���s�n�����ʧ@�A���ݹ�@
		};
	}
	
	/**�إߥ]�t�ץX�μu�����w�����*/
	public static AbstractPreview newSheetWithoutImport() {
		return new AbstractPreview(Mode.WITHOUT_IMPORT) {
			private static final long serialVersionUID = -7704785758861677448L;
			@Override
			public void actionPerformed(ActionEvent e) {}//���U�פJ���s�n�����ʧ@�A���ݹ�@
		};
	}
	
	/**�إߥ]�t�פJ�B�ץX�μu�����w�����*/
	public AbstractPreview() {
		this(Mode.ALL);
	}
	
	private AbstractPreview(Mode mode) {
		viewTable = new ViewTable();
		title = new JLabel("�L��ƥi�w��", JLabel.CENTER);
		
		// �u�����
		switch(mode) {
		case NOTHING:
			setLayout(new BorderLayout());
			add(title, BorderLayout.NORTH);
			add(viewTable, BorderLayout.CENTER);
			break;
		case ALL:
			// �]�t�פJ��ƶ��u���B�x�s���s�Ϊ��
			importButton = new JButton(icon[0]);
			importButton.setToolTipText("�פJ�ɮ�");
			importButton.addActionListener(this);
		case WITHOUT_IMPORT:
			setLayout(new GridBagLayout());
			chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
			
			popUpButton = new JButton(icon[1]);
			popUpButton.setToolTipText("�u�X�s�����A�H�����˵��Ӫ��");
			popUpButton.addActionListener(listener);
			
			storeButton = new JButton(icon[2]);
			storeButton.setToolTipText("�x�s�Ӫ��");
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

	/**�]�w�����D*/
	public void setTableTitle(String text) {
		title.setText(text);
	}
	
	/**���U�u�����s | ���U�x�s���s*/
	private ActionListener listener = e -> {
		if (e.getSource() == popUpButton) {			// ���U�u�����s
		} else if (e.getSource() == storeButton) {	// ���U�x�s���s
			chooser.showSaveDialog(this);
		}
	};

	private class ViewTable extends JScrollPane { // ���ۮe��java�Ҧ������A���������禡�ŧi���R�A�A�������O�]���P���R�A
		private static final long serialVersionUID = 129864934755437301L;

		public ViewTable() {
			JTable table = new JTable();
			table.setToolTipText("�����i�۰ʽվ����j�p");
			setViewportView(table);
		}
	}
}
