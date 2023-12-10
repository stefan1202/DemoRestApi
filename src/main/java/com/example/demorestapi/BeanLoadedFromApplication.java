package com.example.demorestapi;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "ro.sda.bean")
@Validated
public class BeanLoadedFromApplication {
    @NotNull
    @NotBlank
    @Length(min = 3, max = 30,message = "Name must be between 3 and 30 characters")
    private String name;
    @Email
    private String email;

    private String addressValue;

    private List<String> phones;

    private Map<String, String> locationsMap;

    @AssertTrue(message = "Adress must be filled and has to have exactly 2 parts splitted by ','")
    public boolean isValidAddress(){
        return addressValue!=null && addressValue.split(",").length==2;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Map<String, String> getLocationsMap() {
        return locationsMap;
    }

    public void setLocationsMap(Map<String, String> locationsMap) {
        this.locationsMap = locationsMap;
    }

    @Override
    public String toString() {
        return "BeanLoadedFromApplication{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", addressValue='" + addressValue + '\'' +
                ", phones=" + phones +
                ", locationsMap=" + locationsMap +
                '}';
    }
}
