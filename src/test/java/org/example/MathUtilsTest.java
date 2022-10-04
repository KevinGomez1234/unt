package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MathUtilsTest {
    @Captor
    ArgumentCaptor<Integer> acInteger;
    @Captor
    ArgumentCaptor<String> acString;

    @Test
    void test() {
        MathUtils mockMathUtils = mock(MathUtils.class);
        when(mockMathUtils.add(1, 1)).thenReturn(2);
        when(mockMathUtils.isInteger(anyString())).thenReturn(true);

        assertEquals(2, mockMathUtils.add(1, 1));
        assertTrue(mockMathUtils.isInteger("1"));
        assertTrue(mockMathUtils.isInteger("999"));

        ArgumentCaptor<Integer> c = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> s = ArgumentCaptor.forClass(String.class);
        verify(mockMathUtils).add(c.capture(), c.capture());

        List allValues = c.getAllValues();
        assertEquals(Arrays.asList(1, 1), allValues);
        verify(mockMathUtils, times(2)).isInteger(s.capture());
        List allStringValues = s.getAllValues();
        assertEquals(List.of("1", "999"), allStringValues);
    }
}
