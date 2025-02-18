package com.codaconsultancy.centenaryclubadmin.domain;

import com.codaconsultancy.centenaryclubadmin.mappers.AddressMapper;
import com.codaconsultancy.centenaryclubadmin.view.AddressViewBean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {

    public static final String BR_TAG = "<br/>";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;

    @NotNull
    @Column(name = "TOWN")
    private String town;

    @NotNull
    @Column(name = "REGION")
    private String region;

    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Transient
    private String formattedAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

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
            formattedAddress.append(BR_TAG).append(addressLine2);
        }
        if (null != addressLine3 && !addressLine3.isEmpty()) {
            formattedAddress.append(BR_TAG).append(addressLine3);
        }
        formattedAddress.append(BR_TAG).append(town);
        formattedAddress.append(BR_TAG).append(region);
        if (null != postcode && !postcode.isEmpty()) {
            formattedAddress.append(BR_TAG).append(postcode);
        }
        return formattedAddress.toString();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public AddressViewBean toViewBean() {
        return AddressMapper.INSTANCE.entityToViewBean(this);
    }
}
