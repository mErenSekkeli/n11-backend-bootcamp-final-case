package com.merensekkeli.customerreviewservice.enums;

import lombok.Getter;

@Getter
public enum EnumRate {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int value;

    EnumRate(int value) {
        this.value = value;
    }

}
