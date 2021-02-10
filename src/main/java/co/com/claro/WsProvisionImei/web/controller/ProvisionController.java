package co.com.claro.WsProvisionImei.web.controller;

import co.com.claro.WsProvisionImei.models.ResponseGeneral;
import co.com.claro.WsProvisionImei.service.ProvisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/provisionImei")
@Slf4j
public class ProvisionController {

    private ProvisionService provisionService;

    @Autowired
    public ProvisionController(ProvisionService provisionService) {
        this.provisionService = provisionService;
    }

    @GetMapping("/query")
    public ResponseGeneral ProvisionImeiInfo(@RequestParam String imei){
        return provisionService.getByImei(imei);
    }



}
