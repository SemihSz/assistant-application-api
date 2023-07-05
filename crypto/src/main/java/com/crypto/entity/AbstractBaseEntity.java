package com.crypto.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@MappedSuperclass
public class AbstractBaseEntity implements Serializable {
    @Id
    public LocalDateTime time;

    public BigDecimal price;

    public BigDecimal volume;

    public BigDecimal markerCap;

   public AbstractBaseEntity(LocalDateTime time, BigDecimal price, BigDecimal volume, BigDecimal markerCap) {
        this.time = time;
        this.price = price;
        this.volume = volume;
        this.markerCap = markerCap;
    }

    public AbstractBaseEntity() {
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getMarkerCap() {
        return markerCap;
    }

    public void setMarkerCap(BigDecimal markerCap) {
        this.markerCap = markerCap;
    }
}
