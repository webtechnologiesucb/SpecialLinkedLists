public class CasosMatrices {
    public static void main(String[] args) {
        int n = 4; // tamaño de filas y columnas
        int[][] matriz = generarMatrizCaracol(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Generador de la estructura de la matriz caracol
     * @param n Tamaño de la matriz
     * @return La matriz generada
     */
    public static int[][] generarMatrizCaracol(int n) {
        int[][] matriz = new int[n][n];
        return generarMatrizRecursiva(matriz, 1, 0, 0, n, "derecha");
    }

    /**
     * Genera la matriz caracol considerando los parametros solicitados
     * @param matriz La matriz de ingreso
     * @param valor El valor numerico
     * @param fila Posicion de filas
     * @param columna Posicion de la columna
     * @param n Tamaño de filas y columnas
     * @param ruta ruta de registro de los numeros: arriba, abajo, izquierda y derecha
     * @return La matriz caracol generaada
     */
    private static int[][] generarMatrizRecursiva(int[][] matriz, int valor, int fila, int columna, int n, String ruta) {
        if (valor > n * n)  // Caso base matriz ya llenada
            return matriz;

        // Rellenar la celda actual
        matriz[fila][columna] = valor;

        // Calcular la siguiente celda en función de la dirección
        int sigFila = fila;
        int sigCol = columna;
        switch (ruta) {
            case "derecha" -> sigCol++;
            case "abajo" -> sigFila++;
            case "izquierda" -> sigCol--;
            case "arriba" -> sigFila--;
        }

        // Cambiar la dirección si es necesario
        if (sigFila < 0 || sigFila >= n || sigCol < 0 || sigCol >= n || matriz[sigFila][sigCol] != 0) {
            switch (ruta) {
                case "derecha" -> ruta = "abajo";
                case "abajo" -> ruta = "izquierda";
                case "izquierda" -> ruta = "arriba";
                case "arriba" -> ruta = "derecha";
            }
            sigFila = fila;
            sigCol = columna;
            switch (ruta) {
                case "derecha" -> sigCol++;
                case "abajo" -> sigFila++;
                case "izquierda" -> sigCol--;
                case "arriba" -> sigFila--;
            }
        }

        // Llamada recursiva a la siguiente celda
        return generarMatrizRecursiva(matriz, valor + 1, sigFila, sigCol, n, ruta);
    }
}
