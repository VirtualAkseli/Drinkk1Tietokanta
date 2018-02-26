package drinkkitietokanta;

public class Raaka_aine_neste {
    private int raaka_aine_neste_id;
    private String nimi;
    private int maara;
    private double hinta;
    private double alkoholipitoisuus;
    
    public Raaka_aine_neste(int a, String b, int c, double d, double e){
        raaka_aine_neste_id = a;
        nimi = b;
        maara = c;
        hinta = d;
        alkoholipitoisuus = e;
    }
    
    public int getId(){
        return raaka_aine_neste_id;
    }
    
    public String getNimi(){
        return nimi;
    }
    
    public int getMaara(){
        return maara;
    }    
    
    public double getHinta(){
        return hinta;
    }
    
    public double getAlkoholipitoisuus(){
        return alkoholipitoisuus;
    }

    @Override
    public String toString() {
        return nimi + " (" + alkoholipitoisuus +"%)";
    }
    
    
}
