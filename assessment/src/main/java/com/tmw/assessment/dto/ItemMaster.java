/**
 * 
 */
package com.tmw.assessment.dto;

/**
 * @author dulajsam
 * Oct 2, 2021
 */
public class ItemMaster {
	private int itemMaster;
	private int itemsPerCarton;
	private double cartonPrice;
	
	public ItemMaster(int itemMaster, int itemsPerCarton, double cartonPrice) {
		super();
		this.itemMaster = itemMaster;
		this.itemsPerCarton = itemsPerCarton;
		this.cartonPrice = cartonPrice;
	}
	
	public int getItemMaster() {
		return itemMaster;
	}
	public void setItemMaster(int itemMaster) {
		this.itemMaster = itemMaster;
	}
	public int getItemsPerCarton() {
		return itemsPerCarton;
	}
	public void setItemsPerCarton(int itemsPerCarton) {
		this.itemsPerCarton = itemsPerCarton;
	}
	public double getCartonPrice() {
		return cartonPrice;
	}
	public void setCartonPrice(double cartonPrice) {
		this.cartonPrice = cartonPrice;
	}
}
