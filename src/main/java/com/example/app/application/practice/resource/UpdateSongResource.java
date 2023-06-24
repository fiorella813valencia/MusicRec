package com.example.app.application.practice.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSongResource {

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
