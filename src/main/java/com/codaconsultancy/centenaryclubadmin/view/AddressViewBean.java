package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Address;
import com.codaconsultancy.centenaryclubadmin.mappers.AddressMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressViewBean {

    private Long id;

    @NotNull
    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    @NotNull
    private String town;

    @NotNull
    private String region;

    private String postcode;

    private Boolean isActive;

    private Long memberId;

    public String getFormattedAddress() {
        StringBuilder formattedAddress = new StringBuilder(addressLine1);
        if (null != addressLine2 && !addressLine2.isEmpty()) {
            formattedAddress.append("<br/>").append(addressLine2);
        }
        if (null != addressLine3 && !addressLine3.isEmpty()) {
            formattedAddress.append("<br/>").append(addressLine3);
        }
        formattedAddress.append("<br/>").append(town);
        formattedAddress.append("<br/>").append(region);
        if (null != postcode && !postcode.isEmpty()) {
            formattedAddress.append("<br/>").append(postcode);
        }
        return formattedAddress.toString();
    }

    public Address toEntity() {
        return AddressMapper.INSTANCE.viewBeanToEntity(this);
    }

}

