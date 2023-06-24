package com.example.app.application.practice.mapping;

import com.example.app.application.practice.domain.model.Song;
import com.example.app.application.practice.resource.CreateSongResource;
import com.example.app.application.practice.resource.SongResource;
import com.example.app.application.practice.resource.UpdateSongResource;
import com.example.app.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@EnableAutoConfiguration
public class SongMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SongResource toResource(Song model){
        return mapper.map(model, SongResource.class);
    }

    public Page<SongResource> modelListPage(List<Song> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,SongResource.class),pageable,modelList.size());
    }


    public Song toModel(CreateSongResource resource){
        return mapper.map(resource,Song.class);

    }

    public Song toModel(UpdateSongResource resource){
        return mapper.map(resource,Song.class);
    }


}
