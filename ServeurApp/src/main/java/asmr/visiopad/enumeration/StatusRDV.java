package asmr.visiopad.enumeration;

/**
 * Enumeration représentant l'ensemble des
 * status possibles pour le statut d'un
 * rendez-vous
 * @author Mohammad Oubari
 */
public enum StatusRDV {
    NORMAL("NORMAL"),
    EXCEPTIONNEL("EXCEPTIONNEL");

    private String statusRDV;

    /**
     * constructeur par defaut
     * @param statusRDV le code du statut
     */
    private StatusRDV(String statusRDV) {
        this.statusRDV = statusRDV;
    }

    /**
     * Permet de recuperer le code du statut
     * @return Le code du statut
     */
    public String getStatusRDV() {
        return this.statusRDV;
    }

    /**
     * Permet de récupérer l'instance allant avec
     * le code donnée en paramètre
     * @param v Le code de l'état
     * @return une instance de la classe correspondant au code
     */
    public static StatusRDV fromValue(String v) {
        EnumUtilities<StatusRDV> enumU = new EnumUtilities<StatusRDV>();
        return enumU.fromValue(v, StatusRDV.values());
    }
}
