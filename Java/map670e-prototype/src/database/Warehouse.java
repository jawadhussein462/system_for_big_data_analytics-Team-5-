package database;

import java.util.Hashtable;
import java.util.Objects;

public class Warehouse implements DatabaseConstants {
	
	private final int w_id ;
	private String w_name ;		// useless ? t20
	private String w_street1 ;	// useless ? t20
	private String w_street2 ;	// useless ? t20
	private String w_city ;		// useless ? t20
	private String w_state ;		// useless ? t2
	private String w_zip ;		// useless ? t9
	private double w_tax ;				// useless ?
	private double w_ytd ;				// useless ?
	private Hashtable<Integer, District> w_districts ;
	private Hashtable<Integer, Stock> w_stocks ;
	
	public Warehouse(int w_id) {
		
		this.w_id = w_id;
		
		// "Useless" attribute are initialized (but important to simulate a real database)
		this.w_name = "Warehouse" + w_id ;
		this.w_street1 = streets1[(int) (Math.random() * 3)] ;
		this.w_street2 = streets2[(int) (Math.random() * 3)] ;
		this.w_city = cities[(int) (Math.random() * 3)] ;
		this.w_state = Integer.toString((int) Math.random() * 99) ;
		this.w_zip = "9512" + Integer.toString((int) Math.random() * 9) ;
		this.w_tax = w_tax_min + Math.random() * (w_tax_max - w_tax_min);
		this.w_ytd = Math.random() * 365; 
		
		this.w_districts = new Hashtable<Integer, District>() ;
		this.w_stocks = new Hashtable<Integer, Stock>() ;
	}
	
	//Copy Constructor
	public Warehouse(Warehouse w) {
		
		this(w.w_id);
		
		this.w_name = w.w_name;
		this.w_street1 = w.w_street1;
		this.w_street2 = w.w_street2;
		this.w_city = w.w_city;
		this.w_state = w.w_state;
		this.w_zip = w.w_zip;
		this.w_tax = w.w_tax;
		this.w_ytd = w.w_ytd;
		
		
		this.w_districts.putAll(w.w_districts);
		
	}
	
	public void Update(Warehouse w) {
		
		
		this.w_name = w.w_name;
		this.w_street1 = w.w_street1;
		this.w_street2 = w.w_street2;
		this.w_city = w.w_city;
		this.w_state = w.w_state;
		this.w_zip = w.w_zip;
		this.w_tax = w.w_tax;
		this.w_ytd = w.w_ytd;
		
		
		this.w_districts.putAll(w.w_districts);
		
	}
	
	public Hashtable<Integer, District> populate_district(int num) {
		// If we want to avoid the creation in cascade of the instances (otherwise, put in constructor)
		for (int i = 0; i < num; i++) {
			District district = new District(i, this.w_id) ;
			this.w_districts.put(district.hashCode(), district) ; 
		}
		return this.w_districts ;
	}
	
	public Hashtable<Integer, Stock> populate_stock(int num) {
		// If we want to avoid the creation in cascade of the instances (otherwise, put in constructor)
		for (int i = 0; i < num; i++) {
			Stock stock = new Stock(i, this.w_id) ;
			this.w_stocks.put(stock.hashCode(), stock) ; 
		}
		return this.w_stocks ;
	}
	
	public Hashtable<Integer, Item> populate_item(int num) {
		Hashtable<Integer, Item> items = new Hashtable<Integer, Item>() ;
		for (int i = 0; i < num; i++) {
			Item item = new Item(i) ;
			items.put(item.hashCode(), item) ; 
		}
		return items ;
	}
	

	public void setStreet1(String stg) {
		this.w_street1 = stg ;
	}
	public double get_w_tax() 
	{
		return this.w_tax;
	}
	
	public int getId() 
	{
		return this.w_id;
	}
	
	//w_id is the primary key
		@Override
		public int hashCode() {
			return Objects.hash(w_id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Warehouse)) {
				return false;
			}
			Warehouse other = (Warehouse) obj;
			return w_id == other.w_id;
		}
		
		/* TO delete if not needed, only used as an example */
		@Override
		public String toString() {
			return "Warehouse [w_id=" + w_id + ", w_name=" + w_name + ", w_street1=" + w_street1 + ", w_street2="
					+ w_street2 + ", w_city=" + w_city + ", w_state=" + w_state + ", w_zip=" + w_zip + ", w_tax=" + w_tax
					+ ", w_ytd=" + w_ytd + ", w_districts=" + w_districts + "]";
		}
	
}
