package com.shop.productservice.entity.currency;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;

import java.io.Serializable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Currency implements Serializable {
    static final Logger log = Logger.getLogger(Currency.class);

    String name;
    InternationalCode code;
    double course;
    double multiplier;

    public Currency(InternationalCode code) {
        this.code = code;

        switch (code) {
            case UAH:
                this.name = "Hryvnia";
                this.course = 1;
                this.multiplier = 1.2;
                break;
            case USD:
                this.name = "Dollar";
                this.course = 36.68;
                this.multiplier = 1.5;
                break;
            case EUR:
                this.name = "Euro";
                this.course = 39.34;
                this.multiplier = 1.5;
                break;
            default:
                log.info("Wrong currency symbol!");
        }
    }

    public Currency(String name, InternationalCode code, double course, double multiplier) {
        this.name = name;
        this.code = code;
        this.course = course;
        this.multiplier = multiplier;
    }

    public void setUAHCurrency() {
        this.name = "Hryvnia";
        this.course = 1;
        this.multiplier = 1.2;
        this.code = InternationalCode.UAH;
    }

}
