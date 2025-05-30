public class Cours {
    private int id;
    private String nom;
    private int enseignant_id;

    // Constructeur
    public Cours(int id, String nom, int enseignant_id) {
        this.id = id;
        this.nom = nom;
        this.enseignant_id = enseignant_id;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEnseignant_id() {
        return enseignant_id;
    }

    public void setEnseignant_id(int enseignant_id) {
        this.enseignant_id = enseignant_id;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", enseignant_id=" + enseignant_id +
                '}';
    }
}