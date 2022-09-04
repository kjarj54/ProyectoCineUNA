module cr.ac.una.cineuna {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.base;
    requires javafx.web;
    requires com.jfoenix;
    
    opens cr.ac.una.cineuna to javafx.fxml, com.jfoenix;
    opens cr.ac.una.cineuna.controller to javafx.fxml,javafx.web,com.jfoenix,java.base;
    opens cr.ac.una.cineuna.util to javafx.fxml,com.jfoenix; 
    
    
    

    
    exports cr.ac.una.cineuna;
    exports cr.ac.una.cineuna.controller;
    exports cr.ac.una.cineuna.util;
}
