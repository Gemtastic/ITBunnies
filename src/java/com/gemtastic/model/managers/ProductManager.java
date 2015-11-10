package com.gemtastic.model.managers;

import com.gemtastic.model.entities.Product;
import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.entities.database.Graphicstype;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.database.Webshop;
import com.gemtastic.model.entities.database.Website;
import com.gemtastic.model.entities.resourcewrappers.ProductWrapper;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import com.gemtastic.model.entities.resourcewrappers.products.GraphicWrapper;
import com.gemtastic.model.entities.resourcewrappers.products.WebshopWrapper;
import com.gemtastic.model.entities.resourcewrappers.products.WebsiteWrapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class ProductManager {
    
    @PersistenceContext
    private EntityManager em;
    
    private final int otherProducts = 2;

    public List<ProductWrapper> getAllProductsOff(Quote quote) {
        List<ProductWrapper> wrapped = null;
        Quote q = null;
        try {
            q = em.find(Quote.class, quote.getId());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        
        if(q != null) {
            List<ProductInterface> graphics = wrapGraphics(q.getGraphicList());
            List<ProductInterface> webshops = wrapWebshop(q.getWebshopList());
            List<ProductInterface> websites = wrapWebsite(q.getWebsiteList());
            List<ProductInterface> products = new ArrayList<>();
            
            products.addAll(graphics);
            products.addAll(webshops);
            products.addAll(websites);
            wrapped = wrapProducts(products);
        }
        return wrapped;
    }

    public List<Product> getAllProducts() {
        List<Product> wrapped = null;
        List<Graphicstype> gTypes = null;
        try {
            gTypes = em.createNamedQuery("Graphicstype.findAll", Graphicstype.class).getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        
        if(gTypes != null){
            Product website = new Product("Website", 1, null);
            Product webshop = new Product("Webshop", 2, null);
            wrapped.add(webshop);
            wrapped.add(website);
            
            for(Graphicstype gt : gTypes){
                wrapped.add(new Product(gt.getType(), gTypes.size() + 1, null));
            }
        }
        
        return wrapped;
    }

    
    private List<ProductWrapper> wrapProducts(List<ProductInterface> products){
        List<ProductWrapper> wrapped = new ArrayList<>();
        for(ProductInterface pi : products){
            wrapped.add(new ProductWrapper(pi, null));
        }
        return wrapped;
    }
    
    private List<ProductInterface> wrapGraphics(List<Graphic> graphics){
        List<ProductInterface> wrapped = new ArrayList<>();
        for(Graphic g : graphics){
            wrapped.add(new GraphicWrapper(g, null));
        }
        return wrapped;
    }
    
    private List<ProductInterface> wrapWebshop(List<Webshop> webshops){
        List<ProductInterface> wrapped = new ArrayList<>();
        
        for(Webshop w : webshops){
            wrapped.add(new WebshopWrapper(w, null));
        }
        return wrapped;
    }
    
    private List<ProductInterface> wrapWebsite(List<Website> websites){
        List<ProductInterface> wrapped = new ArrayList<>();
        
        for(Website w : websites){
            wrapped.add(new WebsiteWrapper(w, null));
        }
        return wrapped;
    }

    public Product getProduct(int id) {
        Graphicstype gt = null;
        Product p = null;
        
        if(id <= otherProducts){
            switch(id){
                case 1:
                    return new Product("Website", id, null);
                case 2:
                    return new Product("Webshop", id, null);
                default:
                    return null;
            }
        } else {
            try{
                gt = em.find(Graphicstype.class, id - otherProducts);
            }catch(PersistenceException e) {
                e.printStackTrace();
            }

            if(gt != null){
                p = new Product(gt.getType(), gt.getId() + otherProducts, null);
            } else {
                return p;
            }
            return null;
        }
    }
}
