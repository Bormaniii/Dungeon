import java.awt.Color;

public class uderzenie implements Runnable {
	static boolean czas_uderzenia = false;
	
	public uderzenie() {		
	}
	
	
	 @Override 
	 public void run() { 
		 try{
			 czas_uderzenia = false;
			 switch(gra.zwrot) {
			 case 0:
				 gra.tablicaprzyciskow[wojownik.wys][wojownik.szer-1].setBackground(Color.RED);
				 Thread.sleep(200);
				 gra.tablicaprzyciskow[wojownik.wys][wojownik.szer-1].setBackground(Color.GRAY);
				 break;
			 case 1:
				 gra.tablicaprzyciskow[wojownik.wys-1][wojownik.szer].setBackground(Color.RED);
				 Thread.sleep(200);
				 gra.tablicaprzyciskow[wojownik.wys-1][wojownik.szer].setBackground(Color.GRAY);
				 break;
			 case 2:
				 gra.tablicaprzyciskow[wojownik.wys][wojownik.szer+1].setBackground(Color.RED);
				 Thread.sleep(200);
				 gra.tablicaprzyciskow[wojownik.wys][wojownik.szer+1].setBackground(Color.GRAY);
				 break;
			 case 3:
				 gra.tablicaprzyciskow[wojownik.wys+1][wojownik.szer].setBackground(Color.RED);
				 Thread.sleep(200);
				 gra.tablicaprzyciskow[wojownik.wys+1][wojownik.szer].setBackground(Color.GRAY);
				 break;
				 
			 }
			
			 
		 }catch (Exception e) {                      
			 System.out.println("Exception 1 :"+e.getMessage());                
			 }
	 }
}
