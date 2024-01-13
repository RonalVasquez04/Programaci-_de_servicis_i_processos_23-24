import java.io.Serializable;
import java.util.Random;

public class Refresc implements Serializable {
    private String nom;

    public Refresc(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Refresc crearRefrescAleatori() {
        int numAleatori = new Random().nextInt(7) + 1;

        switch (numAleatori) {
            case 1:
                return new Refresc("Fanta Taronja");
            case 2:
                return new Refresc("Fanta Llima");
            case 3:
                return new Refresc("Nestea");
            case 4:
                return new Refresc("Aquarius");
            case 5:
                return new Refresc("CocaCola");
            case 6:
                return new Refresc("Sprite");
            default:
                return new Refresc("Aigua");
        }
    }

}
