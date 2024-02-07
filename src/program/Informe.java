package program;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Informe {

	public static void generarInforme() {
		try {
		    // Establece la conexión a la base de datos MySQL
		    ConexionMySQL conexionMySQL = new ConexionMySQL();
		    conexionMySQL.conexionOpen();
		    // Cargar el archivo JRXML (JasperReport XML) que define el diseño del informe
		    String report = "src/SeriesiReport.jrxml";
		    JasperDesign design = JRXmlLoader.load(report);
		    JasperReport jasperReport = JasperCompileManager.compileReport(design);
		    // Configura la consulta del informe
		    JRDesignQuery designQuery = new JRDesignQuery();
		    designQuery.setText("SELECT * FROM Series");
		    design.setQuery(designQuery);
		    // Genera el informe utilizando JasperReports y llenar los datos desde la base de datos
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conexionMySQL.obtenerConexion());
		    
		    // Muestra el informe en el visor
		    JasperViewer.viewReport(jasperPrint, false);
		    
		    // Muestra la ventana de diálogo para guardar archivo
		    JFileChooser fileChooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
		    fileChooser.setFileFilter(filter);
		    fileChooser.setSelectedFile(new File("informeSerieMarvel.pdf"));
		    int userSelection = fileChooser.showSaveDialog(null);

		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        File fileToSave = fileChooser.getSelectedFile();
		        // Agrega la extensión .pdf si no la tiene
		        String filePath = fileToSave.getAbsolutePath();
		        if (!filePath.toLowerCase().endsWith(".pdf")) {
		            filePath += ".pdf";
		        }
		        // Guarda el informe como PDF
		        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
		        conexionMySQL.cerrarConexion();
		    }
		    conexionMySQL.cerrarConexion();
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, "ERROR al generar el informe: " + ex.getMessage());
		}
	}
}
