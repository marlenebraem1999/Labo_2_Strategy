package model;

public class Spiegeling implements VersleutelGedrag {
    @Override
    public String codeer(String string) {
        return "spiegel";
    }

    @Override
    public String decodeer(String string) {
        return string;
    }
}
