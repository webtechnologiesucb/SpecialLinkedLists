public class QueueSingleList {
    static class Nodo<E>{
        private E dato;
        private Nodo<E> sig;

        public Nodo(E dato) {
            this.dato = dato;
            this.sig = null;
        }
    }

    static class Queue<E>{
        private Nodo<E> cab;
        private Nodo<E> fin;
        public Queue(){
            cab = null;
            fin = null;
        }

        public boolean vacia(){
            return cab == null;
        }

        public Nodo<E> get(int indice){
            Nodo<E> aux = cab;
            if(indice > -1 && indice < this.size()) {
                int valor = 0;
                while (aux != null) {
                    if (valor == indice)
                        return aux; // Nodo encontrado
                    aux = aux.sig;
                    valor++;
                }
            }
            return null;
        }

        public void agregar(E dato){
            Nodo<E> nuevo = new Nodo<>(dato);
            if(cab == null) {
                cab = nuevo;
                fin = nuevo;
            }
            else{
                fin.sig = nuevo;
                fin = nuevo;
            }
        }

        public void quitar(){
            if (vacia())
                throw new IllegalArgumentException("La Fila está vacía");
            Nodo<E> aux = cab;
            cab = cab.sig;
            System.out.println("Atendido: " + aux.dato);
            aux = null;
        }

        public Nodo<E> getPrimero() {
            Nodo<E> aux = this.get(0);
            return aux;
        }

        public Nodo<E> getUltimo() {
            Nodo<E> aux = this.get(this.size()-1);
            return aux;
        }

        public int size() {
            Nodo<E> aux;
            int contador = 0;
            for (aux=cab; aux!=null; aux=aux.sig)
                contador++;
            return contador;
        }

        public void mostrar() {
            Nodo<E> aux;
            for (aux=cab; aux!=null; aux=aux.sig)
                if (aux.sig != null)
                    System.out.print(aux.dato + " - ");
                else
                    System.out.print(aux.dato + "\n");
        }
    }

    public static void main(String[] args) {
        Queue<Integer> one = new Queue() ;
        one.agregar(3);
        one.agregar(6);
        one.agregar(9);
        one.agregar(12);
        one.mostrar();
        one.quitar();
        one.mostrar();
        System.out.println("Tamaño Fila: " + one.size());
        int indice = 1;
        String mensaje = String.format("Elemento: %d  Valor: %d", indice, one.get(indice).dato);
        System.out.println(mensaje);
        mensaje = String.format("Inicio Fila: %d", one.getPrimero().dato);
        System.out.println(mensaje);
        mensaje = String.format("Ultimo Fila: %d", one.getUltimo().dato);
        System.out.println(mensaje);
    }
}

