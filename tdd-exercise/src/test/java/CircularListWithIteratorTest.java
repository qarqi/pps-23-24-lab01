import iterator.CircularListWithIteratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tdd.CircularListImpl;

import java.util.Iterator;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CircularListWithIteratorTest {
    CircularListWithIteratorImpl list = new CircularListWithIteratorImpl();
    Iterator<Optional> forwardIterator;
    Iterator<Optional> backwardIterator;

    @BeforeAll
    public void setUp() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        forwardIterator = list.forwardIterator();
        backwardIterator = list.backwardIterator();
    }

    @Test
    public void isNotEmpty() {
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertTrue(forwardIterator.hasNext());
        Assertions.assertTrue(backwardIterator.hasNext());
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
