import java.util.ArrayList;
import java.util.List;

public class EmploiDuTempsDAO {
    private List<Emploi_du_temps> emploisDuTemps;

    public EmploiDuTempsDAO() {
        this.emploisDuTemps = new ArrayList<>();
    }

    // Ajouter un emploi du temps
    public void ajouter(Emploi_du_temps emploiDuTemps) {
        emploisDuTemps.add(emploiDuTemps);
    }

    // Supprimer un emploi du temps
    public boolean supprimer(int id) {
        return emploisDuTemps.removeIf(e -> e.getId() == id);
    }

    // Modifier un emploi du temps
    public boolean modifier(Emploi_du_temps emploiDuTemps) {
        for (int i = 0; i < emploisDuTemps.size(); i++) {
            if (emploisDuTemps.get(i).getId() == emploiDuTemps.getId()) {
                emploisDuTemps.set(i, emploiDuTemps);
                return true;
            }
        }
        return false;
    }

    // Trouver un emploi du temps par id
    public Emploi_du_temps trouverParId(int id) {
        for (Emploi_du_temps e : emploisDuTemps) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Lister tous les emplois du temps
    public List<Emploi_du_temps> listerTous() {
        return new ArrayList<>(emploisDuTemps);
    }

    // Trouver les emplois du temps par classe
    public List<Emploi_du_temps> trouverParClasse(String classe) {
        List<Emploi_du_temps> emploisParClasse = new ArrayList<>();
        for (Emploi_du_temps e : emploisDuTemps) {
            if (e.getClasse().equals(classe)) {
                emploisParClasse.add(e);
            }
        }
        return emploisParClasse;
    }

    // Trouver les emplois du temps par cours
    public List<Emploi_du_temps> trouverParCours(int cours_id) {
        List<Emploi_du_temps> emploisParCours = new ArrayList<>();
        for (Emploi_du_temps e : emploisDuTemps) {
            if (e.getCours_id() == cours_id) {
                emploisParCours.add(e);
            }
        }
        return emploisParCours;
    }
}