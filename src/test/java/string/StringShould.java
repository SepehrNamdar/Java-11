package string;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

public class StringShould {

    @Test
    void split_a_string_line_by_line() {
        String s = "\t  line 1 \u2005 \n line 2 \r       \n line 3 \r\n line 4";

        final List<String> lines = s.lines()
                //.filter(line -> !line.isBlank())
                .filter(not(String::isBlank))
                .map(String::strip)
                .collect(toUnmodifiableList());
        final String[] strings = lines.toArray(String[]::new);

        assertThat(lines).containsExactly("line 1", "line 2", "line 3", "line 4");
        assertThat(strings).containsExactly("line 1", "line 2", "line 3", "line 4");
    }

}
