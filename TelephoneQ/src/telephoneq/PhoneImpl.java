/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telephoneq;


public class PhoneImpl extends Phone {

    public PhoneImpl(String name, boolean international) {
        super(name, international);
    }
    
    public PhoneImpl()
    {
    this("Rex",true);
    }
}
