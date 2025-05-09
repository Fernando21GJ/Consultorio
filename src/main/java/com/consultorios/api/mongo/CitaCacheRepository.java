package com.consultorios.api.mongo;

import com.consultorios.api.mongo.CitaCache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CitaCacheRepository extends MongoRepository<CitaCache, String> {
    Optional<CitaCache> findByCitaId(Long citaId);

    Optional<CitaCache> findByFechaHora(LocalDateTime fechaHora);
}
