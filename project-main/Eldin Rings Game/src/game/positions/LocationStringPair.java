package game.positions;

import engine.positions.Location;

/**
 * Allows a location to have a string name
 */
public class LocationStringPair {
    /**
     * Location
     */
    private Location location;

    /**
     * Location name
     */
    private String stringValue;

    /**
     * Constructor
     * @param location Location
     * @param stringValue Location name
     */
    public LocationStringPair(Location location, String stringValue) {
        this.location = location;
        this.stringValue = stringValue;
    }

    /**
     * Getter for location
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for location name
     * @return location name
     */
    public String getStringValue() {
        return stringValue;
    }
}
