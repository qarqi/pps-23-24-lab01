import org.junit.jupiter.api.*;
import tdd.CircularListImpl;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * The test suite for testing the CircularList implementation
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CircularListTest {

    CircularListImpl list = new CircularListImpl();
    Predicate<Integer> negativeNum;
    Predicate<Integer> positiveNum;
    Predicate<Integer> evenNum;
    Predicate<Integer> oddNum;

    @BeforeEach
    public void setUp() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        negativeNum = (i) -> i < 0;
        positiveNum = (i) -> i > 0;
        evenNum = (i) -> i % 2 == 0;
        oddNum = (i) -> i % 2 != 0;
    }

    @AfterEach
    public void destroy() {
        list = new CircularListImpl();
    }

    @Test
    public void isNotEmpty() {
        Assertions.assertFalse(list.isEmpty());
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
        Assertions.assertEquals(1, list.next().get());
        Assertions.assertEquals(2, list.next().get());
        Assertions.assertEquals(3, list.next().get());
        Assertions.assertEquals(0, list.next().get());
    }

    @Test
    public void prev() {
        Assertions.assertEquals(3, list.previous().get());
        Assertions.assertEquals(2, list.previous().get());
        Assertions.assertEquals(1, list.previous().get());
        Assertions.assertEquals(0, list.previous().get());
    }

    @Test
    public void reset() {
        list.next();
        list.next();
        list.reset();
        Assertions.assertEquals(0, list.next().get());
        Assertions.assertEquals(1, list.next().get());
        Assertions.assertEquals(3, list.next().get());
    }

    @Test
    public void filteredNext() {
        Assertions.assertEquals(Optional.empty(), list.filteredNext(negativeNum));
        Assertions.assertEquals(1, list.filteredNext(positiveNum).get());
        Assertions.assertEquals(3, list.filteredNext(oddNum).get());
        Assertions.assertEquals(0, list.filteredNext(evenNum).get());
        Assertions.assertEquals(2, list.filteredNext(evenNum).get());

    }
}
