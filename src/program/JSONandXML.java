package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class JSONandXML {

	// Generar fichero JSON
	public static void generarJSON(ArrayList<String[]> seriesList) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("prueba.json"))) {
			writer.write(" [\n");
			ArrayList<String> etiquetas = obtenerEtiquetas();
			for (String[] reserva : seriesList) {
				writer.write(" {\n");
				for (int i = 0; i < etiquetas.size(); i++) {
					String etiqueta = etiquetas.get(i);
					String valor = reserva[i];
					writer.write(String.format("   \"%s\": \"%s\"", etiqueta, valor));

					if (i < etiquetas.size() - 1) {
						writer.write(",");
					}
					writer.write("\n");
				}
				writer.write(" }");
				if (seriesList.indexOf(reserva) < seriesList.size() - 1) {
					writer.write(",");
				}
				writer.write("\n");
			}
			writer.write(" ]\n");
			System.out.println("Archivo 'prueba.json' generado correctamente.");
		} catch (IOException e) {
			System.out.println("Error al generar el archivo JSON: " + e.getMessage());
		}
	}

	// Generar fichero XML
	public static void generarXML(ArrayList<String[]> seriesList) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("prueba.xml"))) {
			writer.write("<series>\n");
			ArrayList<String> etiquetas = obtenerEtiquetas();
			for (String[] reserva : seriesList) {
				writer.write(" <serie>\n");
				for (int i = 0; i < etiquetas.size(); i++) {
					String etiqueta = etiquetas.get(i);
					String valor = reserva[i];
					valor = escaparCaracteresEspeciales(valor); // Escapar caracteres especiales
					writer.write(String.format("     <%s>%s</%s>\n", etiqueta, valor, etiqueta));
				}
				writer.write(" </serie>\n");
			}
			writer.write("</series>\n");
			System.out.println("Archivo 'prueba.xml' generado correctamente.");
		} catch (IOException e) {
			System.out.println("Error al generar el archivo XML: " + e.getMessage());
		}
	}

	public static String escaparCaracteresEspeciales(String valor) {
		valor = valor.replace("&", "&amp;");
		valor = valor.replace("<", "&lt;");
		valor = valor.replace(">", "&gt;");
		valor = valor.replace("\"", "&quot;");
		valor = valor.replace("'", "&apos;");
		return valor;
	}

	// Extrae los valores del fichero XML
	public static ArrayList<ArrayList<String>> extraerValoresXML(String xml) {
		ArrayList<ArrayList<String>> resultados = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xml)));
			NodeList serieNodes = document.getElementsByTagName("serie");
			for (int i = 0; i < serieNodes.getLength(); i++) {
				Node serieNode = serieNodes.item(i);
				if (serieNode.getNodeType() == Node.ELEMENT_NODE) {
					Element serieElement = (Element) serieNode;
					NodeList childNodes = serieElement.getChildNodes();
					ArrayList<String> valores = new ArrayList<>();
					for (int j = 0; j < childNodes.getLength(); j++) {
						Node childNode = childNodes.item(j);

						if (childNode.getNodeType() == Node.ELEMENT_NODE) {
							String valor = childNode.getTextContent().trim();
							valores.add(valor);
						}
					}
					resultados.add(valores);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultados;
	}

	// Extrae los valores del fichero JSON y ordena los campos
	public static ArrayList<ArrayList<String>> extraerValoresJSON(String json) {
		ArrayList<ArrayList<String>> results = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			ArrayList<String> values = new ArrayList<>();
			// Orden de los campos
			String[] fieldsOrder = { "id", "title", "description", "resourceURI", "startYear", "endYear", "urlImagen",
					"storyAvailable", "storyReturned" };
			for (String field : fieldsOrder) {
				String value = jsonObject.optString(field, ""); // Optiene el valor o una cadena vacía si no existe
				values.add(value);
			}
			results.add(values);
		}
		return results;
	}

	// Metodo que almacena las etiquetas
	private static ArrayList<String> obtenerEtiquetas() {
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.add("id");
		etiquetas.add("title");
		etiquetas.add("description");
		etiquetas.add("resourceURI");
		etiquetas.add("startYear");
		etiquetas.add("endYear");
		etiquetas.add("urlImagen");
		etiquetas.add("storyAvailable");
		etiquetas.add("storyReturned");
		return etiquetas;
	}

	// leer el fichero y guardar el contenido
	public static String leerContenidoArchivo(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			StringBuilder contenido = new StringBuilder();
			String linea;
			while ((linea = reader.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
			return contenido.toString();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
			return null;
		}
	}

	// Se procesa las opciones que elige el usaurio (XML o JSON)
	static ArrayList<ArrayList<String>> procesarOpcionInsertarDatos(String file) {
		ArrayList<ArrayList<String>> series = new ArrayList<ArrayList<String>>();
		String contenido = leerContenidoArchivo(file);
		if (file.endsWith(".json")) {
			series = extraerValoresJSON(contenido);
			for (ArrayList<String> s : series) {
				System.out.println(s);
			}
		} else {
			series = extraerValoresXML(contenido);
			for (ArrayList<String> s : series) {
				System.out.println(s);
			}
		}
		return series;
	}

	private static String generarConsultaSQL(ArrayList<ArrayList<String>> series) {
		StringBuilder sqlBuilder = new StringBuilder();
		for (ArrayList<String> s : series) {
			String escapedDescription = s.get(2).replace("'", "''");
			String sql = "INSERT INTO Series (id, title, description, resourceURI, startYear, endYear, urlImagen, storyAvailable, storyReturned) "
					+ "VALUES ('" + s.get(0) + "', '" + s.get(1) + "', '" + escapedDescription + "', '" + s.get(3)
					+ "', " + s.get(4) + ", " + s.get(5) + ", '" + s.get(6) + "', " + s.get(7) + ", " + s.get(8) + ");";
			sqlBuilder.append(sql).append("\n"); // Agrega la consulta actual al StringBuilder
		}
		return sqlBuilder.toString();
	}

	public static void insertarDatosEnMySQLFicheros(ArrayList<ArrayList<String>> series) {
		ConexionMySQL conexion = new ConexionMySQL();
		conexion.conexionOpen();
		String consulta = generarConsultaSQL(series);
		String[] consultasArray = consulta.split(";");
		for (String consultaIndividual : consultasArray) {
			if (!consultaIndividual.trim().isEmpty()) {
				System.out.println(consultaIndividual);
				conexion.insertarDatos(consultaIndividual);
			}
		}
		conexion.cerrarConexion();
	}
}
