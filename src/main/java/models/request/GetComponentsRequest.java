package models.request;

/**
 * A request to retrieve a list of components of a certain type possibly based off certain conditions
 */
public class GetComponentsRequest {
    private String componentType;
    private int budgetPrice;
    private int performanceRating;
    private String cpuFamily;

    public GetComponentsRequest(String componentType, int budgetPrice, int performanceRating, String cpuFamily) {
        this.componentType = componentType;
        this.budgetPrice = budgetPrice;
        this.performanceRating = performanceRating;
        this.cpuFamily = cpuFamily;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public int getBudgetPrice() {
        return budgetPrice;
    }

    public void setBudgetPrice(int budgetPrice) {
        this.budgetPrice = budgetPrice;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    @Override
    public String toString() {
        return "GetComponentsRequest{" +
                "componentType = '" + componentType + '\'' +
                ", priceRange = '" + budgetPrice + '\'' +
                ", performanceRating = " + performanceRating +
                ", cpuFamily = '" + cpuFamily + '\'' +
                '}';
    }
}

