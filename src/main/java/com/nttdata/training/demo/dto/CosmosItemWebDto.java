package com.nttdata.training.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CosmosItemWebDto implements Serializable {
    private static final long serialVersionUID = -8040255741017718640L;
    private String id;
    private String name;
    private String data;
}
