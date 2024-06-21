public class Doacao {
    private String tipo;
    private int quantidade;
    private String data;

    public Doacao(String tipo, int quantidade, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getData() {
        return data;
    }
}
