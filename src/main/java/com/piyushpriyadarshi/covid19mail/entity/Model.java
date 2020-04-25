package com.piyushpriyadarshi.covid19mail.entity;

import java.util.Date;

public class Model {

    private Date dataDate;


    private long totalCase;


    private long curedCase;


    private long totalDeath;


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

    public Model() {
    }

    @Override
    public String toString() {
        return "StateWiseData{" +
                ", dataDate=" + dataDate +
                ", totalCase=" + totalCase +
                ", curedCase=" + curedCase +
                ", totalDeath=" + totalDeath +
                '}';
    }
}
