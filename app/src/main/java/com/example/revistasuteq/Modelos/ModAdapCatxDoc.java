package com.example.revistasuteq.Modelos;

import java.util.List;

public class ModAdapCatxDoc {
    private String NombCateg;
    private List<ModDocumentos> LisDocxCate;

    public ModAdapCatxDoc(String nombCateg, List<ModDocumentos> lisDocxCate) {
        NombCateg = nombCateg;
        LisDocxCate = lisDocxCate;
    }

    public String getNombCateg() {
        return NombCateg;
    }

    public void setNombCateg(String nombCateg) {
        NombCateg = nombCateg;
    }

    public List<ModDocumentos> getLisDocxCate() {
        return LisDocxCate;
    }

    public void setLisDocxCate(List<ModDocumentos> lisDocxCate) {
        LisDocxCate = lisDocxCate;
    }
}
