package model;


public class Riwayat {
    private int id;
    private String pinjam;
    private String kembali;
    private String pengembalian;
    private int denda;
    private int buku_id;
    private int user_id;

    public int getBuku_id() {
        return buku_id;
    }

    public void setBuku_id(int buku_id) {
        this.buku_id = buku_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Riwayat(int id, String pinjam, String kembali, String pengembalian, int denda, int buku_id, int user_id) {
        this.id = id;
        this.pinjam = pinjam;
        this.kembali = kembali;
        this.pengembalian = pengembalian;
        this.denda = denda;
        this.buku_id = buku_id;
        this.user_id = user_id;
    }

    public Riwayat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinjam() {
        return pinjam;
    }

    public void setPinjam(String pinjam) {
        this.pinjam = pinjam;
    }

    public String getKembali() {
        return kembali;
    }

    public void setKembali(String kembali) {
        this.kembali = kembali;
    }

    public String getPengembalian() {
        return pengembalian;
    }

    public void setPengembalian(String pengembalian) {
        this.pengembalian = pengembalian;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }
}
