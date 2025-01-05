/**
 * Clase principal para lanzar la aplicación JavaFX.
 * Inicia la ventana principal cargando el archivo FXML correspondiente.
 */
package joel.dein.ejercicio3_jasper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LanzarInforme extends Application {

    /**
     * Metodo encargado de iniciar la aplicación y mostrar la ventana principal.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si no se puede cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LanzarInforme.class.getResource("/fxml/pantalla.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Supermercado!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo principal que arranca la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
