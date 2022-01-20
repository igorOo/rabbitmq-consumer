package ru.technoteinfo.emart.Helpers;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;

public class Inet implements Serializable {

    private final String address;

    public Inet(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Inet inet = (Inet) o;

        return address != null ?
                address.equals(inet.address) :
                inet.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ?
                address.hashCode() :
                0;
    }

    public InetAddress toInetAddress() {
        try {
            String host = address.replaceAll(
                    "\\/.*$", ""
            );
            return Inet4Address.getByName(host);
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
