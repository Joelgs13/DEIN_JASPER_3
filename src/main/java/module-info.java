module joel.dein.ejercicio3_jasper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;


    opens joel.dein.ejercicio3_jasper to javafx.fxml;
    exports joel.dein.ejercicio3_jasper;
}