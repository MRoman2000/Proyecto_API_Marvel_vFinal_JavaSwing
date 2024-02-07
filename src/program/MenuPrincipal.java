package program;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenuPrincipal() {
		setTitle("Menu Principal");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {

		}
		setBackground(new Color(0, 128, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 665);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(151, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(151, 0, 0));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icono = new ImageIcon("src\\marvel_logo.png");
		Image imagen = icono.getImage();
		// Redimensionar la imagen al tamaño deseado 
		Image nuevaImagen = imagen.getScaledInstance(450, 100, Image.SCALE_SMOOTH);
		// Crear un nuevo ImageIcon con la imagen redimensionada
		ImageIcon iconoRedimensionado = new ImageIcon(nuevaImagen);
		// Crear el JLabel y establecer el icono redimensionado
		lblNewLabel.setIcon(iconoRedimensionado);
		panel.add(lblNewLabel);
	

		JPanel panel_superior = new JPanel();
		panel_superior.setBackground(new Color(151, 0, 0));
		contentPane.add(panel_superior);
		panel_superior.setLayout(new GridLayout(0, 5, 0, 0));

		JPanel panel_generarInforme = new JPanel();
		panel_generarInforme.setBackground(new Color(151, 0, 0));
		panel_superior.add(panel_generarInforme);
		panel_generarInforme.setLayout(new CardLayout(10, 40));

		JButton btn_generarInforme = new JButton("Generar Informe");
		btn_generarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama a la clase , para generar informe y mostrar en la pantalla 
				Informe.generarInforme();
			}
		});
		btn_generarInforme.setForeground(new Color(255, 255, 255));
		btn_generarInforme.setBackground(new Color(0, 0, 64));
		panel_generarInforme.add(btn_generarInforme);

		JPanel panel_insertarDatos = new JPanel();
		panel_insertarDatos.setBackground(new Color(151, 0, 0));
		panel_superior.add(panel_insertarDatos);
		panel_insertarDatos.setLayout(new CardLayout(10, 40));

		JButton btn_insertarDatos = new JButton("Insertar Datos");
		btn_insertarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra la interfaz de Insertar Datos
				InsertarDatos insertarDatos = InsertarDatos.crearInstancia();
				insertarDatos.mostrarVentana();
				dispose();
			}
		});
		btn_insertarDatos.setForeground(new Color(255, 255, 255));
		btn_insertarDatos.setBackground(new Color(0, 0, 64));
		panel_insertarDatos.add(btn_insertarDatos);

		JPanel panel_leerDatos = new JPanel();
		panel_leerDatos.setBackground(new Color(151, 0, 0));
		panel_superior.add(panel_leerDatos);
		panel_leerDatos.setLayout(new CardLayout(10, 40));

		JButton btn_leerDatos = new JButton("Leer Datos");
		btn_leerDatos.setForeground(new Color(255, 255, 255));
		btn_leerDatos.setBackground(new Color(0, 0, 64));
		btn_leerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestra la interfaz de Leer Datos
				LeerDatos leerDatos = LeerDatos.crearInstancia();
				leerDatos.mostrarVentana();
				dispose();
			}
		});
		panel_leerDatos.add(btn_leerDatos);

		JPanel panel_generarXML = new JPanel();
		panel_generarXML.setBackground(new Color(151, 0, 0));
		panel_superior.add(panel_generarXML);
		panel_generarXML.setLayout(new CardLayout(10, 40));

		JButton btn_generarXML = new JButton("Generar XML");
		btn_generarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama al metodo para generar  fichero XML
				procesarDatos(".xml");
			}
		});
		btn_generarXML.setForeground(new Color(255, 255, 255));
		btn_generarXML.setBackground(new Color(0, 0, 64));
		panel_generarXML.add(btn_generarXML);

		JPanel panel_generarJSON = new JPanel();
		panel_generarJSON.setBackground(new Color(151, 0, 0));
		panel_superior.add(panel_generarJSON);
		panel_generarJSON.setLayout(new CardLayout(10, 40));

		JButton btn_generarJSON = new JButton("Generar JSON");
		btn_generarJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama al metodo para generar  fichero JSON
				procesarDatos(".json");
			}
		});
		btn_generarJSON.setForeground(new Color(255, 255, 255));
		btn_generarJSON.setBackground(new Color(0, 0, 64));
		panel_generarJSON.add(btn_generarJSON);

		JPanel panel_interior = new JPanel();
		panel_interior.setBorder(new EmptyBorder(0, 100, 0, 100));
		panel_interior.setBackground(new Color(151, 0, 0));
		contentPane.add(panel_interior);
		panel_interior.setLayout(new GridLayout(3, 1, 10, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_interior.add(lblNewLabel_1);
		
				JButton btn_conectarAPI = new JButton("Conectarse a la API");
				btn_conectarAPI.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Llama a la clase Conexion API para sacar los datos de API 
						ConexionAPI.datosAPI();
						JOptionPane.showMessageDialog(null, "Base de datos y tabla creadas correctamente");
					}
				});
				btn_conectarAPI.setForeground(new Color(255, 255, 255));
				btn_conectarAPI.setFont(new Font("Tahoma", Font.ITALIC, 14));
				btn_conectarAPI.setBackground(new Color(0, 64, 0));
				panel_interior.add(btn_conectarAPI);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_interior.add(lblNewLabel_2);
	}

	public static void procesarDatos(String formato) {
		ConexionMySQL conexionMySQL = new ConexionMySQL();
		conexionMySQL.obtenerConexion();
		String sql = "SELECT * FROM Series";
		ArrayList<String[]> datos = conexionMySQL.mostrarTabla(sql);
		// Dependiendo del fichero , procesa los valores 
		if (formato.endsWith(".json")) {
			JSONandXML.generarJSON(datos);
			JOptionPane.showMessageDialog(null, "Archivo JSON generado correctamente.");
		} else {
			JSONandXML.generarXML(datos);
			JOptionPane.showMessageDialog(null, "Archivo XML generado correctamente.");
		}
		conexionMySQL.cerrarConexion();
	}
}
