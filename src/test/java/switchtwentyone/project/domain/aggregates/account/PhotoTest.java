package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @ParameterizedTest
    @CsvSource({":)", "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"})
    void of_Test_success(String input) {
        //Arrange
        String photo = input;

        // Act
        Photo result = Photo.of(photo);

        //Assert
        assertNotNull(result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void of_Test_AbsentInput(String input) {
        // Arrange
        String photo = input;
        Photo expected = Photo.of("No Photo");

        // Act
        Photo result = Photo.of(photo);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void of_Test_TooLong() {
        // Arrange
        String photo = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Photo.of(photo);
        });
    }

    @Test
    void equals_Test_True_NotSame() {
        // Arrange
        String photo = "-8)";
        Photo photoA = Photo.of(photo);
        Photo photoB = Photo.of(photo);

        // Act & Assert
        assertNotSame(photoA, photoB);
        assertEquals(photoA, photoB);
    }

    @Test
    void equals_Test_True_Same() {
        // Arrange
        String photo = "-8)";
        Photo photoA = Photo.of(photo);
        Photo photoB = photoA;

        // Act & Assert
        assertEquals(photoA, photoB);
    }

    @Test
    void equals_Test_False_Null() {
        // Arrange & Act
        Photo nullPhoto = null;
        Photo photo = Photo.of(":)");

        // Act & Assert
        assertNotEquals(photo, nullPhoto);
    }

    @Test
    void equals_Test_False_OtherClass() {
        // Arrange
        Integer other = Integer.valueOf(1);
        Photo photo = Photo.of(":)");

        // Act & Assert
        assertNotEquals(photo, other);
    }

    @Test
    void equals_Test_False_Different() {
        // Arrange
        String A = "-8)";
        String B = "-8P";
        Photo photoA = Photo.of(A);
        Photo photoB = Photo.of(B);

        // Act & Assert
        assertNotEquals(photoA, photoB);
    }

    @Test
    void sameValueAs_Test_True_NotSame() {
        // Arrange
        String photo = "-8)";
        Photo photoA = Photo.of(photo);
        Photo photoB = Photo.of(photo);

        // Act
        boolean result = photoA.sameValueAs(photoB);

        // Assert
        assertTrue(result);
    }

    @Test
    void sameValueAs_Test_True_Same() {
        // Arrange
        String photo = "-8)";
        Photo photoA = Photo.of(photo);
        Photo photoB = photoA;

        // Act
        boolean result = photoA.sameValueAs(photoB);

        // Assert
        assertTrue(result);
    }

    @Test
    void sameValueAs_Test_False_Null() {
        // Arrange & Act
        Photo nullPhoto = null;
        Photo photo = Photo.of(":)");

        // Act
        boolean result = photo.sameValueAs(nullPhoto);

        // Assert
        assertFalse(result);
    }

    @Test
    void sameValueAs_Test_False_Different() {
        // Arrange
        String A = "-8)";
        String B = "-8P";
        Photo photoA = Photo.of(A);
        Photo photoB = Photo.of(B);

        // Act
        boolean result = photoA.sameValueAs(photoB);

        // Assert
        assertFalse(result);
    }

    @Test
    void hashCode_Test_Equal(){
        // Arrange
        String A = "-8)";
        String B = "-8)";

        // Act
        int hashOne = Photo.of(A).hashCode();
        int hashTwo = Photo.of(B).hashCode();

        // Assert
        assertEquals(hashOne, hashTwo);
    }

    @Test
    void hashCode_Test_DifferentObjects(){
        // Arrange
        String A = "-8)";
        String B = "-8P";

        // Act
        int hashOne = Photo.of(A).hashCode();
        int hashTwo = Photo.of(B).hashCode();

        // Assert
        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void testToString() {
        // Arrange
        String expected = ":)";
        Photo photo = Photo.of(expected);

        // Act
        String result = photo.toString();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getPhotoString() {
        // Arrange
        String expected = ":)";
        Photo photo = Photo.of(expected);

        // Act
        String result = photo.getPhotoString();

        // Assert
        assertEquals(expected, result);
    }
}