package com.companyname.dept.machine.model;

public enum EnumItemType {

    A(60), //
    B(100),//
    C(170),
    NO_VEND(-1);

    private final Integer defaultCost;
    
    EnumItemType(Integer defaultCost) {
        this.defaultCost = defaultCost;
    }
    
    public Integer getDefaultCost() {
        return defaultCost;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("name: ");
        result.append(this.name());
        result.append(", defaultCost: ");
        result.append(this.getDefaultCost());

        return result.toString();
    }

}
