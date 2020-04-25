package com.piyushpriyadarshi.covid19mail.repository;

import com.piyushpriyadarshi.covid19mail.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
    State findStatesById(int id);
    State findStatesByName(String name);
}
