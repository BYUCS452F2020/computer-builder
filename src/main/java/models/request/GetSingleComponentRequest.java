package models.request;

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
