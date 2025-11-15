package org.example;

import java.util.*;

public class PhoneBook {

    private final Map<String, Set<String>> data = new LinkedHashMap<>();

    public void add(String lastName, String phone) {
        data.computeIfAbsent(lastName, k -> new LinkedHashSet<>()).add(phone);
    }

    public Set<String> get(String lastName) {
        Set<String> phones = data.get(lastName);
        return phones == null ? Collections.emptySet()
                : Collections.unmodifiableSet(phones);
    }
}