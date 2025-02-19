import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Calendario calendario = new Calendario();
        Relatorio relatorio = new Relatorio();
        Scanner scanner = new Scanner(System.in);

        // Inicia o verificador de lembretes em uma thread separada
        VerificadorLembretes verificador = new VerificadorLembretes(agenda, 60); // Verifica a cada 60 segundos
        Thread threadVerificador = new Thread(verificador);
        threadVerificador.setDaemon(true); // Define como daemon para encerrar quando o programa principal terminar
        threadVerificador.start();

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar Compromisso");
            System.out.println("2. Editar Compromisso");
            System.out.println("3. Excluir Compromisso");
            System.out.println("4. Visualizar Compromissos Diários");
            System.out.println("5. Visualizar Compromissos Semanais");
            System.out.println("6. Visualizar Compromissos Mensais");
            System.out.println("7. Buscar Compromissos por Palavra-Chave");
            System.out.println("8. Filtrar Compromissos por Categoria");
            System.out.println("9. Gerar Relatório de Compromissos Passados");
            System.out.println("10. Gerar Relatório de Compromissos Futuros");
            System.out.println("11. Sair");

            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    adicionarCompromisso(agenda, scanner);
                    break;

                case 2:
                    editarCompromisso(agenda, scanner);
                    break;

                case 3:
                    excluirCompromisso(agenda, scanner);
                    break;

                case 4:
                    visualizarCompromissosDiarios(agenda, calendario, scanner);
                    break;

                case 5:
                    visualizarCompromissosSemanais(agenda, calendario, scanner);
                    break;

                case 6:
                    visualizarCompromissosMensais(agenda, calendario, scanner);
                    break;

                case 7:
                    buscarCompromissosPorPalavraChave(agenda, scanner);
                    break;

                case 8:
                    filtrarCompromissosPorCategoria(agenda, scanner);
                    break;

                case 9:
                    relatorio.gerarRelatorioPassados(agenda.getCompromissos());
                    break;

                case 10:
                    relatorio.gerarRelatorioFuturos(agenda.getCompromissos());
                    break;

                case 11:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para adicionar um compromisso com validação de dados
    private static void adicionarCompromisso(Agenda agenda, Scanner scanner) {
        System.out.println("Título:");
        String titulo = scanner.nextLine();

        System.out.println("Descrição:");
        String descricao = scanner.nextLine();

        LocalDateTime dataHora = lerDataHora(scanner); // Validação de data e hora

        System.out.println("Categoria:");
        String categoria = scanner.nextLine();

        boolean lembrete = lerBoolean(scanner); // Validação de booleano

        Compromisso compromisso = new Compromisso(titulo, descricao, dataHora, categoria, lembrete);
        agenda.adicionarCompromisso(compromisso);
        System.out.println("Compromisso adicionado com sucesso!");
    }

    // Método para editar um compromisso com validação de dados
    private static void editarCompromisso(Agenda agenda, Scanner scanner) {
        System.out.println("Índice do compromisso a editar:");
        int index = lerInteiro(scanner);

        if (index >= 0 && index < agenda.getCompromissos().size()) {
            System.out.println("Novo Título:");
            String novoTitulo = scanner.nextLine();

            System.out.println("Nova Descrição:");
            String novaDescricao = scanner.nextLine();

            LocalDateTime novaDataHora = lerDataHora(scanner); // Validação de data e hora

            System.out.println("Nova Categoria:");
            String novaCategoria = scanner.nextLine();

            boolean novoLembrete = lerBoolean(scanner); // Validação de booleano

            Compromisso novoCompromisso = new Compromisso(novoTitulo, novaDescricao, novaDataHora, novaCategoria,
                    novoLembrete);
            agenda.editarCompromisso(index, novoCompromisso);
            System.out.println("Compromisso editado com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Método para excluir um compromisso com validação de índice
    private static void excluirCompromisso(Agenda agenda, Scanner scanner) {
        System.out.println("Índice do compromisso a excluir:");
        int index = lerInteiro(scanner);

        if (index >= 0 && index < agenda.getCompromissos().size()) {
            agenda.excluirCompromisso(index);
            System.out.println("Compromisso excluído com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Método para visualizar compromissos diários com validação de data
    private static void visualizarCompromissosDiarios(Agenda agenda, Calendario calendario, Scanner scanner) {
        System.out.println("Data (yyyy-MM-dd):");
        LocalDate data = lerData(scanner); // Validação de data
        calendario.exibirDiario(agenda.getCompromissos(), data);
    }

    // Método para visualizar compromissos semanais com validação de data
    private static void visualizarCompromissosSemanais(Agenda agenda, Calendario calendario, Scanner scanner) {
        System.out.println("Data de início da semana (yyyy-MM-dd):");
        LocalDate inicioSemana = lerData(scanner); // Validação de data
        calendario.exibirSemanal(agenda.getCompromissos(), inicioSemana);
    }

    // Método para visualizar compromissos mensais com validação de mês e ano
    private static void visualizarCompromissosMensais(Agenda agenda, Calendario calendario, Scanner scanner) {
        System.out.println("Mês (1-12):");
        int mes = lerInteiroNoIntervalo(scanner, 1, 12); // Validação de mês

        System.out.println("Ano:");
        int ano = lerInteiro(scanner); // Validação de ano

        calendario.exibirMensal(agenda.getCompromissos(), mes, ano);
    }

    // Método para buscar compromissos por palavra-chave
    private static void buscarCompromissosPorPalavraChave(Agenda agenda, Scanner scanner) {
        System.out.println("Palavra-chave:");
        String palavraChave = scanner.nextLine();
        List<Compromisso> resultados = agenda.buscarCompromissosPorPalavraChave(palavraChave);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum compromisso encontrado.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    // Método para filtrar compromissos por categoria
    private static void filtrarCompromissosPorCategoria(Agenda agenda, Scanner scanner) {
        System.out.println("Digite a categoria para filtrar:");
        String categoria = scanner.nextLine();

        List<Compromisso> compromissosFiltrados = agenda.filtrarPorCategoria(categoria);

        if (compromissosFiltrados.isEmpty()) {
            System.out.println("Nenhum compromisso encontrado para a categoria: " + categoria);
        } else {
            System.out.println("Compromissos na categoria '" + categoria + "':");
            compromissosFiltrados.forEach(System.out::println);
        }
    }

    // Métodos auxiliares para validação de entradas
    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroNoIntervalo(Scanner scanner, int min, int max) {
        while (true) {
            int valor = lerInteiro(scanner);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("Valor fora do intervalo. Digite um número entre " + min + " e " + max + ".");
        }
    }

    private static LocalDateTime lerDataHora(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Data e Hora (yyyy-MM-ddTHH:mm):");
                return LocalDateTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use o formato yyyy-MM-ddTHH:mm.");
            }
        }
    }

    private static LocalDate lerData(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Data (yyyy-MM-dd):");
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use o formato yyyy-MM-dd.");
            }
        }
    }

    private static boolean lerBoolean(Scanner scanner) {
        while (true) {
            System.out.println("Lembrete (true/false):");
            String entrada = scanner.nextLine().toLowerCase();
            if (entrada.equals("true") || entrada.equals("false")) {
                return Boolean.parseBoolean(entrada);
            }
            System.out.println("Entrada inválida. Digite 'true' ou 'false'.");
        }
    }
}