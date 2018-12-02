
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        onkoKontstruktorinParametritPositiivisia(kapasiteetti, kasvatuskoko);
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
	
	public void onkoKontstruktorinParametritPositiivisia(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
	}

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
			lisaaTaulukonEnsimmaiseksiAlkioksi(luku);
            return true;
        } else if (!kuuluu(luku)) {
            lisääLukuTaulukkoon(luku);
            return true;
        }
        return false;
    }
	
	public void lisaaTaulukonEnsimmaiseksiAlkioksi(int luku) {
		ljono[0] = luku;
        alkioidenLkm++;
	}
	
	public void lisääLukuTaulukkoon(int luku) {
		ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
		if (alkioidenLkm % ljono.length == 0) {
			int[] taulukkoOld = new int[ljono.length];
			taulukkoOld = ljono;
			kopioiTaulukko(ljono, taulukkoOld);
			ljono = new int[alkioidenLkm + kasvatuskoko];
			kopioiTaulukko(taulukkoOld, ljono);
		}
	}

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                on++;
            }
        }
		return on > 0;
    }

    public boolean poista(int luku) {
        int kohta = etsiPoistettavaKohtaJoukosta(luku);
        if (kohta != -1) {
            siirraTaulukonLoppuosaaAlkuaKohtiPoistokohdasta(kohta);
            return true;
        }
        return false;
    }
	
	public void siirraTaulukonLoppuosaaAlkuaKohtiPoistokohdasta(int kohta) {
		int apu;
		for (int j = kohta; j < alkioidenLkm - 1; j++) {
			apu = ljono[j];
			ljono[j] = ljono[j + 1];
			ljono[j + 1] = apu;
		}
		alkioidenLkm--;
	}
	
	public int etsiPoistettavaKohtaJoukosta(int luku) {
		int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                ljono[kohta] = 0;
                break;
            }
        }
		return kohta;
	}

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
		
        lisaaIntJoukkoonTaulu(x, aTaulu);
		lisaaIntJoukkoonTaulu(x, bTaulu);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
		teeLeikkausKahdenTaulunValilla(y, aTaulu, bTaulu);
        return y;
    }
	
	public static void teeLeikkausKahdenTaulunValilla(IntJoukko x, int[] aTaulu, int[] bTaulu) {
		for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    x.lisaa(bTaulu[j]);
                }
            }
        }
	}
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
		lisaaIntJoukkoonTaulu(z, aTaulu);
		poistaIntJoukostaTaulunPituudenVerranInteja(z, bTaulu);
 
        return z;
    }
	
	public static void lisaaIntJoukkoonTaulu(IntJoukko x, int[] taulu) {
		for (int i = 0; i < taulu.length; i++) {
            x.lisaa(taulu[i]);
        }
	}
	
	public static void poistaIntJoukostaTaulunPituudenVerranInteja(IntJoukko x, int[] taulu) {
		for (int i = 0; i < taulu.length; i++) {
            x.poista(i);
        }
	}
}