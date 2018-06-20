import javax.swing.JOptionPane;

public class wojownik extends Obserwowane implements Observer{
	
static int poziom = 1;
static int exp = 0;
static int sila = 5;
static int wytrzymalosc = 10;
static int zrecznosc = 2;
static int inteligencja = 1;
static int potexp = 40;
static int hp = wytrzymalosc*3;
static int wys;
static int szer;
static int pkt = 0;
static gra gra;
	public wojownik() {
	}
	
	
	@Override
	public void update() {
		exp = exp+przeciwnik.poziom*3;
		gra.zabici++;
		System.out.println("poziom wroga: "+przeciwnik.poziom);
		System.out.println(gra.zabici);
		System.out.println(exp);
		if(exp>=potexp) {
			potexp+=gra.zabici*przeciwnik.poziom;
			poziom++;
			pkt+=5;
			JOptionPane.showMessageDialog(null, "Nowy poziom postaci!!!!", "LEVEL UP", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
