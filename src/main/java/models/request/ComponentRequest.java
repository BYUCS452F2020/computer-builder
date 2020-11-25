package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user login. All variables are required.
 */
public class ComponentRequest {
    public ComponentRequest(String componentType, String cpuFamily, int performanceRating, int maxPrice, int tdp) {
        this.componentType = componentType;
        this.cpuFamily = cpuFamily;
        this.maxPrice = maxPrice;
        this.performanceRating = performanceRating;
        this.tdp = tdp;
    }

    @SerializedName("componentType")
    String componentType;
    @SerializedName("cpuFamily")
    String cpuFamily;
    @SerializedName("performanceRating")
    int performanceRating;
    @SerializedName("maxPrice")
    int maxPrice;
    @SerializedName("tdp")
    int tdp;

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }
}

