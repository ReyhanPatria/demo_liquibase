package com.example.demo_liquibase.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Data
public class TransactionDetailId implements Serializable {
    private Integer transactionId;
    private Integer productId;
}