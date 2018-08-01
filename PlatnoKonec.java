import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlatnoKonec extends JPanel implements ActionListener{
	protected OknoS okno;
	protected BufferedImage koncna;
	protected JButton gumb;
	
	public void konec() {
		setVisible(true);
	}
	
	public PlatnoKonec(OknoS okno){
		this.okno = okno;
		try {
			koncna = ImageIO.read(new File("baloni.gif"));
		}
		catch (IOException e) {
			System.out.println("napaka");
		}
		gumb = new JButton("ZAÈETEK");

		gumb.addActionListener(this);
		gumb.setPreferredSize(new Dimension(200,100));
		add(gumb);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1450, 1000);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(koncna,0,0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gumb){
			setVisible(false);
			okno.platno.novaIgra();
		}
	}
}
