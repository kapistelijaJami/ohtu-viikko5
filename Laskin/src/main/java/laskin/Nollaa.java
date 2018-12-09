package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa implements Komento {
	private TextField tuloskentta; 
    private TextField syotekentta; 
	private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
	private int edellinenTulos;

	public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
		this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
	}

	@Override
	public void suorita() {
		edellinenTulos = sovellus.tulos();
		
		sovellus.nollaa();
		
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
		
		paivitaFieldit(edellinenTulos);
		undo.disableProperty().set(true);
	}
	
}
