package co.com.claro.WsProvisionImei.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseGeneral {

    private String code;
    private String message;
    private List<ResponseImeiCursor> cursor;

}
