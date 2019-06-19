package model;

import java.util.List;

import classes.Pizza;
import classes.exceptions.DeletePizzaException;
import classes.exceptions.SavePizzaException;
import classes.exceptions.UpdatePizzaException;


public interface IPizzaDao
{	
	List<Pizza> findAll();
	Pizza findById(int id);
	boolean isExists(int id);
	
	void save(Pizza pizza) throws SavePizzaException;
	int update(Pizza pizza) throws UpdatePizzaException;
	boolean delete(int id) throws DeletePizzaException;
	
}
