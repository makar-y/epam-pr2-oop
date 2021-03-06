package test.by.malinouski.soundrecording.comparator;

import static org.junit.Assert.*;

import org.junit.Test;

import by.malinouski.soundrecording.comparator.CompositionComparator;
import by.malinouski.soundrecording.entity.Composition;
import by.malinouski.soundrecording.musicenum.Style;

public class CompositionComparatorTest {

    @Test
    public void compareTest() {
        Composition comp1 = new Composition("Jello-shot", "Stringy", Style.POP, 234534);
        Composition comp2 = new Composition("Every time", "Jay Dillo", Style.ELECTRONIC, 3456);
        
        CompositionComparator comparator = new CompositionComparator();
        assertTrue("must be positive", comparator.compare(comp1, comp2) > 0);
        assertTrue("must be negative", comparator.compare(comp2, comp1) < 0);
    }
    
    @Test
    public void compareTestEquals() {
        Composition comp1 = new Composition("Jello-shot", "Stringy", Style.POP, 4536345);
        Composition comp2 = new Composition("Jello-shot", "Stringy", Style.POP, 4536345);
        
        CompositionComparator comparator = new CompositionComparator();
        assertEquals("must be zero", 0, comparator.compare(comp1, comp2));
        assertEquals("must be zero", 0, comparator.compare(comp2, comp1));
    }

}
