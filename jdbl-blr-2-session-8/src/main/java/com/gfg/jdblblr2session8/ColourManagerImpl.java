package com.gfg.jdblblr2session8;

import com.gfg.jdblblr2session8.entity.Colour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColourManagerImpl implements  ColourManager{
    @Autowired
    ColourRepository colourRepository;
    @Override
    public void CreateColour(String name) {
        Colour colour = Colour.builder().colour(name).build();
        colourRepository.save(colour);

    }
}
