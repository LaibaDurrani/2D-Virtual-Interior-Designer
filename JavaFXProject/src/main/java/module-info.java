module com.rida.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    //Include SQL connector
    requires java.sql;

    opens com.rida.javafxproject to javafx.fxml;
    exports com.rida.javafxproject;
}