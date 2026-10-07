public class Product {
	
	String id;
	String name;
	String category;
	double price;
	String currency;
	double quantity;
	
	public Product(String id, String name, String category, double price, String currency, double quantity) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this. currency = currency;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Product{id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", currency=" + currency + ", quantity=" + quantity + "}";
	}
}