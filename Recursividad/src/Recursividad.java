public class Recursividad {

    /**
     * Funcion recursiva para sumar los elementos de un vector
     * @param lista Lista de total de paginas de libros
     * @return La suma total de las paginas de los libros
     */
    public static int totalPaginas(int lista[]){
        if(lista.length == 1) // Condicion base retorna el primer elemento del array
            return lista[0];
        else{ // usando auxiliar se fracciona el array para almacenar en el stack
            int[] auxiliar = new int[lista.length - 1];
            System.arraycopy(lista, 1, auxiliar, 0, lista.length - 1);
            return lista[0] + totalPaginas(auxiliar);
        }
    }

    /**
     * Constructor del tamaño de la matriz previo al uso de la funcion recursiva
     * @param n Tamaño de las filas y columnas de la matriz
     * @return la matriz generada
     */
    public static int[][] generaTriangularSup(int n) {
        int[][] matriz = new int[n][n];
        return generarMatRec(matriz, 0, 0, n);
    }

    /**
     * Generar la matriz triangular superior que esta por encima de la matriz
     * @param matriz la matriz nxn generada
     * @param fila posicion de las filas
     * @param col posicion de las columnas
     * @param n tamaño de fila/col
     * @return una matriz con su triangular superior igual a 1
     */
    private static int[][] generarMatRec(int[][] matriz, int fila, int col, int n) {
        if (fila == n)  // Caso base: Cuando hemos llegado al final de la matriz
            return matriz;
        if (col >= fila) // Rellenar la fila actual de la matriz triangular superior
            matriz[fila][col] = 1;

        if (col < n - 1) // Pasar a la siguiente col
            return generarMatRec(matriz, fila, col + 1, n);
        else // Pasar a la siguiente fila y reiniciar la col
            return generarMatRec(matriz, fila + 1, 0, n);
    }
    public static void main(String[] args) {
        // vector recursivo
        System.out.println("Vectores");
        int[] listaLibros = new int[]{90, 120, 150};
        int total = totalPaginas(listaLibros);
        System.out.println(total);
        System.out.println("******************************");
        System.out.println("Matrices");
        int num = 4; // num tamaño de la matriz
        int[][] mat = generaTriangularSup(num);

        // Imprimir la matriz generada
        for (int i = 0; i < num; i++)
            for (int j = 0; j < num; j++)
                System.out.print(mat[i][j] + "\t");
            System.out.println();
    }
}
