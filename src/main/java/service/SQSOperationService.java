package service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class SQSOperationService {
    public String read(Context context) {
        StringBuilder msg = new StringBuilder();
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();
        String queueURL = "https://sqs.us-east-1.amazonaws.com/456260224335/LambdaDynamoDBTest";
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withQueueUrl(queueURL)
                .withVisibilityTimeout(10).withMaxNumberOfMessages(10);
        context.getLogger().log("\nRequest Object receiveMessageRequest " + receiveMessageRequest.toString());
        List<Message> messageList = sqs.receiveMessage(receiveMessageRequest).getMessages();

        for (Message message : messageList) {
            context.getLogger().log("\nMessage Body " + message.getBody());
            msg.append(message.getBody()).append(" ");
        }
        return msg.toString();
    }
}
