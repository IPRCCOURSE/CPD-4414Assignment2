/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Hongliang Zhang <c0640045@mylambton.ca>
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() {
        OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
       
       
        orderQueue.add(order);
       
            
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
      }catch (Exception e)
      {
          assertTrue(false);
          
          
      }
    }
    
    @Test
    public void testWhenCustomerIDNoExistsThenThrowException() {
       
        boolean didThrown = false;
        try{
        Order order = new Order(null, "ABC Construction");
        }catch(Exception e)
        {
            didThrown = true;
        }
   
           
        assertTrue(didThrown);
    }
    
    @Test
    public void testWhenCustomerNameNoExistesThenThrowException() {
         boolean didThrown = false;
        try{
        Order order = new Order("CUST00001", null);
        }catch(Exception e)
        {
            didThrown = true;
        }
   
           
        assertTrue(didThrown);
    }
    
    @Test
    public void testWhenCustomerIDNoExistesandCustomerNameNoExistesThenThrowException() {
         boolean didThrown = false;
        try{
        Order order = new Order(null, null);
        }catch(Exception e)
        {
            didThrown = true;
        }
   
           
        assertTrue(didThrown);
    }
    
    @Test
    public void testWhenOrderwithoutPurchaselistsThenThrowException() {
         OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
      //  order.addPurchase(new Purchase("PROD0004", 450));
     //   order.addPurchase(new Purchase("PROD0006", 250));
       
        boolean didThrown = false;
        
        try{
          orderQueue.add(order);
        }catch(Exception e)
        {
            didThrown = true;
        }
            
        assertTrue(didThrown);
       
      }catch (Exception e)
      {
          assertTrue(false);
          
          
      }
    }
    
    @Test
    public void testGetTheEearlistUnProcessedOrder() {
         OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
          Order order2 = new Order("CUST00002", "DEF Construction");
        order2.addPurchase(new Purchase("PROD0104", 450));
        order2.addPurchase(new Purchase("PROD0306", 250));
        
        orderQueue.add(order2);
         
      
        assertTrue(orderQueue.getEarlistUnProcessed() == order);    
       
       
      }catch (Exception e)
      {
          assertTrue(false);
          
          
      }
    }
    
    @Test
    public void testGetTheEearlistUnProcessedOrderNull() {
         OrderQueue orderQueue = new OrderQueue();
       
      
        assertTrue(orderQueue.getEarlistUnProcessed() == null);    
       
       
      
    }
    
   
    
     @Test
    public void testOrderProcessWithTimeReceivedWithInStock() {
           
       OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
       
       
        orderQueue.add(order);
       
        order.processit();
        
        long expResult = new Date().getTime();
        long result = order.getTimeProcessed().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }
    
     @Test
    public void testOrderProcessWithNoTimeReceived() {
         
        boolean didThrow = false;
       OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
       
       
        //orderQueue.add(order);
      // order.setTimeReceived(null);
       try{
        order.processit();
       }catch(Exception e)
        {
           didThrow = true;   
           }
        
      assertTrue(didThrow);
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }

   //fullfill a request......................
     @Test
    public void testOrderFulfilledWithTimeProcessedWithInStock() {
           
       OrderQueue orderQueue = new OrderQueue();
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
       
       
        orderQueue.add(order);
       
        order.processit();
        
        order.fulfillit();
        
        long expResult = new Date().getTime();
        long result = order.getTimeFulfilled().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }
    
     @Test
    public void testOrderFulfilledWithoutTimeProcessedThenThrowException() {
           
       OrderQueue orderQueue = new OrderQueue();
       boolean didThrow = false;
       
       
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
       
       
        orderQueue.add(order);
       
       
        try{
        order.fulfillit();
        }catch(Exception e)
        {
            didThrow = true;
        }
        
       
        assertTrue(didThrow);
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }
    
    @Test
    public void testOrderFulfilledWithoutTimeReceiveddThenThrowException() {
           
       OrderQueue orderQueue = new OrderQueue();
       boolean didThrow = false;
       
       
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
       
       
        orderQueue.add(order);
        order.processit();
        order.setTimeReceived(null);
       
        try{
        order.fulfillit();
        }catch(Exception e)
        {
            didThrow = true;
        }
        
       
        assertTrue(didThrow);
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }
    
     @Test
    public void testOrdersForReportToJsons() {
           
       OrderQueue orderQueue = new OrderQueue();
       
       
       
        try{
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("0004", 450));
        order.addPurchase(new Purchase("0006", 250));
           
        orderQueue.add(order);
        order.processit();
        order.fulfillit();
        
       
        Order order2 = new Order("CUST00002", "DEF Construction");
        order2.addPurchase(new Purchase("0001", 450));
        order2.addPurchase(new Purchase("0002", 250));
        
        orderQueue.add(order2);
        
        order2.processit();
        order2.fulfillit();
        
        
        String expectResult = "{ “orders” : [" + JsonHelper.toJSON(order).toString() + "," + JsonHelper.toJSON(order2).toString() + "] }";
        
        assertTrue(expectResult.equals(orderQueue.getReport()));
        
        
        
      }catch (Exception e)
      {
          e.printStackTrace();
          assertTrue(false);
          
          
      }
       
      
    }
    

}
