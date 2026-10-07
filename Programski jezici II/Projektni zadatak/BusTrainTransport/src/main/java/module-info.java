module jelena.etfbl.bustraintransport {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    opens jelena.etfbl.bustraintransport to javafx.fxml;
    opens jelena.etfbl.scenes to javafx.fxml;
    opens jelena.etfbl.simulation to javafx.fxml;
    opens jelena.etfbl.generate to com.fasterxml.jackson.databind;

    exports jelena.etfbl.bustraintransport;
    exports jelena.etfbl.simulation;
    exports jelena.etfbl.generate;
    exports jelena.etfbl.scenes;
    exports jelena.etfbl.ticket;

}