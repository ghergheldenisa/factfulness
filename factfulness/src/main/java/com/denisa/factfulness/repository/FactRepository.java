package com.denisa.factfulness.repository;

import com.denisa.factfulness.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepository extends JpaRepository<Fact, Integer> {
//    public List<Fact> getAll() {
//        List<Fact> facts = new ArrayList<>();
//        facts.add(new Fact(1, "some random fact", new User(), new Date(), new Date()));
//        facts.add(new Fact(2, "some random fact 2", new User(), new Date(), new Date()));
//        facts.add(new Fact(3, "some random fact 3", new User(), new Date(), new Date()));
//        return facts;
//    }
}
