package com.uni.platform.RequestBodies;

public class CommentRequest {

    private String title;

    private String content;

    public CommentRequest() {
    }

    public CommentRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
