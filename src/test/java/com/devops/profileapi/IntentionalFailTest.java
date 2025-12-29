package com.devops.profileapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class IntentionalFailTest {
    @Test
    void intentionalFailure() {
        // This test will always fail and cause `mvn verify` to fail
        fail("Intentional failure to verify CI detects a failing build");
    }
}
