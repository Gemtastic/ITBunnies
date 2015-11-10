/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.services;

import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.entities.database.Webshop;
import com.gemtastic.model.entities.database.Website;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import javax.ejb.Stateless;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class ProductService {
    
    public String getProductType(ProductInterface product) {
        String type = product.getProduct().getClass().toString();
        return type;
    }
    
    public int getEstTime(ProductInterface product){
        int estTime = 0;
        String type = getProductType(product);
        
        switch(type.toLowerCase()){
            case "graphic":
                estTime = 5;
                break;
            case "webshop":
                estTime = 25;
                break;
            case "webstore":
                estTime = 75;
                break;
            default:
                break;
        }
        return estTime;
    }
    
    public int getPrice(ProductInterface product){
        String type = getProductType(product);
        int price = 0;
        
        switch(type.toLowerCase()){
            case "graphic":
                Graphic g = (Graphic) product;
                price = g.getPrice();
                break;
            case "webshop":
                Webshop wSh = (Webshop) product;
                price = wSh.getPrice();
                break;
            case "webstore":
                Website wSi = (Website) product;
                price = wSi.getPrice();
                break;
            default:
                break;
        }
        return price;
    }
    
}
