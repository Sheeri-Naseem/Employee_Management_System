package org.kane.components;

import org.apache.tapestry5.BindingConstants;

import org.apache.tapestry5.annotations.*;
import org.kane.entities.Employee;

public class Header {

    @Property
    @Parameter (required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String companyName = " KANE SOLUTIONS ";

    @Property
    private String companyAddress = "Central Business Centre\n" +
            "Mdina Road\n" +
            "Zebbug ZBG 9015\n" +
            "Malta ";

    @Property
    private String phoneNo = "+356 2247 1900";

    @Property
    private String emailId = "maltaoffice@kanesolutions.com";

    @SessionState
    private Employee loggedInEmployee;
}

