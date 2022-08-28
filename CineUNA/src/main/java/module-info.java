module cr.ac.una.cineuna {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.base;
    requires javafx.web;
    requires com.jfoenix;
    

    opens cr.ac.una.cineuna to javafx.fxml;
    exports cr.ac.una.cineuna;
}
