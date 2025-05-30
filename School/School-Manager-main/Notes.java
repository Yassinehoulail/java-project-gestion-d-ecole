import java.util.Date;

public class Notes {
    private int id;
    private int eleve_id;
    private int cours_id;
    private float valeur;
    private String date;

    // Constructeur
    public Notes(int id, int eleve_id, int cours_id, float valeur, String date) {
        this.id = id;
        this.eleve_id = eleve_id;
        this.cours_id = cours_id;
        this.valeur = valeur;
        this.date = date;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEleve_id() {
        return eleve_id;
    }

    public void setEleve_id(int eleve_id) {
        this.eleve_id = eleve_id;
    }

    public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", eleve_id=" + eleve_id +
                ", cours_id=" + cours_id +
                ", valeur=" + valeur +
                ", date='" + date + '\'' +
                '}';
    }
}