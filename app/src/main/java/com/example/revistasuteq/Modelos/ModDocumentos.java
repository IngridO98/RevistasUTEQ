package com.example.revistasuteq.Modelos;

import java.util.List;

public class ModDocumentos {

    private String section, publication_id, title, doi, date_published;
    private List<galeys> galeys;
    private List<keywords> keywords;
    private List<authors> authors;

    public ModDocumentos(String section, String publication_id, String title, String doi, String date_published, List<ModDocumentos.galeys> galeys, List<ModDocumentos.keywords> keywords, List<ModDocumentos.authors> authors) {
        this.section = section;
        this.publication_id = publication_id;
        this.title = title;
        this.doi = doi;
        this.date_published = date_published;
        this.galeys = galeys;
        this.keywords = keywords;
        this.authors = authors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public List<ModDocumentos.galeys> getGaleys() {
        return galeys;
    }

    public void setGaleys(List<ModDocumentos.galeys> galeys) {
        this.galeys = galeys;
    }

    public List<ModDocumentos.keywords> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<ModDocumentos.keywords> keywords) {
        this.keywords = keywords;
    }

    public List<ModDocumentos.authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<ModDocumentos.authors> authors) {
        this.authors = authors;
    }

    public class galeys{
        private String galley_id, label, file_id, UrlViewGalley;

        public galeys(String galley_id, String label, String file_id, String urlViewGalley) {
            this.galley_id = galley_id;
            this.label = label;
            this.file_id = file_id;
            UrlViewGalley = urlViewGalley;
        }

        public String getGalley_id() {
            return galley_id;
        }

        public void setGalley_id(String galley_id) {
            this.galley_id = galley_id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getUrlViewGalley() {
            return UrlViewGalley;
        }

        public void setUrlViewGalley(String urlViewGalley) {
            UrlViewGalley = urlViewGalley;
        }
    }

    public class keywords{
        private String keyword;

        public keywords(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

    public class authors{
        private String nombres, filiacion, email;

        public authors(String nombres, String filiacion, String email) {
            this.nombres = nombres;
            this.filiacion = filiacion;
            this.email = email;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getFiliacion() {
            return filiacion;
        }

        public void setFiliacion(String filiacion) {
            this.filiacion = filiacion;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
