package model;

public class Versleuteling {
    private VersleutelGedrag versleutelGedrag;

    public Versleuteling(VersleutelGedrag versleutelGedrag) {
        this.setVersleutelGedrag(versleutelGedrag);
    }

    public void setVersleutelGedrag(VersleutelGedrag versleutelGedrag) {
        this.versleutelGedrag = versleutelGedrag;
    }

    public String codeer(String boodschap) {
        return versleutelGedrag.codeer(boodschap);
    }

    public String decodeer(String boodschap) {
        return versleutelGedrag.decodeer(boodschap);
    }
}
