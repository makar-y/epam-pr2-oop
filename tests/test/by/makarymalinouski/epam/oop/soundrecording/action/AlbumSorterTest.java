package test.by.makarymalinouski.epam.oop.soundrecording.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import by.makarymalinouski.epam.oop.soundrecording.action.AlbumSorter;
import by.makarymalinouski.epam.oop.soundrecording.comparator.RecordingIdComparator;
import by.makarymalinouski.epam.oop.soundrecording.entity.Album;
import by.makarymalinouski.epam.oop.soundrecording.entity.Recording;
import by.makarymalinouski.epam.oop.soundrecording.musicenum.Style;

public class AlbumSorterTest {
    
    @Test
    public void byStyleTestEmpty() {
        Album album = new Album();
        SortedSet<Recording> recordings = new TreeSet<>(new RecordingIdComparator());
        album.setRecordings(recordings);
        AlbumSorter sorter = new AlbumSorter();
        sorter.byStyle(album);
        assertTrue(album.getRecordings().isEmpty());

    }

    @Test
    public void byStyleTest() {
        Album album = new Album();
        SortedSet<Recording> recordings = new TreeSet<>(new RecordingIdComparator());
        recordings.addAll(Arrays.asList(
                    new Recording("Made my day", "Jill Sorry", Style.BLUES, 155),           // id 1
                    new Recording("Something new", "Billy Storm", Style.ROCK, 210),         // id 2
                    new Recording("Die darling", "Jim Scarecrow", Style.METAL, 205),        // id 3
                    new Recording("To see the world", "Manz Dritter", Style.JAZZ, 421),     // id 4
                    new Recording("Love me twice", "Sa-Sha", Style.POP, 329),               // id 5
                    new Recording("Procrustes' bed", "Z-us", Style.ELECTRONIC, 312),        // id 6
                    new Recording("Autumn Elegy", "Serge Simonoff", Style.CLASSICAL, 164)   // id 7
                ));
        album.setRecordings(recordings);
        
        // re-sort alphabetically by style 
        AlbumSorter sorter = new AlbumSorter();
        sorter.byStyle(album);
        Iterator<Recording> iter = album.getRecordings().iterator();
        
        // has to be in this order: BLUES, CLASSICAL, ELECTRONIC, JAZZ, METAL, POP, ROCK
        assertEquals("album's size", 7, album.getRecordings().size());
        assertEquals("album's first element id", 1, iter.next().getRecordingId());
        assertEquals("album's second element id", 7, iter.next().getRecordingId());
        assertEquals("album's third element id", 6, iter.next().getRecordingId());
        assertEquals("album's fourth element id", 4, iter.next().getRecordingId());
        assertEquals("album's fifth element id", 3, iter.next().getRecordingId());
        assertEquals("album's sixth element id", 5, iter.next().getRecordingId());
        assertEquals("album's seventh element id", 2, iter.next().getRecordingId());
    }
    
    @Test
    public void byStyleTestSameStyleCase() {
        Album album = new Album();
        SortedSet<Recording> recordings = new TreeSet<>(new RecordingIdComparator());
        recordings.addAll(Arrays.asList(
                    new Recording("Die darling", "Jim Scarecrow", Style.METAL, 205),        // id 1
                    new Recording("To see the world", "Manz Dritter", Style.JAZZ, 421),     // id 2
                    new Recording("Dirty road", "Razor Blade", Style.METAL, 329),           // id 3
                    new Recording("I never lived", "Shadows", Style.METAL, 164)             // id 4
                ));
        album.setRecordings(recordings);
        AlbumSorter sorter = new AlbumSorter();
        sorter.byStyle(album);
        Iterator<Recording> iter = album.getRecordings().iterator();
        
        assertEquals("album's first element id", 2, iter.next().getRecordingId());
        assertEquals("album's second element id", 1, iter.next().getRecordingId());
        assertEquals("album's third element id", 3, iter.next().getRecordingId());
        assertEquals("album's fourth element id", 4, iter.next().getRecordingId());
    }

}
