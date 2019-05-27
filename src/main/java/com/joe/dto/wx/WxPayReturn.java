package com.joe.dto.wx;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxPayReturn {

    @XmlElement(name = "return_code")
    private String returnCode;

    @XmlElement(name = "return_msg")
    private String returnMsg;
}
