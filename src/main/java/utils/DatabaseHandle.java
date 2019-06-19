package utils;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import classes.Categorie;
import classes.Pizza;
import model.CategorieDao;


public class DatabaseHandle {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("parameters");

		String driverName = rb.getString("db.driver.name");
		String url = rb.getString("db.url");
		String userName = rb.getString("db.user.name");
		String userPassword = rb.getString("db.user.password");

		// DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName(driverName);

		return DriverManager.getConnection(url, userName, userPassword);
	}

	public static void init() throws SQLException {

		Connection connection = null;
		
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			deletePizzaTable(connection);
			initPizza(connection);
			
			connection.commit();
		}
		catch (Exception e)
		{
			connection.rollback();
			
			e.printStackTrace();
		}
		finally
		{
			connection.close();
		}
		
	}

	private static void initPizza(Connection connexion) throws Exception {
		List<Pizza> pizzas = new ArrayList<Pizza>();

		List<Categorie> categories = new CategorieDao().findAll();

		pizzas.add(new Pizza(null, "PEP", "Pépéroni", 12.50f, categories.get(1)));
		pizzas.add(new Pizza(null, "MAR", "Margherita", 14.00f, categories.get(0)));
		pizzas.add(new Pizza(null, "REIN", "La Reine", 11.50f, categories.get(0)));
		pizzas.add(new Pizza(null, "FRO", "La 4 fromages", 12.00f, categories.get(0)));
		pizzas.add(new Pizza(null, "CAN", "La cannibale", 12.50f, categories.get(1)));
		pizzas.add(new Pizza(null, "ORI", "L'orientale", 13.50f, categories.get(1)));
		pizzas.add(new Pizza(null, "IND", "L'indienne", 14.00f, categories.get(3)));

		// insertion de la pizza
		String query = "insert into pizza (code, designation, prix, id_categorie) values (?, ?, ?, ?)";
		PreparedStatement pstmt = connexion.prepareStatement(query);

		for (Pizza pizza : pizzas) {
			pstmt.setString(1, pizza.getCode());
			pstmt.setString(2, pizza.getDesignation());
			pstmt.setFloat(3, pizza.getPrice());
			pstmt.setInt(4, pizza.getCategorie().getId());

			pstmt.executeUpdate();
		}
		
		pstmt.close();

	}

	
	private static void deletePizzaTable(Connection connection) throws Exception {
		
		// suppression des enregistrements
		Statement stmt = connection.createStatement();

		stmt.execute("TRUNCATE TABLE pizza");
		
		stmt.close();
	}
}
