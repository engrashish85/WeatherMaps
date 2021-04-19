package main.java.WeatherMaps;

public enum Top10Beaches {
    BEACH_1 ("BONDI", "2026"),
    BEACH_2 ("MANLY", "2095"),
    BEACH_3 ("BRONTE", "2024"),
    BEACH_4 ("TAMARAMA", "2026"),
    BEACH_5 ("MURRAY ROSE", "2028"),
    BEACH_6 ("AVALON", "2107"),
    BEACH_7 ("MAROUBRA", "2035"),
    BEACH_8 ("COOGEE", "2034"),
    BEACH_9 ("CLOVELLY", "2031"),
    BEACH_10 ("PALM", "2108");

    private final String postalCode;
    private final String beachName;

    Top10Beaches(String beachName, String postalCode) {
        this.beachName = beachName;
        this.postalCode = postalCode;

    }

    public String returnBeachName () {
        return this.beachName;
    }

    public String returnPostalCodeOfBeach() {
        return this.postalCode;
    }
}
