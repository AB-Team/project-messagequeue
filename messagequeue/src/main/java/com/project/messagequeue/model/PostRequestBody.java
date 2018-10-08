package com.project.messagequeue.model;

public class PostRequestBody {
    private String type;
    private String message;

    public PostRequestBody(){}

    public PostRequestBody(String type, String message){
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
