package com.piyushpriyadarshi.covid19mail.repository;

import com.piyushpriyadarshi.covid19mail.entity.Model;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQueries;
import java.util.Date;
import java.util.List;

@Repository
public interface StateWiseDataRepository extends CrudRepository<StateWiseData,Long> {

    @Query(
            value = "select * from state_wise_data where Data_date=?1 and state_name=?2",
            nativeQuery = true)
    List<StateWiseData> findByStateNameAndDate(String date,String name);


    @Query(
            value = "select * from state_wise_data where State_name=?1",
            nativeQuery = true)
    List<StateWiseData> findByStateName(String name);

    @Query(
            value = "select sum(total_cases) as total_cases ,sum(cured_cases) as cured_cases , sum(total_death) as total_death ,data_date from state_wise_data group by  data_date;",
            nativeQuery = true)

    List<Object[]> indiaTotal();
}
