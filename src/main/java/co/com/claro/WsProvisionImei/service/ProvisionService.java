package co.com.claro.WsProvisionImei.service;

import co.com.claro.WsProvisionImei.models.ResponseGeneral;
import co.com.claro.WsProvisionImei.repo.ProvisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvisionService {

    public ProvisionRepository provisionRepository;

    @Autowired
    public ProvisionService(ProvisionRepository provisionRepository) {
        this.provisionRepository = provisionRepository;
    }

    public ResponseGeneral getByImei(String imei){
        return provisionRepository.validateImei(imei);
    }


}
