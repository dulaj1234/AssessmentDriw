/**
 * 
 */
package com.tmw.assessment.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmw.assessment.dto.ItemMaster;
import com.tmw.assessment.dto.ItemsDTO;

/**
 * @author dulajsam
 * Oct 2, 2021
 */


@RestController
public class ItemController {
	
	public ItemController() {
		intItemMaster();
	}
    List<ItemMaster> itemMasters = new ArrayList<ItemMaster>();
    
	private void intItemMaster(){
		itemMasters.add(new ItemMaster(1, 20, 175));
		itemMasters.add(new ItemMaster(2, 5, 825));
	}
    
	//get set of items
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/itemlist", produces = "application/json")
	public List<ItemMaster> getProductList() {		
		return itemMasters;		
	}
    
	//add item data for the calculation
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "/calculate", produces = "application/json")
	public ResponseEntity<String> totalPrice(@RequestBody List<ItemsDTO> dtos) {
	//public String totalPrice(@RequestBody List<ItemsDTO> dtos) {
		//initalizing the reference data
		if(itemMasters.size()==0)
		intItemMaster();
		
		HashMap<ItemMaster, Integer> cartonDetails = new HashMap<>();
		double totalAmount = 0;
		int totalCartons = 0;
		//data set in the array list
		for (ItemsDTO itemsDTO : dtos) {

			for (ItemMaster itemMaster : itemMasters) {
				//item ID match

				if(itemsDTO.getItemId()==itemMaster.getItemMaster()){
					int NoOfCartons = 0;
					double itemPrice = itemMaster.getCartonPrice()/itemMaster.getItemsPerCarton();

					//getting the number of single items
					int singleItems = itemsDTO.getUnits() % itemMaster.getItemsPerCarton();

					double singleItemSellingPrice = 0;
					double totalsingleItemPrice = 0;
					
					if(singleItems==0){
						NoOfCartons = itemsDTO.getUnits() / itemMaster.getItemsPerCarton();
					}else{
						// single item price with 30%
						singleItemSellingPrice = (itemPrice*130)/100;						
						totalsingleItemPrice = singleItemSellingPrice*singleItems;
						NoOfCartons = (itemsDTO.getUnits()-singleItems) / itemMaster.getItemsPerCarton();
						
						// -- 30% for Carton
						/*singleItemSellingPrice = (itemMaster.getCartonPrice()*1.3);
						totalsingleItemPrice = itemPrice*singleItems;
						totalsingleItemPrice = totalsingleItemPrice + singleItemSellingPrice;*/
					}
					if(NoOfCartons>0)
						cartonDetails.put(itemMaster, NoOfCartons);

						totalCartons = totalCartons + NoOfCartons;
					
						totalAmount = totalAmount + totalsingleItemPrice;
				}
			}
		}				
		
		//Adding discounts to the selected items seperately
		double totalCartoonPrice = 0;
		for (ItemMaster key : cartonDetails.keySet()) {
			int noOfCarton = cartonDetails.get(key);
			double cartonPrice = 0;
			if(totalCartons >= 3){
				cartonPrice = (key.getCartonPrice()*noOfCarton)*0.9;
			}else{
				cartonPrice = (key.getCartonPrice()*noOfCarton);
			}
			totalCartoonPrice = totalCartoonPrice + cartonPrice;
		}
		double billAmount = totalCartoonPrice + totalAmount;
		
		return new ResponseEntity<String>(String.valueOf(billAmount), HttpStatus.OK);
		//return "Total Price : "+ String.valueOf(billAmount);
	}
}
