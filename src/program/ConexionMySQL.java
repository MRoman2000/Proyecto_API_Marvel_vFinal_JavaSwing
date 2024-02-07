package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionMySQL {
	private Statement st;
	private ResultSet rs;
	private Connection con;

// Este método devuelve la conexión a la base de datos
	public Connection obtenerConexion() {
		if (con == null) {
			conexionOpen();
		}
		return con;
	}

	// Abre una conexión a la base de datos
	public Connection conexionOpen() {
		String userName = "root";
		String password = "gcf24ea2chBeaABhBcA1A-1c5GDdH51F";
		String baseDeDatos = "railway";
		String url = "jdbc:mysql://@monorail.proxy.rlwy.net:15694/" + baseDeDatos;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return con;
	}

	// Ejecuta una consulta SQL de tipo SELECT y devuelve un objeto ResultSet
	public ResultSet ejecutarConsulta(String consulta) {
		ResultSet resultSet = null;
		try {
			st = con.createStatement();
			resultSet = st.executeQuery(consulta);

		} catch (SQLException e) {
			System.out.println("ERROR al ejecutar consulta: " + e.getMessage());
		}
		return resultSet;
	}

	// Ejecuta una consulta SQL de tipo SELECT y devuelve una lista de arrays de
	// cadenas
	public ArrayList<String[]> mostrarTabla(String consulta) {
		ArrayList<String[]> datosList = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(consulta);
			while (rs.next()) {
				int columnCount = rs.getMetaData().getColumnCount();
				String[] rowData = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = rs.getString(i);
				}
				datosList.add(rowData);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return datosList;
	}

	// Ejecuta una consulta SQL para crear una tabla en la base de datos
	public void crearTabla(String query) {
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(query);
			System.out.println("Tabla creada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al crear la tabla: " + e.getMessage());
		}
	}

	// Ejecuta una consulta SQL para crear una nueva base de datos en el servidor
	// MySQL
	public void crearBaseDeDatos(String query) {
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("No existe la BBDD , se creara una nueva ");
		}
	}

	// Cierra la conexión a la base de datos
	public void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
				System.out.println("Conexion cerrada");
			}
		} catch (Exception e) {
			System.out.println("ERROR al cerrar la conexión: " + e.getMessage());
		}
	}

	// Ejecuta una consulta SQL para insertar, actualizar o eliminar datos en la
	// base de datos
	public void insertarDatos(String consulta) {
		try {
			try (PreparedStatement ps = con.prepareStatement(consulta)) {
				// Ejecutar la consulta
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
		}
	}
}
