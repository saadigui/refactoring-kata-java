package com.sipios.refactoring.service.dto;



public class Body {

    private Item[] items;
    private TypeEnum  type;

    public Body(Item[] is, TypeEnum t) {
        this.items = is;
        this.type = t;
    }

    public Body() {}

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }
}

