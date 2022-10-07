package gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import guiFunction.LoadFile;

class ProcessFrame extends JFrame {
	private static final long serialVersionUID = -8412388088390642503L;
	private String[] name = {"選擇頁面", "設定資料目錄", "資料轉檔", "資料處理", "特徵選取", "資料拆分", "方法設定", "訓練結果", "預測頁面"};
	private int procNum = name.length;
	private Font font38 = new Font("微軟正黑體", Font.PLAIN, 38);
	private Font font32 = new Font("微軟正黑體", Font.PLAIN, 32);
	private Font font30 = new Font("微軟正黑體", Font.PLAIN, 30);
	private Font font26 = new Font("微軟正黑體", Font.PLAIN, 26);
	private Font font24 = new Font("微軟正黑體", Font.PLAIN, 24);
	private Font font22 = new Font("微軟正黑體", Font.PLAIN, 22);
	private Font font20 = new Font("微軟正黑體", Font.PLAIN, 20);
	private String[] iconName = {"\\surgery.png", "\\manual.png", "\\chart.png"};
	private String[] imageName = {"\\Background.png", "\\TitleBackground.png"};
	private ImageIcon[] icon = LoadFile.fromIcons(Info.getIconPath(), iconName, 80, 80);
	private BufferedImage[] image = LoadFile.fromImages(Info.getIconPath(), imageName);
	// 版面宣告
	private OperatingManual operatingManual = new OperatingManual();
	private View view = new View();
	private Title title = new Title();
	private Process process = new Process();
	
