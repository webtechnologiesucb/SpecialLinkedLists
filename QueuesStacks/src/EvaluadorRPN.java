import java.util.Stack;

public class EvaluadorRPN {
    public static int evaluarRPN(String expresion) {
        Stack<Integer> pila = new Stack<>();
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {  // Si el token es un número
                pila.push(Integer.parseInt(token));
            } else {  // Si el token es un operador
                int b = pila.pop();
                int a = pila.pop();

                switch (token) {
                    case "+" -> pila.push(a + b);
                    case "-" -> pila.push(a - b);
                    case "*" -> pila.push(a * b);
                    case "/" -> pila.push(a / b);
                }
            }
        }

        return pila.pop();
    }

    public static void main(String[] args) {
        String expresionRPN = "1 2 3 * +";
        System.out.println("Expresion inicial: " + expresionRPN);
        int resultado = evaluarRPN(expresionRPN);
        System.out.println("Resultado de la expresión RPN: " + resultado);
    }
}
