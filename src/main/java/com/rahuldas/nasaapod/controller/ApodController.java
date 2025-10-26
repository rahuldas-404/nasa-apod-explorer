package com.rahuldas.nasaapod.controller;

import com.rahuldas.nasaapod.dto.ApodResponse;
import com.rahuldas.nasaapod.entity.ApodFavorite;
import com.rahuldas.nasaapod.service.ApodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/apod")
@CrossOrigin(origins = "*")
public class ApodController {

    private final ApodService apodService;

    @Autowired
    public ApodController(ApodService apodService) {
        this.apodService = apodService;
    }

    @GetMapping("/today")
    public Mono<ResponseEntity<ApodResponse>> getTodayApod() {
        return apodService.fetchTodayApod()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{date}")
    public Mono<ResponseEntity<ApodResponse>> getApodByDate(@PathVariable String date) {
        return apodService.fetchApod(date)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<ApodFavorite>> getAllFavorites() {
        return ResponseEntity.ok(apodService.getAllFavorites());
    }

    @PostMapping("/favorites")
    public ResponseEntity<ApodFavorite> addFavorite(@RequestBody ApodFavorite favorite) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(apodService.saveFavorite(favorite));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/favorites/{id}")
    public ResponseEntity<ApodFavorite> getFavoriteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(apodService.getFavoriteById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        try {
            apodService.deleteFavorite(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
