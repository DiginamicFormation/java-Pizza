package appli;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Scanner;

import dao.Pizza;
import dao.PizzaDao;
import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;



public class Main {

	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		PizzaDao pizzaDao = new PizzaDao();
		
		//pour mettre fin au programme
		boolean stopP = false;
		
		// affichage du menu 
		displayMenu();
		
		
		String code = null;
		String name = null;
		Double price = null;
		/**
		 * boucle tant pour afficher le menu tant que l'utilisateur ne tape pas 99 
		 * */
		//scan.nextLine();
		while(!stopP) {
			
			System.out.println("----------------------");
			
			// saisie de l'utilisateur
			int value = sc.nextInt();
			
			// affiche la liste des pizzas
			if (value == 1) {
			
				System.out.println("Affichage des pizzas:");
				pizzaDao.displayAllPizza();
				displayMenu();
			}
			// ajoute une nouvelle pizza
			else if (value == 2)
			{
				try {
					System.out.println("Ajout d’une nouvelle pizza");
					sc.nextLine();
					System.out.println("Veuillez saisir le code");
					code = sc.nextLine();
					System.out.println("Veuillez saisir le nom (sans espace) :");
					name = sc.nextLine();
					System.out.println("Veuillez le prix:");
					price = Double.valueOf(sc.nextLine());
					Pizza newPizza = new Pizza(code, name, price);
					
					pizzaDao.saveNewPizza(newPizza);
				} 
				catch (SavePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			// mise a jour d'une pizza	
			else if (value == 3)
			{
				try {
					System.out.println("Mise à jour d’une pizza");
					pizzaDao.displayAllPizza();
					sc.nextLine();
					System.out.println("Veuillez saisir le code de la pizza à modifier");
					String oldCode = sc.nextLine();
					System.out.println("Veuillez saisir le nouveau code");
					code = sc.nextLine();
					System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
					name = sc.nextLine();
					System.out.println("Veuillez le nouveau prix:");
					price = Double.valueOf(sc.nextLine());
					
					pizzaDao.updatePizza(oldCode, new Pizza(code, name , price));
				}
				catch (UpdatePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			// suppression d'une pizza
			else if (value == 4)
			{
				
				try {
					System.out.println("Suppression d’une pizza");
					pizzaDao.displayAllPizza();
					sc.nextLine();
					System.out.println("Veuillez saisir le code de la pizza à supprimer");
					code = sc.nextLine();
					
					pizzaDao.deletePizza(code);
				}
				catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			// demande de sortie du programme
			else if (value == 99)
			{
				System.out.println("Au revoir");
				
				stopP = true;
			}
			
		}
	}
	
	
	public static void displayMenu() {
		String menu = "\r\n\r\n====================================="
				+ "\r\n\r\n***** Pizzeria Administration *****\r\n" 
				+ "1. Afficher les pizzas\r\n"
				+ "2. Ajouter une nouvelle pizza\r\n" 
				+ "3. Mettre à jour une pizza\r\n" 
				+ "4. Supprimer une pizza\r\n"
				+ "99. Sortir\r\n"
				+ "=====================================\r\n\r\n";
		
		
		System.out.println(menu);
		
		
		
	}




	public static void displayAllPizza(PizzaDao pizzaDao)
	{
		for (Pizza pizza : pizzaDao.findAllPizzas()) 
		{
			System.out.println(pizza.toString());
		}
	}
}
