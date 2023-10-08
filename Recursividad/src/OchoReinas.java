public class OchoReinas {
    public static void main(String[] args) {
        int n = 8; // Tamaño del tablero (en este caso, 8x8)
        resolverNReinas(n);
    }

    /**
     * Cargar las 8 reinas en el algoritmo recursivo
     * @param n Total de reinas
     */
    public static void resolverNReinas(int n) {
        int[] reinas = new int[n];
        posicionarReinas(reinas, 0);
    }

    /**
     * Posicionar las 8 reinas en el tablero de ajedrez
     * @param reinas Vector de las reinas
     * @param fila Numero de fila
     */
    public static void posicionarReinas(int[] reinas, int fila) {
        int n = reinas.length;
        if (fila == n) {
            imprimirReinas(reinas);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (esSeguro(reinas, fila, col)) {
                reinas[fila] = col;
                posicionarReinas(reinas, fila + 1);
            }
        }
    }

    /**
     * Verificar si las reinas no pueden capturarse en la casilla actual
     * @param reinas Vector de reinas
     * @param fila Posicion de fila
     * @param col Posicion de columna
     * @return Valida si esta disponible la casilla
     */
    public static boolean esSeguro(int[] reinas, int fila, int col) {
        for (int filaPrev = 0; filaPrev < fila; filaPrev++) {
            int colPrev = reinas[filaPrev];
            if (colPrev == col || Math.abs(colPrev - col) == Math.abs(filaPrev - fila)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Imprimir las soluciones al problema de las 8 reinas
     * @param reinas Vector de las reinas
     */
    public static void imprimirReinas(int[] reinas) {
        int n = reinas.length;
        for (int reina : reinas) {
            for (int col = 0; col < n; col++) {
                if (reina == col) {
                    System.out.print("Q\t");
                } else {
                    System.out.print("X\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
