package com.example.projectjavasimba.classes.seven;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> orderlist = new ArrayList<Product>();    //продукты в заказе
    private boolean payment = false;    //оплачен ли заказ
    private boolean register = false;   //обработан ли заказ Администратором

    //добавить группу товаров к Заказу
    public void addProdToOrder(ArrayList<Product> orderlist) {
        this.orderlist = orderlist;
    }

    //добавить товар к Заказу
    public void addProdToOrder(Product product) {
        orderlist.add(product);
    }

    //показать Заказ
    public void showOrder() {
        for (Product e: orderlist) {
            System.out.println (e);
        }
    }

    //оплачен или нет
    public boolean isPayment() {
        return payment;
    }

    //оплачен (да/нет)
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
