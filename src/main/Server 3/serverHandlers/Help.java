package com.Twitter.serverHandlers;

public class Help {
    public static void main(String[] args) {
        String tmp = "1/2/3 4:5:6";

        int num = Integer.parseInt(tmp.split(":")[1]);
        System.out.println(num);
    }
}
