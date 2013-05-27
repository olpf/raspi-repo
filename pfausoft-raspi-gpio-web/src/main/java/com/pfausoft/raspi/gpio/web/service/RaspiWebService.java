package com.pfausoft.raspi.gpio.web.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface RaspiWebService {

    @WebMethod(operationName = "pulseGpio")
    void pulseGpio(@WebParam(name = "gpioId") String gpioId);

}
