package com.rahuldas.nasaapod.repository;

import com.rahuldas.nasaapod.entity.ApodFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApodFavoriteRepository extends JpaRepository<ApodFavorite, Long> {
    Optional<ApodFavorite> findByDate(String date);
    boolean existsByDate(String date);
}