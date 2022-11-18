package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.App;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.model.ProComidasDto;
import cr.ac.una.cineuna.model.ProFacturasDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.service.ProComidasService;
import cr.ac.una.cineuna.service.ProFacturasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.URL;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class ComidasClienteViewController extends Controller implements Initializable {

    @FXML
    private Label txtComidas;
    @FXML
    private TableView<ProComidasDto> tbvComidas;
    @FXML
    private TableColumn<ProComidasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProComidasDto, String> tbcDescripcion;
    @FXML
    private TableColumn<ProComidasDto, String> tbcPrecio;
    @FXML
    private TableColumn<ProComidasDto, Boolean> tbcAgregar;
    @FXML
    private TableView<ProComidasDto> tbvFactura;
    @FXML
    private TableColumn<ProComidasDto, String> tbcNombreFactura;
    @FXML
    private TableColumn<ProComidasDto, String> tbcPrecioFactura;
    @FXML
    private TableColumn<ProComidasDto, Boolean> tbcQuitar;
    @FXML
    private Label txtTotal;
    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXButton btnSalir;

    ProFacturasDto proFacturasDto;

    ProComidasDto proComidasDto;

    ProClientesDto proClienteDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tbcAgregar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto, Boolean> p) -> new SimpleObjectProperty(p.getValue() != null));

        tbcAgregar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCellComida());

        tbcQuitar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto, Boolean> p) -> new SimpleObjectProperty(p.getValue() != null));

        tbcQuitar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCellFactura());

        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcDescripcion.setCellValueFactory(clbck -> clbck.getValue().comDescripcion);

        tbcPrecio.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        tbcNombreFactura.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcPrecioFactura.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        actualizarTbv();

        tbvComidas.refresh();

        proFacturasDto = new ProFacturasDto();

        proClienteDto = new ProClientesDto();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void OnActionBtnPagar(ActionEvent event) throws JRException, FileNotFoundException {
        
        try {
            ProFacturasService service = new ProFacturasService();
            ProClientesService serviceCli = new ProClientesService();

            Long cli = (Long) AppContext.getInstance().get("UsuarioId");

            Respuesta respuesta2 = serviceCli.getCliente(cli);
            proClienteDto = (ProClientesDto) respuesta2.getResultado("ProCliente");

            String totalString = txtTotal.getText();

            totalString = totalString.replaceAll("\\,00", "");

            Long total = Long.parseLong(totalString);

            proFacturasDto.setFacTotal(total);
            proFacturasDto.setCliId(proClienteDto);

            Respuesta respuesta = service.guardarFactura(proFacturasDto);

            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Generar la factura", getStage(), respuesta.getMensaje());
               
        
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Generar la factura", getStage(), "Factura generada correctamente.");
                tbvFactura.getItems().clear();
<<<<<<< Updated upstream
                try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("/cr/ac/una/cineuna/resources/factura.jrxml");
            JasperDesign jd = JRXmlLoader.load(is);
            
            String sql = "SELECT PRO_FACTURAS.FAC_FECHA, \n"
=======
                
                try {
                    InputStream instream = new FileInputStream(new File(App.class.getResource("/cr/ac/una/cineuna/resources/factura.jrxml").toString()));
                JasperDesign design = JRXmlLoader.load(instream);
                String sql = "SELECT PRO_FACTURAS.FAC_FECHA, \n"
>>>>>>> Stashed changes
                    + "PRO_FACTURAS.FAC_TOTAL, \n"
                    + "PRO_CLIENTES.CLI_CORREO, \n"
                    + "PRO_CLIENTES.CLI_PAPELLIDO, \n"
                    + "PRO_CLIENTES.CLI_NOMBRE \n"
                    + "FROM PRO_CLIENTES, \n"
                    + "PRO_FACTURAS \n"
                    + "WHERE PRO_CLIENTES.CLI_ADMIN = 'N' ";
<<<<<<< Updated upstream
            
            JRDesignQuery query = new JRDesignQuery();
            query.setText(sql);
            
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap<String, Object> hm = new HashMap<String, Object>();
            
            JasperPrint jp = JasperFillManager.fillReport(jr, hm);
            JasperViewer.viewReport(jp);
            OutputStream os = new FileOutputStream(new File("C:\\factura.pdf"));
            JasperExportManager.exportReportToPdfFile(os.toString());
        }catch(Exception e){}
//                try {
//                    String fileNameJrxml = "C:\\Users\\BiblioPZ UNA\\Desktop\\progra\\gitCineUNA\\CineUNA\\src\\main\\resources\\cr\\ac\\una\\cineuna\\resources\\factura.jrxml";
//                    String fileNamePdf = "C:\\Users\\BiblioPZ UNA\\Desktop\\progra\\gitCineUNA\\CineUNA\\src\\main\\resources\\cr\\ac\\una\\cineuna\\resources\\factura.pdf";
//                    JasperDesign jd = JRXmlLoader.load(fileNameJrxml);
//                    JasperReport jr = JasperCompileManager.compileReport(jd);
//                    HashMap<String, Object> hm = new HashMap<String, Object>();
//                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, hm, new JREmptyDataSource());
//                    JasperExportManager.exportReportToPdfFile(jp, fileNamePdf);
//                    
////                    InputStream instream = new FileInputStream(new File(App.class.getResource("/cr/ac/una/cineuna/resources/factura.jrxml").toString()));
////                JasperDesign design = JRXmlLoader.load(instream);
//                String sql = "SELECT PRO_FACTURAS.FAC_FECHA, \n"
//                    + "PRO_FACTURAS.FAC_TOTAL, \n"
//                    + "PRO_CLIENTES.CLI_CORREO, \n"
//                    + "PRO_CLIENTES.CLI_PAPELLIDO, \n"
//                    + "PRO_CLIENTES.CLI_NOMBRE \n"
//                    + "FROM PRO_CLIENTES, \n"
//                    + "PRO_FACTURAS \n"
//                    + "WHERE PRO_CLIENTES.CLI_ADMIN = 'N' ";
////                JRDesignQuery nQ = new JRDesignQuery();
////                nQ.setText(sql);
////                design.setQuery(nQ);
////                JasperReport report = JasperCompileManager.compileReport(design);
////                HashMap hash = new HashMap();
////                JasperPrint print = JasperFillManager.fillReport(report, hash);
////                JasperViewer.viewReport(in, true);
////                JasperViewer.viewReport(instream, false);
////                OutputStream os = new FileOutputStream(new File("factura.pdf"));
////                JasperExportManager.exportReportToPdfStream(print, os);
//                }catch(Exception e){
//                }
=======
                JRDesignQuery nQ = new JRDesignQuery();
                nQ.setText(sql);
                design.setQuery(nQ);
                JasperReport report = JasperCompileManager.compileReport(design);
                HashMap hash = new HashMap();
                JasperPrint print = JasperFillManager.fillReport(report, hash);
                JasperViewer.viewReport(in, true);
                JasperViewer.viewReport(instream, false);
                OutputStream os = new FileOutputStream(new File("factura.pdf"));
                JasperExportManager.exportReportToPdfStream(print, os);
                }catch(Exception e){
                }
>>>>>>> Stashed changes
                
            }

        } catch (Exception ex) {
            Logger.getLogger(ComidasClienteViewController.class.getName()).log(Level.SEVERE, "Error generando la factura.");
            new Mensaje().showModal(Alert.AlertType.ERROR, "Generar la factura", getStage(), "Ocurrio un error generando la factura.");
        }
        
        txtTotal.setText("0");

    }

    public void Suma() {
        double total1 = 0d;
        for (int i = 0; i < tbvFactura.getItems().size(); i++) {
            DecimalFormat df = new DecimalFormat("########.00");
            Double valor2 = new Double(tbvFactura.getItems().get(i).getComPrecio().toString().replace(",", "."));
            total1 += valor2;
            txtTotal.setText(String.valueOf(df.format(Double.valueOf(total1))));
        }
    }

    @FXML
    private void OnActionBtnCancelar(ActionEvent event) {
    }

    private class ButtonCellComida extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellComida() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Agregar");
            cellButton.setStyle("-fx-background-color: #FFAD5B");
            cellButton.setOnAction((ActionEvent t) -> {
                ProComidasDto com = (ProComidasDto) ButtonCellComida.this.getTableView().getItems().get(ButtonCellComida.this.getIndex());
                com.setModificado(true);
                tbvFactura.getItems().add(com);
                tbvComidas.refresh();
                tbvFactura.refresh();
                proFacturasDto.getComidas().add(com);
                Suma();

            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    public void actualizarTbv() {
        ProComidasService proComidasService = new ProComidasService();

        Respuesta respuesta = proComidasService.getComidas();

        if (respuesta.getEstado()) {
            ObservableList<ProComidasDto> comidasDtos = FXCollections.observableList((List<ProComidasDto>) respuesta.getResultado("ProComidas"));
            tbvComidas.setItems(comidasDtos);
            tbvComidas.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta usuarios", getStage(), respuesta.getMensaje());
        }
    }

    private class ButtonCellFactura extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellFactura() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Eliminar");
            cellButton.setStyle("-fx-background-color: #8EF680");
            cellButton.setOnAction((ActionEvent t) -> {
                ProComidasDto fac = (ProComidasDto) ButtonCellFactura.this.getTableView().getItems().get(ButtonCellFactura.this.getIndex());
                proFacturasDto.getComidasEliminadas().add(fac);
                tbvFactura.getItems().remove(fac);
                tbvFactura.refresh();
                Suma();
                if (tbvFactura.getItems().isEmpty()) {
                    txtTotal.setText("0");
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

}
