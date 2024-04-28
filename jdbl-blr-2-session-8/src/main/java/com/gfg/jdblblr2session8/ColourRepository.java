package com.gfg.jdblblr2session8;

import com.gfg.jdblblr2session8.entity.Car;
import com.gfg.jdblblr2session8.entity.Colour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColourRepository extends CrudRepository<Colour, Long> {
}
