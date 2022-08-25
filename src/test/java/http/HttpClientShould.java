package http;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import player.Player;
import player.Players;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.lang.System.out;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientShould {

    @Test
    void call_a_web_server_and_request_information() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://sevenlearn.free.beeceptor.com/players"))
                .build();

        final HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        final String body = response.body();
        out.println(body);

        final Gson gson = new Gson();
        final Players players = gson.fromJson(body, Players.class);
        players.getPlayers().forEach(out::println);

        final List<Player> topScorers = players.getPlayers().stream()
                .filter(player -> player.getGoal() > 225)
                .collect(toUnmodifiableList());

        assertThat(topScorers).contains(new Player("Ali Karimi", 250));
    }
}
