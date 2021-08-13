package thread.СAS;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrement() {
        CASCount casCount = new CASCount();
        casCount.increment();
        assertThat(casCount.get(), is(1));
    }
    @Test
    public void whenIncrementTen() {
        CASCount casCount = new CASCount();
        while (casCount.get() != 10) {
            casCount.increment();
        }
        assertThat(casCount.get(), is(10));
    }
}