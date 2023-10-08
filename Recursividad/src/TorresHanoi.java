public class TorresHanoi {
    public static void main(String[] args) {
        int numDiscos = 3; // Cambia el número de discos según desees

        System.out.println("Resolver las Torres de Hanoi con " + numDiscos + " discos:");
        resolverTorresDeHanoi(numDiscos, 'A', 'C', 'B');
    }

    /**
     * Generar las torres de Hanoi y resolver el traslado
     * @param n Cantidad de discos de la la torre A
     * @param origen Poste de origen
     * @param destino Poste de destino
     * @param auxiliar Poste intermedio
     */
    public static void resolverTorresDeHanoi(int n, char origen, char destino, char auxiliar) {
        if (n == 1) {
            System.out.println("Mover disco 1 de poste " + origen + " a poste " + destino);
            return;
        }

        // Mover n-1 discos de origen a auxiliar usando destino como auxiliar
        resolverTorresDeHanoi(n - 1, origen, auxiliar, destino);
        System.out.println("Mover disco " + n + " de poste " + origen + " a poste " + destino);
        // Mover n-1 discos de auxiliar a destino usando origen como auxiliar
        resolverTorresDeHanoi(n - 1, auxiliar, destino, origen);
    }
}


