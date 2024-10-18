module tr.cabroo.esnafapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens tr.cabroo.esnafapp to javafx.fxml;
    exports tr.cabroo.esnafapp;
}