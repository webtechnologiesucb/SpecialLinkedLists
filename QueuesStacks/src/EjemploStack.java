import java.util.Stack;

public class EjemploStack {
    static Stack<String> stack = new Stack<>();
    public static void main(String[] args) {
        verificarEstado();
        cargarPila();
        verCantidadElementos();
        verPrimero();
        extraerElemento();
        verCantidadElementos();
        extraerElemento();
        verCantidadElementos();
        verPrimero();
        verificarEstado();
    }

    private static void cargarPila() {
        // Agregar elementos al stack
        stack.push("Elemento 1");
        stack.push("Elemento 2");
        stack.push("Elemento 3");
        stack.push("Elemento 4");
        stack.push("Elemento 5");
    }

    private static void verPrimero() {
        // Acceder al elemento en la cima del stack sin eliminarlo
        String elementoEnCima = stack.peek();
        System.out.println("Elemento en la cima: " + elementoEnCima);
    }

    private static void extraerElemento() {
        // Eliminar el elemento en la cima del stack
        String elementoEliminado = stack.pop();
        System.out.println("Elemento eliminado de la cima: " + elementoEliminado);
    }

    private static void verCantidadElementos() {
        // Tamaño del stack
        int tamaño = stack.size();
        System.out.println("Tamaño del stack: " + tamaño);
    }

    private static void verificarEstado() {
        // Verificar si el stack está vacío
        boolean estaVacio = stack.isEmpty();
        System.out.println("El stack está vacío: " + estaVacio);
    }
}