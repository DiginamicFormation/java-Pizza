package model;

import java.util.List;

import classes.Categorie;


public interface ICategorieDao {
	
	List<Categorie> findAll();
	Categorie findbyId(int id);
	
}
