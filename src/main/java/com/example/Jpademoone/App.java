package com.example.Jpademoone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.Jpademoone.entity.Product;




/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	        // Create EntityManagerFactory and EntityManager
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("product_details");
    	        EntityManager em = emf.createEntityManager();

    	        // Begin transaction
    	        em.getTransaction().begin();

    	        // Persist some sample data
    	        persistSampleData(em);

    	        // Commit transaction
    	        em.getTransaction().commit();

    	        // Query products with price greater than 50
    	        List<Product> expensiveProducts = findExpensiveProducts(em, 500.0);

    	        // Print the results
    	        System.out.println("Expensive Products:");
    	        for (Product product : expensiveProducts) {
    	            System.out.println(product.getName() + " - $" + product.getPrice());
    	        }

    	        // Close EntityManager and EntityManagerFactory
    	        em.close();
    	        emf.close();
    	    }

    	    private static void persistSampleData(EntityManager em) {
    	        Product product1 = new Product();
    	        product1.setName("Laptop");
    	        product1.setPrice(800.0);

    	        Product product2 = new Product();
    	        product2.setName("Smartphone");
    	        product2.setPrice(600.0);

    	        Product product3 = new Product();
    	        product3.setName("Camera");
    	        product3.setPrice(120.0);

    	        em.persist(product1);
    	        em.persist(product2);
    	        em.persist(product3);
    	    }

    	    private static List<Product> findExpensiveProducts(EntityManager em, double minPrice) {
    	        String jpql = "SELECT p FROM Product p WHERE p.price > :minPrice";
    	        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
    	        query.setParameter("minPrice", minPrice);
    	        return query.getResultList();
    	    }
    	

    	
}
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
