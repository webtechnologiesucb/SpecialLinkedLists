public class RecursiveDoubleLinkedList<T> {
    private Nodo<T> cab;
    private Nodo<T> fin;

    public RecursiveDoubleLinkedList() {
        cab = null;
        fin = null;
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


    public void agregar(T dato) {
        cab = agregarRecursivo(cab, fin, dato);
    }

    private Nodo<T> agregarRecursivo(Nodo<T> aux, Nodo<T> prev, T dato) {
        if (aux == null) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.ant = prev;
            fin = nuevo;
            if (prev != null)
                prev.sig = nuevo;

            return nuevo;
        }
        aux.sig = agregarRecursivo(aux.sig, aux, dato);
        return aux;
    }

    // Método para imprimir los elementos de la lista hacia adelante
    public void imprimirDerecha() {
        imprimirDerechaRecursivo(cab);
    }

    private void imprimirDerechaRecursivo(Nodo<T> aux) {
        if (aux == null)
            return;

        System.out.print(aux.dato + " ");
        imprimirDerechaRecursivo(aux.sig);
    }

    // Método para imprimir los elementos de la lista hacia atrás
    public void imprimirIzquierda() {
        imprimirIzquierdaRec(fin);
    }

    private void imprimirIzquierdaRec(Nodo<T> aux) {
        if (aux == null)
            return;

        System.out.print(aux.dato + " ");
        imprimirIzquierdaRec(aux.ant);
    }

    public static void main(String[] args) {
        RecursiveDoubleLinkedList<Integer> list = new RecursiveDoubleLinkedList<>();
        list.agregar(1);
        list.agregar(2);
        list.agregar(3);
        list.agregar(4);

        System.out.print("Lista enlazada hacia derecha: ");
        list.imprimirDerecha(); // Salida: 1 2 3 4
        System.out.println();

        System.out.print("Lista enlazada hacia izquierda: ");
        list.imprimirIzquierda(); // Salida: 4 3 2 1
    }
}
