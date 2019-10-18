package edu.learn.spring5recipeapp.repositories;

import edu.learn.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    public Optional<UnitOfMeasure> findByDescription(String description);
}
