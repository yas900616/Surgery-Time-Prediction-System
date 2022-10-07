package guiComponent;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**�ɮ׾𪺩�H���A����@�I����@���ذ��檺�ʧ@*/
public abstract class AbstractFileTree extends JScrollPane implements TreeSelectionListener, FileVisitor<Path> {
	private static final long serialVersionUID = 8137115178579328106L;
	private Font font16 = new Font("�L�n������", Font.PLAIN, 16);
	private int defaultRowHeight = font16.getSize() + 10;
	private JTree tree = new JTree();

	protected AbstractFileTree() {
		clear();
		tree.setFont(font16);
		tree.setRowHeight(defaultRowHeight);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		setViewportView(tree);
	}

	/**
	 * ��s�ɮ׾�
	 * @param folder �r����|
	 * @throws IOException
	 */
	public void update(String folder) throws IOException {
		update(Paths.get(folder));
	}
	
	/**
	 * ��s�ɮ׾�
	 * @param folder Path���|
	 * @throws IOException
	 */
	public void update(Path folder) throws IOException {
		Files.walkFileTree(folder, this);
		((DefaultTreeModel) tree.getModel()).setRoot(folderNode);
	}
	
	/**�M���ɮ׾�*/
	public void clear() {
		((DefaultTreeModel) tree.getModel()).setRoot(null);
	}
	
	private Stack<DefaultMutableTreeNode> nodeStack = new Stack<>();// �ؿ�
	private Stack<ArrayList<String>> fileStack = new Stack<>();		// �ɮ�
	private DefaultMutableTreeNode folderNode;
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		folderNode = new DefaultMutableTreeNode(dir.getFileName());
		if(!nodeStack.isEmpty()) {
			nodeStack.peek().add(folderNode);
		}
		nodeStack.push(folderNode);
		fileStack.push(new ArrayList<>());
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		fileStack.peek().add(file.getFileName().toString());
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		folderNode = nodeStack.pop();
		ArrayList<String> fileList = fileStack.pop();
		fileList.forEach(s -> folderNode.add(new DefaultMutableTreeNode(s, false)));
		fileList.clear();
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.err.println(exc);
		return FileVisitResult.CONTINUE;
	}
}
