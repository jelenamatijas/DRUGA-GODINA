class Product{
	String id;
	String name;	
	String category;
	String price;
	String currency;
	String quantity;
	
	public Product(String id, String name, String category, String price, String currency, String quantity){
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.price = price;
		this.currency = currency;
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return "Product{ID: " + id + " Name: " + name + " Category: " + category + " Price: " + price + " Currency: " + currency + " Quantity: " + quantity + "}";
	}
}