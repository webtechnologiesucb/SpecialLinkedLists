import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinatoriaRecursivaTest {
    private CombinatoriaRecursiva oClase;
    @BeforeEach
    void setUp() {
        oClase = new CombinatoriaRecursiva();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Prueba completada");
    }

    @Test
    void combinatoria() {
        int n = 5;
        int k = 3;
        int resultado = 10;
        assertEquals(resultado, oClase.combinatoria(n, k));
        n = 10;
        k = 5;
        resultado = 252;
        assertEquals(resultado, oClase.combinatoria(n, k));
    }
}