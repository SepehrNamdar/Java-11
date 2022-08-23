import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationShould {

    @Test
    void works() {
        final Application application = new Application();

        final boolean isWorking = application.isWorking();

        assertThat(isWorking).isTrue();
    }

}
