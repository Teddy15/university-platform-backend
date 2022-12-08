package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity(name="attachment")
@Table(name="attachment", schema="uni_platform")
@Data
public class Attachment {
    @Id
    private UUID fileKey;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;
}
