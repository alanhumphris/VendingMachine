/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.model;

import java.util.Objects;

/**
 * Details concerning an item of stock type
 *
 * @author Alan Humphris
 */
public class AvailableItem {

    private EnumItemType itemType = EnumItemType.NO_VEND;
    private Integer level = 0;
    private Integer cost = 0;

    public AvailableItem(EnumItemType itemType, Integer level, Integer cost) {
        this.itemType = itemType;
        this.level = level;
        this.cost = cost;
    }

    public EnumItemType getItemType() {
        return itemType;
    }

    public void setItemType(EnumItemType itemType) {
        this.itemType = itemType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.itemType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvailableItem other = (AvailableItem) obj;
        if (this.itemType != other.itemType) {
            return false;
        }
        return true;
    }

}
