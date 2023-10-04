import java.util.Arrays;

public class Ejercicios1 {
    public static int sumaResta(int lista[]) {
        if (lista.length == 1) // Condicion base retorna el primer elemento del array
            return lista[0];
        else { // usando auxiliar se fracciona el array para almacenar en el stack
            int[] auxiliar = new int[lista.length - 1];
            System.arraycopy(lista, 1, auxiliar, 0, lista.length - 1);
            if (lista.length % 2 == 0)
                return sumaResta(auxiliar) - lista[0];
            else
                return sumaResta(auxiliar) + lista[0];
        }
    }

    public static int[] generarVector(int n, int num, int[] pares, int indice) {
        if (indice == n) {
            return pares;
        } else {
            if (num % 2 == 0) {
                pares[indice] = num;
                indice++;
            }
            return generarVector(n, num + 1, pares, indice);
        }
    }

    public static void main(String[] args) {
        // vector recursivo
        System.out.println("Suma y Resta de Vectores Recursivos");
        int[] listaLibros = new int[]{90, 120, 50};
        Arrays.stream(listaLibros).mapToObj(listaLibro -> listaLibro + "\t").forEach(System.out::print);
        System.out.println("");
        int total = sumaResta(listaLibros);
        System.out.println(total);

        System.out.println("Vectores Generados");
        int n = 5;
        int[] vectorPares = new int[n];
        vectorPares = generarVector(n, 2, vectorPares, 0);
        // Imprimir el array de números pares generados
        for (int i = 0; i < n; i++)
            System.out.print(vectorPares[i] + "\t");
    }
}
