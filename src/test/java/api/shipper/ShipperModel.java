package api.shipper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipperModel {

    @Column(name="shipperid")
    private int id;

    @Column(name="companyname")
    private String companyname;

    @Column(name="phone")
    private String phone;


    public ShipperModel(){
        //Constructor
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
