import java.util.Stack;

public class VerificadorPalindromos {
    public static boolean esPalindromo(String palabra) {
        // Elimina espacios y convierte todo a minúsculas
        palabra = palabra.replaceAll(" ", "").toLowerCase();

        Stack<Character> pila = new Stack<>();
        // Agrega la mitad de los caracteres a la pila
        int mitad = palabra.length() / 2;
        for (int i = 0; i < mitad; i++) {
            pila.push(palabra.charAt(i));
        }

        // Compara los caracteres de la segunda mitad con los de la pila
        int inicioSegundaMitad = mitad + (palabra.length() % 2); // Para omitir el carácter del medio si es impar
        for (int i = inicioSegundaMitad; i < palabra.length(); i++) {
            if (palabra.charAt(i) != pila.pop()) {
                return false; // No es un palíndromo
            }
        }

        return true; // Es un palíndromo
    }

    public static void main(String[] args) {
        String ejemplo1 = "anilina"; // Ejemplo de palíndromo
        String ejemplo2 = "hola mundo"; // No es un palíndromo

        System.out.println(ejemplo1 + " es palíndromo: " + esPalindromo(ejemplo1));
        System.out.println(ejemplo2 + " es palíndromo: " + esPalindromo(ejemplo2));
    }
}