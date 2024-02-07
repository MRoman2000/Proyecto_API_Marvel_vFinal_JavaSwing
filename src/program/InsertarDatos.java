package program;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class InsertarDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_title;
	private JTextField textField_description;
	private JTextField textField_resourceURI;
	private JTextField textField_urlImagen;
	private JSpinner spinner_startYear;
	private JSpinner spinner_endYear;
	private JSpinner spinner_storyAvailable;
	private JSpinner spinner_storyReturned;

	// Método principal para ejecutar la aplicación de inserción de datos
	public static InsertarDatos crearInstancia() {
		return new InsertarDatos();
	}

	public void mostrarVentana() {
		setVisible(true);
		setLocationRelativeTo(null);
	}

	// Constructor de la clase InsertarDatos
	public InsertarDatos() {
		setTitle("Insertar Datos");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {

		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2));

		// Panel izquierdo con la imagen de fondo
		JPanel panel_izquierdo = new JPanel();
		panel_izquierdo.setBackground(new Color(0, 64, 128));
		contentPane.add(panel_izquierdo);
		JLabel label_fondo = new JLabel("");
		label_fondo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_fondo.setBackground(new Color(0, 64, 128));
		label_fondo.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			Image imagenOriginal = ImageIO.read(new File("src/Marvel.png"));
			Image imagenRedimensionada = imagenOriginal.getScaledInstance(650, 300, Image.SCALE_SMOOTH);
			ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
			label_fondo.setIcon(iconoRedimensionado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_izquierdo.setLayout(new GridLayout(0, 1, 0, 0));
		panel_izquierdo.add(label_fondo);

		// Panel derecho con los campos para insertar datos
		JPanel panel_derecho = new JPanel();
		panel_derecho.setBorder(new EmptyBorder(1, 20, 0, 20));
		panel_derecho.setForeground(new Color(255, 255, 255));
		panel_derecho.setBackground(new Color(0, 64, 128));
		contentPane.add(panel_derecho);
		panel_derecho.setLayout(new GridLayout(11, 1, 0, 15));

		JPanel panel_titulo = new JPanel();
		panel_titulo.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_titulo);
		panel_titulo.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_9 = new JLabel("Insertar datos en MySQL");
		lblNewLabel_9.setBackground(new Color(2, 70, 153));
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(lblNewLabel_9);

		JPanel panel_id = new JPanel();
		panel_id.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_id);
		panel_id.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel_id.add(lblNewLabel);

		textField_id = new JTextField();
		textField_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_id.add(textField_id);
		textField_id.setColumns(10);

		JPanel panel_title = new JPanel();
		panel_title.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_title);
		panel_title.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_title.add(lblNewLabel_1);

		textField_title = new JTextField();
		textField_title.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_title.add(textField_title);
		textField_title.setColumns(10);

		JPanel panel_description = new JPanel();
		panel_description.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_description);
		panel_description.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panel_description.add(lblNewLabel_2);

		textField_description = new JTextField();
		textField_description.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_description.add(textField_description);
		textField_description.setColumns(10);

		JPanel panel_resourceURI = new JPanel();
		panel_resourceURI.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_resourceURI);
		panel_resourceURI.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("resourceURI");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		panel_resourceURI.add(lblNewLabel_3);

		textField_resourceURI = new JTextField();
		textField_resourceURI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_resourceURI.add(textField_resourceURI);
		textField_resourceURI.setColumns(10);

		JPanel panel_startYear = new JPanel();
		panel_startYear.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_startYear);
		panel_startYear.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblNewLabel_4 = new JLabel("startYear");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		panel_startYear.add(lblNewLabel_4);

		spinner_startYear = new JSpinner();
		spinner_startYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner_startYear.setModel(new SpinnerNumberModel(Integer.valueOf(1980), null, null, Integer.valueOf(1)));
		panel_startYear.add(spinner_startYear);

		JPanel panel_endYear = new JPanel();
		panel_endYear.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_endYear);
		panel_endYear.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("endYear");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		panel_endYear.add(lblNewLabel_5);

		spinner_endYear = new JSpinner();
		spinner_endYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner_endYear.setModel(new SpinnerNumberModel(Integer.valueOf(1980), null, null, Integer.valueOf(0)));
		panel_endYear.add(spinner_endYear);

		JPanel panel_url = new JPanel();
		panel_url.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_url);
		panel_url.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblNewLabel_6 = new JLabel("urlImagen");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		panel_url.add(lblNewLabel_6);

		textField_urlImagen = new JTextField();
		textField_urlImagen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_url.add(textField_urlImagen);
		textField_urlImagen.setColumns(10);

		JPanel panel_storyAvailable = new JPanel();
		panel_storyAvailable.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_storyAvailable);
		panel_storyAvailable.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel_7 = new JLabel("storyAvailable");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		panel_storyAvailable.add(lblNewLabel_7);

		spinner_storyAvailable = new JSpinner();
		spinner_storyAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner_storyAvailable
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
		panel_storyAvailable.add(spinner_storyAvailable);

		JPanel panel_storyReturned = new JPanel();
		panel_storyReturned.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_storyReturned);
		panel_storyReturned.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel_8 = new JLabel("storyReturned");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		panel_storyReturned.add(lblNewLabel_8);

		spinner_storyReturned = new JSpinner();
		spinner_storyReturned.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner_storyReturned
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
		panel_storyReturned.add(spinner_storyReturned);

		// Panel de botones para guardar y regresar al menú principal
		JPanel panel_botones = new JPanel();
		panel_botones.setBorder(new EmptyBorder(5, 15, 5, 15));
		panel_botones.setBackground(new Color(0, 64, 128));
		panel_derecho.add(panel_botones);
		panel_botones.setLayout(new GridLayout(0, 2, 20, 10));

		JButton btnNewButton_1 = new JButton("Guardar daton en MySQL");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatosEnMySQL();
			}

		});
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		panel_botones.add(btnNewButton_1);
		// Botón para ir al menú principal
		JButton btnNewButton_2 = new JButton("Ir al Menu Principal");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
				principal.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setBackground(new Color(255, 128, 64));
		panel_botones.add(btnNewButton_2);
	}

	// Método para guardar datos en MySQL
	private void guardarDatosEnMySQL() {
		// Verificando que los campos obligatorios no estén vacíos
		if (textField_id.getText().isEmpty() || textField_title.getText().isEmpty() || textField_description.getText().isEmpty() || 
		    textField_resourceURI.getText().isEmpty() || textField_urlImagen.getText().isEmpty() || textField_urlImagen.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "¡Todos los campos obligatorios deben estar llenos!");
		} else {
		    // Si todos los campos obligatorios tienen valores, proceder con la asignación de variables
		    int id = Integer.parseInt(textField_id.getText());
		    String title = textField_title.getText();
		    String descripcion = textField_description.getText();
		    String resourceURI = textField_resourceURI.getText();
		    int startYear = (int) spinner_startYear.getValue();
		    int endYear = (int) spinner_endYear.getValue();
		    String urlImagen = textField_urlImagen.getText();
		    int storyAvailable = (int) spinner_storyAvailable.getValue();
		    int storyReturned = (int) spinner_storyReturned.getValue();
			ConexionMySQL conexionMySQL = new ConexionMySQL();
			conexionMySQL.conexionOpen();
			String sql = "INSERT INTO Series (id, title, description, resourceURI, startYear, endYear, urlImagen, storyAvailable, storyReturned) "
					+ "VALUES ('" + id + "', '" + title + "', '" + descripcion + "', '" + resourceURI + "', " + startYear
					+ ", " + endYear + ", '" + urlImagen + "', " + storyAvailable + ", " + storyReturned + ")";
			conexionMySQL.insertarDatos(sql);
			JOptionPane.showMessageDialog(null, "Campo registrado correctamente.");
			conexionMySQL.cerrarConexion();
			textField_id.setText("");
			textField_title.setText("");
			textField_description.setText("");
			textField_resourceURI.setText("");
			spinner_startYear.setValue(1980);
			spinner_endYear.setValue(1980);
			textField_urlImagen.setText("");
			spinner_storyAvailable.setValue(1);
			spinner_storyReturned.setValue(1);
		}
	}
}