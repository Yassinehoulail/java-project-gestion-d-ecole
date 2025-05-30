import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteDAO {
    private List<Notes> notes;

    public NoteDAO() {
        this.notes = new ArrayList<>();
    }

    // Ajouter une note
    public void ajouter(Notes note) {
        notes.add(note);
    }

    // Supprimer une note
    public boolean supprimer(int id) {
        return notes.removeIf(n -> n.getId() == id);
    }

    // Modifier une note
    public boolean modifier(Notes note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == note.getId()) {
                notes.set(i, note);
                return true;
            }
        }
        return false;
    }

    // Trouver une note par id
    public Notes trouverParId(int id) {
        for (Notes n : notes) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    // Lister toutes les notes
    public List<Notes> listerTous() {
        return new ArrayList<>(notes);
    }

    // Trouver les notes d'un élève
    public List<Notes> trouverNotesParEleve(int eleve_id) {
        List<Notes> notesEleve = new ArrayList<>();
        for (Notes n : notes) {
            if (n.getEleve_id() == eleve_id) {
                notesEleve.add(n);
            }
        }
        return notesEleve;
    }

    // Trouver les notes d'un cours
    public List<Notes> trouverNotesParCours(int cours_id) {
        List<Notes> notesCours = new ArrayList<>();
        for (Notes n : notes) {
            if (n.getCours_id() == cours_id) {
                notesCours.add(n);
            }
        }
        return notesCours;
    }

    // Calculer la moyenne d'un élève pour un cours
    public float calculerMoyenne(int eleve_id, int cours_id) {
        List<Notes> notesEleveCours = new ArrayList<>();
        for (Notes n : notes) {
            if (n.getEleve_id() == eleve_id && n.getCours_id() == cours_id) {
                notesEleveCours.add(n);
            }
        }

        if (notesEleveCours.isEmpty()) {
            return 0;
        }

        float somme = 0;
        for (Notes n : notesEleveCours) {
            somme += n.getValeur();
        }

        return somme / notesEleveCours.size();
    }

    // Générer un bulletin pour un élève
    public Map<Integer, Float> genererBulletin(int eleve_id) {
        Map<Integer, Float> bulletin = new HashMap<>();
        List<Notes> notesEleve = trouverNotesParEleve(eleve_id);

        for (Notes note : notesEleve) {
            int cours_id = note.getCours_id();
            float moyenne = calculerMoyenne(eleve_id, cours_id);
            bulletin.put(cours_id, moyenne);
        }

        return bulletin;
    }
}