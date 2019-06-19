package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Categorie;
import utils.DatabaseHandle;


public class CategorieDao implements ICategorieDao {

	public List<Categorie> findAll() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseHandle.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM categorie");

			List<Categorie> categories = new ArrayList<Categorie>();

			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("id"));
				categorie.setNom(rs.getString("nom"));
				
				categories.add(categorie);
			}

			return categories;
			
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// do nothing
				// e.printStackTrace();
			}
		}

		return null;
	}
	
	public Categorie findbyId(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseHandle.getConnection();
			String query = "SELECT * FROM categorie WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("id"));
				categorie.setNom("nom");

				return categorie;
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// do nothing
				// e.printStackTrace();
			}
		}

		return null;
	}

}
