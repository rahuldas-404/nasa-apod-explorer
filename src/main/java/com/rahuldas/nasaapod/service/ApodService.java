package com.rahuldas.nasaapod.service;

import com.rahuldas.nasaapod.dto.ApodResponse;
import com.rahuldas.nasaapod.entity.ApodFavorite;
import com.rahuldas.nasaapod.repository.ApodFavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ApodService {

    private static final Logger log = LoggerFactory.getLogger(ApodService.class);

    private final WebClient webClient;
    private final ApodFavoriteRepository repository;
    private final Map<String, ApodResponse> cache = new ConcurrentHashMap<>();

    @Value("${nasa.api.key}")
    private String apiKey;

    public ApodService(WebClient.Builder webClientBuilder,
                       @Value("${nasa.api.base-url}") String baseUrl,
                       ApodFavoriteRepository repository) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.repository = repository;
    }

    public Mono<ApodResponse> fetchApod(String date) {
        if (cache.containsKey(date)) {
            log.info("Returning cached APOD for date: {}", date);
            return Mono.just(cache.get(date));
        }

        log.info("Fetching APOD from NASA API for date: {}", date);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("api_key", apiKey)
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .bodyToMono(ApodResponse.class)
                .doOnNext(response -> {
                    cache.put(date, response);
                    log.info("Cached APOD for date: {}", date);
                })
                .doOnError(error -> log.error("Error fetching APOD: {}", error.getMessage()));
    }

    public Mono<ApodResponse> fetchTodayApod() {
        return fetchApod(LocalDate.now().toString());
    }

    public List<ApodFavorite> getAllFavorites() {
        return repository.findAll();
    }

    public ApodFavorite saveFavorite(ApodFavorite favorite) {
        if (repository.existsByDate(favorite.getDate())) {
            throw new IllegalArgumentException("Favorite already exists for date: " + favorite.getDate());
        }
        return repository.save(favorite);
    }

    public ApodFavorite getFavoriteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found with id: " + id));
    }

    public void deleteFavorite(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Favorite not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public ApodFavorite updateFavorite(Long id, ApodFavorite updatedFavorite) {
        ApodFavorite existing = getFavoriteById(id);
        existing.setTitle(updatedFavorite.getTitle());
        existing.setExplanation(updatedFavorite.getExplanation());
        return repository.save(existing);
    }
}
