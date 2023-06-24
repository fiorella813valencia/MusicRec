package com.example.app.application.practice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String group1;
    @NotBlank
    @NotNull
    private String singer;

    private String country;
    @NotBlank
    @NotNull
    private String type1;

    @NotBlank
    @NotNull
    private String rhythm;

    @NotBlank
    @NotNull
    private String year;

}
