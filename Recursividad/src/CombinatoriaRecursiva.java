public class CombinatoriaRecursiva {
    public static void main(String[] args) {
        int n = 5; // Número total de elementos
        int k = 3; // Número de elementos a elegir

        int resultado = combinatoria(n, k);
        System.out.println("C(" + n + ", " + k + ") = " + resultado);
    }

    /**
     * Combinatoria de n sobre k: C(n,k) = n! / k! (n-k)!
     * @param n Numero de elementos
     * @param k Un conjunto seleccionado de elementos
     * @return La combinatoria generada de forma recursiva
     */
    public static int combinatoria(int n, int k) {
        if (k == 0 || k == n) {
            return 1; // Caso base: C(n, 0) = C(n, n) = 1
        } else {
            // Usar la fórmula recursiva para calcular la combinatoria
            return combinatoria(n - 1, k - 1) + combinatoria(n - 1, k);
        }
    }
}
