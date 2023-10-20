package com.toolongtapes.retromoviesite.model.repositories;

import com.toolongtapes.retromoviesite.model.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer > {
}
