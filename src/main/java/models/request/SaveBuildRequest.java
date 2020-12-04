package models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Contains all the fields necessary for a successful user registration. All variables are required.
 */
public class SaveBuildRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("cpu")
    public String cpu;
    @SerializedName("motherboard")
    public String motherboard;
    @SerializedName("gpu")
    public String gpu;
    @SerializedName("ram")
    public String ram;
    @SerializedName("storage")
    public String storage;
    @SerializedName("psu")
    public String psu;
    @SerializedName("cooler")
    public String cooler;
    @SerializedName("pc_case")
    public String pc_case;
    @SerializedName("build_name")
    public String build_name;



    public SaveBuildRequest(){};


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getPsu() {
        return psu;
    }

    public void setPsu(String psu) {
        this.psu = psu;
    }

    public String getCooler() {
        return cooler;
    }

    public void setCooler(String cooler) {
        this.cooler = cooler;
    }

    public String getPc_case() {
        return pc_case;
    }

    public void setPc_case(String pc_case) {
        this.pc_case = pc_case;
    }

    public String getBuild_name() {
        return build_name;
    }

    public void setBuild_name(String build_name) {
        this.build_name = build_name;
    }
}
