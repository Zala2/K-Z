import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spomin {
	protected BufferedImage hrbet;
	protected BufferedImage[] sezObrazov;  //protected - dovolimo uporabo samo v razredu iz istega paketa
	protected BufferedImage[] sezImen;
	protected int[][] matrika;
	protected boolean[][] odprta;
	protected int ind1;
	protected int ind2;
	protected int stParov = 0;
	protected int vsiPari = 10;
	protected int a;
	protected int b;
	protected int stevecKlikov;

	public Spomin() {
		sezObrazov = new BufferedImage[10];
		sezImen = new BufferedImage[10];
		matrika = new int[4][5];
		odprta = new boolean[4][5];
		uvozi();
		zacetnaMatrika();
	}

	public void uvozi() {
		BufferedImage img = null;
		try {
			hrbet = ImageIO.read(new File("bela.gif"));
		} catch (IOException e) {
		}
		for (int i = 0; i < 10; i += 1) {
			try {
				img = ImageIO.read(new File("obraz" + (i+1) + ".gif"));
				sezObrazov[i] = img;
			} catch (IOException e) {
			}

			try {
				img = ImageIO.read(new File("ime" + (i+1) + ".gif"));
				sezImen[i] = img;
			} catch (IOException e) {
			}
		}
	}

	// USTVARIMO MATRIKO
	public void zacetnaMatrika() {
		List<Integer> sez = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i += 1) {
			sez.add(i);
			sez.add(-i);
		}
		Collections.shuffle(sez);

		for (int i = 0; i < 4; i += 1) {
			for (int j = 0; j < 5; j += 1) {
				matrika[i][j] = sez.get(5 * i + j);
				odprta[i][j] = false;
			}
		}
	}
	
	
}