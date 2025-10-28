package se.apiva;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestAssumption {


    @Test
    public void onlyRunsOnWindows() {
        assumeTrue(
            System.getProperty("os.name").toLowerCase().contains("windows"),
            "Körs bara på Windows"
        );
    }

    @Test
    public void onlyRunsOnMac() {
        assumeTrue(
                System.getProperty("os.name").toLowerCase().contains("mac"),
                "Körs bara på Mac"
        );
    }

}
