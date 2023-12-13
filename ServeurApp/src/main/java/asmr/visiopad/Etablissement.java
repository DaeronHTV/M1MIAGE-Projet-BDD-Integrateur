package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Etablissement de la base de données
 * @author Mohammad Oubari
 * @implNote Cette classe respecte le design pattern Builder
 */
public class Etablissement {

    private String id;
    private String nom;
    private String numeroTelephone;
    private String adresseMail;
    private String rue;
    private int codePostal;
    private String ville;
    private String pays;
    private int nbPlaces;
    private int nbUnitesOff;

    /**
     * Constructeur par Defaut
     */
    public Etablissement() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param builder
     */
    private Etablissement(EtablissementBuilder builder) {
        this.id = builder.id;
        this.nom = builder.nom;
        this.numeroTelephone = builder.numeroTelephone;
        this.adresseMail = builder.adresseMail;
        this.rue = builder.rue;
        this.codePostal = builder.codePostal;
        this.ville = builder.ville;
        this.pays = builder.pays;
        this.nbPlaces = builder.nbPlaces;
        this.nbUnitesOff = builder.nbUnitesOff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbUnitesOff() {
        return nbUnitesOff;
    }

    public void setNbUnitesOff(int nbUnitesOff) {
        this.nbUnitesOff = nbUnitesOff;
    }

    public static class EtablissementBuilder 
    {
        private String id;
        private String nom;
        private String numeroTelephone;
        private String adresseMail;
        private String rue;
        private int codePostal;
        private String ville;
        private String pays;
        private int nbPlaces;
        private int nbUnitesOff;

        public EtablissementBuilder(String id, String nom) {
            this.id = id;
            this.nom = nom;
        }
        public EtablissementBuilder adresseMail(String adresseMail) {
            this.adresseMail = adresseMail;
            return this;
        }
        public EtablissementBuilder numeroTelephone(String numeroTelephone) {
            this.numeroTelephone = numeroTelephone;
            return this;
        }
        public EtablissementBuilder rue(String rue) {
            this.rue = rue;
            return this;
        }
        public EtablissementBuilder ville(String ville) {
            this.ville = ville;
            return this;
        }
        public EtablissementBuilder codePostal(int codePostal) {
            this.codePostal = codePostal;
            return this;
        }
        public EtablissementBuilder pays(String pays) {
            this.pays = pays;
            return this;
        }
        public EtablissementBuilder nbPlaces(int nbPlaces) {
            this.nbPlaces = nbPlaces;
            return this;
        }
        public EtablissementBuilder nbUnitesOff(int nbUnitesOff) {
            this.nbUnitesOff = nbUnitesOff;
            return this;
        }
        public Etablissement build() {
            Etablissement etablissement =  new Etablissement(this);
            return etablissement;
        }
    }

}
