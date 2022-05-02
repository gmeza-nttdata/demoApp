package com.nttdata.training.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CosmosContainerWebDto implements Serializable {
    private static final long serialVersionUID = -6568351014609871165L;
    private String container;
}
