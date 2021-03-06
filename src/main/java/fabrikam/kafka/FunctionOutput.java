package fabrikam.kafka;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

public class FunctionOutput {
    /**
     * This function listens at endpoint "api/KafkaInput-Java" and send message to the topic. Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP BODY" {your host}/api/KafkaInput-Java
     * 2. curl "{your host}/api/KafkaInput-Java?message=hello"
     */
    @FunctionName("KafkaInput-Java")
    public HttpResponseMessage input(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @KafkaOutput(
                name = "kafkaOutput",
                topic = "%TopicName%", 
                brokerList="%BrokerList%"
                //username = "%ConfluentCloudUsername%", 
                //password = "%ConfluentCloudPassword%",
                //authenticationMode = BrokerAuthenticationMode.PLAIN,
                // sslCaLocation = "confluent_cloud_cacert.pem", // Enable this line for windows.  
                //protocol = BrokerProtocol.SASLSSL
            )  OutputBinding<String> output,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("message");
        String message = request.getBody().orElse(query);
        context.getLogger().info("Message:" + message);
        output.setValue(message);
        return request.createResponseBuilder(HttpStatus.OK).body("Message Sent, " + message).build();
    }
}
