import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class AlgoritmoRutaVuelo {
    static Map<String, String> segmentosVuelo = new HashMap<>();
    public static boolean esPosibleLlegar(Map<String, String> segmentoVuelo, String origen, String destino) {
        Stack<String> pila = new Stack<>();
        Map<String, Boolean> visitado = new HashMap<>();

        pila.push(origen);
        visitado.put(origen, true);

        while (!pila.isEmpty()) {
            String actual = pila.pop();
            String siguiente = segmentoVuelo.get(actual);

            if (siguiente != null) {
                if (siguiente.equals(destino)) {
                    return true; // Llegamos a la ciudad de destino
                }
                if (!visitado.containsKey(siguiente)) {
                    pila.push(siguiente);
                    visitado.put(siguiente, true);
                }
            }
        }
        return false; // No se puede llegar a la ciudad de destino
    }

    public static void main(String[] args) {
        segmentosVuelo.put("Montevideo", "Asuncion");
        segmentosVuelo.put("Asuncion", "Lima");
        segmentosVuelo.put("Lima", "Cartagena");
        segmentosVuelo.put("Cartagena", "San Andres");

        String ciudadOrigen = "Montevideo";
        String ciudadDestino = "San Andres";

        boolean esPosible = esPosibleLlegar(segmentosVuelo, ciudadOrigen, ciudadDestino);

        if (esPosible) {
            System.out.println("Es posible llegar desde " + ciudadOrigen + " a " + ciudadDestino);
        } else {
            System.out.println("No es posible llegar desde " + ciudadOrigen + " a " + ciudadDestino);
        }
    }
}
