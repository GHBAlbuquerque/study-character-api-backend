package com.neo.characterapi.application.exceptions.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionDetails {

    private String type;

    private String title;

    private String detail;

    private Integer status;

    private Date date;

    private Map<String, List<String>> errors;

    public ExceptionDetails(String type, String title, String detail, Integer status, Date date, Map<String, List<String>> errors) {
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.date = date;
        this.errors = errors;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}