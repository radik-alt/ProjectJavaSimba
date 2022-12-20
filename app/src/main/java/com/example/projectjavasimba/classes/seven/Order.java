package com.example.projectjavasimba.classes.seven;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> orderlist = new ArrayList<Product>();    //продукты в заказе
    private boolean payment = false;    //оплачен ли заказ
    private boolean register = false;   //обработан ли заказ Администратором

    public void addProdToOrder(ArrayList<Product> orderlist) {
        this.orderlist = orderlist;
    }

    public void addProdToOrder(Product product) {
        orderlist.add(product);
    }

    public void showOrder() {
        for (Product e: orderlist) {
            System.out.println (e);
        }
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean s) {
        payment = s;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean s) {
        register = s;
    }

}
