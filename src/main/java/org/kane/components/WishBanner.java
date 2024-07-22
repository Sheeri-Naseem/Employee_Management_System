package org.kane.components;

import org.apache.tapestry5.annotations.Parameter;

public class WishBanner {

    @Parameter
    private String gender;

    @Parameter
    private String message;

    public String getBannerColor() {
        System.out.println("Inside WishBanner.java : "+gender + "\n" + message);
        return "F".equalsIgnoreCase(gender) ? "#FFCCE5" : "#CCFFFF";
    }

    public String getMessage() {
        return message;
    }
}
