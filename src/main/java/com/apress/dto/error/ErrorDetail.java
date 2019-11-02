package com.apress.dto.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String developerMessage;
    private String path;
    private Map<String, List<ValidationError>> errors = new HashMap<String,
            List<ValidationError>>();

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getPath() {
        return path;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
