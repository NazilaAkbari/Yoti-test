package com.yoti.hoovering.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * This entity contains request and response of hoovering
 */
@Entity
@Table(name = "HooveringLog")
public class HooveringLog {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String request;
    @Column
    private String response;
    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public HooveringLog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public HooveringLog setRequest(String request) {
        this.request = request;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public HooveringLog setResponse(String response) {
        this.response = response;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public HooveringLog setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
