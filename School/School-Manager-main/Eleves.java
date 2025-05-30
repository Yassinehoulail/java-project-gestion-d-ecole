public class Eleves extends Utilisateurs {
    private String matricule;
    private String classe;
    private int utilisateur_id;

    // Constructeur
    public Eleves(int id, String nom, String prenom, String email, String motDePasse, String role,
                  String matricule, String classe, int utilisateur_id) {
        super(id, nom, prenom, email, motDePasse, role);
        this.matricule = matricule;
        this.classe = classe;
        this.utilisateur_id = utilisateur_id;
    }

    // Getters et Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    @Override
    public String toString() {
        return "Eleve{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", matricule='" + matricule + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}