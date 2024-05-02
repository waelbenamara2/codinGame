package com.demo;

import java.util.Optional;

public class CodingGame {

    public static void main(String[] args) {
        //Exercice 1
        System.out.println(checkString("not empty"));
        System.out.println(checkString(null));


        //Exercice 2
        // Creating a person with an optional address
        Optional<Address> address = Optional.of(new Address("123 Rue HENRI BARBUSSE"));
        Optional<Person> person = Optional.of(new Person("Wael amara", address));

        // Using map() to access the address
        Optional<String> street = person.map(Person::getAddress)  //  Gets Optional<Address>
                .flatMap(addr -> addr.map(Address::getStreet)); // Accesses the street

        System.out.println(street);

        //Exercice 3
        Optional<String> emptyOptional = Optional.empty();
        try {
            String value2 = getValue(emptyOptional);
            System.out.println("Value 2: " + value2); // This line won't be reached
        } catch (CustomException e) {
            System.err.println("Error: " + e.getMessage()); // Output: Error: Value is not present
        }


        //Exercice 4
        Optional<Integer> optionalNumberEven = Optional.of(10);
        String message1 = checkNumber(optionalNumberEven);
        System.out.println("Message 1: " + message1); // Output: Message 1: Number is even

        Optional<Integer> optionalNumberOdd = Optional.of(7);
        String message2 = checkNumber(optionalNumberOdd);
        System.out.println("Message 2: " + message2); // Output: Message 2: Number is not even
    }


    public static String checkString(String s) {
        Optional<String> optional = Optional.ofNullable(s);
        return optional.orElse("UNKNOWN");
    }


    public static <T> T getValue(Optional<T> optional) {
        return optional.orElseThrow(() -> new CustomException("Value is not present"));
    }

    static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }

    public static String checkNumber(Optional<Integer> optionalNumber) {
        return optionalNumber.filter(num -> num % 2 == 0) // Check if the number is even
                .map(num -> "Number is even") // Return message if the number is even
                .orElse("Number is odd"); // Otherwise, return a default message
    }







    static class Address {
        private String street;

        public Address(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }
    }

    static class Person {
        private String name;
        private Optional<Address> address;

        public Person(String name, Optional<Address> address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public Optional<Address> getAddress() {
            return address;
        }
    }
}
