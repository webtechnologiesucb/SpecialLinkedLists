import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class SimulacionSupermercado {
    static Stack<String> carritos = new Stack<>();
    static Queue<String> clientes = new LinkedList<>();

    private static void atenderCajas() {
        // Simular el proceso de compra en el supermercado
        while (!clientes.isEmpty()) {
            String cliente = clientes.poll();
            System.out.println(cliente + " está siendo atendido. Comprando con " + carritos.pop());
            System.out.println("Clientes en cola: " + clientes);
        }
        // Verificar si todos los clientes han sido atendidos
        if (clientes.isEmpty() && carritos.isEmpty()) {
            System.out.println("Todos los clientes han sido atendidos y han realizado sus compras.");
        }
    }

    private static void cargarClientes() {
        // Crear una cola de clientes en la caja
        clientes.offer("Carlos");
        clientes.offer("Paola");
        clientes.offer("Daniela");
        clientes.offer("Teddy");
        clientes.offer("Vanessa");
        clientes.offer("Diego");
    }

    private static void cargarCarritos() {
        // Crear una pila de carritos
        carritos.push("CAR01");
        carritos.push("CAR02");
        carritos.push("CAR03");
        carritos.push("CAR04");
        carritos.push("CAR05");
        carritos.push("CAR06");
    }

    public static void main(String[] args) {
        cargarCarritos();
        cargarClientes();
        atenderCajas();
    }
}
