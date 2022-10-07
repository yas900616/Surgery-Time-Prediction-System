package guiFunction;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**���J�Ϥ��Τ�r��*/
public class LoadFile {
	/**
	 * ���Jicon��
	 * @param iconPath �Ϥ��ӷ����|
	 * @param iconName �W�ٰ}�C
	 * @param width �e��
	 * @param height ����
	 * @return icon��
	 */
	public static ImageIcon[] fromIcons(String iconPath, String[] iconName, int width, int height) {
		ImageIcon[] icons = new ImageIcon[iconName.length];
		for(int i = 0, length = icons.length; i < length; i++) {
			icons[i] = fromIcon(iconPath, iconName[i], width, height);
		}
		return icons;
	}
	/**
	 * ���Jicon
	 * @param iconPath �Ϥ��ӷ����|
	 * @param iconName �W�ٰ}�C
	 * @param width �e��
	 * @param height ����
	 * @return icon
	 */
	public static ImageIcon fromIcon(String iconPath, String iconName, int width, int height) {
		return new ImageIcon(fromImage(iconPath, iconName).getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	/**
	 * ���J�Ϥ���
	 * @param imagePath �Ϥ��ӷ����|
	 * @param imageName �W�ٰ}�C
	 * @return �Ϥ���
	 */
	public static BufferedImage[] fromImages(String imagePath, String[] imageName) {
		BufferedImage[] images = new BufferedImage[imageName.length];
		for(int i = 0, length = images.length; i < length; i++) {
			images[i] = fromImage(imagePath, imageName[i]);
		}
		return images;
	}
	/**
	 * ���J�Ϥ�
	 * @param imagePath �Ϥ��ӷ����|
	 * @param imageName �W�ٰ}�C
	 * @return �Ϥ�
	 */
	public static BufferedImage fromImage(String imagePath, String imageName) {
		try {
			return ImageIO.read(new File(imagePath + imageName));
		} catch (IOException e) {
			System.err.println(imageName + "���J���ѡC���ˬd���|�O�_���T���ɮ׬O�_�s�b�C");
		}
		return new BufferedImage(0, 0, 0);
	}
	
	/**
	 * ���J��r��
	 * @param textPath ��r�ӷ����|
	 * @param tipName �W�ٰ}�C
	 * @return ��r��
	 * @throws IOException
	 */
	public static String[] fromTexts(String textPath, String[] tipName) {
		int length = tipName.length;
		String[] tips = new String[length];
		for (int i = 0; i < length; i++) {
			tips[i] = fromText(textPath + tipName[i]);
		}
		return tips;
	}
	
	/**
	 * ���J��r
	 * @param textPath ��r�ӷ�������|
	 * @return ��r
	 * @throws IOException
	 */
	public static String fromText(String textPath) {
		try {
			return Files.readString(Paths.get(textPath), Charset.defaultCharset());
		} catch (IOException e) {
			System.err.println(textPath + "���J���ѡC���ˬd���|�O�_���T���ɮ׬O�_�s�b�C");
		}
		return null;
	}
}
