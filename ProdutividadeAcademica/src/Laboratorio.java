import java.util.ArrayList;
import java.util.Scanner;

public class Laboratorio {

    private static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static ArrayList<Projeto> projetos = new ArrayList<>();
    private static ArrayList<Publicacao> publicacoes = new ArrayList<>();
    private static ArrayList<Orientacao> orientacoes = new ArrayList<>();

    Scanner input = new Scanner(System.in);

    public void novaProducao() {

        System.out.println("Nova Produção Acadêmica\n(1) Publicação \t (2) Orientação");
        if (input.nextInt() == 1) {

            input.nextLine();
            System.out.print("\n - Nova Publicação - \nTítulo: ");
            String titulo = input.nextLine();

            Publicacao checarNome = new Publicacao().buscarPublicacao(titulo);
            if (checarNome != null) {
                System.out.println("Já existe uma publicação com esse nome\nCadastro cancelado");
                return;
            }

            System.out.print("Nome da conferência: ");
            String conferencia = input.nextLine();

            System.out.print("Ano de publicação: ");
            int ano = input.nextInt();
            input.nextLine();

            Publicacao novaPublicacao = new Publicacao(titulo, conferencia, ano);

            System.out.println("Projeto de pesquisa associado?\n(1) Sim \t (2) Não");
            if (input.nextInt() == 1) {
                input.nextLine();

                Projeto projeto = null;
                while (projeto == null) {
                    System.out.print("Nome do projeto: ");
                    String entrada = input.nextLine();

                    if (entrada.equals("-1")) break;

                    projeto = new Projeto().buscarProjeto(entrada);
                    if (projeto == null) {
                        System.out.println("Projeto não encontrado\n (-1) Cancelar");
                    }
                    else {
                        projeto.setProducaoAcademica(novaPublicacao);
                        novaPublicacao.setProjetoAssociado(projeto);
                        System.out.println("Publicação associada ao projeto");
                    }
                }

            }


            System.out.println(" - Autores - ");
            boolean aux = true;
            while (aux) {
                System.out.print("Nome: ");
                Colaborador colaborador = new Colaborador().buscarColaborador(input.nextLine());
                if (colaborador != null) {
                    if (novaPublicacao.getAutores().contains(colaborador)) {
                        System.out.println("Esse colaborador já foi adicionado como autor");
                        if (novaPublicacao.getAutores().size() > 0) {
                            System.out.println("(1) Continuar \t (2) Parar");
                            if (input.nextInt() == 2) {
                                aux = false;
                            }
                            input.nextLine();
                        }
                        else {
                            System.out.println(" - Tente novamente - ");
                        }
                    }
                    else {
                        novaPublicacao.setAutores(colaborador);
                        colaborador.setPublicacao(novaPublicacao);
                        System.out.println(" - Autor adicionado - \n(1) Continuar \t (2) Parar");
                        if (input.nextInt() == 2) {
                            aux = false;
                        }
                        input.nextLine();
                    }
                }
                else {
                    System.out.println("Nenhum colaborador encontrado com esse nome\n - Tente novamente - ");
                }

            }

            Laboratorio.setPublicacoes(novaPublicacao);
            System.out.println("Publicação cadastrada");

        }
        else {

            System.out.print("\n - Nova Orientação - \nTítulo: ");
            String titulo = input.nextLine();

            System.out.print("Ano: ");
            int ano = input.nextInt();
            input.nextLine();

            Colaborador colaborador = null;
            while (colaborador == null) {
                System.out.print("Orientador: ");
                colaborador = new Colaborador().buscarColaborador(input.nextLine());

                if (colaborador == null) {
                    System.out.println("Nenhum colaborador foi encontrado com esse nome\n - Tente Novamente - ");
                } else if (!colaborador.getTipo().equals("professor")) {
                    System.out.println("Só professores podem ser orientadores\n - Tenta Novamente - ");
                }
            }

            Orientacao orientacao = new Orientacao(titulo, ano, colaborador);
            colaborador.setOrientacao(orientacao);
            Laboratorio.setOrientacoes(orientacao);
            System.out.println("Orientação cadastrada");
        }

    }

    public void relatorio() {

        System.out.println("Relatório de Produção Acadêmica do Laboratório\n");
        System.out.println("\tNúmero de colaboradores: " + colaboradores.size());

        int num = 0;
        for (Projeto i: projetos) {
            if (i.getStatus().equals("Em elaboração")) {
                num++;
            }
        }
        System.out.println("\tNúmero de projetos em elaboração: " + num);

        num = 0;
        for (Projeto i: projetos) {
            if (i.getStatus().equals("Em andamento")) {
                num++;
            }
        }
        System.out.println("\tNúmero de projetos em andamento: " + num);

        num = 0;
        for (Projeto i: projetos) {
            if (i.getStatus().equals("Concluído")) {
                num++;
            }
        }
        System.out.println("\tNúmero de projetos concluídos: " + num);
        System.out.println("\tNúmero total de projetos: " + projetos.size());
        System.out.println("\tNúmero total de produção acadêmica: " + (publicacoes.size() + orientacoes.size()));
        System.out.println("\tNúmero de publicações: " + publicacoes.size());
        System.out.println("\tNúmero de orientações: " + orientacoes.size());

        System.out.println("\n-----------------------------------------------");
    }

    public void colaboradores() {

        System.out.println("Colaboradores:");

        for (Colaborador i: colaboradores) {
            System.out.println(" - " + i.getNome());
        }

    }

    public void projetos() {
        System.out.println("Projetos:");

        for (Projeto i: projetos) {
            System.out.println(" - " + i.getTitulo() + " '" + i.getStatus() + "'");
        }

    }

    public static ArrayList<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public static ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public static ArrayList<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public static ArrayList<Orientacao> getOrientacoes() {
        return orientacoes;
    }

    public static void setColaboradores(Colaborador colaborador) {
        colaboradores.add(colaborador);
    }

    public static void setProjetos(Projeto projeto) {
        projetos.add(projeto);
    }

    public static void setPublicacoes(Publicacao publicacao) {
        Laboratorio.publicacoes.add(publicacao);
    }

    public static void setOrientacoes(Orientacao orientacao) {
        Laboratorio.orientacoes.add(orientacao);
    }
}
