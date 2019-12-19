/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telephoneq;

public abstract class Phone {
 private String name;
 private boolean international;
 
  public Phone(String name, boolean international) {
  this.name = name;
  this.international = international;
  }
  
  public void setInternational()
  {
  this.international = international;
  }
  
  public boolean getInternational(){
   
  return international;
   
   
  }
   
   @Override
    public String toString() {
        return name + " " + getInternational();
    }
 
} 
