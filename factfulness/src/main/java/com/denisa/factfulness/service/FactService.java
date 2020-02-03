package com.denisa.factfulness.service;

import com.denisa.factfulness.dto.FactDTO;
import com.denisa.factfulness.mappers.FactMapper;
import com.denisa.factfulness.model.Fact;
import com.denisa.factfulness.model.User;
import com.denisa.factfulness.repository.FactRepository;
import com.denisa.factfulness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FactService {
    @Autowired
    private FactRepository factRepository;
    @Autowired
    private FactMapper factMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    Random random = new Random();

    @Transactional
    public FactDTO addFact(FactDTO factDTO) {
        Fact fact = factMapper.mapToFact(factDTO);
        fact.setCreatedBy(userRepository.findByUsername(userService.getCurrentUser().getUsername()));
        fact = factRepository.save(fact);
        return factMapper.mapToFactDTO(fact);
    }

    @Transactional
    public FactDTO updateFact(FactDTO factDTO) {
        Fact fact = factRepository.save(factMapper.mapToFact(factDTO));
        return factMapper.mapToFactDTO(fact);
    }

    @Transactional
    public void deleteFact(Integer id) {
        factRepository.deleteById(id);
    }

    public FactDTO getFact(Integer id) {
        return factMapper.mapToFactDTO(factRepository.findById(id).get());
    }

    public FactDTO getRandomFact() {
        List<Fact> facts = factRepository.findAll();
        return factMapper.mapToFactDTO(facts.get(random.nextInt(facts.size())));
    }

    public List<FactDTO> getFactsForUser() {
        Integer userId = userService.getCurrentUser().getUserId();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return factMapper.mapToFactDTOs(new ArrayList<>(user.get().getCreatedFacts()));
        } else {
            return Collections.emptyList();
        }
    }
}
