package com.uni.platform.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="attachment")
@Table(name="attachment", schema="uni_platform")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String attachmentKey;

    @NotBlank
    private String attachmentName;

    @NotBlank
    private String attachmentType;

//    @OneToOne(mappedBy = "attachment")
//    @JsonIgnore
//    private Post post;
//
//    @OneToOne(mappedBy = "attachment")
//    @JsonIgnore
//    private Comment comment;
}
