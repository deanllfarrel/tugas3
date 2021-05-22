package tugas2;

public class Item{
	private String nama;
	private String tipe;
	private int harga;
	private int hargaori;
	
	public Item (){}
	public Item (String nama, String tipe, int harga){
		this.nama= nama;
		this.tipe = tipe;
		this.harga = harga;
		this.hargaori = harga;
	}
	public String getName() {return this.nama;}
	public String getType() {return this.tipe;}
	public int getPrice() {return this.harga;}
	public int getOriPrice() {return this.hargaori;}
	public void payitem(int payment) {this.harga -= payment;}
}