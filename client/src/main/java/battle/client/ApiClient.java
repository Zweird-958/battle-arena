package battle.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/players";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Méthode pour récupérer l'état du jeu via l'endpoint /game/state
    public static List<Player> getPlayers() {
        try {
            // Construire l'URL avec le paramètre playerId
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream is = connection.getInputStream()) {

                    System.out.println(is);

                    return Collections.singletonList(mapper.readValue(is, Player.class));
                }
            } else {
                System.out.println("Erreur API, code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
