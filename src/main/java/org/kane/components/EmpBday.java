package org.kane.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class EmpBday {

    @Parameter
    private String gender;

    @Parameter
    private String name;

    @Property
    private String gen;

    @Property
    private String message;

        void setupRender() {
            message = "Happy Birthday " +name +" !!  Hope you have a great day! :) ";
            gen = gender;
            System.out.println("Inside EmpBday.java : "+gen + "\n" + message);
    }


}
