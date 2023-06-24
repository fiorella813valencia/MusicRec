package com.example.app.application.practice.domain.service;

import com.example.app.application.practice.domain.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SongService {
    //CRUD here
    List<Song> getAll();
    Page<Song> getAll(Pageable pageable);
    Song getById(Long songId);
    Song create(Song song);
    Song update(Long songId, Song request);

    ResponseEntity<?> delete(Long songId);
}
