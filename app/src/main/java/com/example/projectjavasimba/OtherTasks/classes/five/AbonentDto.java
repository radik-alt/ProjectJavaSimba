package com.example.projectjavasimba.OtherTasks.classes.five;

public class AbonentDto implements Comparable <AbonentDto>{

    private Long id;
    private String number;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String adress;
    private String numberCreditCard;
    private String numberDebitCard;
    private Long timeIntarnationalConversation;
    private Long timeUrbanConversation;

    public AbonentDto(Long id, String number,  String firstName, String lastName, String patronymic,
                      String adress, String numberCreditCard, String numberDebitCard,
                      Long timeIntarnationalConversation, Long timeUrbanConversation){
        this.id = id;
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.adress = adress;
        this.numberCreditCard = numberCreditCard;
        this.numberDebitCard = numberDebitCard;
        this.timeIntarnationalConversation = timeIntarnationalConversation;
        this.timeUrbanConversation = timeUrbanConversation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNumberCreditCard(String numberCreditCard) {
        this.numberCreditCard = numberCreditCard;
    }

    public void setNumberDebitCard(String numberDebitCard) {
        this.numberDebitCard = numberDebitCard;
    }

    public void setTimeIntarnationalConversation(Long timeIntarnationalConversation) {
        this.timeIntarnationalConversation = timeIntarnationalConversation;
    }

    public void setTimeUrbanConversation(Long timeUrbanConversation) {
        this.timeUrbanConversation = timeUrbanConversation;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getAdress() {
        return adress;
    }

    public String getNumberCreditCard() {
        return numberCreditCard;
    }

    public String getNumberDebitCard() {
        return numberDebitCard;
    }

    public Long getTimeIntarnationalConversation() {
        return timeIntarnationalConversation;
    }

    public Long getTimeUrbanConversation() {
        return timeUrbanConversation;
    }

    @Override
    public String toString() {
        return "AbonentDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", adress='" + adress + '\'' +
                ", numberCreditCard='" + numberCreditCard + '\'' +
                ", numberDebitCard='" + numberDebitCard + '\'' +
                ", timeIntarnationalConversation=" + timeIntarnationalConversation +
                ", timeUrbanConversation=" + timeUrbanConversation +
                '}';
    }


    @Override
    public int compareTo(AbonentDto abonent) {
        int result = this.lastName.compareTo(abonent.lastName);

        if (result == 0) {
            result = this.firstName.compareTo(abonent.firstName);
        }
        return result;
    }
}
