package br.edu.utfpr.aps.bmi.servlet.security;

import java.util.function.BiPredicate;

class Credential {
    private BiPredicate<String, String> isEquals;

    private final String username;
    private final String password;

    Credential(String username, String password) {
        this.username = username;
        this.password = password;

        this.isEquals = (user, pass) -> user.equals(this.username) && pass.equals(this.password);
    }

    boolean isValidCredentials(String username, String password) {
        return this.isEquals.test(username, password);
    }
}
