/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mumsnet;

/**
 *
 * @author Ed
 */
public class Car  {

    private final String registration;
    private final String name;

    public Car(String name, String registration) {

        this.name=name;
        this.registration = registration;

    }

    public String getRegistration() {
        return registration;
    }
}
