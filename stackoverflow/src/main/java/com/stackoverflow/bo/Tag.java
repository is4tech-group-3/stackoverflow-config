package com.stackoverflow.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long idTag;

    @Size(max = 15, message = "The name tag must not be longer than 15 characters")
    @NotNull(message = "The tag name field cannot be null")
    @NotBlank(message =  "name tag is required")
    private String name;

    @Size(max = 30, message = "The description tag must not be longer than 30 characters")
    @NotNull(message = "The description tag field cannot be null")
    @NotBlank(message =  "description tag is required")
    private String description;

    private Boolean status;
}
