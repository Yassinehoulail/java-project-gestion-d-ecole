import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO {
    private List<Enseignants> enseignants;

    public EnseignantDAO() {
        this.enseignants = new ArrayList<>();
    }

    // Ajouter un enseignant
    public void ajouter(Enseignants enseignant) {
        enseignants.add(enseignant);
    }

    // Supprimer un enseignant
    public boolean supprimer(int id) {
        return enseignants.removeIf(e -> e.getId() == id);
    }

    // Modifier un enseignant
    public boolean modifier(Enseignants enseignant) {
        for (int i = 0; i < enseignants.size(); i++) {
            if (enseignants.get(i).getId() == enseignant.getId()) {
                enseignants.set(i, enseignant);
                return true;
            }
        }
        return false;
    }

    // Trouver un enseignant par id
    public Enseignants trouverParId(int id) {
        for (Enseignants e : enseignants) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Lister tous les enseignants
    public List<Enseignants> listerTous() {
        return new ArrayList<>(enseignants);
    }
}