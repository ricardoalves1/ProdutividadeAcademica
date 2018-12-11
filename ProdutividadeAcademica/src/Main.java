import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int opc = -1;

        while (opc != 0) {

            System.out.println("\nSistema de Gestão de Produtividade Acadêmica\n" +
                    "(1) Adicionar colaborador\n" +
                    "(2) Novo projeto\n" +
                    "(3) Nova produção acadêmica\n" +
                    "(4) Alocar colaborador em um projeto\n" +
                    "(5) Dados de um Colaborador\n" +
                    "(6) Dados de um Projeto\n" +
                    "(7) Todos os colaboradores\n" +
                    "(8) Todos os projetos\n" +
                    "(9) Alterar status de um projeto\n" +
                    "(10) Associar publicação a um projeto\n" +
                    "(11) Editar produção acadêmica\n" +
                    "(12) Relatório\n" +
                    "(0) Sair");

            opc = input.nextInt();

            /* ------------------------------------ */
            Laboratorio laboratorio = new Laboratorio();
            Colaborador colaborador = new Colaborador();
            Projeto projeto = new Projeto();
            Publicacao publicacao = new Publicacao();
            /* ------------------------------------ */

            switch (opc){
                case 1: // Adicionar colaborador
                    colaborador.addColaborador();
                    break;
                case 2: // Novo projeto
                    projeto.novoProjeto();
                    break;
                case 3: // Nova produção acadêmica
                    laboratorio.novaProducao();
                    break;
                case 4: // Alocar colaborador
                    projeto.alocarColaborador();
                    break;
                case 5: // Dados de um colaborador
                    colaborador.dadosColaborador();
                    break;
                case 6: // Dados de um projeto
                    projeto.dadosProjeto();
                    break;
                case 7: // Listar Colaboradores
                    laboratorio.colaboradores();
                    break;
                case 8: // Listar Projetos
                    laboratorio.projetos();
                    break;
                case 9: // Alterar status
                    projeto.alterarStatus();
                    break;
                case 10: // Associar publicação a um projeto
                    publicacao.associarPublicacao(null);
                    break;
                case 11: // Editar produção acadêmica
                    publicacao.editarPublicacao();
                    break;
                case 12: // Relatório
                    laboratorio.relatorio();
                    break;
            }

        }

    }

}
