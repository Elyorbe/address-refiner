package me.elyor.addressrefiner.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostalAddress {

    private String provinceCity; // 시도
    private String cityCountyDistrict; //시군구
    private String township; //읍면
    private String road; //도로명

}
