package appli;


import java.util.Collections;



import java.util.List;
import java.util.Scanner;

import classes.Categorie;
import classes.Pizza;
import classes.comparators.PizzaPrixCroissantComparator;
import classes.comparators.PizzaPrixDecroissantComparator;
import classes.exceptions.DeletePizzaException;
import classes.exceptions.SavePizzaException;
import classes.exceptions.UpdatePizzaException;
import model.CategorieDao;
import model.PizzaDao;
import utils.DatabaseHandle;



public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SavePizzaException {
		
		PizzaDao pizzaDaoImpl = new PizzaDao();
		CategorieDao categorieDaoImpl = new CategorieDao();
		
		// pour mettre fin au programme
		boolean stopP = false;
		
		// affichage du menu 
		displayMenu();
		
		Integer id = null;
		String code = null;
		String designation = null;
		Float price = null;
		Categorie categorie = null;
		
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
			
				System.out.println(pizzaDaoImpl.allToString());
				
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
					System.out.println("Veuillez saisir la désignation :");
					designation = sc.nextLine();
					System.out.println("Veuillez saisir le prix:");
					price = sc.nextFloat();
					
					System.out.println("Veuillez saisir le ID de la catégorie :");
					List<Categorie> categories = categorieDaoImpl.findAll();
					for (Categorie c : categories) {
						System.out.println("\t" + c.toString());
					}
					
					categorie = null;
					Integer idCategorie = sc.nextInt();
					for (Categorie c : categories) {
						if (c.getId() == idCategorie)
						{
							categorie = c;
							break;
						}
					}
										
					Pizza newPizza = new Pizza(null, code, designation, price, categorie);
					
					pizzaDaoImpl.save(newPizza);
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
					sc.nextLine();
					System.out.println("Veuillez saisir le ID de la pizza à modifier");
					
					System.out.println(pizzaDaoImpl.allToString());
					
					id = sc.nextInt();
					sc.nextLine();
					System.out.println("Veuillez saisir le nouveau code");
					code = sc.nextLine();
					System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
					designation = sc.nextLine();
					System.out.println("Veuillez le nouveau prix:");
					price = sc.nextFloat();
					
					System.out.println("Veuillez saisir le ID de la catégorie :");
					List<Categorie> categories = categorieDaoImpl.findAll();
					for (Categorie c : categories) {
						System.out.println("\t" + c.toString());
					}
					
					categorie = null;
					Integer idCategorie = sc.nextInt();
					for (Categorie c : categories) {
						if (c.getId() == idCategorie)
						{
							categorie = c;
							break;
						}
					}
					
					Pizza newPizza = new Pizza(id, code, designation, price, categorie);
					
					pizzaDaoImpl.update(newPizza);
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
					
					System.out.println("Veuillez saisir le ID de la pizza à supprimer");
					
					System.out.println(pizzaDaoImpl.allToString());
					
					id = sc.nextInt();
					sc.nextLine();
					
					pizzaDaoImpl.delete(id);
				}
				catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			else if (value == 5)
			{
				System.out.println("Affichage des pizzas par prix croissants");
				List<Pizza> pizzas = pizzaDaoImpl.findAll();
				Collections.sort(pizzas, new PizzaPrixCroissantComparator());

				for (Pizza pizza : pizzas) {
					System.out.println(pizza.toString());
				}
				
				displayMenu();
			}
			else if (value == 6)
			{
				System.out.println("Affichage des pizzas par prix décroissants");
				List<Pizza> pizzas = pizzaDaoImpl.findAll();
				Collections.sort(pizzas, new PizzaPrixDecroissantComparator());
				
				for (Pizza pizza : pizzas) {
					System.out.println(pizza.toString());
				}
				
				displayMenu();
			}
			// initialisation de la base de données
			else if (value == 7) {
				
				try 
				{
					DatabaseHandle.init();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(pizzaDaoImpl.allToString());
				
				displayMenu();
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
				+ "5. Afficher les pizzas par prix croissants\r\n"
				+ "6. Afficher les pizzas par prix décroissants\r\n"
				+ "7. Initialiser la base de données\r\n"
				+ "99. Sortir\r\n"
				+ "=====================================\r\n\r\n";
		
		
		System.out.println(menu);
		
		
		
	}

}
