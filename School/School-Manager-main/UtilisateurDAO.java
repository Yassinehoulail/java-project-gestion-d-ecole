import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private List<Utilisateurs> utilisateurs;

    public UtilisateurDAO() {
        this.utilisateurs = new ArrayList<>();
    }

    // Ajouter un utilisateur
    public void ajouter(Utilisateurs utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // Supprimer un utilisateur
    public boolean supprimer(int id) {
        return utilisateurs.removeIf(u -> u.getId() == id);
    }

    // Modifier un utilisateur
    public boolean modifier(Utilisateurs utilisateur) {
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).getId() == utilisateur.getId()) {
                utilisateurs.set(i, utilisateur);
                return true;
            }
        }
        return false;
    }

    // Trouver un utilisateur par id
    public Utilisateurs trouverParId(int id) {
        for (Utilisateurs u : utilisateurs) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    // Lister tous les utilisateurs
    public List<Utilisateurs> listerTous() {
        return new ArrayList<>(utilisateurs);
    }
}