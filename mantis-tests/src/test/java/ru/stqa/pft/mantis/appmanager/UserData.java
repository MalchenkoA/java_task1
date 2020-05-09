package ru.stqa.pft.mantis.appmanager;

public class UserData {

        private String username;
        private String password;
        private String email;

        public String getEmail() {
        return email;
    }

        public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

        public String getUsername() {
        return username;
    }

        public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

        public String getPassword() {
        return password;
    }

        public UserData withPassword(String password) {
        this.password = password;
        return this;
    }


    }
