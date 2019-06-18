package dao;


public class PizzaMemDao implements IPizzaDao {
	
	private Pizza[] pizzas = null;
	
	public PizzaMemDao()
	{
		pizzas = new Pizza[7];
		
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.50);
		pizzas[1] = new Pizza("MAR", "Margherita", 14.00);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.50);	
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.50);
		pizzas[5] = new Pizza("ORI","L'orientale", 13.50);
		pizzas[6] = new Pizza("IND", "L'indienne", 14.00);
	}



	public Pizza[] findAllPizzas() {
		
		return pizzas;
	}
	
	
	public void updatePizza(String codePizza, Pizza pizza) 
	{
		for (int i = 0; i < pizzas.length; i++)
		{
			if (pizzas[i].getCode().equals(codePizza)) 
			{
				pizzas[i] = pizza;
			}
			
		}
	}



	public Pizza findPizzaByCode(String codePizza) 
	{
		for (int i = 0; i < pizzas.length; i++)
		{
			if (pizzas[i].getCode().equals(codePizza)) 
			{
				return pizzas[i];
			}
		}
		
		return null;
	}



	public boolean isPizzaExists(String codePizza)
	{
		return findPizzaByCode(codePizza) != null;
	}
	
	public void displayAllPizza() {

		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());

		}
	}



	public void addPizza(Pizza pizza) 
	{
		Pizza[] newPizzas = new Pizza[pizzas.length + 1];
		
		for (int i = 0; i < pizzas.length; i++) {
			newPizzas[i] = pizzas[i];
		}
		
		pizza.setCode(pizza.getCode());
		newPizzas[newPizzas.length - 1] = pizza;
		
		pizzas = newPizzas;
		
	}



	public void deletePizza(String codePizza)
	{
		Pizza[] newPizzas = new Pizza[pizzas.length - 1];

		int newPizzasIndice = -1;
		for (int i = 0; i < pizzas.length; i++) {
			Pizza pizza = pizzas[i];
			
			if (!pizza.getCode().equals(codePizza)) {

				newPizzas[++newPizzasIndice] = pizza;
			}

		}
		
		pizzas = newPizzas;
		
	}

}
