package models;

import java.util.Objects;

public class Component {

    private String componentID;
    private String componentName;
    private String componentType;
    private String manufacturer;
    private int performanceRating;
    private double price;
    private String cpuFamily;
    private int tdp;
    private String imageURL;

    public Component(String componentID, String componentName, String componentType, String manufacturer,
                     int performanceRating, double priceRating, String cpuFamily, int tpd) {
        this.componentID = componentID;
        this.componentName = componentName;
        this.componentType = componentType;
        this.manufacturer = manufacturer;
        this.performanceRating = performanceRating;
        this.price = priceRating;
        this.cpuFamily = cpuFamily;
        this.tdp = tpd;
        this.imageURL = null;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    public int getTpd() {
        return tdp;
    }

    public void setTpd(int tpd) {
        this.tdp = tpd;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return performanceRating == component.performanceRating &&
                price == component.price &&
                tdp == component.tdp &&
                Objects.equals(componentID, component.componentID) &&
                Objects.equals(componentName, component.componentName) &&
                Objects.equals(componentType, component.componentType) &&
                Objects.equals(manufacturer, component.manufacturer) &&
                Objects.equals(cpuFamily, component.cpuFamily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentID, componentName, componentType, manufacturer, performanceRating, price, cpuFamily, tdp);
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentID='" + componentID + '\'' +
                ", componentName='" + componentName + '\'' +
                ", componentType='" + componentType + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", performanceRating=" + performanceRating +
                ", priceRating=" + price +
                ", cpuFamily='" + cpuFamily + '\'' +
                ", tpd=" + tdp +
                '}';
    }
}
