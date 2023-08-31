package com.javarush.task.task20.task2001;

import java.io.*;

public class Asset {
    public Asset(String name, double price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (Double.compare(asset.price, price) != 0) return false;
        return name != null ? name.equals(asset.name) : asset.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    public void save(OutputStream stream){
        PrintStream printer = new PrintStream(stream);
        if(this.name == null)
            printer.println(0);
        else{
            printer.println(1);
            printer.println(this.name);
        }
        printer.println(this.price);
    }
    public static Asset load(BufferedReader reader) throws IOException {
        String name = null;
        double value;
        String c = reader.readLine();
        if (c.equals("1"))
            name = reader.readLine();
        value = Double.parseDouble(reader.readLine());
        return new Asset(name, value);
    }
}
