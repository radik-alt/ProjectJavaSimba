package com.example.projectjavasimba.classes.seven;

public class Client {

    private Order order;

    public void getOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void showOrder() {
        System.out.println("Ваш заказ: ");
        order.showOrder();
    }

    public void pay() {
        if(order.isPayment()) {
            System.out.println("Вы уже оплатили заказ");
        } else {
            order.setPayment(true);
        }
    }

    public void take() {
        if(!order.isPayment()) {
            System.out.println("Вы ещё не оплатили товар");
        } else if(!order.isRegister()) {
            System.out.println("Ваша заявка ещё не обработана");
        } else {
            System.out.println("Спасибо за покупку!");
        }
    }
}
