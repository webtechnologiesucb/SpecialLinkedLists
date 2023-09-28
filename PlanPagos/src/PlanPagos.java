import java.time.LocalDateTime;
import java.util.Scanner;

public class PlanPagos
{
    static class Nodo {
        int nro;
        LocalDateTime fecha;
        double capital;
        double interes;
        Nodo sig;
        Nodo ant;

        public Nodo(int nro, LocalDateTime fecha, double capital, double interes) {
            this.nro = nro;
            this.fecha = fecha;
            this.capital = capital;
            this.interes = interes;
            this.sig = null;
            this.ant = null;
        }
    }

    static class ListaCircularDoble {
        private Nodo primero;
        private Nodo ultimo;

        public ListaCircularDoble() {
            this.primero = null;
            this.ultimo = null;
        }

        public void agregarNodo(int cuota, LocalDateTime fecha, double capital, double interes) {
            Nodo nuevoNodo = new Nodo(cuota, fecha, capital, interes);
            if (primero == null) {
                primero = nuevoNodo;
                ultimo = nuevoNodo;
                nuevoNodo.sig = nuevoNodo;
                nuevoNodo.ant = nuevoNodo;
            } else {
                nuevoNodo.sig = primero;
                nuevoNodo.ant = ultimo;
                primero.ant = nuevoNodo;
                ultimo.sig = nuevoNodo;
                ultimo = nuevoNodo;
            }
        }

        public void mostrarLista() {
            if (primero == null) {
                System.out.println("La lista está vacía.");
                return;
            }

            Nodo actual = primero;
            do {
                System.out.println(actual.nro + "\t" + actual.fecha + "\t" + actual.capital + "\t" + actual.interes);
                actual = actual.sig;
            } while (actual != primero);
        }
    }

    public static void main(String[] args) {
        ListaCircularDoble lista = new ListaCircularDoble();
        double capInicial = 200;
        double intInicial = 800;
        double capital = capInicial;
        double interes = intInicial;
        LocalDateTime fecha = LocalDateTime.now();
        int cuota = 1;
        while(capital <= intInicial && interes >= capInicial)
        {
            lista.agregarNodo(cuota, fecha, capital, interes);
            fecha = fecha.plusMonths(1);
            cuota ++;
            capital += 100;
            interes -= 100;
        }

        System.out.println("Cuota\t Fecha\t\t\t\t\t Capital\t Interes");
        lista.mostrarLista();

        Scanner oLector = new Scanner(System.in);
        System.out.println("PAGO PRESTAMO");
        double montoPres;
        double interesPorc;
        double montoCuota;
        int nCuotas;
        System.out.println("Ingresar Monto Prestamo: ");
        montoPres = oLector.nextDouble();
        System.out.println("Ingresar Porcentaje Interes: ");
        interesPorc = oLector.nextDouble();
        System.out.println("Ingresar total cuotas: ");
        nCuotas = oLector.nextInt();
        String cadInteres = String.format("%.4f", interesPorc/100.0);
        interesPorc = Double.parseDouble(cadInteres.replace(',','.'));
        //double interesPagado = saldo * tasaInteresPeriodica;
        // double capitalPagado = cuotaX - interesPagado;
        // saldo -= capitalPagado; P=893,49   I=8,99   NC=7  CUOTA=177,47
        // =(INTERES *(1+INTERES)^CUOTAS)*PRESTAMO/(((1+INTERES)^CUOTAS)-1)
        montoCuota = (interesPorc *(Math.pow((interesPorc+1),nCuotas))*montoPres)/((Math.pow((interesPorc+1), nCuotas))-1);
        System.out.println("Monto Cuota: " + montoCuota);
    }
}