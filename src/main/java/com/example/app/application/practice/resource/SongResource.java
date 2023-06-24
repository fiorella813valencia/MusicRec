package com.example.app.application.practice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SongResource {
    private Long songId;
    private String name;
    private String group1;
    private String singer;
    private String country;
    private String type1;
    private String rhythm;
    private String year;

}
