package asmr.dataUtilities;

public final class BddHelper {
    private static String[] accents = {"À", "Á", "Â", "Ä", "Ç", "É", "È", "Ê", "Ë", "Í", "Ì", "Î", "Ï", "Ñ", "Ó", "Ò", "Ô", "Ö", "Ú", "Ù", "Û", "Ü"};
    private static String[] saccents = {"A", "A", "A", "A", "C", "E", "E", "E", "E", "I", "I", "I", "I", "N", "O", "O", "O", "O", "U", "U", "U", "U"};
    /**
     * Permet de respecter la norme DGOS sur l'insertion des prénoms
     * et des noms dans une base de données afin d'éviter les doublons
     * @param chaine
     * @return
     */
    @Deprecated
    public static String NormeDGOS(String chaine){
        String result = Trim(chaine);
        result = toASCII(chaine);
        return result.toUpperCase();
    }


    /**
     * Supprime tous les accents présents dans la chaine de caractère
     * et met la chaine de caractère en majuscule
     * @param chaine
     * @return
     */
    public static String toASCII(String chaine){
        int i = 0;
        String result = chaine.toUpperCase();
        for(String s : accents){
            result = result.replace(s, saccents[i]);
            i++;
        }
        return result;
    }

    @Deprecated
    public static String Trim(String chaine){
        return chaine.substring(1);
    }
}
