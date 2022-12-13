package JPABOARD.JPACRUD.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Address {

    @Column(name = "city")
    private String city;
    private String street;
    private String zipcode;


}
