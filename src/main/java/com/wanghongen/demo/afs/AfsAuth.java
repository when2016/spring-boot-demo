package com.wanghongen.demo.afs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfsAuth implements Serializable {


    private String sessionId;
    private String sign;
    private String token;
    private String scene;
    private String appKey;


}
