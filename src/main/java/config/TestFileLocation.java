package main.java.config;

public enum TestFileLocation {
    HEROKUAPP_FACTS {
        @Override
        public String toString() { return "/testData/AutomationEngineerDatasheet.xls"; }
    }
}
