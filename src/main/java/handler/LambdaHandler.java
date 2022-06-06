package handler;

import com.amazonaws.services.lambda.runtime.Context;
import config.Configuration;
import model.MySqsMsgTable;
import service.SQSOperationService;

public class LambdaHandler {
    public String handleRequest(Context context) {
        SQSOperationService sqsOperationService = new SQSOperationService();
        context.getLogger().log("\nSTARTING READ");
        String msg = sqsOperationService.read(context);
        Configuration configuration = new Configuration();
        MySqsMsgTable msgObj = new MySqsMsgTable();
        msgObj.setMsgId(String.valueOf(Math.random()));
        msgObj.setMsg(msg);
        configuration.mapper().save(msgObj);
        context.getLogger().log("\nCOMPLETED READ");
        context.getLogger().log(msg);
        return msg;
    }
}
