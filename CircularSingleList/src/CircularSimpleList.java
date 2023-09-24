@SuppressWarnings("ALL")
public class CircularSimpleList {
    /**
     * Clase Nodo genérica
     * @param <E> Valor generico E
     */
    static class Nodo<E>{
        E dato;
        Nodo<E> sig;

        /**
         * Crea el nodo suelto de la lista circular simple
         * @param dato Dato numerico/texto/objeto
         */
        public Nodo(E dato) {
            this.dato = dato;
            this.sig = null;
        }
    }

    /**
     * Lista Circular Simple
     * @param <E>
     */
    static class CircularLinkedList<E>{
        Nodo<E> cab;

        /**
         * Carga inicial de la lista circular simple
         */
        public CircularLinkedList() {
            this.cab = null;
        }

        /**
         * Insertar valor a la izquierda de la lista circular
         * @param dato Dato numerico/texto/objeto
         */
        public void insertarIzquierda(E dato)
        {
            Nodo<E> nuevo = new Nodo<>(dato);
            if(cab==null){
                cab = nuevo;
                nuevo.sig = cab;
            }
            else{
                Nodo<E> aux;
                for(aux=cab;aux.sig!=cab;aux=aux.sig);
                aux.sig = nuevo;
                nuevo.sig = cab;
                cab = nuevo;
            }
        }

        /**
         * Insertar valor a la derecha de la lista circular
         * @param dato Dato numerico/texto/objeto
         */
        public void insertarDerecha(E dato)
        {
            Nodo<E> nuevo = new Nodo<>(dato);
            if(cab==null){
                cab = nuevo;
                nuevo.sig = cab;
            }
            else{
                Nodo<E> aux;
                for(aux=cab;aux.sig!=cab;aux=aux.sig);
                nuevo.sig = aux.sig;
                aux.sig = nuevo;
            }
        }

        /**
         * Insertar valor a la derecha del indice señalado
         * @param indice Un numero entre 0 y el tamaño - 1 de la lista circular simple
         * @param dato Dato numerico/texto/objeto
         */
        public void insertarIndice(int indice, E dato) {
            Nodo<E> nuevo = new Nodo(dato);
            if (indice == 0) {
                nuevo.sig = cab;
                cab = nuevo;
            } else {
                Nodo<E> aux;
                int i= 0;
                for(aux = cab; aux.sig!=cab & i < indice - 1;aux=aux.sig){
                    i++;
                }
                nuevo.sig = aux.sig;
                aux.sig = nuevo;
            }
        }

        /**
         * Eliminar el indice señalado quitando el nodo contado
         * @param indice Un numero entre 0 y el tamaño - 1 de la lista circular simple
         */
        public void borrarIndice(int indice) {
            Nodo<E> aux;
            if (indice == 0) {
                if (this.size()>1) {
                    for (aux = cab; aux.sig != cab; aux = aux.sig) ;
                    aux.sig = cab.sig;
                    cab = cab.sig;
                } else {
                    cab.sig = null;
                    cab = null;
                }
            } else {
                int i = 0;
                for(aux = cab; aux.sig!=cab & i < indice - 1;aux=aux.sig){
                    i++;
                }
                aux.sig = aux.sig.sig;
            }
        }

        /**
         * Devuelve el tamaño de la lista
         * @return Un valor entero del tamaño total de la lista
         */
        public int size() {
            int contador = 1;
            Nodo<E> aux = cab;
            for (aux = cab; aux.sig != cab; aux = aux.sig)
                contador++;
            return contador;
        }

        /**
         * Obtener el valor de un elemento de la lista circular simple
         * @param indice numero de indice elegido
         * @return el valor del índice seleccionado
         */
        public E get(int indice) {
            Nodo<E> aux;
            int i= 0;
            for(aux = cab; aux.sig!=cab & i < indice; aux=aux.sig)
                i++;
            return (E) aux.dato;
        }

        /**
         * Recorre la lista circular simple controlando la cabecera
         */
        public void recorridoLista(){
            Nodo<E> aux;
            aux = cab;
            do{
                System.out.print(aux.dato + "->");
                aux = aux.sig;
            }while(aux!=cab);
            System.out.println("");
        }

        /**
         * Verificamos si la lista circular esta vacia
         * @return Un valor booleano true si la lista esta vacia
         */
        public boolean isEmpty(){
            return cab == null;
        }

        /**
         * El problema de Josefo es el siguiente: n personas están en un círculo,
         * y son despedidas en orden contando cada k personas; el que queda solo
         * al final es el sobreviviente. 12 = 2^3 + 4 (usa potencias de 2 para pronosticar el resultado)
         * Por ejemplo, con m=12 y k=3, el sobreviviente es la persona 10:
         * @param lista Lista circular con los nombres de las personas
         * @param k el valor de conteo para efectuar la reduccion de la lista
         */
        public static void resolverJosefo(CircularLinkedList lista, int k) {
            if (lista.isEmpty()) {
                throw new IllegalArgumentException("La lista no puede estar vacía");
            }

            int indice = 0;
            while (lista.size() > 1) {
                indice = (indice + k - 1) % lista.size(); // aplica el modulo con los elementos pendientes
                System.out.println("Despedido: " + lista.get(indice));
                lista.borrarIndice(indice);
            }
            System.out.println("El contratado es: " + lista.get(0));
        }
    }

    public static void main(String[] args) {
        // casos simples
        CircularLinkedList<Integer> listado = new CircularLinkedList<>();
        listado.insertarIzquierda(3);
        listado.insertarIzquierda(2);
        listado.insertarIzquierda(1);
        listado.insertarIndice(2,4);
        listado.recorridoLista();
        System.out.println(listado.size());
        listado.borrarIndice(1);
        listado.recorridoLista();
        System.out.println(listado.get(2));
        //problema de josefo
        CircularLinkedList<String> listado1 = new CircularLinkedList<>();
        listado1.insertarDerecha("Jeremías"); //**
        listado1.insertarDerecha("Zacarías"); //***
        listado1.insertarDerecha("Ezequiel"); //*
        listado1.insertarDerecha("Jebedías"); //**
        listado1.insertarDerecha("Jonás"); // ****
        listado1.insertarDerecha("Nahum"); //*
        listado1.insertarDerecha("Miqueas"); //***
        listado1.insertarDerecha("Daniel"); //**
        listado1.insertarDerecha("David"); //*
        listado1.insertarDerecha("Josefo"); // se salva
        listado1.insertarDerecha("Sirac"); //***
        listado1.insertarDerecha("Baruc"); //*
        listado1.resolverJosefo(listado1, 3);
    }
}
