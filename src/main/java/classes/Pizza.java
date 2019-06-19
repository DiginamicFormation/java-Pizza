package classes;

import classes.exceptions.StockageException;

public class Pizza {
	/*
	 * Id (ou identifiant unique pour chaque Pizza) : 1 Code : MAR Désignation :
	 * MARGARITA Prix : 9,20 €
	 */
	private Integer id;
	private String code = null;
	private String designation = null;
	private Float price = null;
	private Categorie categorie = null;
	
	public static final double PRICE_MIN = 4;
	public static final double PRICE_MAX = 100;
	public static final double CODE_LENGTH = 3;
	
		
	public Pizza() {
		super();
	}

	/**
	 * 
	 * Constructeur de la classe Pizza
	 * 
	 * @param id id de la pizza 
	 * @param code code de la pizza (les trois premieres lettre en Maj)
	 * @param designation nom de la pizza
	 * @param price prix de la pizza
	 */
	public Pizza(Integer id, String code, String designation, Float price, Categorie categorie) {
		super();
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.price = price;
		this.categorie = categorie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	@Override
	public String toString() {
		return id + " => " + code + " - " + designation + " - " + price
				+ " (" + categorie + ")";
	}

	public void dataControl() throws StockageException
	{
		String message = "";
		
		if (id != null && id < 0)
		{
			message += "Le ID de la pizza est négatif\r\n";
		}
		
		if (code == null || code.trim().length() != CODE_LENGTH)
		{
			message += "Le code de la pizza n'est pas sur " + CODE_LENGTH + " caractères\r\n";
		}
		
		if (designation == null || designation.trim().length() <= 0)
		{
			message += "La désignation de la pizza doit être renseignée\r\n";
		}
		
		if (price == null || price.doubleValue() < PRICE_MIN || price.doubleValue() > PRICE_MAX)
		{
			message += "Le prix doit être renseigné et compris entre " + PRICE_MIN + " et " + PRICE_MAX + "\r\n";
		}
		
		if (categorie == null)
		{
			message += "La catégorie doit être renseignée";
		}
		
		if (message != null && message.trim().length() > 0)
		{
			throw new StockageException(message);
		}
	}
	

}
