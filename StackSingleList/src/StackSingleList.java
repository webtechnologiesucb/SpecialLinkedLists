public class StackSingleList {
    static class Nodo<E>{
        private E dato;
        private Nodo<E> sig;

        public Nodo(E dato) {
            this.dato = dato;
            this.sig = null;
        }
    }

    static class Stack<E>{
        private Nodo<E> top;
        public Stack(){
            top = null;
        }

        public boolean vacia(){
            return top == null;
        }

        public Nodo<E> get(int indice){
            Nodo<E> aux = top;
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

        public void push(E dato){
            Nodo<E> nuevo = new Nodo<>(dato);
            if(top == null) {
                top = nuevo;
                top.sig = null;
            }else{
                nuevo.sig = top;
                top = nuevo;
            }
        }

        public void pop(){
            if (vacia())
                throw new IllegalArgumentException("La Pila está vacía");
            Nodo<E> aux = top;
            top = top.sig;
            System.out.println("Elemento extraido: " + aux.dato);
            aux = null;
        }

        public Nodo<E> getTop() {
            top = this.get(this.size() -1);
            return top;
        }

        public int size() {
            Nodo<E> aux;
            int contador = 0;
            for (aux=top; aux!=null; aux=aux.sig)
                contador++;
            return contador;
        }

        public void mostrar() {
            Nodo<E> aux;
            for (aux=top; aux!=null; aux=aux.sig)
                System.out.println(aux.dato);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> one = new Stack() ;
        one.push(3);
        one.push(6);
        one.push(9);
        one.push(12);
        one.mostrar();
        one.pop();
        one.mostrar();
        System.out.println("Tamaño Pila: " + one.size());
        int indice = 1;
        String mensaje = String.format("Elemento: %d  Valor: %d", indice, one.get(indice).dato);
        System.out.println(mensaje);
        mensaje = String.format("Inicio de la Pila: %d", one.getTop().dato);
        System.out.println(mensaje);
    }
}
