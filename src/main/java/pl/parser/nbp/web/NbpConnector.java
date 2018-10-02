package pl.parser.nbp.web;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NbpConnector {

    Client client;
    WebResource webResource;


    public NbpConnector(String url) {

        client = Client.create();
        webResource = client.resource(url);

    }

    public String getResponse(String typeResponse) {
        ClientResponse response = webResource.accept(typeResponse).get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP Error..." + response.getStatus() );
        }
        String result = response.getEntity(String.class);
        return result;
    }

}
