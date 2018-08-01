import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PlatnoIgra extends JPanel implements MouseListener{
	protected OknoS okno;
	protected Spomin igra;
	
	public PlatnoIgra(OknoS okno) {
        super();
        this.okno = okno;
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
		setBackground(Color.orange);
		    
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
		removeMouseListener(this); //da ne moreš kr klikat

		int x = event.getX() / 250;
		int y = event.getY() / 250;
		if(igra.odprta[y][x] != true) { // da ne moremo ponovno kliknit na že odprto sliko
			if(y < 4 && x < 5 && event.getX()% 250 > 25 && event.getY()% 250 > 25 ){ //ali smo kliknili v polje - �e zunaj
				igra.odprta[y][x] = true;
				repaint();
			}
			int vrednost = igra.matrika[y][x];
			int indeks = 0;
			if(vrednost < 0){
				indeks = -vrednost-1;
			}
			else{
				indeks = vrednost-1;
			}
			igra.stevecKlikov += 1;

			if(igra.stevecKlikov %2 != 0 ){
				igra.ind1 = indeks;
				igra.a = x;
				igra.b = y;

				addMouseListener(this); //aktiviraš nazaj
			}
			else{
				igra.ind2 = indeks;
				int c = x;
				int d = y;
				if(igra.ind1 == igra.ind2){
					igra.stParov += 1;
					if(igra.stParov == igra.vsiPari){
						setVisible(false);
						okno.platnoK.konec();
					}

					addMouseListener(this);
				}
				else{
					//omogoči da daš na drugo nit 
					new Thread(new Runnable() { //anonimni razred, ima samo metodo run
						@Override
						public void run() {
							try {
								Thread.sleep(1000); //počaka 1 sek
							} catch (InterruptedException e) { // morš met
								e.printStackTrace();
							}
							igra.odprta[igra.b][igra.a] = false;
							igra.odprta[d][c] = false;
							repaint();

							addMouseListener(PlatnoIgra.this);
						}
                    }).start(); //da se izvede run
				}
			}
		}
		else {
			addMouseListener(this);
		}

	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) { }
}

