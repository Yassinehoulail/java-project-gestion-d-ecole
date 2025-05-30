import java.util.ArrayList;
import java.util.List;

public class EleveDAO {
    private List<Eleves> eleves;

    public EleveDAO() {
        this.eleves = new ArrayList<>();
    }

    // Ajouter un élève
    public void ajouter(Eleves eleve) {
        eleves.add(eleve);
    }

    // Supprimer un élève
    public boolean supprimer(int id) {
        return eleves.removeIf(e -> e.getId() == id);
    }

    // Modifier un élève
    public boolean modifier(Eleves eleve) {
        for (int i = 0; i < eleves.size(); i++) {
            if (eleves.get(i).getId() == eleve.getId()) {
                eleves.set(i, eleve);
                return true;
            }
        }
        return false;
    }

    // Trouver un élève par id
    public Eleves trouverParId(int id) {
        for (Eleves e : eleves) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Lister tous les élèves
    public List<Eleves> listerTous() {
        return new ArrayList<>(eleves);
    }

    // Trouver les élèves par classe
    public List<Eleves> trouverParClasse(String classe) {
        List<Eleves> elevesParClasse = new ArrayList<>();
        for (Eleves e : eleves) {
            if (e.getClasse().equals(classe)) {
                elevesParClasse.add(e);
            }
        }
        return elevesParClasse;
    }
}