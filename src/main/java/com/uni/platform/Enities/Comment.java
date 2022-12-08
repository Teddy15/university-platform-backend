package com.uni.platform.Enities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {

    public Comment() {
    }

    public Comment(Integer id, String title, String content, Timestamp created_at) {
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.id = id;
    }

    @Id
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Timestamp created_at;

    @Column
    private Timestamp last_updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(Timestamp last_updated_at) {
        this.last_updated_at = last_updated_at;
    }
}
