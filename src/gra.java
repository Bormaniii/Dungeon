import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class gra extends Obserwowane implements Observer {
	static int zwrot = 1;
	static JButton tablicaprzyciskow[][];
	TimerTask task;
	Random random;
	static int zabici = 0;
	static JButton hp = new JButton("HP: "+wojownik.hp);

	public gra() {
		random = new Random();
		tablicaprzyciskow = new JButton[12][12];
		
		JPanel podstawa = new JPanel(new BorderLayout());
		JPanel gora = new JPanel(new GridLayout(1,3));
		JPanel plansza = new JPanel(new GridLayout(12,12));
		JPanel dol = new JPanel(new GridLayout(1,3));
	
		//tworzenie przyciskow na mapie
		for(int a=0;a<12;a++) {
			for(int b=0;b<12;b++) {
				//Tworzenie przycisku
					final int v =a;
					final int vv =b;
				tablicaprzyciskow[a][b] = new JButton();
				tablicaprzyciskow[a][b].setBackground(Color.GRAY);
				plansza.add(tablicaprzyciskow[a][b]); //przyciski sa dodane od razu
				System.out.print(a+" "+b+"  ");
				tablicaprzyciskow[a][b].setBorderPainted(false);
			}
			System.out.println("");
			}
		//poczatkowe ustawienie wojownika
		wojownik.wys=11;
		wojownik.szer=1;
		tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(postac.icon);
		//przyciski poza mapa
		JButton staty = new JButton("Statystyki");
		JButton eq = new JButton("Ekwipunek");
		JButton atak = new JButton("atak");
		JButton rozmowa = new JButton("rozmowa");
		rozmowa.setEnabled(false);
		JButton odpoczynek = new JButton("Odpocznij");
		
		
		//dodawanie przyciskow
		gora.add(staty);
		gora.add(eq);
		gora.add(hp);
		dol.add(atak);
		dol.add(rozmowa);
		dol.add(odpoczynek);
		//ustawianie wygladu
		gora.setVisible(true);
		plansza.setVisible(true);
		dol.setVisible(true);
		podstawa.add(gora,BorderLayout.NORTH);
		podstawa.add(plansza,BorderLayout.CENTER);
		podstawa.add(dol,BorderLayout.SOUTH);
		podstawa.setVisible(true);
		Main.dungeon.add(podstawa);
		
		//funkcja poruszania sie
		ruch(tablicaprzyciskow);
		
		//wywolanie przeciwnika
				przeciwnik przeciw = new przeciwnik();
				Thread Threadprzeciw = new Thread(przeciw);
				Threadprzeciw.start();
				
								
				wojownik wojownik = new wojownik();
		

		
		//otwarcie okna ze statystykami oraz awansowanie postaci
		staty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				statystyki stat = new statystyki(wojownik);
			}
						
		});
		
		
	}
	
	void ruch(JButton[][] tablicaprzyciskow) {
		KeyboardFocusManager keyManager;
		 
		keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			
			//strzalka w prawo
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if((wojownik.szer+1<=11) && (tablicaprzyciskow[wojownik.wys][wojownik.szer+1].getIcon()==null)) {
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(null);
					wojownik.szer++;
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(postac.icon);
					}
					zwrot = 2;
            return true;
				}
				return false;
			} 
		});
		
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			
			//strzalka w lewo
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_LEFT) {
					if((wojownik.szer-1>=0) && (tablicaprzyciskow[wojownik.wys][wojownik.szer-1].getIcon()==null)) {
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(null);
					wojownik.szer--;
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(postac.icon);
					}
					zwrot = 0;
            return true;
				}
				return false;
			}
		});
		
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			//strzalka w gore 
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_UP) {
					if((wojownik.wys-1>=0) && (tablicaprzyciskow[wojownik.wys-1][wojownik.szer].getIcon()==null)) {
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(null);
					wojownik.wys--;
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(postac.icon);
					}
					zwrot = 1;
            return true;
				}
				return false;
			}
		});
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			 
			//strzalka w dol
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_DOWN) {
					if((wojownik.wys+1<=11) && (tablicaprzyciskow[wojownik.wys+1][wojownik.szer].getIcon()==null)) {
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(null);
					wojownik.wys++;
					tablicaprzyciskow[wojownik.wys][wojownik.szer].setIcon(postac.icon);
					}
					zwrot = 3;
            return true;
				}
				return false;
			}
		});
		
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			 //atak - spacja
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_SPACE) {
					switch (zwrot){
					case 0:
						if(wojownik.szer-1>=0) {
							uderzenie uderz = new uderzenie();
						Thread zamach = new Thread(uderz);
						zamach.start();
							if(tablicaprzyciskow[wojownik.wys][wojownik.szer-1].getIcon()!=null) {
								int zmienna = (wojownik.sila - przeciwnik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									przeciwnik.hp=przeciwnik.hp-wojownik.sila;
									System.out.println("trafiony");
									System.out.println(przeciwnik.hp);
								}
							}
							tablicaprzyciskow[wojownik.wys][wojownik.szer-1].setBackground(Color.GRAY);
						}
						break;
					case 1:
						if(wojownik.wys>=0) {
							uderzenie uderz = new uderzenie();
						Thread zamach = new Thread(uderz);
						zamach.start();
						
							if(tablicaprzyciskow[wojownik.wys-1][wojownik.szer].getIcon()!=null) {
								int zmienna = (wojownik.sila - przeciwnik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									przeciwnik.hp=przeciwnik.hp-wojownik.sila;
									System.out.println("trafiony");
									System.out.println(przeciwnik.hp);
								}
							}
							tablicaprzyciskow[wojownik.wys-1][wojownik.szer].setBackground(Color.GRAY);
							
						}
						break;
					case 2:
						if(wojownik.szer<=11) {
							uderzenie uderz = new uderzenie();
						Thread zamach = new Thread(uderz);
						zamach.start();
							if(tablicaprzyciskow[wojownik.wys][wojownik.szer+1].getIcon()!=null) {
								int zmienna = (wojownik.sila - przeciwnik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									przeciwnik.hp=przeciwnik.hp-wojownik.sila;
								}
							}
							tablicaprzyciskow[wojownik.wys][wojownik.szer+1].setBackground(Color.GRAY);
							
						}
						break;
					case 3:
						if(wojownik.wys<=11) {
							uderzenie uderz = new uderzenie();
						Thread zamach = new Thread(uderz);
						zamach.start();
							if(tablicaprzyciskow[wojownik.wys+1][wojownik.szer].getIcon()!=null) {
								int zmienna = (wojownik.sila - przeciwnik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									przeciwnik.hp=przeciwnik.hp-wojownik.sila;
									System.out.println("trafiony");
									System.out.println(przeciwnik.hp);
								}
							}
							tablicaprzyciskow[wojownik.wys+1][wojownik.szer].setBackground(Color.GRAY);
							
						}
						break;
					}
					if(przeciwnik.hp<0) {
						wojownik wojownik = new wojownik();
						wojownik.update();
					}
					
            return true;
				}
				return false;
			}
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		

		
	}
	
	

}
