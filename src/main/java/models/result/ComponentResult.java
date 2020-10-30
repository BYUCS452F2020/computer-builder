package models.result;

import models.Component;

import java.util.List;

/**
 * Contains all the necessary fields for a login response. All fields are required.
 */
//TODO add component model

public class ComponentResult {
    public ComponentResult(boolean success, List<Component> components) {
        this.success = success;
        this.components = components;
    }

    public ComponentResult(boolean success, String message) {
        this.success = success;
        this.error = message;
    }

    List<Component> components = null;
    boolean success;
    String error = null;

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
