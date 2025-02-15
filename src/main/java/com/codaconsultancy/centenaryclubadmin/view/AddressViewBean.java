package com.codaconsultancy.centenaryclubadmin.view;

import com.codaconsultancy.centenaryclubadmin.domain.Address;
import com.codaconsultancy.centenaryclubadmin.mappers.AddressMapper;
import jakarta.validation.constraints.NotNull;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

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

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Address toEntity() {
        return AddressMapper.INSTANCE.viewBeanToEntity(this);
    }

}

