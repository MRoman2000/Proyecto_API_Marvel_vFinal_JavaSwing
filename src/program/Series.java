package program;

import java.util.ArrayList;

public class Series {
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private int startYear;
	private int endYear;
	private String urlImagen;
	private int storyAvailable;
	private int storyReturned;

	public Series(int id, String title, String description, String resourceURI, int startYear, int endYear,
			String urlImagen, int storyAvailable, int storyReturned) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.resourceURI = resourceURI;
		this.startYear = startYear;
		this.endYear = endYear;
		this.urlImagen = urlImagen;
		this.storyAvailable = storyAvailable;
		this.storyReturned = storyReturned;
	}
	
	// Toma una lista de objetos Series y los inserta en una base de datos MySQL
	public void insertarDatosEnMySQL(ArrayList<Series> series) {
		ConexionMySQL conexionMySQL = new ConexionMySQL();
		conexionMySQL.obtenerConexion();
		// Escapar comillas simples en la descripción
		StringBuilder sqlBuilder = new StringBuilder();
		String escapedDescription = getDescription().replace("'", "''");
		String sql = "INSERT INTO Series (id, title, description, resourceURI, startYear, endYear, urlImagen, storyAvailable, storyReturned) "
				+ "VALUES (" + getId() + ", '" + getTitle() + "', '" + escapedDescription + "', '" + getResourceURI()
				+ "', " + getStartYear() + ", " + getEndYear() + ", '" + getUrlImagen() + "', " + getStoryAvailable()
				+ ", " + getStoryReturned() + ")";
		Thread hilo = new Thread(() -> {
			sqlBuilder.append(sql).append("\n");
			conexionMySQL.insertarDatos(sqlBuilder.toString());
			conexionMySQL.cerrarConexion();
		});
		hilo.start();
	}

	public void printSeriesInfo() {
		System.out.println("ID: " + id);
		System.out.println("Serie: " + title);
		System.out.println("Descripción: " + description);
		System.out.println("Resource URI: " + resourceURI);
		System.out.println("Start Year: " + startYear);
		System.out.println("End Year: " + endYear);
		System.out.println("Foto: " + urlImagen);
		System.out.println("storyAvailable: " + storyAvailable);
		System.out.println("storyReturned: " + storyReturned);
		System.out.println();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public int getStoryAvailable() {
		return storyAvailable;
	}

	public void setStoryAvailable(int storyAvailable) {
		this.storyAvailable = storyAvailable;
	}

	public int getStoryReturned() {
		return storyReturned;
	}

	public void setStoryReturned(int storyReturned) {
		this.storyReturned = storyReturned;
	}

}