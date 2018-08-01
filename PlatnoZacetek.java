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
public class PlatnoZacetek extends JPanel implements ActionListener{
	protected OknoS okno;
	protected BufferedImage naslovna;
	protected JButton gumb;
	
	public PlatnoZacetek(OknoS okno){
		this.okno = okno;
		try {
			naslovna = ImageIO.read(new File("naslovnica.gif"));
		}
		catch (IOException e) {
			System.out.println("napaka");
		}
		gumb = new JButton("ZAÈETEK");

		gumb.addActionListener(this);
		gumb.setPreferredSize(new Dimension(200,100));
		//gumb.setBounds(750, 700, 200, 100);
		//gumb.repaint();
		add(gumb);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1450, 1000);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(naslovna,0,0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gumb){
			setVisible(false);
			okno.platno.novaIgra();
		}
	}
}
