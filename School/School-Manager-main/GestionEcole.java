import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestionEcole {
    private static UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();
    private static EleveDAO eleveDAO = new EleveDAO();
    private static CoursDAO coursDAO = new CoursDAO();
    private static NoteDAO noteDAO = new NoteDAO();
    private static EmploiDuTempsDAO emploiDuTempsDAO = new EmploiDuTempsDAO();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {
        initialiserDonneesDemonstration();

        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    gestionEtudiants();
                    break;
                case 2:
                    gestionEnseignants();
                    break;
                case 3:
                    gestionCours();
                    break;
                case 4:
                    gestionEmploiDuTemps();
                    break;
                case 5:
                    gestionNotes();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void afficherMenu() {
        System.out.println("\n===== GESTION DE L'ÉCOLE =====");
        System.out.println("1. Gestion des Étudiants");
        System.out.println("2. Gestion des Enseignants");
        System.out.println("3. Gestion des Cours");
        System.out.println("4. Gestion des Emplois du Temps");
        System.out.println("5. Gestion des Notes");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void gestionEtudiants() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES ÉTUDIANTS =====");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Modifier un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Lister tous les étudiants");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    modifierEtudiant();
                    break;
                case 3:
                    supprimerEtudiant();
                    break;
                case 4:
                    listerEtudiants();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterEtudiant() {
        System.out.println("\n=== Ajouter un étudiant ===");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        System.out.print("Classe : ");
        String classe = scanner.nextLine();

        int id = idCounter++;
        Utilisateurs utilisateur = new Utilisateurs(id, nom, prenom, email, motDePasse, "eleve");
        utilisateurDAO.ajouter(utilisateur);

        Eleves eleve = new Eleves(id, nom, prenom, email, motDePasse, "eleve", matricule, classe, id);
        eleveDAO.ajouter(eleve);

        System.out.println("Étudiant ajouté avec succès ! ID: " + id);
    }

    private static void modifierEtudiant() {
        System.out.println("\n=== Modifier un étudiant ===");
        listerEtudiants();

        System.out.print("Entrez l'ID de l'étudiant à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Eleves eleve = eleveDAO.trouverParId(id);
        if (eleve == null) {
            System.out.println("Étudiant non trouvé !");
            return;
        }

        System.out.print("Nouveau nom (actuel: " + eleve.getNom() + ") : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) eleve.setNom(nom);

        System.out.print("Nouveau prénom (actuel: " + eleve.getPrenom() + ") : ");
        String prenom = scanner.nextLine();
        if (!prenom.isEmpty()) eleve.setPrenom(prenom);

        System.out.print("Nouvel email (actuel: " + eleve.getEmail() + ") : ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) eleve.setEmail(email);

        System.out.print("Nouveau matricule (actuel: " + eleve.getMatricule() + ") : ");
        String matricule = scanner.nextLine();
        if (!matricule.isEmpty()) eleve.setMatricule(matricule);

        System.out.print("Nouvelle classe (actuelle: " + eleve.getClasse() + ") : ");
        String classe = scanner.nextLine();
        if (!classe.isEmpty()) eleve.setClasse(classe);

        eleveDAO.modifier(eleve);

        // Mettre à jour l'utilisateur correspondant
        Utilisateurs utilisateur = utilisateurDAO.trouverParId(eleve.getUtilisateur_id());
        if (utilisateur != null) {
            utilisateur.setNom(eleve.getNom());
            utilisateur.setPrenom(eleve.getPrenom());
            utilisateur.setEmail(eleve.getEmail());
            utilisateurDAO.modifier(utilisateur);
        }

        System.out.println("Étudiant modifié avec succès !");
    }

    private static void supprimerEtudiant() {
        System.out.println("\n=== Supprimer un étudiant ===");
        listerEtudiants();

        System.out.print("Entrez l'ID de l'étudiant à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Eleves eleve = eleveDAO.trouverParId(id);
        if (eleve == null) {
            System.out.println("Étudiant non trouvé !");
            return;
        }

        // Supprimer l'élève
        eleveDAO.supprimer(id);

        // Supprimer l'utilisateur correspondant
        utilisateurDAO.supprimer(eleve.getUtilisateur_id());

        System.out.println("Étudiant supprimé avec succès !");
    }

    private static void listerEtudiants() {
        System.out.println("\n=== Liste des étudiants ===");
        List<Eleves> eleves = eleveDAO.listerTous();

        if (eleves.isEmpty()) {
            System.out.println("Aucun étudiant trouvé.");
            return;
        }

        for (Eleves eleve : eleves) {
            System.out.println(eleve);
        }
    }

    private static void gestionEnseignants() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES ENSEIGNANTS =====");
            System.out.println("1. Ajouter un enseignant");
            System.out.println("2. Modifier un enseignant");
            System.out.println("3. Supprimer un enseignant");
            System.out.println("4. Lister tous les enseignants");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEnseignant();
                    break;
                case 2:
                    modifierEnseignant();
                    break;
                case 3:
                    supprimerEnseignant();
                    break;
                case 4:
                    listerEnseignants();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterEnseignant() {
        System.out.println("\n=== Ajouter un enseignant ===");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        System.out.print("Spécialité : ");
        String specialite = scanner.nextLine();

        int id = idCounter++;
        Utilisateurs utilisateur = new Utilisateurs(id, nom, prenom, email, motDePasse, "enseignant");
        utilisateurDAO.ajouter(utilisateur);

        Enseignants enseignant = new Enseignants(id, nom, prenom, email, motDePasse, "enseignant", specialite, id);
        enseignantDAO.ajouter(enseignant);

        System.out.println("Enseignant ajouté avec succès ! ID: " + id);
    }

    private static void modifierEnseignant() {
        System.out.println("\n=== Modifier un enseignant ===");
        listerEnseignants();

        System.out.print("Entrez l'ID de l'enseignant à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Enseignants enseignant = enseignantDAO.trouverParId(id);
        if (enseignant == null) {
            System.out.println("Enseignant non trouvé !");
            return;
        }

        System.out.print("Nouveau nom (actuel: " + enseignant.getNom() + ") : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) enseignant.setNom(nom);

        System.out.print("Nouveau prénom (actuel: " + enseignant.getPrenom() + ") : ");
        String prenom = scanner.nextLine();
        if (!prenom.isEmpty()) enseignant.setPrenom(prenom);

        System.out.print("Nouvel email (actuel: " + enseignant.getEmail() + ") : ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) enseignant.setEmail(email);

        System.out.print("Nouvelle spécialité (actuelle: " + enseignant.getSpecialite() + ") : ");
        String specialite = scanner.nextLine();
        if (!specialite.isEmpty()) enseignant.setSpecialite(specialite);

        enseignantDAO.modifier(enseignant);

        // Mettre à jour l'utilisateur correspondant
        Utilisateurs utilisateur = utilisateurDAO.trouverParId(enseignant.getUtilisateur_id());
        if (utilisateur != null) {
            utilisateur.setNom(enseignant.getNom());
            utilisateur.setPrenom(enseignant.getPrenom());
            utilisateur.setEmail(enseignant.getEmail());
            utilisateurDAO.modifier(utilisateur);
        }

        System.out.println("Enseignant modifié avec succès !");
    }

    private static void supprimerEnseignant() {
        System.out.println("\n=== Supprimer un enseignant ===");
        listerEnseignants();

        System.out.print("Entrez l'ID de l'enseignant à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Enseignants enseignant = enseignantDAO.trouverParId(id);
        if (enseignant == null) {
            System.out.println("Enseignant non trouvé !");
            return;
        }

        // Supprimer l'enseignant
        enseignantDAO.supprimer(id);

        // Supprimer l'utilisateur correspondant
        utilisateurDAO.supprimer(enseignant.getUtilisateur_id());

        System.out.println("Enseignant supprimé avec succès !");
    }

    private static void listerEnseignants() {
        System.out.println("\n=== Liste des enseignants ===");
        List<Enseignants> enseignants = enseignantDAO.listerTous();

        if (enseignants.isEmpty()) {
            System.out.println("Aucun enseignant trouvé.");
            return;
        }

        for (Enseignants enseignant : enseignants) {
            System.out.println(enseignant);
        }
    }

    private static void gestionCours() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES COURS =====");
            System.out.println("1. Ajouter un cours");
            System.out.println("2. Modifier un cours");
            System.out.println("3. Supprimer un cours");
            System.out.println("4. Lister tous les cours");
            System.out.println("5. Lister les cours par enseignant");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterCours();
                    break;
                case 2:
                    modifierCours();
                    break;
                case 3:
                    supprimerCours();
                    break;
                case 4:
                    listerCours();
                    break;
                case 5:
                    listerCoursParEnseignant();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterCours() {
        System.out.println("\n=== Ajouter un cours ===");

        System.out.print("Nom du cours : ");
        String nom = scanner.nextLine();

        listerEnseignants();
        System.out.print("ID de l'enseignant responsable : ");
        int enseignantId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (enseignantDAO.trouverParId(enseignantId) == null) {
            System.out.println("Enseignant non trouvé !");
            return;
        }

        int id = idCounter++;
        Cours cours = new Cours(id, nom, enseignantId);
        coursDAO.ajouter(cours);

        System.out.println("Cours ajouté avec succès ! ID: " + id);
    }

    private static void modifierCours() {
        System.out.println("\n=== Modifier un cours ===");
        listerCours();

        System.out.print("Entrez l'ID du cours à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Cours cours = coursDAO.trouverParId(id);
        if (cours == null) {
            System.out.println("Cours non trouvé !");
            return;
        }

        System.out.print("Nouveau nom (actuel: " + cours.getNom() + ") : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) cours.setNom(nom);

        System.out.println("ID actuel de l'enseignant : " + cours.getEnseignant_id());
        listerEnseignants();
        System.out.print("Nouvel ID de l'enseignant (laissez vide pour conserver l'actuel) : ");
        String enseignantIdStr = scanner.nextLine();

        if (!enseignantIdStr.isEmpty()) {
            int enseignantId = Integer.parseInt(enseignantIdStr);
            if (enseignantDAO.trouverParId(enseignantId) == null) {
                System.out.println("Enseignant non trouvé !");
                return;
            }
            cours.setEnseignant_id(enseignantId);
        }

        coursDAO.modifier(cours);
        System.out.println("Cours modifié avec succès !");
    }

    private static void supprimerCours() {
        System.out.println("\n=== Supprimer un cours ===");
        listerCours();

        System.out.print("Entrez l'ID du cours à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (coursDAO.trouverParId(id) == null) {
            System.out.println("Cours non trouvé !");
            return;
        }

        coursDAO.supprimer(id);
        System.out.println("Cours supprimé avec succès !");
    }

    private static void listerCours() {
        System.out.println("\n=== Liste des cours ===");
        List<Cours> cours = coursDAO.listerTous();

        if (cours.isEmpty()) {
            System.out.println("Aucun cours trouvé.");
            return;
        }

        for (Cours c : cours) {
            Enseignants enseignant = enseignantDAO.trouverParId(c.getEnseignant_id());
            String nomEnseignant = enseignant != null ? enseignant.getNom() + " " + enseignant.getPrenom() : "N/A";
            System.out.println(c + " - Enseignant: " + nomEnseignant);
        }
    }

    private static void listerCoursParEnseignant() {
        System.out.println("\n=== Liste des cours par enseignant ===");
        listerEnseignants();

        System.out.print("Entrez l'ID de l'enseignant : ");
        int enseignantId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Enseignants enseignant = enseignantDAO.trouverParId(enseignantId);
        if (enseignant == null) {
            System.out.println("Enseignant non trouvé !");
            return;
        }

        List<Cours> coursEnseignant = coursDAO.trouverParEnseignant(enseignantId);

        if (coursEnseignant.isEmpty()) {
            System.out.println("Aucun cours trouvé pour cet enseignant.");
            return;
        }

        System.out.println("Cours enseignés par " + enseignant.getNom() + " " + enseignant.getPrenom() + " :");
        for (Cours c : coursEnseignant) {
            System.out.println(c);
        }
    }

    private static void gestionEmploiDuTemps() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES EMPLOIS DU TEMPS =====");
            System.out.println("1. Ajouter un emploi du temps");
            System.out.println("2. Modifier un emploi du temps");
            System.out.println("3. Supprimer un emploi du temps");
            System.out.println("4. Lister tous les emplois du temps");
            System.out.println("5. Lister les emplois du temps par classe");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEmploiDuTemps();
                    break;
                case 2:
                    modifierEmploiDuTemps();
                    break;
                case 3:
                    supprimerEmploiDuTemps();
                    break;
                case 4:
                    listerEmploiDuTemps();
                    break;
                case 5:
                    listerEmploiDuTempsParClasse();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterEmploiDuTemps() {
        System.out.println("\n=== Ajouter un emploi du temps ===");

        System.out.print("Classe : ");
        String classe = scanner.nextLine();

        listerCours();
        System.out.print("ID du cours : ");
        int coursId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (coursDAO.trouverParId(coursId) == null) {
            System.out.println("Cours non trouvé !");
            return;
        }

        int id = idCounter++;
        Emploi_du_temps emploiDuTemps = new Emploi_du_temps(id, classe, coursId);
        emploiDuTempsDAO.ajouter(emploiDuTemps);

        System.out.println("Emploi du temps ajouté avec succès ! ID: " + id);
    }

    private static void modifierEmploiDuTemps() {
        System.out.println("\n=== Modifier un emploi du temps ===");
        listerEmploiDuTemps();

        System.out.print("Entrez l'ID de l'emploi du temps à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Emploi_du_temps emploiDuTemps = emploiDuTempsDAO.trouverParId(id);
        if (emploiDuTemps == null) {
            System.out.println("Emploi du temps non trouvé !");
            return;
        }

        System.out.print("Nouvelle classe (actuelle: " + emploiDuTemps.getClasse() + ") : ");
        String classe = scanner.nextLine();
        if (!classe.isEmpty()) emploiDuTemps.setClasse(classe);

        System.out.println("ID actuel du cours : " + emploiDuTemps.getCours_id());
        listerCours();
        System.out.print("Nouvel ID du cours (laissez vide pour conserver l'actuel) : ");
        String coursIdStr = scanner.nextLine();

        if (!coursIdStr.isEmpty()) {
            int coursId = Integer.parseInt(coursIdStr);
            if (coursDAO.trouverParId(coursId) == null) {
                System.out.println("Cours non trouvé !");
                return;
            }
            emploiDuTemps.setCours_id(coursId);
        }

        emploiDuTempsDAO.modifier(emploiDuTemps);
        System.out.println("Emploi du temps modifié avec succès !");
    }

    private static void supprimerEmploiDuTemps() {
        System.out.println("\n=== Supprimer un emploi du temps ===");
        listerEmploiDuTemps();

        System.out.print("Entrez l'ID de l'emploi du temps à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (emploiDuTempsDAO.trouverParId(id) == null) {
            System.out.println("Emploi du temps non trouvé !");
            return;
        }

        emploiDuTempsDAO.supprimer(id);
        System.out.println("Emploi du temps supprimé avec succès !");
    }

    private static void listerEmploiDuTemps() {
        System.out.println("\n=== Liste des emplois du temps ===");
        List<Emploi_du_temps> emploisDuTemps = emploiDuTempsDAO.listerTous();

        if (emploisDuTemps.isEmpty()) {
            System.out.println("Aucun emploi du temps trouvé.");
            return;
        }

        for (Emploi_du_temps edt : emploisDuTemps) {
            Cours cours = coursDAO.trouverParId(edt.getCours_id());
            String nomCours = cours != null ? cours.getNom() : "N/A";
            System.out.println(edt + " - Cours: " + nomCours);
        }
    }

    private static void listerEmploiDuTempsParClasse() {
        System.out.println("\n=== Liste des emplois du temps par classe ===");

        System.out.print("Entrez le nom de la classe : ");
        String classe = scanner.nextLine();

        List<Emploi_du_temps> emploisParClasse = emploiDuTempsDAO.trouverParClasse(classe);

        if (emploisParClasse.isEmpty()) {
            System.out.println("Aucun emploi du temps trouvé pour cette classe.");
            return;
        }

        System.out.println("Emplois du temps pour la classe " + classe + " :");
        for (Emploi_du_temps edt : emploisParClasse) {
            Cours cours = coursDAO.trouverParId(edt.getCours_id());
            String nomCours = cours != null ? cours.getNom() : "N/A";
            System.out.println(edt + " - Cours: " + nomCours);
        }
    }

    private static void gestionNotes() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES NOTES =====");
            System.out.println("1. Ajouter une note");
            System.out.println("2. Modifier une note");
            System.out.println("3. Supprimer une note");
            System.out.println("4. Lister toutes les notes");
            System.out.println("5. Lister les notes par élève");
            System.out.println("6. Lister les notes par cours");
            System.out.println("7. Générer un bulletin pour un élève");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterNote();
                    break;
                case 2:
                    modifierNote();
                    break;
                case 3:
                    supprimerNote();
                    break;
                case 4:
                    listerNotes();
                    break;
                case 5:
                    listerNotesParEleve();
                    break;
                case 6:
                    listerNotesParCours();
                    break;
                case 7:
                    genererBulletin();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void ajouterNote() {
        System.out.println("\n=== Ajouter une note ===");

        listerEtudiants();
        System.out.print("ID de l'élève : ");
        int eleveId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (eleveDAO.trouverParId(eleveId) == null) {
            System.out.println("Élève non trouvé !");
            return;
        }

        listerCours();
        System.out.print("ID du cours : ");
        int coursId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (coursDAO.trouverParId(coursId) == null) {
            System.out.println("Cours non trouvé !");
            return;
        }

        System.out.print("Valeur de la note (sur 20) : ");
        float valeur = scanner.nextFloat();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (valeur < 0 || valeur > 20) {
            System.out.println("La note doit être comprise entre 0 et 20 !");
            return;
        }

        System.out.print("Date (JJ/MM/AAAA) : ");
        String date = scanner.nextLine();

        int id = idCounter++;
        Notes note = new Notes(id, eleveId, coursId, valeur, date);
        noteDAO.ajouter(note);

        System.out.println("Note ajoutée avec succès ! ID: " + id);
    }

    private static void modifierNote() {
        System.out.println("\n=== Modifier une note ===");
        listerNotes();

        System.out.print("Entrez l'ID de la note à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Notes note = noteDAO.trouverParId(id);
        if (note == null) {
            System.out.println("Note non trouvée !");
            return;
        }

        System.out.println("Note actuelle : " + note.getValeur() + "/20 pour l'élève ID " +
                note.getEleve_id() + " au cours ID " + note.getCours_id());

        System.out.print("Nouvelle valeur (sur 20) : ");
        float valeur = scanner.nextFloat();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (valeur < 0 || valeur > 20) {
            System.out.println("La note doit être comprise entre 0 et 20 !");
            return;
        }
        note.setValeur(valeur);

        System.out.print("Nouvelle date (actuelle: " + note.getDate() + ") : ");
        String date = scanner.nextLine();
        if (!date.isEmpty()) note.setDate(date);

        noteDAO.modifier(note);
        System.out.println("Note modifiée avec succès !");
    }

    private static void supprimerNote() {
        System.out.println("\n=== Supprimer une note ===");
        listerNotes();

        System.out.print("Entrez l'ID de la note à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        if (noteDAO.trouverParId(id) == null) {
            System.out.println("Note non trouvée !");
            return;
        }

        noteDAO.supprimer(id);
        System.out.println("Note supprimée avec succès !");
    }

    private static void listerNotes() {
        System.out.println("\n=== Liste des notes ===");
        List<Notes> notes = noteDAO.listerTous();

        if (notes.isEmpty()) {
            System.out.println("Aucune note trouvée.");
            return;
        }

        for (Notes note : notes) {
            Eleves eleve = eleveDAO.trouverParId(note.getEleve_id());
            String nomEleve = eleve != null ? eleve.getNom() + " " + eleve.getPrenom() : "N/A";

            Cours cours = coursDAO.trouverParId(note.getCours_id());
            String nomCours = cours != null ? cours.getNom() : "N/A";

            System.out.println(note + " - Élève: " + nomEleve + " - Cours: " + nomCours);
        }
    }

    private static void listerNotesParEleve() {
        System.out.println("\n=== Liste des notes par élève ===");
        listerEtudiants();

        System.out.print("Entrez l'ID de l'élève : ");
        int eleveId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Eleves eleve = eleveDAO.trouverParId(eleveId);
        if (eleve == null) {
            System.out.println("Élève non trouvé !");
            return;
        }

        List<Notes> notesEleve = noteDAO.trouverNotesParEleve(eleveId);

        if (notesEleve.isEmpty()) {
            System.out.println("Aucune note trouvée pour cet élève.");
            return;
        }

        System.out.println("Notes de " + eleve.getNom() + " " + eleve.getPrenom() + " :");
        for (Notes note : notesEleve) {
            Cours cours = coursDAO.trouverParId(note.getCours_id());
            String nomCours = cours != null ? cours.getNom() : "N/A";
            System.out.println("Cours: " + nomCours + " - Note: " + note.getValeur() + "/20 - Date: " + note.getDate());
        }
    }

    private static void listerNotesParCours() {
        System.out.println("\n=== Liste des notes par cours ===");
        listerCours();

        System.out.print("Entrez l'ID du cours : ");
        int coursId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Cours cours = coursDAO.trouverParId(coursId);
        if (cours == null) {
            System.out.println("Cours non trouvé !");
            return;
        }

        List<Notes> notesCours = noteDAO.trouverNotesParCours(coursId);

        if (notesCours.isEmpty()) {
            System.out.println("Aucune note trouvée pour ce cours.");
            return;
        }

        System.out.println("Notes pour le cours " + cours.getNom() + " :");
        for (Notes note : notesCours) {
            Eleves eleve = eleveDAO.trouverParId(note.getEleve_id());
            String nomEleve = eleve != null ? eleve.getNom() + " " + eleve.getPrenom() : "N/A";
            System.out.println("Élève: " + nomEleve + " - Note: " + note.getValeur() + "/20 - Date: " + note.getDate());
        }
    }

    private static void genererBulletin() {
        System.out.println("\n=== Générer un bulletin ===");
        listerEtudiants();

        System.out.print("Entrez l'ID de l'élève : ");
        int eleveId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Eleves eleve = eleveDAO.trouverParId(eleveId);
        if (eleve == null) {
            System.out.println("Élève non trouvé !");
            return;
        }

        Map<Integer, Float> bulletin = noteDAO.genererBulletin(eleveId);

        if (bulletin.isEmpty()) {
            System.out.println("Aucune note trouvée pour cet élève.");
            return;
        }

        System.out.println("\n===== BULLETIN DE " + eleve.getNom().toUpperCase() + " " + eleve.getPrenom() + " =====");
        System.out.println("Classe: " + eleve.getClasse());
        System.out.println("--------------------------------------");

        float sommeGenerale = 0;
        int nombreCours = 0;

        for (Map.Entry<Integer, Float> entry : bulletin.entrySet()) {
            int coursId = entry.getKey();
            float moyenne = entry.getValue();

            Cours cours = coursDAO.trouverParId(coursId);
            String nomCours = cours != null ? cours.getNom() : "N/A";

            System.out.println(nomCours + ": " + moyenne + "/20");

            sommeGenerale += moyenne;
            nombreCours++;
        }

        float moyenneGenerale = nombreCours > 0 ? sommeGenerale / nombreCours : 0;
        System.out.println("--------------------------------------");
        System.out.println("Moyenne générale: " + moyenneGenerale + "/20");
    }

    private static void initialiserDonneesDemonstration() {
        // Ajout d'utilisateurs
        utilisateurDAO.ajouter(new Utilisateurs(idCounter++, "Dupont", "Jean", "jean.dupont@email.com", "pass123", "enseignant"));
        utilisateurDAO.ajouter(new Utilisateurs(idCounter++, "Martin", "Sophie", "sophie.martin@email.com", "pass123", "enseignant"));
        utilisateurDAO.ajouter(new Utilisateurs(idCounter++, "Dubois", "Pierre", "pierre.dubois@email.com", "pass123", "eleve"));
        utilisateurDAO.ajouter(new Utilisateurs(idCounter++, "Lefevre", "Marie", "marie.lefevre@email.com", "pass123", "eleve"));
        utilisateurDAO.ajouter(new Utilisateurs(idCounter++, "Bernard", "Thomas", "thomas.bernard@email.com", "pass123", "eleve"));

        // Ajout d'enseignants
        enseignantDAO.ajouter(new Enseignants(1, "Dupont", "Jean", "jean.dupont@email.com", "pass123", "enseignant", "Mathématiques", 1));
        enseignantDAO.ajouter(new Enseignants(2, "Martin", "Sophie", "sophie.martin@email.com", "pass123", "enseignant", "Français", 2));

        // Ajout d'élèves
        eleveDAO.ajouter(new Eleves(3, "Dubois", "Pierre", "pierre.dubois@email.com", "pass123", "eleve", "E001", "Terminale S", 3));
        eleveDAO.ajouter(new Eleves(4, "Lefevre", "Marie", "marie.lefevre@email.com", "pass123", "eleve", "E002", "Terminale S", 4));
        eleveDAO.ajouter(new Eleves(5, "Bernard", "Thomas", "thomas.bernard@email.com", "pass123", "eleve", "E003", "Première ES", 5));

        // Mise à jour du compteur d'ID
        idCounter = 6;

        // Ajout de cours
        coursDAO.ajouter(new Cours(idCounter++, "Mathématiques", 1));
        coursDAO.ajouter(new Cours(idCounter++, "Français", 2));
        coursDAO.ajouter(new Cours(idCounter++, "Philosophie", 2));

        // Ajout d'emplois du temps
        emploiDuTempsDAO.ajouter(new Emploi_du_temps(idCounter++, "Terminale S", 6));
        emploiDuTempsDAO.ajouter(new Emploi_du_temps(idCounter++, "Terminale S", 7));
        emploiDuTempsDAO.ajouter(new Emploi_du_temps(idCounter++, "Première ES", 7));
        emploiDuTempsDAO.ajouter(new Emploi_du_temps(idCounter++, "Première ES", 8));

        // Ajout de notes
        noteDAO.ajouter(new Notes(idCounter++, 3, 6, 15.5f, "01/04/2025"));
        noteDAO.ajouter(new Notes(idCounter++, 3, 7, 13.0f, "05/04/2025"));
        noteDAO.ajouter(new Notes(idCounter++, 4, 6, 17.0f, "01/04/2025"));
        noteDAO.ajouter(new Notes(idCounter++, 4, 7, 14.5f, "05/04/2025"));
        noteDAO.ajouter(new Notes(idCounter++, 5, 7, 12.0f, "06/04/2025"));
        noteDAO.ajouter(new Notes(idCounter++, 5, 8, 16.0f, "10/04/2025"));
    }
}