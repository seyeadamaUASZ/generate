package sn.gainde2000.pi.core.tools;

public  class StringProcess {

    public  String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
