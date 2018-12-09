package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
	private Sovelluslogiikka sovellus;
	private Button undo;
    
	private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.sovellus = new Sovelluslogiikka();
		this.komennot = new HashMap<>();
		this.undo = undo;
		
		komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus));
		komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
    }
    
    @Override
    public void handle(Event event) {
		if (event.getTarget() != undo) {
			Komento komento = komennot.get((Button) event.getTarget());
			komento.suorita();
			edellinen = komento;
			undo.disableProperty().set(false);
		} else {
			edellinen.peru();
			edellinen = null;
		}
    }

}
