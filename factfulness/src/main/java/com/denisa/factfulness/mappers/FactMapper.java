package com.denisa.factfulness.mappers;

import com.denisa.factfulness.dto.FactDTO;
import com.denisa.factfulness.model.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactMapper {
    @Autowired
    private UserMapper userMapper;

    public FactDTO mapToFactDTO(Fact fact) {
        FactDTO factDTO = new FactDTO();
        factDTO.setFactId(fact.getFactId());
        factDTO.setText(fact.getText());
        factDTO.setCreatedBy(userMapper.mapToUserDTO(fact.getCreatedBy()));
        return factDTO;
    }

    public Fact mapToFact(FactDTO factDTO) {
        Fact fact = new Fact();
        fact.setFactId(factDTO.getFactId());
        fact.setText(factDTO.getText());
        fact.setCreatedBy(userMapper.mapToUser(factDTO.getCreatedBy()));
        return fact;
    }

    public List<FactDTO> mapToFactDTOs(List<Fact> facts) {
        return facts.stream().map(this::mapToFactDTO).collect(Collectors.toList());
    }

    public List<Fact> mapToFacts(List<FactDTO> factDTOs) {
        return factDTOs.stream().map(this::mapToFact).collect(Collectors.toList());
    }
}
