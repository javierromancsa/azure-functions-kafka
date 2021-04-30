package com.contoso.kafka;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TriggerFunction {
    /**
     * BrokerList and UserName, and Password. The value wrapped with `%` will be replaced with enviornment variables. 
     * For more details, refer https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-expressions-patterns#binding-expressions---app-settings
     * The function is a sample of consuming kafkaEvent on batch.
     * @param kafkaEventData
     * @param context
     */
    @FunctionName("KafkaTrigger-Java")
    public void runOne(
            @KafkaTrigger(
                //name = "kafkaTriggerMany",
                name = "kafkaTrigger",
                topic = "%TopicName%", 
                brokerList="%BrokerList%",
                consumerGroup="%GroupName%"
	        //Settings below are only require if you need auth or you do Many messages per trigger	
                //username = "%ConfluentCloudUsername%", 
                //password = "%ConfluentCloudPassword%",
                //authenticationMode = BrokerAuthenticationMode.PLAIN,
                //protocol = BrokerProtocol.SASLSSL,
                // sslCaLocation = "confluent_cloud_cacert.pem", // Enable this line for windows.
                //cardinality = Cardinality.MANY,
                //dataType = "string"
             ) String kafkaEventData,
             //) String[] kafkaEventData,
            final ExecutionContext context) {
            context.getLogger().info(kafkaEventData);
            //for (String message: kafkaEventData) {
            //    context.getLogger().info(message);
            //} 
    }
}
