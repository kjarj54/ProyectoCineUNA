module cr.ac.una.cineuna {
    requires javafx.controls;
    requires javafx.fxml;

    opens cr.ac.una.cineuna to javafx.fxml;
    exports cr.ac.una.cineuna;
}
