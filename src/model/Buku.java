package model;

public class Buku {
    private int id;
    private String judul;
    private int status;

    public Buku(int id, String judul, int status) {
        this.id = id;
        this.judul = judul;
        this.status = status;
    }

    public Buku(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public Buku() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
