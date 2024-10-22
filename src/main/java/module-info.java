module tr.cabroo.esnafapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.bootstrapfx.core;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;


    opens tr.cabroo.esnafapp to javafx.fxml;
    opens tr.cabroo.esnafapp.customer to javafx.fxml;
    exports tr.cabroo.esnafapp;
}