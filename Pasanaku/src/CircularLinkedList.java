
import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

import java.math.BigDecimal;
import java.time.*;
import java.util.Iterator;

@SuppressWarnings("ALL")
class Nodo<T> {
    private T dato;
    private Nodo<T> sig;

    public Nodo(T dato) {
        this.dato = dato;
        this.sig = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSig() {
        return sig;
    }

    public void setSig(Nodo<T> sig) {
        this.sig = sig;
    }
}


@SuppressWarnings("ALL")
class CircularList<T> implements Iterable<Estudiante>  {
    Nodo<T> cab;
    Nodo<T> fin;

    /**
     * Carga inicial de la lista circular simple
     */
    public CircularList() {
        this.cab = null;
        this.fin = null;
    }

    /**
     * Insertar valor a la izquierda de la lista circular
     *
     * @param dato Dato numerico/texto/objeto
     */
    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cab == null) {
            cab = nuevo;
            nuevo.setSig(cab);
            fin = nuevo;
        } else {
            Nodo<T> aux;
            fin.setSig(nuevo);
            nuevo.setSig(cab);
            fin = nuevo;
        }
    }

    @Override
    public Iterator<Estudiante> iterator() {
        return new ListaCircularSimpleIterator(cab);
    }
}

@SuppressWarnings("ALL")
class ListaCircularSimpleIterator implements Iterator<Estudiante> {
    private Nodo aux;
    private Nodo inicio;
    private boolean iteracion1;

    public ListaCircularSimpleIterator(Nodo cab) {
        this.aux = cab;
        this.inicio = cab;
        this.iteracion1 = true;
    }

    @Override
    public boolean hasNext() {
        return (aux != null && (iteracion1 || aux != inicio));
    }

    @Override
    public Estudiante next() {
        if (aux == null) {
            return null;
        }
        Estudiante estudiante = (Estudiante) aux.getDato();
        aux = aux.getSig();
        iteracion1 = false;
        return estudiante;
    }
}

@SuppressWarnings("ALL")
public class CircularLinkedList {
    public static void main(String[] args) {
        System.out.println("PASANAKU SISTEMAS");
        BigDecimal cuota = new BigDecimal(300);
        CircularList<Estudiante> listado1 = new CircularList<>();
        LocalDateTime fecha = LocalDateTime.now();
        System.out.println(fecha);
        cargarArchivo(listado1, cuota);
        System.out.println(fecha.plusMonths(3));
        System.out.println("La fecha actual es: " + LocalDate.now());
        System.out.println("La hora actual es: " + LocalTime.now() );
        System.out.println("La fecha y hora actuales son: " + LocalDateTime.now() );
        System.out.println("El instante actual es: " + Instant.now() );
        System.out.println("La fecha y hora actuales con zona horaria son: " + ZonedDateTime.now());

    }

    private static void cargarArchivo(CircularList<Estudiante> listado1, BigDecimal cuota) {
        try{
            String archivo = System.getProperty("user.dir") + "\\ejemplo.csv";
            String[] linea;
            CsvReader estudiantes = new CsvReader(archivo);
            estudiantes.readHeaders();
            while (estudiantes.readRecord())
            {
                int codigo = Integer.parseInt(estudiantes.get("CodigoEstudiante"));
                String nombre = estudiantes.get("NombreEstudiante");
                listado1.insertar(new Estudiante(codigo, nombre, LocalDateTime.now(), cuota));
            }
            estudiantes.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (Estudiante estudiante : listado1)
                System.out.println(estudiante);
        }
    }
}
