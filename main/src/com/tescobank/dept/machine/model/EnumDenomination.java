package com.tescobank.dept.machine.model;

public enum EnumDenomination {

    // list of valid coin demoninations
    TEN(10), //
    TWENTY(20),//
    FIFTY(50),
    HUNDRED(100);

    private final Integer value;

    EnumDenomination(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("name: ");
        result.append(this.name());
        result.append(", value: ");
        result.append(getValue());

        return result.toString();
    }

}
