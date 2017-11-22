package fr.soat.soadle.doodle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoodleTest {

    @Test
    @DisplayName(" A doodle cannot be constructed with a null uri")
    void doodle_uri_should_not_be_null(TestInfo testInfo) {
        assertThrows(NullPointerException.class, () -> {
            // given
            String uri = null;
            // when
            Doodle newDoodle = Doodle.withUri(uri);
            // then exception
        });
    }

    @Test
    @DisplayName(" A doodle cannot be constructed with an empty uri")
    void doodle_uri_should_not_be_empty(TestInfo testInfo) {
        assertThrows(IllegalArgumentException.class, () -> {
            // given
            String uri = "";
            // when
            Doodle newDoodle = Doodle.withUri(uri);
            // then exception
        });
    }

    @Test
    @DisplayName(" A doodle should return a full usable url")
    void doodle_url_should_not_be_empty(TestInfo testInfo) {
        // given
        Doodle newDoodle = Doodle.withUri("sepun3s652qwzfuk");
        // when
        String url = newDoodle.getUrl();
        // then
        assertThat(url).isEqualTo("https://doodle.com/poll/sepun3s652qwzfuk");
    }

    @Nested
    @DisplayName("Two doodles")
    class Identity {

        @Nested
        @DisplayName(" with same uris")
        class Equals {

            Doodle firstDoodle = Doodle.withUri("some uri");
            Doodle secondDoodle = Doodle.withUri("some uri");

            @Test
            @DisplayName(" should have same hashcode")
            void doodles_with_same_hashcode(TestInfo testInfo) {
                // given
                // then
                assertThat(firstDoodle.hashCode()).isEqualTo(secondDoodle.hashCode());
            }

            @Test
            @DisplayName(" be equals")
            void equal_doodles(TestInfo testInfo) {
                // given
                // then
                assertThat(firstDoodle).isEqualTo(secondDoodle);
            }
        }

        @Nested
        @DisplayName(" with different uris")
        class Different {

            Doodle firstDoodle = Doodle.withUri("some uri");
            Doodle secondDoodle = Doodle.withUri("another uri");

            @Test
            @DisplayName(" should not be equals")
            void equal_doodles(TestInfo testInfo) {
                // given
                // then
                assertThat(firstDoodle).isNotEqualTo(secondDoodle);
            }

        }
    }
}