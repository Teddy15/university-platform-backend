package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="attachment")
@Table(name="attachment", schema="uni_platform")
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fileKey;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;
}
