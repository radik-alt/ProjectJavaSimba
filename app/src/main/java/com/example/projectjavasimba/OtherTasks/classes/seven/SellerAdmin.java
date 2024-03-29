package com.example.projectjavasimba.OtherTasks.classes.seven;

import java.util.ArrayList;


/*
     VII
     Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
     Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
     Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
    */
public class SellerAdmin {

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Client> blackclients = new ArrayList<Client>();

    public void registerOrder(Client client) {
        if (client.getOrder().isPayment()) {
            client.getOrder().setRegister(true);
        }
    }

    public void addBlackList(Client client){
        blackclients.add(client);
    }

    public void createNewProduct(Product product) {
        products.add(product);
    }

    public void printAllProduct(){
        System.out.println("Все продукты в магазине:");
        for (Product product: products){
            System.out.println(product);
        }
        System.out.println();
    }

    public boolean clientContainsBlackList(Client client){
        return blackclients.contains(client);
    }

    public void printBlackList(Client client){
        if (blackclients.contains(client)){
            System.out.println("Клиент находится в черном списке магазина");
        }
    }
}
