package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus implements Komento {
	private TextField tuloskentta; 
    private TextField syotekentta; 
	private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
	private int edellinenLuku;

	public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
		this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
	}

	@Override
	public void suorita() {
		int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
		
		edellinenLuku = arvo;
		
		sovellus.miinus(arvo);
		
		int laskunTulos = sovellus.tulos();
        paivitaFieldit(laskunTulos);
	}
	
	private void paivitaFieldit(int laskunTulos) {
		syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
		
		if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
	}

	@Override
	public void peru() {
		System.out.println("undo pressed");
		sovellus.plus(edellinenLuku);
		
		paivitaFieldit(sovellus.tulos());
		undo.disableProperty().set(true);
	}
	
}
