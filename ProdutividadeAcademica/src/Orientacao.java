public class Orientacao {

    private String titulo;
    private int ano;
    private Colaborador orientador;

    public Orientacao(String titulo, int ano, Colaborador orientador) {
        this.titulo = titulo;
        this.ano = ano;
        this.orientador = orientador;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Colaborador getOrientador() {
        return orientador;
    }

    public void setOrientador(Colaborador orientador) {
        this.orientador = orientador;
    }
}
