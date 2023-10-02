public class CircularDoubleLinkedList<T> {
    private Nodo<T> cab;

    public CircularDoubleLinkedList() {
        cab = null;
    }
    
    private static class Nodo<T> {
        T dato;
        Nodo<T> sig;
        Nodo<T> ant;

        Nodo(T dato) {
            this.dato = dato;
            this.sig = null;
            this.ant = null;
        }
    }

    /**
     * Método para agregar un elemento al final de la lista
     * @param dato Dato a ingresar
     */
    public void agregar(T dato) {
        cab = agregarRecursivo(cab, dato);
    }

    /**
     * Metodo para agregar de forma recursiva nodos
     * @param aux nodo auxiliar para ingresar
     * @param dato dato a ingresar
     * @return el nodo agregado a la lista
     */
    private Nodo<T> agregarRecursivo(Nodo<T> aux, T dato) {
        if (aux == null) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.ant = nuevo; // El nuevo nodo apunta a sí mismo tanto en prev como en next
            nuevo.sig = nuevo;
            return nuevo;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.sig = aux.sig;
        nuevo.ant = aux;
        aux.sig.ant = nuevo;
        aux.sig = nuevo;
        return aux;
    }

    // Método para imprimir los elementos de la lista hacia adelante
    public void imprimirDerecha() {
        if (cab == null) {
            return;
        }
        Nodo<T> aux = cab;
        do {
            System.out.print(aux.dato + " ");
            aux = aux.sig;
        } while (aux != cab);
    }

    // Método para imprimir los elementos de la lista hacia atrás
    public void imprimirIzquierda() {
        if (cab == null) {
            return;
        }
        Nodo<T> aux = cab.ant; // Empezar desde el último nodo
        do {
            System.out.print(aux.dato + " ");
            aux = aux.ant;
        } while (aux != cab.ant);
    }

    public static void main(String[] args) {
        CircularDoubleLinkedList<Integer> lista = new CircularDoubleLinkedList<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);

        System.out.print("Lista circular doble hacia adelante: ");
        lista.imprimirDerecha(); // Salida: 1 2 3 4
        System.out.println();

        System.out.print("Lista circular doble hacia atrás: ");
        lista.imprimirIzquierda(); // Salida: 4 3 2 1
    }
}
