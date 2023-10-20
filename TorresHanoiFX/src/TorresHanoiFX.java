import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TorresHanoiFX extends Application {
    private static final int NUM_DISCOS = 3;
    private static final int TORRE_ORIGEN = 0;
    private static final int TORRE_AUXILIAR = 1;
    private static final int TORRE_DESTINO = 2;

    private Pane pane;
    private final Torre[] torres = new Torre[3];
    private Disco discoSeleccionado;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Torres de Hanoi");
        pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);

        iniciarTorres();
        iniciarDiscos();

        primaryStage.show();

        // Coloca los discos en la torre de origen al inicio
        // torres[TORRE_ORIGEN].apilarDiscos(NUM_DISCOS);
    }

    private void iniciarTorres() {
        for (int i = 0; i < 3; i++) {
            torres[i] = new Torre(i);
            pane.getChildren().add(torres[i]);
            torres[i].setOnMouseReleased(this::soltarDisco);
        }
    }

    private void iniciarDiscos() {
        for (int i = 0; i < NUM_DISCOS; i++) {
            Disco disco = new Disco(NUM_DISCOS - i);
            torres[TORRE_ORIGEN].apilarDisco(disco);
            pane.getChildren().add(disco);
            disco.setOnMousePressed(this::seleccionarDisco);
            disco.setOnMouseDragged(this::arrastrarDisco);
        }
    }

    private void seleccionarDisco(MouseEvent event) {
        Disco disco = (Disco) event.getSource();
        torres[disco.getTorre()].desapilarDisco();
        discoSeleccionado = disco;
        discoSeleccionado.setMouseTransparent(true);
    }

    private void arrastrarDisco(MouseEvent event) {
        if (discoSeleccionado != null) {
            double x = event.getSceneX();
            double y = event.getSceneY();
            discoSeleccionado.setLayoutX(x - discoSeleccionado.getWidth() / 2);
            discoSeleccionado.setLayoutY(y - discoSeleccionado.getHeight() / 2);
        }
    }

    private void soltarDisco(MouseEvent event) {
        if (discoSeleccionado != null) {
            Disco disco = (Disco) event.getSource();
            int torreDestino = disco.getTorre();
            if (torres[torreDestino].puedeApilar(discoSeleccionado)) {
                torres[torreDestino].apilarDisco(discoSeleccionado);
                discoSeleccionado.setTorre(torreDestino);
                discoSeleccionado.setMouseTransparent(false);
                comprobarVictoria();
            } else {
                torres[disco.getTorre()].apilarDisco(discoSeleccionado);
                discoSeleccionado.setMouseTransparent(false);
            }
            discoSeleccionado = null;
        }
    }

    private void comprobarVictoria() {
        if (torres[TORRE_DESTINO].getNumDiscos() == NUM_DISCOS) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Has ganado!");
            alert.setHeaderText(null);
            alert.setContentText("Has completado las Torres de Hanoi.");
            alert.showAndWait();
        }
    }

    public static class Torre extends Pane {
        private final int numTorre;

        public Torre(int numTorre) {
            this.numTorre = numTorre;
            setPrefSize(120, 200);
            setStyle("-fx-border-color: black;");
        }

        public boolean puedeApilar(Disco disco) {
            if (getChildren().isEmpty()) {
                return true;
            } else {
                Disco discoSuperior = (Disco) getChildren().get(0);
                return discoSuperior.getSize() > disco.getSize();
            }
        }

        public void apilarDisco(Disco disco) {
            getChildren().add(0, disco);
            disco.setTorre(numTorre);
            double offsetY = (getChildren().size() - 1) * disco.getHeight();
            disco.setLayoutX(getPrefWidth() / 2 - disco.getWidth() / 2);
            disco.setLayoutY(getPrefHeight() - offsetY);
        }

        public void desapilarDisco() {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
            }
        }

        public int getNumDiscos() {
            return getChildren().size();
        }
    }

    public static class Disco extends Rectangle {
        private final int size;
        private int torre;

        public Disco(int size) {
            this.size = size;
            this.torre = -1;
            setWidth(40 + size * 20);
            setHeight(20);
            setFill(Color.BLUE);
            setArcWidth(10);
            setArcHeight(10);
        }

        public int getSize() {
            return size;
        }

        public int getTorre() {
            return torre;
        }

        public void setTorre(int torre) {
            this.torre = torre;
        }
    }
}
