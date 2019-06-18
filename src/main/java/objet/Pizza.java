package objet;

public class Pizza
{

	private String code = null;
	private String designation = null;
	private double prix = Float.NaN;

	public Pizza(String code, String designation, double prix) {
		super();
		this.code = code;
		this.designation = designation;
		this.prix = prix;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Pizza [code=" + code + ", designation=" + designation + ", prix=" + prix + "]"  + "\r\n" + "\r\n";
	}
	
	
	
}
