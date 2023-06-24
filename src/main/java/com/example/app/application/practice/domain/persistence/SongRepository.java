package com.example.app.application.practice.domain.persistence;

import com.example.app.application.practice.domain.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAll();

    List<Song> findSongsByName(String name);

    Song findSongByName(String name);
    Song findSongBySinger(String singer);
    Song findByType1(String type);
    Song findByCountry(String country);
    //Song findByType1AndAndGroup1(String type,String group);

    Song findByNameAndAndGroup1AndAndSinger(String name,String group, String singer);
    Song findByType1AndAndRhythm(String type, String rhythm);

}
