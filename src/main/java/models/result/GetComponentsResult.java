package models.result;

import models.Component;

import java.util.List;

public class GetComponentsResult extends Result {
    private List<Component> componentList;

    public GetComponentsResult(boolean success, List<Component> componentList) {
        super(success);
        this.componentList = componentList;
    }

    public GetComponentsResult(boolean success, String message) {
        super(success, message);
    }

    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    @Override
    public String toString() {
        return "GetComponentsResult{" +
                "componentList = " + componentList +
                '}';
    }
}
