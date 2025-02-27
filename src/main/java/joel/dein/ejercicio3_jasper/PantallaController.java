/**
 * Controlador de la pantalla principal que gestiona las acciones de los botones para generar informes
 * con JasperReports.
 */
package joel.dein.ejercicio3_jasper;

import joel.dein.ejercicio3_jasper.BBDD.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PantallaController {

    @FXML
    private Button btAgruparPorSeccion;

    @FXML
    private Button btGraficoProductos;

    @FXML
    private Button btListarEnTabla;

    @FXML
    private Button btListarProductos;

    /**
     * Acción asociada al botón que genera un informe agrupado por sección.
     *
     * @param event Evento de acción del botón.
     */
    @FXML
    void agrupadosPorSeccion(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/img/").toString());
        generarReporte("/JasperReport/agrupadosPorSeccion.jasper", parameters);
    }

    /**
     * Acción asociada al botón que genera un gráfico de productos.
     *
     * @param event Evento de acción del botón.
     */
    @FXML
    void graficoProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/img/").toString());
        generarReporte("/JasperReport/GraficoUnidades.jasper", parameters);
    }

    /**
     * Acción asociada al botón que genera una tabla de productos.
     *
     * @param event Evento de acción del botón.
     */
    @FXML
    void listarEnTabla(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/img/").toString());
        generarReporte("/JasperReport/tablaDeProductos.jasper", parameters);
    }

    /**
     * Acción asociada al botón que lista los productos.
     *
     * @param event Evento de acción del botón.
     */
    @FXML
    void listarProductos(ActionEvent event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_PATH", getClass().getResource("/img/").toString());
        generarReporte("/JasperReport/listarProductos.jasper", parameters);
    }

    /**
     * Genera un reporte utilizando JasperReports.
     *
     * @param reportePath Ruta del archivo JasperReport.
     * @param parameters Parámetros necesarios para la generación del informe.
     */
    private void generarReporte(String reportePath, Map<String, Object> parameters) {
        try {
            ConexionBBDD db = new ConexionBBDD();
            InputStream reportStream = getClass().getResourceAsStream(reportePath);

            if (reportStream == null) {
                System.out.println("El archivo no está ahí: " + reportePath);
                return;
            }

            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            mostrarError("Error en la base de datos", "No se pudo conectar a la base de datos o generar el informe.");
        }
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje de error.
     *
     * @param titulo Título del cuadro de diálogo.
     * @param mensaje Mensaje a mostrar en el cuadro de diálogo.
     */
    private void mostrarError(String titulo, String mensaje) {
        // Crear una ventana emergente de tipo "error"
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // No queremos un encabezado
        alert.setContentText(mensaje); // El mensaje que queremos mostrar
        alert.showAndWait(); // Mostrar el mensaje y esperar a que el usuario lo cierre
    }

}
