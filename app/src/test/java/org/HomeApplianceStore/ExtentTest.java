package org.HomeApplianceStore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtentTest {

    @Test
    void loadClassListWithNullLocationShouldThrow() {
        assertThrows(NullPointerException.class, () ->
                Extent.loadClassList(null)
        );
    }

    @Test
    void loadClassListWithEmptyLocationShouldThrow() {
        assertThrows(NullPointerException.class, () ->
                Extent.loadClassList("")
        );
    }

    @Test
    void saveClassListWithNullLocationShouldThrow() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        assertThrows(NullPointerException.class, () ->
                Extent.saveClassList(null, list)
        );
    }

    @Test
    void saveClassListWithEmptyLocationShouldThrow() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        assertThrows(NullPointerException.class, () ->
                Extent.saveClassList("", list)
        );
    }

    @Test
    void saveAndLoadRoundTripShouldPreserveContents(@TempDir Path tempDir) {
        // given
        Path file = tempDir.resolve("extent-test.ser");
        String location = file.toString();

        ArrayList<String> original = new ArrayList<>();
        original.add("one");
        original.add("two");

        // when
        Extent.saveClassList(location, original);
        ArrayList<String> loaded = Extent.loadClassList(location);

        // then
        assertEquals(original.size(), loaded.size());
        assertEquals(original, loaded);
    }

    @Test
    void getImmutableClassListShouldReturnUnmodifiableView() {
        ArrayList<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");

        List<String> immutable = Extent.getImmutableClassList(list);

        assertEquals(2, immutable.size());
        assertThrows(UnsupportedOperationException.class, () ->
                immutable.add("z")
        );
    }
}