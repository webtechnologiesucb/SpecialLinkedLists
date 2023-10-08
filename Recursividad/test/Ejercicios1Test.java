import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class Ejercicios1Test {

    @Test
    void sumaResta() {
        Ejercicios1 oEj = new Ejercicios1();
        int[] vec = new int[]{90,50,70};
        assertEquals(110, oEj.sumaResta(vec));
    }

    @Test
    void generarVector() {
        Ejercicios1 oEj = new Ejercicios1();
        int n = 5;
        int[] vec = new int[]{2,4,6,8,10};
        int[] vectorPares = new int[n];
        assertArrayEquals(vec, oEj.generarVector(n, 2, vectorPares, 0));
    }
}