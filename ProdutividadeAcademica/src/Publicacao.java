import java.util.ArrayList;
import java.util.Scanner;

public class Publicacao {

    private String titulo;
    private String conferencia;
    private int ano;
    private Projeto projetoAssociado;
    private ArrayList<Colaborador> autores = new ArrayList<>();

    public Publicacao() {}

    public Publicacao(String titulo, String conferencia, int ano) {
        this.titulo = titulo;
        this.conferencia = conferencia;
        this.ano = ano;
    }

    Scanner input = new Scanner(System.in);

    public void associarPublicacao(Publicacao publicacao) {


        if (publicacao == null) {
            System.out.print("Associar Publicação\nTítulo da Publicação: ");
            publicacao = new Publicacao().buscarPublicacao(input.nextLine());
        }

        if (publicacao != null) {

            System.out.print("Título do Projeto: ");
            Projeto projeto = new Projeto().buscarProjeto(input.nextLine());

            if (projeto != null) {
                if (projeto.getProducaoAcademica().contains(publicacao)) {
                    System.out.println("Essa publicação já está associada ao projeto");
                }
                else if (!projeto.getStatus().equals("Em andamento")) {
                    System.out.println("Não é possível associar publicações a esse projeto");
                }
                else {
                    projeto.setProducaoAcademica(publicacao);
                    publicacao.setProjetoAssociado(projeto);
                    System.out.println("A Publicação foi associada ao Projeto");
                }
            }
            else {
                System.out.println("Não foi encontrado projeto com esse título");
            }

        }
        else {
            System.out.println("Não foi encontrada publicação com esse título");
        }

    }

    public void editarPublicacao() {

        System.out.println("Editar Publicação");

        System.out.print("\nTítulo da Publicação: ");
        Publicacao publicacao = new Publicacao().buscarPublicacao(input.nextLine());

        if (publicacao != null) {
            System.out.println(" - Editar Publicação - \n" +
                    "(1) Título\n" +
                    "(2) Nome da conferência\n" +
                    "(3) Ano de publicação\n" +
                    "(4) Projeto de Pesquisa associado\n" +
                    "(5) Adicionar autor\n" +
                    "(0) Cancelar");

            int opc = input.nextInt();
            input.nextLine();

            if (opc == 1) {
                System.out.print("Novo título: ");
                String titulo = input.nextLine();

                if (publicacao.getTitulo().equals(titulo)) {
                    System.out.println("Título continua o mesmo");
                }
                else if (new Publicacao().buscarPublicacao(titulo) != null) {
                    System.out.println("Já existe uma publicação com esse nome\nCadastro cancelado");
                }
                else {
                    publicacao.setTitulo(titulo);
                }
            }
            else if (opc == 2) {
                System.out.print("Nome da conferência: ");
                String conferencia = input.nextLine();

                if (publicacao.getConferencia().equals(conferencia)) {
                    System.out.println("Nome da conferência continua o mesmo");
                }
                else {
                    publicacao.setConferencia(conferencia);
                }
            }
            else if (opc == 3) {
                System.out.print("Ano de publicação: ");
                int ano = input.nextInt();
                input.nextLine();

                if (publicacao.getAno() == ano) {
                    System.out.println("Ano de publicação continua o mesmo");
                }
                else {
                    publicacao.setAno(ano);
                }
            }
            else if (opc == 4) {
                System.out.println("Projeto de Pesquisa associado");
                if (publicacao.getProjetoAssociado() != null) {
                    System.out.println("Projeto de pesquisa atual: " + publicacao.getProjetoAssociado().getTitulo());
                    System.out.println("Deseja alterar o projeto associado?\n(1) Sim \t (2) Não");

                    if (input.nextInt() == 1) {
                        new Publicacao().associarPublicacao(publicacao);
                    }
                    else {
                        System.out.println("Operação cancelada");
                    }
                    input.nextLine();
                }
            }
            else if (opc == 5) {
                System.out.println("Adicionar Autor");

                while (true) {
                    System.out.print("Nome: ");
                    Colaborador colaborador = new Colaborador().buscarColaborador(input.nextLine());

                    if (colaborador != null) {
                        if (publicacao.getAutores().contains(colaborador)) {
                            System.out.println(colaborador.getNome() + " já é autor da publicação");
                        }
                        else {
                            publicacao.setAutores(colaborador);
                            System.out.println(colaborador.getNome() + " foi adicionado como autor");
                        }
                    }
                    else {
                        System.out.println("Não foi encontrado colaborador com esse nome");
                    }

                    System.out.println("(1) Continuar \t (2) Parar");
                    if (input.nextInt() != 1) {
                        return;
                    }
                }
            }
        }

    }

    public Publicacao buscarPublicacao(String titulo) {

        for (Publicacao i: Laboratorio.getPublicacoes()) {
            if (i.getTitulo().equals(titulo)) {
                return i;
            }
        }

        return null;

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

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public Projeto getProjetoAssociado() {
        return projetoAssociado;
    }

    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

    public ArrayList<Colaborador> getAutores() {
        return autores;
    }

    public void setAutores(Colaborador autor) {
        this.autores.add(autor);
    }
}
