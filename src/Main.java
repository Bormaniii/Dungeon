import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Main {
static JFrame dungeon;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	
	public Main() {			
		dungeon = new JFrame("Dungeony");
		dungeon.setSize(416, 500);
		dungeon.setLocation(500, 100);  //(bok,gora) ulozenie na ekranie
		dungeon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel menu = new JPanel(new BorderLayout());
		JLabel photo = new JLabel();
		JButton nowa = new JButton("nowa gra");
		
		//ladowanie obrazka
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("dungeon.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(413, 102, Image.SCALE_SMOOTH);
		ImageIcon iconLogo = new ImageIcon(dimg);
		photo.setIcon(iconLogo);
		photo.setBorder(BorderFactory.createEmptyBorder());
		menu.setBackground(Color.RED);
		
		// dodawanie komponentow
		menu.add(photo,BorderLayout.NORTH);
		menu.add(nowa,BorderLayout.SOUTH);
		menu.setVisible(true);
		dungeon.add(menu);
		dungeon.setVisible(true);
		
		
		nowa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				dungeon.remove(menu);
				postac now = new postac();
				
			}
						
		});
	}
}
