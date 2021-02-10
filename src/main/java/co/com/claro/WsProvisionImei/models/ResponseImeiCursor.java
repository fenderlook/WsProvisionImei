package co.com.claro.WsProvisionImei.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseImeiCursor {

    private String ide;
    private String imsi;
    private String mini;
    //private String fecha_registro;
    //private String fecha_ejecucion;
    //private String tipo_operacion;
    private Date date_registry;
    private Date date_execution;
    private String operation_type;

}
