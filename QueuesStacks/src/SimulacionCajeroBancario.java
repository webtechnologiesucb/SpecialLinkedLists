import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SimulacionCajeroBancario {
    static Queue<String> clientes = new LinkedList<>();
    static Random random = new Random();
    static int tiempoTotalAtencion = 0;
    static int tiempoPromedioAtencion = 0;

    public static void main(String[] args) {
        cargarFila();
        atencionCajeros();
        mostrarResultados();
    }

    private static void mostrarResultados() {
        // Calcular el tiempo promedio de atención
        tiempoPromedioAtencion = tiempoTotalAtencion / 10;
        System.out.println("\nTodos los clientes han sido atendidos.");
        System.out.println("Tiempo promedio de atención: " + tiempoPromedioAtencion + " minutos.");
    }

    private static void atencionCajeros() {
        // Simular la atención de los clientes por un cajero
        while (!clientes.isEmpty()) {
            String cliente = clientes.poll();
            int tiempoAtencion = random.nextInt(10) + 1; // Tiempo aleatorio menor a 10 minutos
            tiempoTotalAtencion += tiempoAtencion;

            System.out.println(cliente + " está siendo atendido durante " + tiempoAtencion + " minutos");

            try {
                Thread.sleep(tiempoAtencion * 1000); // Simula el tiempo de atención en milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void cargarFila() {
        // Simular la llegada de 20 clientes a la fila
        for (int i = 1; i <= 10; i++) {
            clientes.offer("Cliente " + i);
        }
    }
}
