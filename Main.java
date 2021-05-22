package tugas2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	static ArrayList<Item> ListOfItems = new ArrayList<Item>();
	static ArrayList<Payment> ListOfpayments = new ArrayList<Payment>();
	static Scanner scan = new Scanner(System.in);
	
	public static void seedData(){
		ListOfItems.add(new Item("Kulkas","Elektronic",4800000));
		ListOfItems.add(new Item("TV","Elektronic",1280000));
		ListOfItems.add(new Item("Laptop","Computer",6000000));	
		ListOfItems.add(new Item("PC","Computer",12000000));
	}
	public static void printItem(Item item){
		System.out.println("Nama : "+item.getName());
		System.out.println("Tipe : "+item.getType());
		System.out.println("Harga : "+item.getPrice());
	}
	public static void pembayaran (){
		System.out.println("-----------Tipe Pembayaran-----------");
		System.out.println("1. Cash\n2. Credit");
		System.out.print("Pilih : ");
	}
	public static void main(String[] args){
		int opt= 0;
		int produk,transaksi; 
		int tipe,uang,kredit = 0; String bayar;
		int uangupdate = 0, pay; 
		seedData();
		do{
			System.out.println("-----Program Toko Elektronik-----");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Pesanan");
			System.out.println("0. Keluar");
			System.out.print("Pilihan : ");
			opt = scan.nextInt();
			switch (opt){
				case 1:
				System.out.println("\n-----Daftar Barang-----");
				for(int i = 0; i < ListOfItems.size();i++){
					System.out.println("No : "+(i+1));
					printItem(ListOfItems.get(i));
					System.out.println("------------------------------------");
				}
				System.out.print("Pilih : ");
				produk = scan.nextInt();
				printItem(ListOfItems.get(produk-1));
				pembayaran();
				tipe = scan.nextInt();

				if (tipe == 1){
					System.out.print("Bayar (Y/N) : ");
					ListOfpayments.add(new Cash (ListOfItems.get(produk-1)));
					scan.nextLine();
					bayar = scan.nextLine();
					if (bayar.equalsIgnoreCase("Y")){
						System.out.println("Harga Pembayaran : "+ListOfpayments.get(ListOfpayments.size()-1).pay());
						uang = 0;
						while (uang < ListOfItems.get(produk-1).getPrice()){
							System.out.print("Bayar : ");
							uang = scan.nextInt();						
						}	
						System.out.println("Transaksi telah dibayar lunas!");
					}else if (bayar.equalsIgnoreCase("n")){
						System.out.println("Transaksi telah disimpan");
					}
				}else if (tipe == 2){
					kredit = 0;
					while (kredit != 3 && kredit != 6 && kredit != 9 && kredit != 12){	
						System.out.print("Lama Cicilan (3/6/9/12): ");
						kredit = scan.nextInt();
					}  
					ListOfpayments.add(new Credit (ListOfItems.get(produk-1),kredit));
					System.out.println("Harga Pembayaran : "+ListOfpayments.get((ListOfpayments.size()-1)).pay());
					uang = 0;
					while (uang < ListOfpayments.get(ListOfpayments.size()-1).pay()){
						System.out.print("Bayar : ");
						uang = scan.nextInt();
					}
					System.out.println("Transaksi telah dibayar");
				}
				break;
				case 2 :
				for (int i = 0; i < ListOfpayments.size(); i++){
					System.out.println("No \t\t\t: "+(i+1));
					System.out.println("Nama \t\t\t: "+ListOfpayments.get(i).getItemName());
					System.out.println("Tipe \t\t\t: "+ListOfpayments.get(i).getItem().getType());
					System.out.println("Status \t\t\t: "+ListOfpayments.get(i).getStatus());
					System.out.println("Sisa Pembayaran \t: "+ListOfpayments.get(i).getRemainingAmount());
					System.out.println("-------------------------------------------------------------");
				}
				System.out.print("Pilih no transaksi : ");
				transaksi = scan.nextInt();
				if (transaksi > 0 && transaksi <= ListOfpayments.size()){
					System.out.println("Nama \t\t\t: "+ListOfpayments.get(transaksi-1).getItemName());
					System.out.println("Tipe \t\t\t: "+ListOfpayments.get(transaksi-1).getItem().getType());
					System.out.println("Status \t\t\t: "+ListOfpayments.get(transaksi-1).getStatus());
					System.out.println("Sisa Pembayaran \t: "+ListOfpayments.get(transaksi-1).getRemainingAmount());
					pay = ListOfpayments.get(transaksi-1).pay();
					System.out.println("Harga Pembayaran \t: "+pay);
					
					if (pay > 0){
						uangupdate = 0;
						while (uangupdate < pay){
						System.out.print("Bayar : ");
						uangupdate = scan.nextInt();
					}
					if (uangupdate >= ListOfpayments.get(transaksi-1).pay() && ListOfpayments.get(transaksi-1).getRemainingAmount() == ListOfpayments.get(transaksi-1).pay()){
							ListOfpayments.get(transaksi-1).payItem();
							System.out.println("Transaksi telah dibayar lunas");
					}else{
							ListOfpayments.get(transaksi-1).payItem();
							System.out.println("Transaksi telah dibayar");
						}	
					}
				}else{
					System.out.println("Nomor transaksi tidak ada");
				}
			}	
		}while (opt != 0);
	}
}
