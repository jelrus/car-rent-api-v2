package com.car_rent_api.utils.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonPrinter {

    private final Gson gson;

    public GsonPrinter() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .disableHtmlEscaping()
                .create();
    }

    public Gson print() {
        return this.gson;
    }
}