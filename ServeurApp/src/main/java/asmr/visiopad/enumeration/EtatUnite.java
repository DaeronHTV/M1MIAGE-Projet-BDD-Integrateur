package asmr.visiopad.enumeration;

/**
 * Enumeration de représenter l'ensemble
 * des etat possible pour une unité
 * @author Mohammad Oubari
 */
public enum EtatUnite {
    OUVERTE("OUVERTE"),
    FERMEE("FERMEE"),
    DEJOUR("DEJOUR"),
    CONTAGIEUSE("CONTAGIEUSE");

    private String etatUnite;

    /**
     * constructeur par defaut
     * @param etatUnite Code de l'état
     */
    private EtatUnite(String etatUnite) {
        this.etatUnite = etatUnite;
    }

    /**
     * Permet de récupérer le code de l'état
     * @return le code de l'état
     */
    public String getEtatUnite() {
        return this.etatUnite;
    }

    /**
     * Permet de récupérer l'instance allant avec
     * le code donnée en paramètre
     * @param v Le code de l'état
     * @return une instance de la classe correspondant au code
     */
    public static EtatUnite fromValue(String v) {
        EnumUtilities<EtatUnite> enumU = new EnumUtilities<EtatUnite>();
        return enumU.fromValue(v, EtatUnite.values());
    }
}
