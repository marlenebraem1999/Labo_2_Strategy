package model;

public class Caesercijfer implements VersleutelGedrag {
    @Override
    public String codeer(String string) {
        return "test";
    }

    @Override
    public String decodeer(String string) {
        return string;
    }
}
