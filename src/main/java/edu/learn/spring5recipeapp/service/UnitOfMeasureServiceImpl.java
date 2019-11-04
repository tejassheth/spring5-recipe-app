package edu.learn.spring5recipeapp.service;

import edu.learn.spring5recipeapp.commands.UnitOfMeasureCommand;
import edu.learn.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import edu.learn.spring5recipeapp.domain.UnitOfMeasure;
import edu.learn.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(),false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
