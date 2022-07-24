package main.java.config;

public enum Globals {
    PROJECT_PATH () {
        @Override
        public String toString() { return System.getProperty("user.dir");}
    },
    TEST_DATA_LOCATION() {
        public String toString() { return Globals.PROJECT_PATH.toString() + "/src/main/resources";}
    },
    PROPERTIES_FILE_LOCATION() {
        public String toString() { return Globals.PROJECT_PATH.toString() + "/src/main/java/config/config.properties"; }
    }

}
