package com.example.revistasuteq.Modelos;

public class ModCategorias {
    private String section;
    private String section_id;

    public ModCategorias(String section, String section_id) {
        this.section = section;
        this.section_id = section_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }
}
