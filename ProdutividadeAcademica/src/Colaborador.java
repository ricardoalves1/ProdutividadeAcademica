import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Colaborador {

    private String tipo;
    private String nome;
    private String email;
    private ArrayList<Projeto> projetos = new ArrayList<>();
    private ArrayList<Publicacao> publicacao = new ArrayList<>();
    private ArrayList<Orientacao> orientacao = new ArrayList<>();


    public Colaborador() {}

    public Colaborador(String tipo, String nome, String email) {
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
    }

    Scanner input = new Scanner(System.in);

    public void addColaborador() {

        System.out.print("Adicionar Colaborador\nNome: ");
        String nome = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.println("Tipo do colaborador:\n" +
                "(1) Aluno de Graduação\n" +
                "(2) Aluno de Mestrado\n" +
                "(3) Aluno de Doutorado\n" +
                "(4) Professor\n" +
                "(5) Pesquisador");

        int opc = input.nextInt();
        String tipo;
        if (opc == 1) {
            tipo = "graduacao";
        }
        else if (opc == 2) {
            tipo = "mestrado";
        }
        else if (opc == 3) {
            tipo = "doutorado";
        }
        else if (opc == 4) {
            tipo = "professor";
        }
        else if (opc == 5) {
            tipo = "pesquisador";
        }
        else {
            System.out.println("Tipo de colaborador inválido");
            return;
        }

        Colaborador colaborador = new Colaborador(tipo, nome, email);
        Laboratorio.setColaboradores(colaborador);

        System.out.println("Novo colaborador adicionado\n");
    }

    public void dadosColaborador() {

        System.out.print("Dados de um Colaborador\nNome do colaborador: ");
        Colaborador colaborador = buscarColaborador(input.nextLine());

        if (colaborador != null) {

            System.out.println("\nNome: " + colaborador.getNome() + "\n" +
                    "E-mail: " + colaborador.getEmail() + "\n" +
                    "Projetos:");

            for (Projeto i: colaborador.getProjetos()) {
                System.out.println("\t - " + i.getTitulo());
            }

            System.out.println("Produção Acadêmica:");
            for (Publicacao i: colaborador.getPublicacao()) {
                System.out.println("\t - " + i.getTitulo());
            }
            for (Orientacao i: colaborador.getOrientacao()) {
                System.out.println("\t - " + i.getTitulo());
            }

        }
        else {
            System.out.println("Colaborador não encontrado");
        }

    }

    public Colaborador buscarColaborador(String nome) {

        for (Colaborador i: Laboratorio.getColaboradores()) {
            if (i.getNome().equals(nome)) {
                return i;
            }
        }

        return null;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Projeto projeto) {
        this.projetos.add(projeto);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Collections.sort(projetos, new Comparator<Projeto>() {
            public int compare(Projeto o1, Projeto o2) {
                LocalDate data1 = LocalDate.parse(o1.getDataTermino(), formato);
                LocalDate data2 = LocalDate.parse(o2.getDataTermino(), formato);
                return data1.isAfter(data2) ? -1 : 1;
            }
        });

    }

    public ArrayList<Publicacao> getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao.add(publicacao);

        Collections.sort(this.publicacao, new Comparator<>() {
            public int compare(Publicacao o1, Publicacao o2) {
                return o1.getAno() > o2.getAno() ? -1 : 1;
            }
        });
    }

    public ArrayList<Orientacao> getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao.add(orientacao);

        Collections.sort(this.orientacao, new Comparator<>() {
            public int compare(Orientacao o1, Orientacao o2) {
                return o1.getAno() > o2.getAno() ? -1 : 1;
            }
        });
    }
}
