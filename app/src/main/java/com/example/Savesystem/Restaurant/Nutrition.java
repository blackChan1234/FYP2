package Restaurant;

public class Nutrition {

	private String name;
	private float quantity;
	private image img;
	public String getName() {
		return name;
	}

	public float getQuantity() {
		return quantity;
	}

	public image getImg() {
		return img;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param q
	 */
	public void setQuantity(float q) {
		this.quantity = q;
	}

	/**
	 * 
	 * @param name
	 * @param quantity
	 */
	public Nutrition(String name, float quantity) {
		this.name = name;
		this.quantity = quantity;
		this.img = new image();
	}
	public Nutrition(String name, float quantity, String imagePath) {
		this.name = name;
		this.quantity = quantity;
		this.img = new image(imagePath);
	}
}