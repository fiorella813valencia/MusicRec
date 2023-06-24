package com.example.app.application.practice.service;

import com.example.app.application.practice.domain.model.Song;
import com.example.app.application.practice.domain.persistence.SongRepository;
import com.example.app.application.practice.domain.service.SongService;
import com.example.app.shared.exception.ResourceNotFoundException;
import com.example.app.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SongServiceImp implements SongService {

    private static final String ENTITY="Song";
    private final SongRepository songRepository;

    private final Validator validator;

    public SongServiceImp(SongRepository songRepository,Validator validator){
        this.songRepository=songRepository;
        this.validator=validator;
    }
    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public Page<Song> getAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Song getById(Long songId) {
        return songRepository.findById(songId).orElseThrow(()->new ResourceNotFoundException(ENTITY,songId));
    }

    @Override
    public Song create(Song song) {
        Set<ConstraintViolation<Song>> violations=validator.validate(song);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        //validator for name group and signer
        Song songWithNameGroupSigner=songRepository.findByNameAndAndGroup1AndAndSinger(song.getName(), song.getGroup1(), song.getSinger());
        if(songWithNameGroupSigner!=null)
            throw new ResourceValidationException(ENTITY,"Song with the same name, group and signer already exists");


        Song songWithTypeRhythm=songRepository.findByType1AndAndRhythm(song.getType1(),song.getRhythm());
        if(songWithTypeRhythm!=null)
            throw new ResourceValidationException(ENTITY,"Song with the same type and rhythm already exists");


        return songRepository.save(song);
    }

    @Override
    public Song update(Long songId, Song request) {
        Set<ConstraintViolation<Song>> violations=validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //validate if the product exist
        Optional<Song> existingSong=songRepository.findById(request.getSongId());
        if(existingSong.isEmpty())
            throw new ResourceNotFoundException(ENTITY,request.getSongId());


        Song songWithNameGroupSigner = songRepository.findByNameAndAndGroup1AndAndSinger(request.getName(), request.getGroup1(), request.getSinger());
        if (songWithNameGroupSigner != null && !songWithNameGroupSigner.getSongId().equals(songId)) {
            throw new ResourceValidationException(ENTITY, "Song with the same name, group and signer already exists");
        }

        Song songWithTypeRhythm = songRepository.findByType1AndAndRhythm(request.getType1(),request.getRhythm());
        if (songWithTypeRhythm != null && !songWithTypeRhythm.getSongId().equals(songId)) {
            throw new ResourceValidationException(ENTITY, "Song with the same type and rhythm already exists");
        }

        Song updatedSong = existingSong.get()
                .withName(request.getName())
                .withGroup1(request.getGroup1())
                .withSinger(request.getSinger())
                .withCountry(request.getCountry())
                .withType1(request.getType1())
                .withRhythm(request.getRhythm())
                .withYear(request.getYear());

        return songRepository.save(updatedSong);
    }

    @Override
    public ResponseEntity<?> delete(Long songId) {
        return songRepository.findById(songId).map(song ->{
            songRepository.delete(song);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,songId)); //estudio live
    }
}
