package asmr.visiopad;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test pour la classe Utilisateur réalisé avec JUnit 5
 * @author Avanzino Aurélien
 * @since 28/01/2021
 * @version 1.0
 * @see JUnit5
 */
@DisplayName("Test Unitaire du type Personne")
@TestInstance(Lifecycle.PER_CLASS)
public class UtilisateurTest {

    @Order(1) 
    @Test @Tag("Constructeur")
	@DisplayName("Test constructeur Utilisateur")
	public void testPersonne() {
        Utilisateur u = new Utilisateur("DCF75BBAC7B74203A41CA34C7A1491DE", "JEAN", "TEST", "0666666666", "asmr.chuchoter@test.fr");
        Personne p = new Personne();
        Class<? extends Object> test = u.getClass();
        assertEquals(p.getClass(), test.getSuperclass());
        assertEquals(0, test.getFields().length);
        assertEquals(2, test.getDeclaredFields().length);
    }

    @Order(2)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test telephone Getter")
    @ValueSource(strings = {"00000000", "0621356421", "0654856495"})
    public void testGetTelephone(String telephone){
        Utilisateur u = new Utilisateur("DCF75BBAC7B74203A41CA34C7A1491DE", "JEAN", "TEST", telephone, "asmr.chuchoter@test.fr");
        assertEquals(telephone, u.getNumeroTelephone());
    }

    @Order(2)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test mail Getter")
    @ValueSource(strings = {"belle.delphine@gmail.com", "test.bonjour@xyz.fr", ""})
    public void testGetPrenom(String mail){
        Utilisateur u = new Utilisateur("DCF75BBAC7B74203A41CA34C7A1491DE", "JEAN", "TEST", "021548754", mail);
        assertEquals(mail, u.getAdresseMail());
    }

    @Order(3)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test telephone Setter")
    @ValueSource(strings = {"00000000", "0621356421", "0654856495"})
    public void testSetId(String telephone){
        Utilisateur u = new Utilisateur();
        u.setIdPersonne(telephone);
        assertEquals(telephone, u.getIdPersonne());
    }

    @Order(3)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test mail Setter")
    @ValueSource(strings = {"belle.delphine@gmail.com", "test.bonjour@xyz.fr", ""})
    public void testSetIdEtablissement(String mail){
        Utilisateur u = new Utilisateur();
        u.setAdresseMail(mail);
        assertEquals(mail, u.getAdresseMail());
    }
}
