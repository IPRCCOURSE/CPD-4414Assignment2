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

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author Hongliang Zhang <c0640045@mylambton.ca>
 */
public class OrderQueue {
    Queue<Order> orderQueue = new ArrayDeque<>();
    
    public void add(Order order) throws Exception{
       
        if (order.getListOfPurchases().isEmpty()) throw new Exception("No puchurse list");
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }
    
    public Order getEarlistUnProcessed(){
        Order o = null;
        if (!orderQueue.isEmpty())
        {
            Order order = orderQueue.poll();
            while(order != null)
            {
               
                if (order.getTimeProcessed() == null)
                {
                    o = order;
                    break;
                       
                    
                    
                }
                
                
               order = orderQueue.poll();
            }
            
            
            
        }
        
        
        
        
        return o; 
        
    }
    
    
    public String getReport()
    {
        String strJson = "{ “orders” : [";
        Order order = orderQueue.poll();
        
        while(order != null)
            {
               
               strJson += (JsonHelper.toJSON(order).toString() + ",");
                
                
               order = orderQueue.poll();
            }
            
        if (strJson.endsWith(",")) strJson = strJson.substring(0, strJson.length()-1);
        
        strJson +=  "] }";
        
        
        
        
      return strJson;
        
    }
}
