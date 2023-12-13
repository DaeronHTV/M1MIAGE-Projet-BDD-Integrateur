package asmr.visiopad.enumeration;

/**
 * Enumeration représentant l'ensemble des 
 * état possibles pour une tablette
 * @author Mohammad Oubari
 */
public enum EtatTablette {
    HS("HS"),
    ENCHARGEMENT("ENCHARGEMENT");

    private String etatTablette;

    /**
     * constructeur par defaut
     * @param etatTablette le code de l'état
     */
    private EtatTablette(String etatTablette) {
        this.etatTablette = etatTablette;
    }

    /**
     * Permet de récupérer le code de l'état
     * @return le code l'état
     */
    public String getEtatTablette() {
        return this.etatTablette;
    }

    /**
     * Permet de récupérer l'instance allant avec
     * le code donnée en paramètre
     * @param v Le code de l'état
     * @return une instance de la classe correspondant au code
     */
    public static EtatTablette fromValue(String v) {
        EnumUtilities<EtatTablette> enumU = new EnumUtilities<EtatTablette>();
        return enumU.fromValue(v, EtatTablette.values());
    }
}
