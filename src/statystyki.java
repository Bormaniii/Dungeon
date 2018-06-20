import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class statystyki extends Obserwowane implements Observer{

	public statystyki(wojownik wojownik){
		//tworzy osobne okno
		JFrame staty = new JFrame("Statystyki");
		staty.setLocation(200, 300);
		staty.setSize(400, 400);
		staty.setVisible(true);
		JPanel stat = new JPanel(new GridLayout(8,2));
		JLabel pusty = new JLabel();
		
		//uruchamia funkcje aktualizacyjna
		aktualizuj(staty, stat, wojownik);		
		}
		
	
	
	public void aktualizuj(JFrame staty, JPanel stat, wojownik woj) {
					
		//pobiera dane
		JButton[] wzrost = new JButton[4]; //przyciski do ulepszania
		JLabel postac = new JLabel("wojownik");
		JLabel poziom = new JLabel("Poziom: "+wojownik.poziom);
		JLabel doswiadczenie = new JLabel("doswiadczenie: "+wojownik.exp+"/"+wojownik.potexp);
		JLabel pkt = new JLabel("Twoje punkty do wydania: "+wojownik.pkt);
		JLabel sila = new JLabel("sila: "+wojownik.sila);
		JLabel wytrzymalosc = new JLabel("wytrzymalosc: "+wojownik.wytrzymalosc);
		JLabel zrecznosc = new JLabel("zrecznosc: "+wojownik.zrecznosc);
		JLabel inteligencja = new JLabel("inteligencja: "+wojownik.inteligencja);
		for(int a=0;a<4;a++) {
			wzrost[a] = new JButton("+");
			if(wojownik.pkt==0) {
					wzrost[a].setEnabled(false);
			}
		}
		
		//dodaje do panelu
		stat.add(postac);
		stat.add(poziom);
		stat.add(doswiadczenie);
		stat.add(pkt);
		stat.add(sila);
		stat.add(wzrost[0]);
		stat.add(wytrzymalosc);
		stat.add(wzrost[1]);
		stat.add(zrecznosc);
		stat.add(wzrost[2]);
		stat.add(inteligencja);
		stat.add(wzrost[3]);
		stat.setVisible(true);
		
		staty.add(stat);
		
		
		//aktualizuje
		for(int a=0;a<4;a++) {
			final int v = a;
			
			wzrost[a].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(wojownik.pkt>0) {
					switch (v) {
					case 0:
						wojownik.sila++;
						wojownik.pkt--;
						sila.setText("sila: "+wojownik.sila);
						pkt.setText("Twoje punkty do wydania: "+wojownik.pkt);
					break;
					case 1:
						wojownik.wytrzymalosc++;
						wojownik.pkt--;
						wytrzymalosc.setText("wytrzymalosc: "+wojownik.wytrzymalosc);
						pkt.setText("Twoje punkty do wydania: "+wojownik.pkt);
					break;
					case 2:
						wojownik.zrecznosc++;
						wojownik.pkt--;
						zrecznosc.setText("zrecznosc: "+wojownik.zrecznosc);
						pkt.setText("Twoje punkty do wydania: "+wojownik.pkt);
					break;
					case 3:
						wojownik.inteligencja++;
						wojownik.pkt--;
						inteligencja.setText("inteligencja: "+wojownik.inteligencja);
						pkt.setText("Twoje punkty do wydania: "+wojownik.pkt);
					break;
					}	
				}
					if(wojownik.pkt==0) {
						for(int a=0;a<4;a++)
						wzrost[a].setEnabled(false);
				}
					}
							
			});
		}
	}
	
	@Override
	public void update() {
		
	}


}
