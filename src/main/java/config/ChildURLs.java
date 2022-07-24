package main.java.config;

public enum ChildURLs {
    GET_FACTS () {
        @Override
        public String toString() { return "/facts";}
    },
    GET_FACTS_ID () {
        @Override
        public String toString() { return "/facts/{id}";}
    }
}
