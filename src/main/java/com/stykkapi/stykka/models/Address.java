package com.stykkapi.stykka.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="addresses")
@Data
public class Address {
    @Id
    private String addressId;
    private String streetLine;
    private String city;
    private String state;
    private String country;
    private String userId;

    /**
    * This overrides the equals method for the address object
    * @author saucekode
    * @since 2021-06-4
    */
    @Override
    public boolean equals(Object obj){
        if(this.getClass() == obj.getClass()){
            Address address = (Address)obj;
            if(this.streetLine.equals(address.streetLine)
                    && this.city.equals(address.city)
                    && this.state.equals(address.state)
                    && this.country.equals(address.country)){
                return true;
            }
        }
        return false;
    }
}
