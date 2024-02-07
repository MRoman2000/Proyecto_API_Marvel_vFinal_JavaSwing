package program;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class LeerDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JTextField textField_file;
	private JTable table;
	private String rutaFichero;

	/**
	 * Launch the application.
	 */
	public static LeerDatos crearInstancia() {
		return new LeerDatos();
	}

	public void mostrarVentana() {
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public LeerDatos() {
		setTitle("Mostrar Datos");
		setBackground(new Color(0, 128, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 83, 166));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 83, 166));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

		textField_file = new JTextField();
		textField_file.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(textField_file);
		textField_file.setColumns(30);

		JButton btn_file = new JButton("Seleccionar...");
		btn_file.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_file.setForeground(new Color(0, 0, 0));
		btn_file.setBackground(new Color(0, 128, 255));
		btn_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionar();
			}
		});
		panel.add(btn_file);

		JButton btn_cargarDatos = new JButton("Mostrar Datos");
		btn_cargarDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_cargarDatos.setForeground(new Color(0, 0, 0));
		btn_cargarDatos.setBackground(new Color(0, 128, 128));
		btn_cargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
			}
		});
		panel.add(btn_cargarDatos);

		JButton btn_cargarEnMySQL = new JButton("Subir datos en MySQL");
		btn_cargarEnMySQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rutaFichero != null && !rutaFichero.isEmpty()) {
					ArrayList<ArrayList<String>> series = JSONandXML.procesarOpcionInsertarDatos(rutaFichero);
					JSONandXML.insertarDatosEnMySQLFicheros(series);
				} else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_cargarEnMySQL.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_cargarEnMySQL.setForeground(new Color(0, 0, 0));
		btn_cargarEnMySQL.setBackground(new Color(0, 128, 128));
		panel.add(btn_cargarEnMySQL);

		JButton btn_cargarDatosDeMySQL = new JButton("Cargar datos desde MySQL");
		btn_cargarDatosDeMySQL.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_cargarDatosDeMySQL.setForeground(new Color(0, 0, 0));
		btn_cargarDatosDeMySQL.setBackground(new Color(0, 128, 128));
		btn_cargarDatosDeMySQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosDesdeMySQL();
			}
		});
		panel.add(btn_cargarDatosDeMySQL);

		JButton btn_irMenuPrincipal = new JButton("Ir al Menu Principal");
		btn_irMenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_irMenuPrincipal.setForeground(new Color(0, 0, 0));
		btn_irMenuPrincipal.setBackground(new Color(255, 128, 0));
		btn_irMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra el menu principal 
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
				principal.setLocationRelativeTo(null);
			}
		});
		panel.add(btn_irMenuPrincipal);

		JPanel panel_tabla = new JPanel();
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		model.addColumn("id");
		model.addColumn("title");
		model.addColumn("description");
		model.addColumn("resourceURI");
		model.addColumn("startYear");
		model.addColumn("endYear");
		model.addColumn("urlImagen");
		model.addColumn("storyAvailable");
		model.addColumn("storyReturned");
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(new Color(192, 192, 192));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setToolTipText("");

		panel_tabla.setBackground(new Color(0, 83, 166));
		contentPane.add(panel_tabla);
		panel_tabla.setLayout(new GridLayout(0, 1, 15, 15));
		panel_tabla.add(scrollPane);
	}

	public File seleccionar() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int seleccion = fc.showOpenDialog(contentPane);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fc.getSelectedFile();
			textField_file.setText(fichero.getAbsolutePath());
			rutaFichero = fichero.getAbsolutePath(); // Almacenar la ruta del fichero
			return fichero;
		}
		return null; // Si no se seleccionó ningún archivo
	}

	public void cargarDatos() {
		if (rutaFichero != null && !rutaFichero.isEmpty()) {
			ArrayList<ArrayList<String>> series = JSONandXML.procesarOpcionInsertarDatos(rutaFichero);
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(new String[] { "id", "title", "description", "resourceURI", "startYear",
					"endYear", "urlImagen", "storyAvailable", "storyReturned" });
			// Limpiar el modelo actual
			model.setRowCount(0);
			for (ArrayList<String> s : series) {
				// Agregar cada fila al modelo de tabla
				model.addRow(s.toArray(new String[0]));
			}
			// Asignar el modelo a la tabla
			table.setModel(model);
			JOptionPane.showMessageDialog(null, "Datos han sido cargados.");
		} else {
			// Manejar el caso cuando no se ha seleccionado ningún fichero
			JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarDatosDesdeMySQL() {
		ConexionMySQL conexionMySQL = new ConexionMySQL();
		conexionMySQL.obtenerConexion();
		String sql = "SELECT * from Series";
		ArrayList<String[]> datosMySQL = conexionMySQL.mostrarTabla(sql);
		// Crear el modelo de la tabla
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "id", "title", "description", "resourceURI", "startYear", "endYear",
				"urlImagen", "storyAvailable", "storyReturned" });
		// Limpiar el modelo actual
		model.setRowCount(0);
		for (String[] s : datosMySQL) {
			model.addRow(s);
		}
		// Asignar el modelo a la tabla
		table.setModel(model);
		JOptionPane.showMessageDialog(null, "Datos han sido cargados desde MySQL.");
		conexionMySQL.cerrarConexion();
	}
}
