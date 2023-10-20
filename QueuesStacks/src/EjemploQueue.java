import java.util.LinkedList;
import java.util.Queue;

public class EjemploQueue {
    static Queue<String> fila = new LinkedList<>();
    public static void main(String[] args) {
        cargarFila();
        atenderPrimero();
        mostrarFila();
        atenderPrimero();
        mostrarFila();
    }

    private static void atenderPrimero() {
        // Eliminar y mostrar elementos de la cola
        if (!fila.isEmpty()) {
            System.out.println("Elemento eliminado de la cola: " + fila.poll());
        }
    }

    private static void cargarFila() {
        // Agregar elementos a la cola
        fila.offer("Elemento 1");
        fila.offer("Elemento 2");
        fila.offer("Elemento 3");
        fila.offer("Elemento 4");
        fila.offer("Elemento 5");
    }

    private static void mostrarFila(){
        // Mostrar el estado actual de la fila
        System.out.println("Fila actual: " + fila);
    }
}
