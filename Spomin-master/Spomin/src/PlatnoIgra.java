import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlatnoIgra extends JPanel implements MouseListener{
	
	protected Spomin igra;
	
	public PlatnoIgra() {
        super();
        addMouseListener(this);
    }
	
	public void novaIgra() {
		setVisible(true);
		igra = new Spomin();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1450, 1000);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		for (int i = 0; i < 4; i += 1) {
			for (int j = 0; j < 5; j += 1) {
				if(! igra.odprta[i][j]){
					g.drawImage(igra.hrbet, 250*j+25, 250*i+25, null);
				}
				else if(igra.matrika[i][j] < 0){
					g.drawImage(igra.sezImen[-igra.matrika[i][j]-1], 250*j+25, 250*i+25, null);
				}
				else {
					g.drawImage(igra.sezObrazov[igra.matrika[i][j]-1], 250*j+25, 250*i+25, null);
				}
			}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent event) {
		int x = event.getX() / 250;
		int y = event.getY() / 250;
		if(y < 4 && x < 5 && event.getX()% 250 > 25 && event.getY()% 250 > 25 ){ //ali smo kliknali v polje - èe zunaj 
			igra.odprta[y][x] = true;
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
