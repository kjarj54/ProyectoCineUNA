module cr.ac.una.cineuna {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.base;
    requires javafx.web;
    requires com.jfoenix;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires java.sql;
    requires jakarta.json;
    requires javafx.media;
    requires jasperreports;
    requires jakarta.mail;
    
    opens cr.ac.una.cineuna to javafx.fxml, com.jfoenix,javafx.graphics;
    opens cr.ac.una.cineuna.controller to javafx.fxml, javafx.web, com.jfoenix, java.base, jasperreports, jakarta.json, jakarta.xml.bind, jakarta.mail;
    opens cr.ac.una.cineuna.util to java.base,javafx.fxml,com.jfoenix,javafx.media,jakarta.json,jakarta.xml.bind; 
     
    exports cr.ac.una.cineuna;
    exports cr.ac.una.cineuna.controller;
    exports cr.ac.una.cineuna.util;
    exports cr.ac.una.cineuna.model;
}
