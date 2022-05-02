package com.nttdata.training.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CosmosDbWebDto implements Serializable {
    private static final long serialVersionUID = -2284334006453134246L;
    private String id;
    private String link;
}
