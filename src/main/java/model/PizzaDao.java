package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Categorie;
import classes.Pizza;
import classes.exceptions.DeletePizzaException;
import classes.exceptions.SavePizzaException;
import classes.exceptions.UpdatePizzaException;
import utils.DatabaseHandle;


public class PizzaDao implements IPizzaDao
{	
	public List<Pizza> findAll() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DatabaseHandle.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM pizza p, categorie c WHERE c.id = p.id_categorie");
			
			List<Pizza> pizzas = new ArrayList<Pizza>();
			
			while (rs.next())
			{
				Pizza pizza = new Pizza();
				pizza.setId(rs.getInt("p.id"));
				pizza.setCode(rs.getString("code"));
				pizza.setDesignation(rs.getString("designation"));
				pizza.setPrice(rs.getFloat("prix"));
				
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("c.id"));
				categorie.setNom(rs.getString("nom"));
				
				pizza.setCategorie(categorie);
				
				pizzas.add(pizza);
			}
			
			return pizzas;
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
			}
			catch (SQLException e)
			{
				// do nothing
				// e.printStackTrace();
			}
		}
		
		return null;
	}

	
	public Pizza findById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DatabaseHandle.getConnection();
			String query = "SELECT * FROM pizza p, categorie c WHERE c.id = p.id_categorie AND c.id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Pizza pizza = new Pizza();
				pizza.setId(rs.getInt("p.id"));
				pizza.setCode(rs.getString("code"));
				pizza.setDesignation(rs.getString("designation"));
				pizza.setPrice(rs.getFloat("prix"));
				
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("c.id"));
				categorie.setNom(rs.getString("nom"));
				
				pizza.setCategorie(categorie);
				return pizza;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}
			catch (SQLException e)
			{
				// do nothing
				// e.printStackTrace();
			}
		}
		
		return null;
	}
	

	public boolean isExists(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = DatabaseHandle.getConnection();
			String query = "SELECT * FROM pizza WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			return rs.next();
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}
			catch (SQLException e)
			{
				// do nothing
				// e.printStackTrace();
			}
		}
		
		return false;
	}

	public void save(Pizza pizza) throws SavePizzaException {
		
		if (pizza == null)
		{
			throw new SavePizzaException("ERREUR : La pizza n'existe pas");
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			pizza.dataControl();
			
			conn = DatabaseHandle.getConnection();
			
			// insertion de la pizza
			String query = "insert into pizza (code, designation, prix, id_categorie) values (?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
						
			pstmt.setString(1, pizza.getCode());
			pstmt.setString(2, pizza.getDesignation());
			pstmt.setFloat(3, pizza.getPrice());
			pstmt.setInt(4, pizza.getCategorie().getId());
			
			pstmt.executeUpdate();
			
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			throw new SavePizzaException("ERREUR : " + e.getMessage());
		}
		finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// ne rien faire
				// e.printStackTrace();
			}
		}
					
		
	}

	public int update(Pizza pizza) throws UpdatePizzaException {
		
		if (pizza == null)
		{
			throw new UpdatePizzaException("ERREUR : Mise à jour de la Pizza");
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			pizza.dataControl();
			
			conn = DatabaseHandle.getConnection();
					
			// mise à jour de la pizza
			String query = "update pizza SET code = ?, designation = ?, prix = ?, id_categorie = ? WHERE (id = ?)";
				
			pstmt = conn.prepareStatement(query);
							
			pstmt.setString(1, pizza.getCode());
			pstmt.setString(2, pizza.getDesignation());
			pstmt.setFloat(3, pizza.getPrice());
			pstmt.setInt(4, pizza.getCategorie().getId());
			pstmt.setInt(5, pizza.getId());
			
			return pstmt.executeUpdate();			
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			throw new UpdatePizzaException("ERREUR : Mise à jour de la Pizza");
		}
		finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// ne rien faire
				// e.printStackTrace();
			}
		}
	}
	
	public String allToString()
	{
		String result = "";
		
		List<Pizza> pizzas = this.findAll();
		
		for (Pizza pizza : pizzas) {
			result += pizza.toString() + "\r\n";
		}
		
		if (result.length() > 0)
		{
			return result;
		}
		else
		{
			return null;
		}
	}
	
	public boolean delete(int id) throws DeletePizzaException 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = DatabaseHandle.getConnection();
			
			// recherche de l'id de la catégorie
			String query = "DELETE FROM pizza WHERE (id = ?);";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			 return pstmt.execute();			
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			throw new DeletePizzaException("ERREUR : Suppression de la Pizza");
		}
		finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// ne rien faire
				// e.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
