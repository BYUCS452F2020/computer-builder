package models.request;

/**
 * A request for a certain component based off id
 */

public class GetSingleComponentRequest {
    private String componentId;

    public GetSingleComponentRequest(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
