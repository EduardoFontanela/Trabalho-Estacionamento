public class Vaga {
    int numero;
    String tamanho;
    boolean disponivel = true;

    public Vaga(int numero, String tamanho) {
        this.numero = numero;
        this.tamanho = tamanho;
    }

    public void ocupar() {
        disponivel = false;
    }

    public void liberar() {
        disponivel = true;
    }
}
