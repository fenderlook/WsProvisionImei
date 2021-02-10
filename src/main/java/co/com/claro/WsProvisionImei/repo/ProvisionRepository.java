package co.com.claro.WsProvisionImei.repo;

import co.com.claro.WsProvisionImei.models.ResponseGeneral;
import co.com.claro.WsProvisionImei.models.ResponseImeiCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository
public class ProvisionRepository {
    private final EntityManager entityManager;

    @Autowired
    public ProvisionRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ResponseGeneral validateImei(String imei){
        ResponseGeneral responseGeneral = new ResponseGeneral();
        ResponseImeiCursor responseImeiCursor = new ResponseImeiCursor();
        boolean find  = false;
        try {
            StoredProcedureQuery spq = entityManager
                    .createStoredProcedureQuery("PL_INFORMACION_APROVISIONAMIENTO_IMEI")
                    .registerStoredProcedureParameter("p_imei",String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("vch_codigo"  ,  String.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("vch_mensaje" ,  String.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("vch_cursor"  ,  Class.class, ParameterMode.REF_CURSOR);

            spq.setParameter("p_imei",imei);
            spq.execute();

            String codigo = (String) spq.getOutputParameterValue("vch_codigo");
            responseGeneral.setCode(codigo);
            String mensaje = (String) spq.getOutputParameterValue("vch_mensaje") ;
            responseGeneral.setMessage(mensaje);

            if (codigo.equals("1")){
                List<Object[]> results = spq.getResultList();
                for (Object[] data :results) {
                        find = true ;
                        BigDecimal dato = (BigDecimal) data[0];
                        responseImeiCursor.setIde(dato.toString());
                        responseImeiCursor.setImsi((String) data[1]);
                        responseImeiCursor.setMini((String) data[2]);
                        Timestamp tmp = (Timestamp) data[3];
                        responseImeiCursor.setDate_registry((new Date(tmp.getTime())));
                        Timestamp tmp2 = (Timestamp) data[4];
                        responseImeiCursor.setDate_execution((new Date(tmp2.getTime())));
                        responseImeiCursor.setOperation_type((String) data[5]);
                }
                if (find){
                    responseGeneral.setCode("1");
                    responseGeneral.setMessage("Se han encontrado resultados");
                    responseGeneral.setCursor(responseImeiCursor);
                } else {
                    responseGeneral.setCode("-1");
                    responseGeneral.setMessage("El IMEI no tiene el formato adecuado");
                }
            }
            // responseGeneral.setCursor(responseImeiCursor);
        }catch (Exception e){
            responseGeneral.setCode("-1");
            responseGeneral.setMessage("Error al consultar, Error -> " + e.getMessage());
        }
        return responseGeneral;
    }
}