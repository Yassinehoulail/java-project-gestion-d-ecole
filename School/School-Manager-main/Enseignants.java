public class Enseignants extends Utilisateurs {
    private String specialite;
    private int utilisateur_id;

    // Constructeur
    public Enseignants(int id, String nom, String prenom, String email, String motDePasse, String role,
                       String specialite, int utilisateur_id) {
        super(id, nom, prenom, email, motDePasse, role);
        this.specialite = specialite;
        this.utilisateur_id = utilisateur_id;
    }

    // Getters et Setters
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }
}