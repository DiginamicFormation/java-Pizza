
public interface IPizzaDao {
	
	Pizza[] findAllPizzas();
	void updatePizza(String codePizza, Pizza pizza);
	Pizza findPizzaByCode(String codePizza);
	boolean isPizzaExists(String codePizza);
}
