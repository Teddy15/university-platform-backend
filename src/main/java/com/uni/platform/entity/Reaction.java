package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "reaction")
@Table(name = "reaction", schema = "uni_platform")
@Data
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String reactedBy;

    @NotNull
    private Boolean isPositive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public String getReactedBy() {
        return reactedBy;
    }

    public void setReactedBy(String reactedBy) {
        this.reactedBy = reactedBy;
    }
}
