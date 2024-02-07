package program;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConexionAPI {
	public static void datosAPI() {
		try {
			// Claves de autenticación
			String publicKey = "441da954e50a210d72c69a0001fc3fe6";
			String privateKey = "55a5164c8d3bdd1ccdc3440f4e1499a226cdb2c3";
			// Generar el timestamp y hash para la autenticación
			long timeStamp = new Date().getTime();
			String hash = generateHash(timeStamp, privateKey, publicKey);
			// Construir la URL de la API
			String apiUrl = "https://gateway.marvel.com:443/v1/public/series";
			URI uri = URI.create(apiUrl + "?apikey=" + publicKey + "&ts=" + timeStamp + "&hash=" + hash);
			// Configurar cliente HTTP y realizar la solicitud
			HttpResponse<String> response = HttpClient.newHttpClient()
					.send(HttpRequest.newBuilder().uri(uri).GET().build(), HttpResponse.BodyHandlers.ofString());
			// Procesar la respuesta
			if (response.statusCode() == 200) {
				String responseBody = response.body();
				if (responseBody.startsWith("{")) {
					// Convertir la respuesta JSON a objetos Java
					JSONObject jsonResponse = new JSONObject(responseBody);
					JSONArray seriesArray = jsonResponse.getJSONObject("data").getJSONArray("results");
					ArrayList<Series> seriesList = new ArrayList<Series>();
					// Recorrer y procesar cada serie en el arreglo JSON
					for (int i = 0; i < seriesArray.length(); i++) {
						JSONObject jo = seriesArray.getJSONObject(i);
						JSONObject thumbnailObject = jo.getJSONObject("thumbnail");
						JSONObject stories = jo.getJSONObject("stories");
						int id = jo.getInt("id");
						String title = jo.getString("title");
						String description = jo.optString("description", "No description");
						int available = stories.getInt("available");
						int returned = stories.getInt("returned");
						String path = thumbnailObject.getString("path");
						String extension = thumbnailObject.getString("extension");
						String imagen = path + "." + extension;
						String resourceURI = jo.getString("resourceURI");
						int startYear = jo.getInt("startYear");
						int endYear = jo.optInt("endYear");
				        // Crear objeto Series y agregarlo a la lista
						Series series = new Series(id, title, description, resourceURI, startYear, endYear, imagen,
								available, returned);
						seriesList.add(series);
				        // Imprimir información de la serie y guardar en MySQL
						series.printSeriesInfo();
						series.insertarDatosEnMySQL(seriesList);
					}
				} else {
					System.out.println("La respuesta no es un objeto JSON válido.");
				}
			} else {
				System.out.println("Error en la solicitud: " + response.statusCode());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

// Se genera un hash único que combina el timestamp, la clave privada y la clave pública
	private static String generateHash(long timeStamp, String privateKey, String publicKey)
			throws NoSuchAlgorithmException {
		String input = timeStamp + privateKey + publicKey;
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes());

		StringBuilder hashBuilder = new StringBuilder();
		for (byte b : messageDigest) {
			hashBuilder.append(String.format("%02x", b));
		}
		return hashBuilder.toString();
	}
}
