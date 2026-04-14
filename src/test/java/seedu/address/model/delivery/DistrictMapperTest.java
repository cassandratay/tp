package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DistrictMapperTest {

    @Test
    public void getDistrictFromPrefix_prefixOne_returnsDistrictOne() {
        assertEquals(1, DistrictMapper.getDistrictFromPrefix(1));
    }

    @Test
    public void getDistrictFromPrefix_prefixSix_returnsDistrictOne() {
        assertEquals(1, DistrictMapper.getDistrictFromPrefix(6));
    }

    @Test
    public void getDistrictFromPrefix_prefixSeven_returnsDistrictTwo() {
        assertEquals(2, DistrictMapper.getDistrictFromPrefix(7));
    }

    @Test
    public void getDistrictFromPrefix_prefixEight_returnsDistrictTwo() {
        assertEquals(2, DistrictMapper.getDistrictFromPrefix(8));
    }

    @Test
    public void getDistrictFromPrefix_prefixEightyOne_returnsDistrictSeventeen() {
        assertEquals(17, DistrictMapper.getDistrictFromPrefix(81));
    }

    @Test
    public void getDistrictFromPrefix_prefixEightyTwo_returnsDistrictNineteen() {
        assertEquals(19, DistrictMapper.getDistrictFromPrefix(82));
    }

    @Test
    public void getDistrictFromPrefix_prefixSeventyNine_returnsDistrictTwentyEight() {
        assertEquals(28, DistrictMapper.getDistrictFromPrefix(79));
    }

    @Test
    public void getDistrictFromPrefix_prefixEighty_returnsDistrictTwentyEight() {
        assertEquals(28, DistrictMapper.getDistrictFromPrefix(80));
    }

    @Test
    public void getDistrictFromPrefix_invalidPrefix_returnsNegativeOne() {
        assertEquals(-1, DistrictMapper.getDistrictFromPrefix(99));
    }

    @Test
    public void getDistrictFromPrefix_zeroPrefix_returnsNegativeOne() {
        assertEquals(-1, DistrictMapper.getDistrictFromPrefix(0));
    }

    @Test
    public void getDistrictFromPrefix_negativePrefix_returnsNegativeOne() {
        assertEquals(-1, DistrictMapper.getDistrictFromPrefix(-1));
    }

    @Test
    public void getDistrictFromPrefix_prefix83_returnsNegativeOne() {
        // 83 is just above the valid maximum of 82
        assertEquals(-1, DistrictMapper.getDistrictFromPrefix(83));
    }

    @Test
    public void getDistrictFromPrefix_prefix82_returnsNineteen() {
        // 82 is the maximum valid prefix
        assertEquals(19, DistrictMapper.getDistrictFromPrefix(82));
    }

    @Test
    public void getDistrictFromPrefix_prefix01_returnsOne() {
        // 1 is the minimum valid prefix
        assertEquals(1, DistrictMapper.getDistrictFromPrefix(1));
    }

    @Test
    public void getDistrictFromPrefix_remainingRepresentativePrefixes_returnExpectedDistricts() {
        int[][] cases = {
            {2, 1},
            {3, 1},
            {4, 1},
            {5, 1},
            {9, 4},
            {10, 4},
            {11, 5},
            {12, 5},
            {13, 5},
            {14, 3},
            {15, 3},
            {16, 3},
            {17, 6},
            {18, 7},
            {19, 7},
            {20, 8},
            {21, 8},
            {22, 9},
            {23, 9},
            {24, 10},
            {25, 10},
            {26, 10},
            {27, 10},
            {28, 11},
            {29, 11},
            {30, 11},
            {31, 12},
            {32, 12},
            {33, 12},
            {34, 13},
            {35, 13},
            {36, 13},
            {37, 13},
            {38, 14},
            {39, 14},
            {40, 14},
            {41, 14},
            {42, 15},
            {43, 15},
            {44, 15},
            {45, 15},
            {46, 16},
            {47, 16},
            {48, 16},
            {49, 17},
            {50, 17},
            {51, 18},
            {52, 18},
            {53, 19},
            {54, 19},
            {55, 19},
            {56, 20},
            {57, 20},
            {58, 21},
            {59, 21},
            {60, 22},
            {61, 22},
            {62, 22},
            {63, 22},
            {64, 22},
            {65, 23},
            {66, 23},
            {67, 23},
            {68, 23},
            {69, 24},
            {70, 24},
            {71, 24},
            {72, 25},
            {73, 25},
            {74, 27},
            {75, 27},
            {76, 27},
            {77, 26},
            {78, 26}
        };

        for (int[] testCase : cases) {
            assertEquals(testCase[1], DistrictMapper.getDistrictFromPrefix(testCase[0]),
                    "Unexpected district for prefix " + testCase[0]);
        }
    }
}
