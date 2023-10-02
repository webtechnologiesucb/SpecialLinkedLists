public class RecursiveLinkedList<T> {
    private Nodo<T> cab; // El nodo inicial de la lista

    public RecursiveLinkedList() {
        cab = null; // Inicialmente, la lista está vacía
    }

    private static class Nodo<T> {
        T dato;
        Nodo<T> sig;

        Nodo(T data) {
            this.dato = data;
            this.sig = null;
        }
    }

    // Método para agregar un elemento al final de la lista
    public void agregar(T dato) {
        cab = agregarRec(cab, dato);
    }

    private Nodo<T> agregarRec(Nodo<T> current, T dato) {
        if (current == null)
            return new Nodo<T>(dato); // Crear un nuevo nodo si llegamos al final de la lista

        current.sig = agregarRec(current.sig, dato); // Recursión para avanzar en la lista
        return current;
    }

    // Método para imprimir los elementos de la lista
    public void imprimir() {
        imprimirRecursivo(cab);
    }

    private void imprimirRecursivo(Nodo<T> aux) {
        if (aux == null)
            return;
        System.out.print(aux.dato + " ");
        imprimirRecursivo(aux.sig); // Recursión para imprimir el siguiente elemento
    }

    public static void main(String[] args) {
        RecursiveLinkedList<Integer> list = new RecursiveLinkedList<>();
        list.agregar(1);
        list.agregar(2);
        list.agregar(3);
        list.agregar(4);

        System.out.print("Lista enlazada: ");
        list.imprimir(); // Salida: 1 2 3 4
    }
}
