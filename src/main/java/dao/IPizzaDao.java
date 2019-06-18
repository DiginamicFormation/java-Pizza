package dao;

import java.util.List;

import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;

public interface IPizzaDao {

	List<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza) throws SavePizzaException;

	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	void deletePizza(String codePizza) throws DeletePizzaException;

	Pizza findPizzaByCode(String codePizza);

	boolean pizzaExists(String codePizza);

}