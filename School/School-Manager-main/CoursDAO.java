import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    private List<Cours> cours;

    public CoursDAO() {
        this.cours = new ArrayList<>();
    }

    // Ajouter un cours
    public void ajouter(Cours nouveauCours) {
        cours.add(nouveauCours);
    }

    // Supprimer un cours
    public boolean supprimer(int id) {
        return cours.removeIf(c -> c.getId() == id);
    }

    // Modifier un cours
    public boolean modifier(Cours nouveauCours) {
        for (int i = 0; i < cours.size(); i++) {
            if (cours.get(i).getId() == nouveauCours.getId()) {
                cours.set(i, nouveauCours);
                return true;
            }
        }
        return false;
    }

    // Trouver un cours par id
    public Cours trouverParId(int id) {
        for (Cours c : cours) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // Lister tous les cours
    public List<Cours> listerTous() {
        return new ArrayList<>(cours);
    }

    // Trouver les cours par enseignant
    public List<Cours> trouverParEnseignant(int enseignant_id) {
        List<Cours> coursParEnseignant = new ArrayList<>();
        for (Cours c : cours) {
            if (c.getEnseignant_id() == enseignant_id) {
                coursParEnseignant.add(c);
            }
        }
        return coursParEnseignant;
    }
}