package com.example.cocomo.model;

import java.util.List;

public class CocomoModel {
    boolean success;
    String message;
    List<Cocomo> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Cocomo> getData() {
        return data;
    }

    public void setData(List<Cocomo> data) {
        this.data = data;
    }
}
