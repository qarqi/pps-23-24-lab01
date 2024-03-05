import org.junit.jupiter.api.*;
import tdd.CircularListImpl;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * The test suite for testing the CircularList implementation
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CircularListTest {

    CircularListImpl sut;
    Predicate<Integer> negativeNum;
    Predicate<Integer> positiveNum;
    Predicate<Integer> evenNum;
    Predicate<Integer> oddNum;

    @BeforeEach
    public void setUp() {
        sut = new CircularListImpl();
        sut.add(0);
        sut.add(1);
        sut.add(2);
        sut.add(3);
        negativeNum = (i) -> i < 0;
        positiveNum = (i) -> i > 0;
        evenNum = (i) -> i % 2 == 0;
        oddNum = (i) -> i % 2 != 0;
    }

    @Test
    public void isNotEmpty() {
        Assertions.assertFalse(sut.isEmpty());
    }

    @Test
    public void add() {
        CircularListImpl sut = new CircularListImpl();
        sut.add(5);
        sut.add(4);
        sut.add(3);
        sut.add(2);
        sut.add(1);
        Assertions.assertEquals(5, sut.size());
    }

    @Test
    public void next() {
        Assertions.assertEquals(1, sut.next().get());
        Assertions.assertEquals(2, sut.next().get());
        Assertions.assertEquals(3, sut.next().get());
        Assertions.assertEquals(0, sut.next().get());
    }

    @Test
    public void prev() {
        Assertions.assertEquals(3, sut.previous().get());
        Assertions.assertEquals(2, sut.previous().get());
        Assertions.assertEquals(1, sut.previous().get());
        Assertions.assertEquals(0, sut.previous().get());
    }

    @Test
    public void reset() {
        sut.next();
        sut.next();
        sut.reset();
        Assertions.assertEquals(0, sut.next().get());
        Assertions.assertEquals(1, sut.next().get());
        Assertions.assertEquals(3, sut.next().get());
    }

    @Test
    public void filteredNext() {
        Assertions.assertEquals(Optional.empty(), sut.filteredNext(negativeNum));
        Assertions.assertEquals(1, sut.filteredNext(positiveNum).get());
        Assertions.assertEquals(3, sut.filteredNext(oddNum).get());
        Assertions.assertEquals(0, sut.filteredNext(evenNum).get());
        Assertions.assertEquals(2, sut.filteredNext(evenNum).get());

    }
}
