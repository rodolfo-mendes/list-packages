package listpackages;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ListPackages listPackages = new ListPackages(System.out);
        listPackages.listPackages(new File("").getAbsoluteFile());
    }
}
