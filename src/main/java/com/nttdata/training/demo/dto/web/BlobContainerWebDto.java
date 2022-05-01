package com.nttdata.training.demo.dto.web;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BlobContainerWebDto implements Serializable {

    private static final long serialVersionUID = -9020136733165226548L;
    private String accountName;
    private String containerName;
    private String serviceVersion;
}
