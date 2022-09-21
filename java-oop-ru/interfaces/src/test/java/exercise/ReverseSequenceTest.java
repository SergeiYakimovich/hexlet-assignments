package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseSequenceTest {

    @Test
    void testReverseSequence1() {
        CharSequence text = new ReversedSequence("abcdef");
        assertThat(text.toString()).isEqualTo("fedcba");
        assertThat(text.charAt(1)).isEqualTo('e');
        assertThat(text.length()).isEqualTo(6);
        assertThat(text.subSequence(1, 4).toString()).isEqualTo("edc");
    }

    @Test
    void testReverseSequence2() {
        CharSequence text = new ReversedSequence("a");
        assertThat(text.toString()).isEqualTo("a");
        assertThat(text.charAt(0)).isEqualTo('a');
        assertThat(text.length()).isEqualTo(1);
        assertThat(text.subSequence(0, 1).toString()).isEqualTo("a");
    }

    @Test
    void testReverseSequence3() {
        CharSequence text = new ReversedSequence("12");
        assertThat(text.toString()).isEqualTo("21");
        assertThat(text.charAt(1)).isEqualTo('1');
        assertThat(text.length()).isEqualTo(2);
        assertThat(text.subSequence(0, 1).toString()).isEqualTo("2");
    }

}
