package kapitaz;

import kapitaz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;


@Component
public class Interaction {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String URL = "http://91.241.64.178:7081/api/users";

    @Autowired
    public Interaction(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
        this.httpHeaders.set("Cookie",
                String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));
    }

    public ResponseEntity<String> addUser() {
        User user = new User(3L, "James", "Brown", (byte) 32);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.postForEntity(URL, userHttpEntity, String.class);
    }

    public ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 45);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange(URL, HttpMethod.PUT, userHttpEntity, String.class, 3);
    }

    public ResponseEntity<String> deleteUser() {
        Map<String, Long> variables = new HashMap<>();
        variables.put("id", 3L);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        return restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, httpEntity, String.class, variables);
    }

}
