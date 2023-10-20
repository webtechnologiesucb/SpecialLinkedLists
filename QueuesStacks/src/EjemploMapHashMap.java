import java.util.HashMap;
import java.util.Map;

public class EjemploMapHashMap {
    // Crear un objeto HashMap
    static Map<String, Integer> edades = new HashMap<>();

    public static void main(String[] args) {
        cargarMapa();
        String nombre = "Juan";
        obtenerElemento(nombre);
        retornarClave(nombre);
        mostrarListaMapa();
        eliminarElemento(nombre);
        retornarClave(nombre);
        verificarEstado();
    }

    private static void verificarEstado() {
        // Verificar si el mapa está vacío
        boolean estaVacio = edades.isEmpty();
        System.out.println("¿El mapa está vacío? " + estaVacio);
    }

    private static void eliminarElemento(String nombre) {
        // Eliminar un elemento del mapa
        edades.remove(nombre);
        System.out.println(nombre + " eliminado de la lista del mapa");
    }

    private static void mostrarListaMapa() {
        // Iterar a través de las claves y valores del mapa
        for (Map.Entry<String, Integer> entry : edades.entrySet()) {
            String nombre1 = entry.getKey();
            int edad = entry.getValue();
            System.out.println(nombre1 + " tiene " + edad + " años.");
        }
    }

    private static void retornarClave(String nombre) {
        // Verificar si una clave existe en el mapa
        boolean existeClave = edades.containsKey(nombre);
        System.out.println("¿"+ nombre +" está en el mapa? " + existeClave);
    }

    private static void obtenerElemento(String nombre) {
        // Acceder a elementos del mapa
        int edad = edades.get(nombre);
        System.out.println("Edad de "+ nombre + ": " + edad);
    }

    private static void cargarMapa() {
        // Agregar elementos al mapa (clave-valor)
        edades.put("Juan", 25);
        edades.put("María", 30);
        edades.put("Pedro", 28);
        edades.put("Luisa", 35);
    }
}
