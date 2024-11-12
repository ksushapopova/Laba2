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
}
