package dao;

import java.util.ArrayList;
import java.util.List;

public class PizzaDao implements IPizzaDao {

	private List<Pizza> listPizza = null;
	
	
	public PizzaDao() {
		super();

		listPizza = new ArrayList<Pizza>();
		
		listPizza.add(new Pizza("MAR", "Margarita", 6.20F));
		listPizza.add(new Pizza("MER", "Merguez", 8.10F));
		listPizza.add(new Pizza("FRO", "Formages", 7.20F));
		listPizza.add(new Pizza("DYN", "Dynamite", 8.20F));
		
	}

	
	public List<Pizza> findAllPizzas() {
		
		return listPizza;
	}

	public void saveNewPizza(Pizza pizza) {
		listPizza.add(pizza);
		
	}

	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza pizzaOld = findPizzaByCode(codePizza);
		
		if (pizzaOld != null)
		{
			listPizza.remove(pizzaOld);
			listPizza.add(pizza);
		}
		
	}

	public void deletePizza(String codePizza) {
		Pizza pizza = findPizzaByCode(codePizza);
		
		if (pizza != null)
		{
			listPizza.remove(pizza);
		}
		
	}

	public Pizza findPizzaByCode(String codePizza) {

		boolean trouveP = false;
		Pizza result = null;
		
		for (int i = 0; i < listPizza.size() && !trouveP; i++) 
		{
			Pizza pizza = listPizza.get(i);
			
			if (pizza.getCode().equalsIgnoreCase(codePizza))
			{
				trouveP = true;
				
				result = pizza;				
			}
		}
		
		return result;
	}

	public boolean pizzaExists(String codePizza) {

		Pizza pizza = findPizzaByCode(codePizza);
		
		return pizza != null;
	}
	
	public String toString() {
		
		String result = new String();
		
		for (Pizza pizza : listPizza) {
			result += pizza.toString() + "\r\n";
		}
		
		return result + "\r\n" + "\r\n";
	}

}
