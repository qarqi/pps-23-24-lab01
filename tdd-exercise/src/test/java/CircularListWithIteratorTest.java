import iterator.CircularListWithIteratorImpl;
import org.junit.jupiter.api.*;
import tdd.CircularListImpl;

import java.util.Iterator;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CircularListWithIteratorTest {
    CircularListWithIteratorImpl sut;
    Iterator<Optional> forwardIterator;
    Iterator<Optional> backwardIterator;

    @BeforeEach
    public void setUp() {
        sut = new CircularListWithIteratorImpl();
        sut.add(0);
        sut.add(1);
        sut.add(2);
        sut.add(3);
        forwardIterator = sut.forwardIterator();
        backwardIterator = sut.backwardIterator();
    }

    @Test
    public void isNotEmpty() {
        Assertions.assertFalse(sut.isEmpty());
        Assertions.assertTrue(forwardIterator.hasNext());
        Assertions.assertTrue(backwardIterator.hasNext());
    }

    @Test
    public void add() {
        Assertions.assertEquals(4, sut.size());
    }

    @Test
    public void next() {
        Assertions.assertEquals(1, forwardIterator.next().get());
        Assertions.assertEquals(2, forwardIterator.next().get());
        Assertions.assertEquals(3, forwardIterator.next().get());
        Assertions.assertEquals(0, forwardIterator.next().get());
    }

    @Test
    public void prev() {
        Assertions.assertEquals(3, backwardIterator.next().get());
        Assertions.assertEquals(2, backwardIterator.next().get());
        Assertions.assertEquals(1, backwardIterator.next().get());
        Assertions.assertEquals(0, backwardIterator.next().get());
    }

}
