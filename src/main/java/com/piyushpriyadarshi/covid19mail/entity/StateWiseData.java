package com.piyushpriyadarshi.covid19mail.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "state_wise_data")
public class StateWiseData implements Serializable {
    /**
     * State_Name varchar(50),Data_Date Date,Total_Cases integer,Cured_Case integer,Total_Death integer
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "State_Name")
    private String stateName;

    @Column(name = "Data_Date")
    private Date dataDate;

    @Column(name = "Total_Cases")
    private long totalCase;

    @Column(name = "Cured_Cases")
    private long curedCase;

    @Column(name = "Total_Death")
    private long totalDeath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public long getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(long totalCase) {
        this.totalCase = totalCase;
    }

    public long getCuredCase() {
        return curedCase;
    }

    public void setCuredCase(long curedCase) {
        this.curedCase = curedCase;
    }

    public long getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(long totalDeath) {
        this.totalDeath = totalDeath;
    }

    public StateWiseData() {
    }

    @Override
    public String toString() {
        return "StateWiseData{" +
                "stateName='" + stateName + '\'' +
                ", dataDate=" + dataDate +
                ", totalCase=" + totalCase +
                ", curedCase=" + curedCase +
                ", totalDeath=" + totalDeath +
                '}';
    }
}
