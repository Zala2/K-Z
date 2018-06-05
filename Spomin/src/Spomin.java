import java.io.BufferedReader;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Spomin {
	public BufferedImage hrbet = null;
	public List<BufferedImage> sezObrazov = null;
	public List<BufferedImage> sezImen = null;
	public int[][] matrika = new int[4][5];

	public Spomin() {
		sezObrazov = new ArrayList<BufferedImage>();
		sezImen = new ArrayList<BufferedImage>();
		uvozi();
		zacetnaMatrika();
	}

	public void uvozi() {
		int stVrstic = 4;
		int stStolpcev = 5;
		int vsiPari = 10;
		BufferedImage img = null;
		try {
			hrbet = ImageIO.read(new File("bela.gif"));
		} catch (IOException e) {
		}
		for (int i = 0; i <= 10; i += 1) {
			try {
				img = ImageIO.read(new File("obraz" + 'i' + ".gif"));
				sezObrazov.add(img);
			} catch (IOException e) {
			}

			try {
				img = ImageIO.read(new File("ime" + 'i' + ".gif"));
				sezImen.add(img);
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
		int n = sez.size();
		Collections.shuffle(sez);

		for (int i = 0; i < 4; i += 1) {
			for (int j = 0; j < 5; j += 1) {
				matrika[i][j] = sez.get(5 * i + j);
			}
		}
	}
	// public static int narišiMatrikoSlik() {
	//
	//
	// }
	// def matrika(self):

	// '''Funkcija ustvari matriko velikosti 5*4'''
	// sez = list(range(1,11))+list(range(-10,0))
	// random.shuffle(sez)
	// matrika = []
	// for i in range(0,len(sez),5):
	// v = sez[i:i+5]
	// matrika.append(v)
	// return matrika

}