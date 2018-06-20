import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class postac {
	static ImageIcon icon;
	static int boh;


	public postac() {
		String w = "wojownik";
		String l = "lucznik";
		String m = "mag";
		JPanel wybor = new JPanel(new GridLayout(2,1)); // gora, bok
		JPanel wybor1 = new JPanel(new GridLayout(2,3));
		JLabel wybierz = new JLabel("Wybierz postac:");
		JButton wojownik = new JButton();
		JButton lucznik = new JButton();
		JButton mag = new JButton();
		//wciaz do zrobienia
		lucznik.setEnabled(false);
		mag.setEnabled(false);
		
		JLabel woj = new JLabel(w);
		JLabel lucz = new JLabel(l);
		JLabel ma = new JLabel(m);
		
		//dodawanie wygladu
		obraz(w);
		wojownik.setIcon(icon);		
		wybor1.add(wojownik);		
		obraz(l);
		lucznik.setIcon(icon);
		wybor1.add(lucznik);		
		obraz(m);
		mag.setIcon(icon);
		wybor1.add(mag);
		
		//dodawanie komponentow
		wybor1.add(woj);
		wybor1.add(lucz);
		wybor1.add(ma);
		wybor1.setVisible(true);
		wybor.add(wybierz);
		wybor.add(wybor1);
		wybor.setVisible(true);
		Main.dungeon.add(wybor);
		

		wojownik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boh=1;
				wybor.setVisible(false);
				Main.dungeon.remove(wybor);
				gra nowa = new gra();
			}
						
		});
		
		
		
	}
	
	public void obraz(String obraz) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(obraz+".png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(50, 70, Image.SCALE_SMOOTH);
		icon = new ImageIcon(dimg);
	}
	
}
