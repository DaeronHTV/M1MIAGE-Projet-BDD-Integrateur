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
 * Test pour la classe Personne réalisé avec JUnit 5
 * @author Avanzino Aurélien
 * @since 28/01/2021
 * @version 1.0
 * @see JUnit5
 */
@DisplayName("Test Unitaire du type Personne")
@TestInstance(Lifecycle.PER_CLASS)
public class PersonneTest {

    @Order(1) 
    @Test @Tag("Constructeur")
	@DisplayName("Test constructeur (string, string, string)")
	public void testPersonne() {
        Personne p1 = new Personne("DCF75BBAC7B74203A41CA34C7A1491DE", "JEAN", "TEST");
        Class<? extends Object> test = p1.getClass();
        assertEquals("Personne", test.getSimpleName()); //On vérifie qu'on a une bonne instance
        assertEquals("java.lang.Object", test.getSuperclass().getName()); //On test l'héritage
        assertEquals(0, test.getFields().length); //Test le nombre d'attribut public
        assertEquals(3, test.getDeclaredFields().length); //Test le nombre de d'attributs déclarée (mais pas ceux hérités)
    }
    
    @Order(2)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test Id Getter")
    @ValueSource(strings = {"DCF75BBAC7B74203A41CA34C7A1491DE", "B962C01C4EEC-4B1C-A000-B7565BACB73E"})
    public void testGetIdPersonne(String Id){
        Personne p = new Personne(Id, "JEAN", "TEST");
        assertEquals(Id, p.getIdPersonne());
    }

    @Order(2)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test nom Getter")
    @ValueSource(strings = {"Marie", "JEAN", "01212454"})
    public void testGetNom(String nom){
        Personne p = new Personne("1EFAAB630F294DCF954EE70EAE042DB8", "TEST", nom);
        assertEquals(nom, p.getNom());
    }

    @Order(2)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test prenom Getter")
    @ValueSource(strings = {"Marie", "JEAN", "01212454"})
    public void testGetPrenom(String prenom){
        Personne p = new Personne("1EFAAB630F294DCF954EE70EAE042DB8", prenom, "TEST");
        assertEquals(prenom, p.getPrenom());
    }

    @Order(3)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test id Setter")
    @ValueSource(strings = {"DCF75BBAC7B74203A41CA34C7A1491DE", "B962C01C4EEC-4B1C-A000-B7565BACB73E"})
    public void testSetId(String Id){
        Personne p = new Personne();
        p.setIdPersonne(Id);
        assertEquals(Id, p.getIdPersonne());
    }

    @Order(3)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test nom Setter")
    @ValueSource(strings = {"Marie", "JEAN", "01212454"})
    public void testSetNom(String nom){
        Personne p = new Personne();
        p.setNom(nom);
        assertEquals(nom, p.getNom());
    }

    @Order(3)  @Tag("Method")
    @ParameterizedTest
    @DisplayName("Test prenom Setter")
    @ValueSource(strings = {"Marie", "JEAN", "01212454"})
    public void testSetPrenom(String prenom){
        Personne p = new Personne();
        p.setPrenom(prenom);
        assertEquals(prenom, p.getPrenom());
    }

}
