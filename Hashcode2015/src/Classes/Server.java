package Classes;

import javax.swing.Spring;

public class Server {
	private int id;
	private int capacity;
	private int size;
	private int idPool;
	private String descr;
	
	public Server(int id, int size,int capacity) {
		this.id = id;
		this.capacity = capacity;
		this.size = size;
	}
	
	public int getCapacity() {
		return capacity;
	}
	private void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	private int getSize() {
		return size;
	}
	private void setSize(int size) {
		this.size = size;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public void setIdPool(int idPool) {
		this.idPool = idPool;
	}
	
	public int getIdPool() {
		return idPool;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return "Server #"+id+" of Pool"+idPool+": Num.of Slots"+size +" and Capacity of"+capacity+  " -- "+descr;
	}
}
