import java.util.LinkedList;
import java.util.Queue;

public class SimulacionArrayQueue {
    static Queue<Integer> cola = new LinkedList<>();
    public static void main(String[] args) {
        cargarElementos();
        mostrarElementos();
        int indice = 2;
        accederEspecificamente(indice);
    }

    private static void accederEspecificamente(int indice) {
        // Acceder a un elemento específico (simulando acceso aleatorio)
        int elemento = obtenerElementoEnIndice(cola, indice);
        System.out.println("Elemento en el índice " + indice + ": " + elemento);
    }

    private static void mostrarElementos() {
        // Acceder a los elementos en secuencia
        System.out.println("Elementos en el 'array':");
        for (int elemento : cola) {
            System.out.println(elemento);
        }
    }

    private static void cargarElementos() {
        // Agregar elementos a la "cola-array"
        cola.offer(10);
        cola.offer(20);
        cola.offer(30);
        cola.offer(40);
        cola.offer(50);
        cola.offer(60);
    }

    public static int obtenerElementoEnIndice(Queue<Integer> cola, int indice) {
        int i = 0;
        for (int elemento : cola) {
            if (i == indice) {
                return elemento;
            }
            i++;
        }
        throw new IndexOutOfBoundsException("El índice está fuera de rango");
    }
}