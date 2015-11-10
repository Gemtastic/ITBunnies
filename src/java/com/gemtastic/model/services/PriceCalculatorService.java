/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.services;

import com.gemtastic.model.entities.resourcewrappers.ProductWrapper;
import com.gemtastic.model.entities.resourcewrappers.QuoteWrapper;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class PriceCalculatorService {
    
    private final int hourCost = 200;
    
    @EJB
    ProductService ps;
    
    public QuoteWrapper calculatePrice(QuoteWrapper quote) {
        int price = 0;
        List<ProductWrapper> products = quote.getProducts();
        
        for(ProductWrapper pw : products){
            ProductInterface pi = pw.getProduct();
            price += (ps.getEstTime(pi) * hourCost);
        }
        
        quote.getQuote().setQuotesum(price);
        return quote;
    }
    
    public QuoteWrapper calculateEstTime(QuoteWrapper quote) {
        int estTime = 0;
        List<ProductWrapper> products = quote.getProducts();
        
        for(ProductWrapper pw : products){
            ProductInterface pi = pw.getProduct();
            estTime += ps.getEstTime(pi);
        }
        // quote.getQuote().getTimeEstimate();               //future implementation
        return quote;
    }
}
