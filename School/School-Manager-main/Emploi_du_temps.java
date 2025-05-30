public class Emploi_du_temps {
    private int id;
    private String classe;
    private int cours_id;

    // Constructeur
    public Emploi_du_temps(int id, String classe, int cours_id) {
        this.id = id;
        this.classe = classe;
        this.cours_id = cours_id;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }

    @Override
    public String toString() {
        return "Emploi_du_temps{" +
                "id=" + id +
                ", classe='" + classe + '\'' +
                ", cours_id=" + cours_id +
                '}';
    }
}