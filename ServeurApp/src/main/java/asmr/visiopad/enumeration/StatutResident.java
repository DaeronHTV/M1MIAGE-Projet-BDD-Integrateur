package asmr.visiopad.enumeration;

/**
 * Enumeration représentant l'ensemble des
 * status possibles pour le statut d'un
 * resident
 * @author Mohammad Oubari
 */
public enum StatutResident {
    DISPONIBLE("DISPONIBLE"),
    INDISPONIBLE("INDISPONIBLE"),
    ANCIENRESIDENT("ANCIENRESIDENT");

    private String statusResident;

    /**
     * Constructeur par defaut
     * @param statusResident Le code du statut
     */
    private StatutResident(String statusResident) {
        this.statusResident = statusResident;
    }

    /**
     * Permet de recuperer le code du statut
     * @return Le code du statut
     */
    public String getStatutResident() {
        return this.statusResident;
    }

    /**
     * Permet de récupérer l'instance allant avec
     * le code donnée en paramètre
     * @param v Le code de l'état
     * @return une instance de la classe correspondant au code
     */
    public static StatutResident fromValue(String v) {
        EnumUtilities<StatutResident> enumU = new EnumUtilities<StatutResident>();
        return enumU.fromValue(v, StatutResident.values());
    }

}
