import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Projeto {

    private String status;

    private String titulo;
    private String dataInicio;
    private String dataTermino;
    private String agencia;
    private Float valor;
    private String objetivo;
    private String descricao;
    private ArrayList<Colaborador> professores = new ArrayList<>();
    private ArrayList<Colaborador> participantes = new ArrayList<>();
    private ArrayList<Publicacao>  producaoAcademica = new ArrayList<>();

    public Projeto() {}

    public Projeto(String status, String titulo, String dataInicio, String dataTermino, String agencia, Float valor, String objetivo, String descricao) {
        this.status = status;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agencia = agencia;
        this.valor = valor;
        this.objetivo = objetivo;
        this.descricao = descricao;
    }

    private Scanner input = new Scanner(System.in);

    public void novoProjeto() {

        System.out.print("Novo projeto\nTítulo: ");
        String titulo = input.nextLine();

        if (buscarProjeto(titulo) != null) {
            System.out.println("Já existe projeto com esse nome");
            return;
        }

        System.out.print("Data de início (dd/mm/aaaa): ");
        String dataInicio = input.nextLine();

        System.out.print("Data de término (dd/mm/aaaa): ");
        String dataTermino = input.nextLine();

        System.out.print("Agência financiadora: ");
        String agencia = input.nextLine();

        System.out.print("Valor Financiado: ");
        Float valor = input.nextFloat();
        input.nextLine();

        System.out.print("Objetivo: ");
        String objetivo = input.nextLine();

        System.out.print("Descrição: ");
        String descricao = input.nextLine();

        Projeto novoProjeto = new Projeto("Em elaboração", titulo, dataInicio, dataTermino, agencia, valor, objetivo, descricao);
        int  esc = 1;
        while (esc == 1) {
            System.out.print("Professor: ");
            String professor = input.nextLine();

            for (Colaborador i : Laboratorio.getColaboradores()) {
                if (i.getNome().equals(professor)) {
                    if (!i.getTipo().equals("professor")) {
                        System.out.println("O colaborador precisa ser um professor");
                        break;
                    }

                    novoProjeto.setProfessores(i);
                    i.setProjetos(novoProjeto);

                    System.out.println("O projeto foi criado\n\nIniciar projeto?\n(1) Sim \t (2) Não");
                    if (input.nextInt() == 1) {
                        novoProjeto.setStatus("Em andamento");
                        System.out.println("Projeto em andamento");
                    } else {
                        System.out.println("O projeto não foi iniciado");
                    }
                    Laboratorio.setProjetos(novoProjeto);

                    return;
                }
            }

            System.out.println("Professor não encontrado\n(1) Tentar novamente \t (0) Cancelar");
            esc = input.nextInt();
            input.nextLine();
        }

        System.out.println("Criação do projeto cancelada");
    }

    public void alterarStatus() {

        System.out.print("Alterar Status de um Projeto\nTítulo do projeto: ");
        Projeto projeto = buscarProjeto(input.nextLine());

        if (projeto == null) {
            System.out.println("Não foi encontrado projeto com esse título");
        }
        else {
            if (projeto.getStatus().equals("Concluído")) {
                System.out.println("O projeto já foi concluído");
                return;
            }
            System.out.println("Projeto: " + projeto.getTitulo() + "\nStatus: " + projeto.getStatus());
            System.out.println("Deseja alterar o status do projeto?\n(1) Sim \t (2) Não");

            if (input.nextInt() == 1) {
                if (projeto.getStatus().equals("Em elaboração")) {
                    projeto.setStatus("Em andamento");
                }
                else {
                    if (projeto.getProducaoAcademica().size() > 0) {
                        projeto.setStatus("Concluído");
                    }
                    else {
                        System.out.println("Não há publicações associadas ao projeto\nProjeto não pode ser concluído sem publicações");
                        return;
                    }
                }
                System.out.println("Status alterado");
            }
            else {
                System.out.println("Status do projeto não foi alterado");;
            }
        }

    }

    public void dadosProjeto() {

        System.out.print("Dados de um Projeto:\nTítulo do projeto: ");
        Projeto projeto = buscarProjeto(input.nextLine());

        if (projeto != null) {
            System.out.println("\nProjeto " + projeto.getTitulo() + "\n" +
                    "Status: " + projeto.getStatus() + "\n" +
                    "Início: " + projeto.getDataInicio() + "\n" +
                    "Término: " + projeto.getDataTermino() + "\n" +
                    "Agência financiadora: " + projeto.getAgencia() + "\n" +
                    "Valor financiado: " + projeto.getValor() + "\n" +
                    "Objetivo: " + projeto.getObjetivo() + "\n" +
                    "Descrição: " + projeto.getDescricao());


            System.out.println("Colaboradores:");
            for (Colaborador i: projeto.getProfessores()) {
                System.out.println("\t" + i.getNome());
            }
            for (Colaborador i: projeto.getParticipantes()) {
                System.out.println("\t" + i.getNome());
            }

            System.out.println("Publicações:");
            for (Publicacao i: projeto.getProducaoAcademica()) {
                System.out.println("\tTítulo: " + i.getTitulo() + " - (" + i.getAno() + ")");
            }

        }
        else {
            System.out.println("Nenhum projeto encontrado");
        }

    }

    public void alocarColaborador() {

        System.out.print("Alocar Colaborador\nNome do projeto: ");
        Projeto projeto = buscarProjeto(input.nextLine());

        if (projeto != null) {

            if (!projeto.getStatus().equals("Em elaboração")) {
                System.out.println("Não é possível alocar colaborador nesse projeto\nStatus: " + projeto.getStatus());
                return;
            }

            System.out.print("Nome do colaborador: ");
            Colaborador addColaborador = new Colaborador();
            Colaborador colaborador = addColaborador.buscarColaborador(input.nextLine());

            if (projeto.getProfessores().contains(colaborador) || projeto.getParticipantes().contains(colaborador)) {
                System.out.println("Esse colaborador já está no projeto");
                return;
            }
            else if (colaborador.getTipo().equals("professor")) {
                projeto.setProfessores(colaborador);
            }
            else if (colaborador.getTipo().equals("graduacao")) {
                int projetosAluno = 0;

                for (Projeto i: colaborador.getProjetos()) {
                    if (i.getStatus().equals("Em elaboração") || i.getStatus().equals("Em andamento")) {
                        projetosAluno++;
                    }
                }

                if (projetosAluno > 2) {
                    System.out.println("O Aluno não pode participar desse projeto");
                    return;
                }

                projeto.setParticipantes(colaborador);
            }
            else {
                projeto.setParticipantes(colaborador);
            }

            colaborador.setProjetos(projeto);
            System.out.println("Participante alocado ao projeto");

        }
        else {
            System.out.println("Projeto não encontrado");
        }

    }

    public Projeto buscarProjeto(String titulo) {

        for (Projeto i: Laboratorio.getProjetos()) {
            if (i.getTitulo().equals(titulo)) {
                return i;
            }
        }

        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Colaborador> getProfessores() {
        return professores;
    }

    public void setProfessores(Colaborador professor) {
        this.professores.add(professor);
    }

    public ArrayList<Colaborador> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Colaborador participante) {
        this.participantes.add(participante);
    }

    public ArrayList<Publicacao> getProducaoAcademica() {
        return producaoAcademica;
    }

    public void setProducaoAcademica(Publicacao producaoAcademica) {
        this.producaoAcademica.add(producaoAcademica);

        Collections.sort(this.producaoAcademica, new Comparator<>() {
            public int compare(Publicacao o1, Publicacao o2) {
                return o1.getAno() > o2.getAno() ? -1 : 1;
            }
        });
    }
}
