import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class przeciwnik extends Obserwowane implements Runnable {
	boolean exit = false;
	static int poziom = 1;
	static int sila = 4;
	static int wytrzymalosc = 7;
	static int zrecznosc = 2;
	static int hp = wytrzymalosc*3;
	gra gra;
		
	public przeciwnik() {
		Random random = new Random();
		int starypoziom = poziom;
		poziom = gra.zabici/10+1;
		hp = wytrzymalosc*3;
		
		if(poziom!=starypoziom) {
			int pkt = 5;
			while(pkt!=0) {
			int wybor = random.nextInt((3-1)+1)+1;
			switch (wybor) {
			case 1:{
			sila++;
			pkt--;			
			}
			break;
			case 2:{
				wytrzymalosc++;
				pkt--;				
			}
			break;
			case 3:{
				zrecznosc++;
				pkt--;
			}
			break;
			}
			}
			hp = wytrzymalosc*3;
		}
	}
	
			 @Override 
			 public void run() { 
				 try{
					 //ustawianie losowo przeciwnika na mapie
					 Random random = new Random();
					 int wys = random.nextInt((10-1)+1)+1;
					 int szer = random.nextInt((10-1)+1)+1;
					 
						BufferedImage img = null;
						try {
							img = ImageIO.read(new File("Goblin.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						Image dimg = img.getScaledInstance(50, 70, Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
					 gra.tablicaprzyciskow[wys][szer].setIcon(icon);
					 while(!exit) {
					 //sprawdza, czy przeciwnik jeszcze zyje
						 if(hp<=0) {
							 gra.tablicaprzyciskow[wys][szer].setBackground(Color.BLACK);
							 gra.tablicaprzyciskow[wys][szer].setIcon(null);
							 Thread.sleep(1500);
							 gra.tablicaprzyciskow[wys][szer].setBackground(Color.GRAY);
							 exit = true;						 
							 continue;
						 }
						 //przeciwnik szuka gracza na przestrzeni swojej widocznosci
					 for(int a=wys-4;a<=wys+4;a++) {
						 for(int b=szer-4;b<=szer+4;b++) {
							 if((a>11) || (a<0) || (b>11) || (b<0) || ((a==wys) && (b==szer))) {
								 continue; //pomija niepotrzebne i nieistniejace obszary
							 }
							 //jesli znajdzie gracza, zaczyna go gonic i szuka najprostszej drogi
							 if(gra.tablicaprzyciskow[a][b].getIcon()!=null) {
								int w = wys-a;
								int s = szer-b;
								if(w>0) {
									gra.tablicaprzyciskow[wys][szer].setIcon(null);
									if(gra.tablicaprzyciskow[wys-1][szer].getIcon()==null)
									wys--;
										gra.tablicaprzyciskow[wys][szer].setText(null);
									gra.tablicaprzyciskow[wys][szer].setIcon(icon);
									try        
									{
									    Thread.sleep(1000);
									} 
									catch(InterruptedException ex) 
									{
									    Thread.currentThread().interrupt();
									}
								}else if(w<0) {
									gra.tablicaprzyciskow[wys][szer].setIcon(null);
									if(gra.tablicaprzyciskow[wys+1][szer].getIcon()==null)
									wys++;
									gra.tablicaprzyciskow[wys][szer].setIcon(icon);
									try        
									{
									    Thread.sleep(1000);
									} 
									catch(InterruptedException ex) 
									{
									    Thread.currentThread().interrupt();
									}
								}else if(s>0) {
									gra.tablicaprzyciskow[wys][szer].setIcon(null);
									if(gra.tablicaprzyciskow[wys][szer-1].getIcon()==null)
									szer--;
									gra.tablicaprzyciskow[wys][szer].setIcon(icon);
									try        
									{
									    Thread.sleep(1000);
									} 
									catch(InterruptedException ex) 
									{
									    Thread.currentThread().interrupt();
									}
								}else if(s<0) {
									gra.tablicaprzyciskow[wys][szer].setIcon(null);
									if(gra.tablicaprzyciskow[wys][szer+1].getIcon()==null)
									szer++;
									gra.tablicaprzyciskow[wys][szer].setIcon(icon);
									try        
									{
									    Thread.sleep(1000);
									} 
									catch(InterruptedException ex) 
									{
									    Thread.currentThread().interrupt();
									}
								}else {
									exit = true;
								}								
																
							if(gra.tablicaprzyciskow[wys+1][szer].getIcon()!=null) {
								gra.tablicaprzyciskow[wys+1][szer].setBackground(Color.YELLOW);
								int zmienna = (przeciwnik.sila - wojownik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									wojownik.hp=wojownik.hp-przeciwnik.sila;
									gra.hp.setText("HP: "+wojownik.hp);
								}
							
								Thread.sleep(250);
								gra.tablicaprzyciskow[wys+1][szer].setBackground(Color.GRAY);
								
							}else if(gra.tablicaprzyciskow[wys-1][szer].getIcon()!=null) {
								gra.tablicaprzyciskow[wys-1][szer].setBackground(Color.YELLOW);
								int zmienna = (przeciwnik.sila - wojownik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									wojownik.hp=wojownik.hp-przeciwnik.sila;
									gra.hp.setText("HP: "+wojownik.hp);
								}
							
								Thread.sleep(250);
								gra.tablicaprzyciskow[wys-1][szer].setBackground(Color.GRAY);
								
							}else if(gra.tablicaprzyciskow[wys][szer+1].getIcon()!=null) {
								gra.tablicaprzyciskow[wys][szer+1].setBackground(Color.YELLOW);
								int zmienna = (przeciwnik.sila - wojownik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									wojownik.hp=wojownik.hp-przeciwnik.sila;
									gra.hp.setText("HP: "+wojownik.hp);
								}
							
								Thread.sleep(250);
								gra.tablicaprzyciskow[wys][szer+1].setBackground(Color.GRAY);
								
							}else if(gra.tablicaprzyciskow[wys][szer-1].getIcon()!=null) {
								gra.tablicaprzyciskow[wys][szer-1].setBackground(Color.YELLOW);
								int zmienna = (przeciwnik.sila - wojownik.zrecznosc)*5;
								int randomNumber = random.nextInt((100-1)+1)+1;
								if(randomNumber>50-zmienna) {
									wojownik.hp=wojownik.hp-przeciwnik.sila;
									gra.hp.setText("HP: "+wojownik.hp);
								}
							
								Thread.sleep(250);
								gra.tablicaprzyciskow[wys][szer-1].setBackground(Color.GRAY);
							}
						 }}
						 	
						 //jak gracz straci cale zycie
							if(wojownik.hp<=0) {
								postac.icon=null;
								JOptionPane koniec = new JOptionPane();
								koniec.showMessageDialog(null, "przegrales", "przegrana", JOptionPane.INFORMATION_MESSAGE);
								Thread.sleep(10000);
							}
							 }
						 
					 }

						//wywolanie przeciwnika
								przeciwnik przeciw = new przeciwnik();
								Thread Threadprzeciw = new Thread(przeciw);
								Threadprzeciw.start();
					 
								
								
					 
					 }catch (Exception e) {                      
						 System.out.println("Exception 1 :"+e.getMessage());                
						 }}


	}

