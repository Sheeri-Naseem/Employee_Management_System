package org.kane.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class Header {

    @Property
    @Parameter (required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block style;

    @Property
    private final String companyName = " KANE SOLUTIONS ";

    @Property
    private final String companyAddress = "Central Business Centre\n" +
            "Mdina Road\n" +
            "Zebbug ZBG 9015\n" +
            "Malta ";

    @Property
    private final String phoneNo = "+356 2247 1900";

    @Property
    private final String emailId = "maltaoffice@kanesolutions.com";

}
