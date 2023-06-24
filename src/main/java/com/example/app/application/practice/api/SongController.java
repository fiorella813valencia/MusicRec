package com.example.app.application.practice.api;

import com.example.app.application.practice.domain.service.SongService;
import com.example.app.application.practice.mapping.SongMapper;
import com.example.app.application.practice.resource.CreateSongResource;
import com.example.app.application.practice.resource.SongResource;
import com.example.app.application.practice.resource.UpdateSongResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/songs")
public class SongController {
    public final SongService songService;
    private final SongMapper mapper;

    public SongController(SongService songService, SongMapper mapper){
        this.songService=songService;
        this.mapper=mapper;
    }

    //GET ALL
    @GetMapping
    public Page<SongResource> getAllSongs(Pageable pageable){
        return mapper.modelListPage(songService.getAll(),pageable);
    }

    //GET BY ID
    @GetMapping("{songId}")
    public SongResource getSongById(@PathVariable Long songId){
        return mapper.toResource(songService.getById(songId));
    }

    //POST
    @PostMapping
    public SongResource createSong(@RequestBody CreateSongResource resource){
        return mapper.toResource(songService.create(mapper.toModel(resource)));
    }

    //UPDATE
    @PutMapping("/{songId}")
    public SongResource updateSong(@PathVariable Long songId, @RequestBody UpdateSongResource resource) {
        return mapper.toResource(songService.update(songId, mapper.toModel(resource)));
    }

    //DELETE
    @DeleteMapping("{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable Long songId) {
        return songService.delete(songId);
    }




}
