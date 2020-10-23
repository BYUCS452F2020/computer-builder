package models.result;

import models.Component;

/**
 * A result that has a single component
 */

public class GetSingleComponentResult extends Result {

    private Component component;
    public GetSingleComponentResult(boolean success, Component component) {
        super(success);
        this.component = component;
    }

    public GetSingleComponentResult(boolean success, String message) {
        super(success, message);
    }

    @Override
    public String toString() {
        return "GetSingleComponentResult{" +
                "component = " + component +
                '}';
    }
}
