package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExecuteTest {

    @Test
    public void testInfixToPostfix() {
        Execute executor = new Execute();

        assertEquals("3 4 +", executor.infix_to_postfix("3 + 4"));
        assertEquals("3 4 2 * +", executor.infix_to_postfix("3 + 4 * 2"));
        assertEquals("3 4 2 * + 7 -", executor.infix_to_postfix("3 + 4 * 2 - 7"));
        assertEquals("3 4 2 * + 7 5 ^ -", executor.infix_to_postfix("3 + 4 * 2 - 7 ^ 5"));
        assertEquals("3 4 2 * + 7 5 ^ 2 / -", executor.infix_to_postfix("3 + 4 * 2 - 7 ^ 5 / 2"));
        assertEquals("1 2 + 3 *", executor.infix_to_postfix("(1 + 2) * 3"));
        assertEquals("1 2 + 3 4 + *", executor.infix_to_postfix("(1 + 2) * (3 + 4)"));
    }

    @Test
    public void testExecute() {
        Execute executor = new Execute();

        assertEquals(7.0, executor.execute("3 4 +"), 0.001);
        assertEquals(14.0, executor.execute("3 4 + 2 *"), 0.001);
        assertEquals(11.0, executor.execute("3 4 2 * +"), 0.001);
        assertEquals(4.0, executor.execute("3 4 2 * + 7 -"), 0.001);
        assertEquals(-16796.0, executor.execute("3 4 2 * + 7 5 ^ -"), 0.001);
        assertEquals(-8392.5, executor.execute("3 4 2 * + 7 5 ^ 2 / -"), 0.001);
        assertEquals(9.0, executor.execute("1 2 + 3 *"), 0.001);
        assertEquals(21.0, executor.execute("1 2 + 3 4 + *"), 0.001);
    }

    @Test
    public void testIsOperator() {
        Execute executor = new Execute();

        assertTrue(executor.is_operator('+'));
        assertTrue(executor.is_operator('-'));
        assertTrue(executor.is_operator('*'));
        assertTrue(executor.is_operator('/'));
        assertTrue(executor.is_operator('^'));
        assertFalse(executor.is_operator('a'));
        assertFalse(executor.is_operator(' '));
    }

    @Test
    public void testIsDigit() {
        Execute executor = new Execute();

        assertTrue(executor.is_digit('0'));
        assertTrue(executor.is_digit('5'));
        assertTrue(executor.is_digit('9'));
        assertFalse(executor.is_digit('a'));
        assertFalse(executor.is_digit(' '));
    }
}
