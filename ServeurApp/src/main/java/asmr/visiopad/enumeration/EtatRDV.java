package asmr.visiopad.enumeration;

/**
 * Enumeration représentant l'ensemble
 * des etat possibles pour un rendez-vous
 * @author Mohammad Oubari
 */
public enum EtatRDV {
    PROGRAMME("PROGRAMME"),
    VALIDE("VALIDE"),
    ANNULE("ANNULE"),
    TERMINE("TERMINE");

    private String etatRDV;

    /**
     * Constructeur par defaut
     * @param etatRDV le code de l'état
     */
    private EtatRDV(String etatRDV) {
        this.etatRDV = etatRDV;
    }

    /**
     * Permet de récupérer le code de l'état
     * @return le code de l'état
     */
    public String getEtatRDV() {
        return this.etatRDV;
    }

    /**
     * Permet de récupérer l'instance allant avec
     * le code donnée en paramètre
     * @param v Le code de l'état
     * @return une instance de la classe correspondant au code
     */
    public static EtatRDV fromValue(String v) {
        EnumUtilities<EtatRDV> enumU = new EnumUtilities<EtatRDV>();
        return enumU.fromValue(v, EtatRDV.values());
    }
}