	/**程式執行點*/
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UIManager.put("OptionPane.messageDialogTitle", "提示訊息");
				Font mainFont = new Font("微軟正黑體", Font.PLAIN, 20);
				UIManager.put("Label.font", mainFont);
				UIManager.put("Button.font", mainFont);
				UIManager.put("RadioButton.font", mainFont);
				UIManager.put("ComboBox.font", mainFont);
				UIManager.put("CheckBox.font", mainFont);
				UIManager.put("TextField.font", mainFont);
				UIManager.put("TextArea.font", mainFont);
				UIManager.put("ProgressBar.font", mainFont);
				UIManager.put("TitledBorder.font", mainFont);
				UIManager.put("TabbedPane.font", mainFont);
				Color mainColor = Color.decode("#e9ebfe");
				UIManager.put("info", mainColor);
				UIManager.put("control", mainColor);
				UIManager.put("Panel.background", mainColor);
				UIManager.put("RadioButton.background", mainColor);
				UIManager.put("CheckBox.background", mainColor);
				UIManager.put("TextField.background", mainColor);
				UIManager.put("TextArea.background", mainColor);
				UIManager.put("OptionPane.background", mainColor);
				UIManager.put("ComboBox.background", mainColor);
				UIManager.put("TabbedPane.background", mainColor);
				UIManager.put("Button.background", Color.decode("#e8f4ff"));
				UIManager.put("ProgressBar.background", Color.WHITE);
				UIManager.put("ProgressBar.foreground", Color.decode("#5649a5"));
				UIManager.put("SplitPane.dividerSize", 10);
				UIManager.put("FileChooser.readOnly", true);	// 將檔案選擇器設為唯讀
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				
				new ProcessFrame();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "初始化失敗");
			}
		});
	}
	
	/**主框架*/
	public ProcessFrame() {
		JRootPane rootPane = new JRootPane() {
			private static final long serialVersionUID = -4902174568799098300L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
		        g.drawImage(image[0], 0, 0, getWidth(), getHeight(), this);
			}
		};
		rootPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setRootPane(rootPane);
		setIconImage(icon[0].getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH); //一開始就全畫面
		setTitle("「醫」刻千金 · 料「術」如神 手術時間預測系統 (預覽版)");
		setSize(1024, 768); // 縮小時的解析度
		setLocationRelativeTo(null);
		
		add(view, BorderLayout.CENTER);
		add(title, BorderLayout.NORTH); // 製作標題
		add(process, BorderLayout.SOUTH);
		setVisible(true);
	}

	/**框架頂部*/
	private class Title extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1745813210715743430L;
		private Color systemNameColor = Color.decode("#5649a5"),
				sloganColor = Color.decode("#25119e");
		private JButton logo = new JButton(icon[0]),
				help = new JButton(icon[1]);
		private JLabel tip = new JLabel("歡迎使用本系統 (預覽版)"),
				slogan = new JLabel("「醫」刻千金 · 料「術」如神"),
				systemName = new JLabel("手術時間預測系統");
		
		public Title() {
			setLayout(new BorderLayout(10, 0));
			logo.setFocusPainted(false);
			logo.setBorderPainted(false);
			logo.setContentAreaFilled(false);
			logo.setToolTipText("返回主頁面");
			logo.addActionListener(this);
			add(logo, BorderLayout.WEST);
			
			slogan.setFont(font38);
			slogan.setForeground(sloganColor);
			systemName.setFont(font26);
			systemName.setForeground(systemNameColor);
			tip.setFont(font30);
			tip.setForeground(sloganColor);
			tip.setAlignmentX(RIGHT_ALIGNMENT);
			
			Box verticalBox = Box.createVerticalBox();
			verticalBox.add(slogan);
			verticalBox.add(systemName);
			verticalBox.add(Box.createVerticalGlue());
			Box centerBox = Box.createHorizontalBox();
			centerBox.add(verticalBox);
			centerBox.add(Box.createHorizontalGlue());
			centerBox.add(tip);
			add(centerBox, BorderLayout.CENTER);
			
			help.setFocusPainted(false);
			help.setBorderPainted(false);
			help.setContentAreaFilled(false);
			help.addActionListener(this);
			help.setAlignmentX(RIGHT_ALIGNMENT);
			help.setToolTipText("使用手冊");
			add(help, BorderLayout.EAST);
			
			ProcessFrame.this.addWindowStateListener((WindowEvent e) -> {
				switch(e.getNewState()) {
				case 0:
					System.out.println("縮小");
					slogan.setFont(font32);
					systemName.setFont(font22);
					tip.setFont(font26);
					for (int i = 0; i < procNum; i++)
						process.setProcFont(i, font20);
					break;
				case 6:
					System.out.println("全螢幕");
					slogan.setFont(font38);
					systemName.setFont(font26);
					tip.setFont(font30);
					for (int i = 0; i < procNum; i++)
						process.setProcFont(i, font24);
					break;
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
	        g.drawImage(image[1], 0, 0, getWidth(), getHeight(), this);
		}

		/**按下logo按鈕*/
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == logo) {
				JOptionPane.showMessageDialog(null, "此版本僅供預覽");
			}else if(e.getSource() == help) {
				operatingManual.setVisible(true);
			}
		}
	}
	
	/**主版面*/
	private class View extends JPanel implements ActionListener {
		private static final long serialVersionUID = 2606502395233174342L;
		private JButton prev = new JButton("上一頁"),
				next = new JButton("下一頁");
		private CardLayout card = new CardLayout(); // 用來切換頁面的
		private JPanel panel = new JPanel(card);
		private int current = 0, previous = 0;
		
		public View() {
			setLayout(new BorderLayout(10, 10));
			prev.addActionListener(this);
			LineBorder redBorder = new LineBorder(Color.RED, 3);
			prev.setBorder(redBorder);
			next.addActionListener(this);
			next.setBorder(redBorder);
			
			Box box = Box.createHorizontalBox();
			box.add(prev);
			box.add(Box.createHorizontalGlue());
			box.add(next);
			add(box, BorderLayout.SOUTH);
			
			panel.add(new ChoosePanel());
			panel.add(new SelectFolderPanel());
			panel.add(new DataTransformPanel());
			panel.add(new DataHandlePanel());
			panel.add(new FeatureSelectPanel());
			panel.add(new DataSplitPanel());
			panel.add(new MethodSelectPanel());
			panel.add(new TrainResultPanel());
			panel.add(new MainPagePanel());
			add(panel, BorderLayout.CENTER);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == prev) {
				current = (--current < 0) ? procNum - 1 : current;
				previous = (current == procNum - 1) ? 0 : current + 1;
				card.previous(panel);
			}else if (e.getSource() == next) {
				current = ++current > procNum - 1 ? 0 : current;
				previous = (current == 0) ? procNum - 1 : current - 1;
				card.next(panel);
			}
			process.setIsProcessing(current, previous);
		}
	}
	
	/**框架底部*/
	private class Process extends JPanel {
		private static final long serialVersionUID = -1881550063813242073L;
		private Box[] procBox = new Box[procNum];
		private JTextField[] rectangle = new JTextField[procNum]; 	// 用陣列的方式來取代(因為有七個動作加一個主頁面)
		private JLabel[] pageText = new JLabel[procNum]; 	// 所以每一個都用索引值來引導動作
		private Color isProcessingColor = Color.decode("#3c4074");
		
		public Process() {
			setLayout(new GridLayout(1, procNum, 10, 0));
			setBackground(Color.decode("#b1b5fd"));
			setBorder(new EmptyBorder(10, 10, 10, 10));
			
			for(int i = 0; i < procNum; i++) {
				pageText[i] = new JLabel(name[i]);
				pageText[i].setFont(font24);
				pageText[i].setAlignmentX(CENTER_ALIGNMENT);
				rectangle[i] = new JTextField();
				rectangle[i].setEditable(false);
				rectangle[i].setBackground(Color.WHITE);
				
				procBox[i] = Box.createVerticalBox();
				procBox[i].add(pageText[i]);
				procBox[i].add(rectangle[i]);
				add(procBox[i]);
			}
			setIsProcessing(0, 1);
		}
		
		public void setIsProcessing(int curr, int prev) {
			rectangle[curr].setBackground(isProcessingColor);
			rectangle[prev].setBackground(Color.WHITE);
		}
		
		public void setProcFont(int index, Font font) {
			pageText[index].setFont(font);
		}
	}
}