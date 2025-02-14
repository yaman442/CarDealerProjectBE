package com.binary.carDealerApp.classCarDealerApp.dto;

/**
 * Data Transfer Object (DTO) for Dealer information.
 * This class is used to transfer dealer data between different layers of the application,
 * particularly when sending or receiving dealer information to/from clients.
 *
 * Key points:
 * 1. Mutable design with getter and setter for the name field.
 * 2. Contains only the name of the dealer, suggesting a simplified representation.
 * 3. Includes a custom toString() method for easy debugging and logging.
 */
public class DealerDto {

    private String name;

    /**
     * Constructs a new DealerDto with the specified name.
     *
     * @param name The name of the dealer.
     */
    public DealerDto(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the dealer.
     *
     * @return The dealer's name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the dealer.
     *
     * @param name The new name to set for the dealer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of the DealerDto object.
     *
     * @return A string containing the dealer's name.
     */
    @Override
    public String toString() {
        return "DealerDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
