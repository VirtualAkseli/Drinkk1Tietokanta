package drinkki;


public class Drinkki {
    private int drinkki_id;
    private String nimi;
    private String lasityyppi;
    private String resepti;

    public Drinkki(int drinkki_id, String nimi, String lasityyppi, String resepti) {
        this.drinkki_id = drinkki_id;
        this.nimi = nimi;
        this.lasityyppi = lasityyppi;
        this.resepti = resepti;
    }

    public int getDrinkki_id() {
        return drinkki_id;
    }

    public String getLasityyppi() {
        return lasityyppi;
    }

    public String getNimi() {
        return nimi;
    }

    public String getResepti() {
        return resepti;
    }
    
}
