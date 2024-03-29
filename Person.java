public class Person {
    String name;
    String surname;
    String email;
    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    // Getters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Setter have been used because the coursework instructed to do so
     * But they have not been implemented in the code due to them having no usages.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


