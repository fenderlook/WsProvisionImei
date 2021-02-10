package co.com.claro.WsProvisionImei.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseGeneral {

    private String code;
    private String message;
    private ResponseImeiCursor cursor;

}
