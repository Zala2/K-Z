import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class OknoS extends JFrame {
   
	protected PlatnoIgra platno;
    protected PlatnoZacetek platnoZ;
    protected PlatnoKonec platnoK;
    
    public OknoS(){
		super();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());  //na�in razporejanja platn v okno
		platno = new PlatnoIgra();
		add(platno);
		platno.setVisible(false);
		platnoZ = new PlatnoZacetek(this);
		add(platnoZ, null);
		platnoZ.setVisible(true);
		platnoK = new PlatnoKonec(this);
		add(platnoK);
		platnoK.setVisible(false);
   }
    
	public static void main(String[] args) {
		OknoS okno = new OknoS();
		okno.pack();
		okno.setVisible(true);
	}
}
