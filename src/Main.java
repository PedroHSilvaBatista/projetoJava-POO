import br.com.alura.minhasmusicas.modelos.*;
import br.com.alura.minhasmusicas.classificacao.*;
import br.com.alura.minhasmusicas.calculos.CalculadoraDeTempo;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void menu(){
        System.out.println();
        System.out.println("""
                SEJA BEM VINDO A COMUNIDADE JAVAFY! SUA PLATAFORMA PREFERIDA DE RECOMENDAÇÃO DE MÚSICAS E PODCASTS
                **************************************************************************************************
                Escolha uma das opções abaixo:
                1 - Sugerir um novo título à comunidade
                2 - Listar os títulos sugeridos por você
                3 - Exibir títulos sugeridos por você e que foram adicionados a lista de favoritos
                4 - Exibir o tempo de reprodução para todos os títulos da lista de favoritos
                5 - Exibir a avaliação de um título sugerido
                6 - Sair do programa
                **************************************************************************************************
                """);
    }


    public static void main(String[] args) {
        List<Audio> listaDeAudios = new ArrayList<>();
        List<Musica> listaDeMusicas = new ArrayList<>();
        List<Podcast> listaDePodcasts = new ArrayList<>();
        List<Audio> listaDeTitulosFavoritos = new ArrayList<>();

        boolean controle = true;
        while (controle) {
            menu();
            Scanner input = new Scanner(System.in);
            System.out.print("Digite sua opção: ");
            String opcaoUsuario = input.nextLine();
            switch (opcaoUsuario) {
                case "1":
                    System.out.println("""
                            Dentre as categorias abaixo, qual gostaria de recomendar?
                            1 - Música
                            2 - Podcast
                            """);
                    System.out.print("Sua opção: ");
                    String opcaoInserir = input.nextLine();
                    switch (opcaoInserir){
                        case "1":
                            System.out.println();
                            System.out.println("Para recomendar uma nova música à comunidade, primeiro preencha algumas informações importantes");

                            System.out.print("Diga o nome da música que deseja recomendar: ");
                            String tituloMusica = input.nextLine();

                            System.out.print("Diga a duração aproximada da música em minutos: ");
                            int duracaoMusica = input.nextInt();

                            System.out.print("Diga o ano de lançamento que a música foi lançada: ");
                            int anoDeLancamentoMusica = input.nextInt();

                            System.out.print("Diga o artista que produziu a música: ");
                            input.nextLine();
                            String artista = input.nextLine();

                            System.out.print("Diga o gênero da música: ");
                            String genero = input.nextLine();

                            System.out.print("Diga o album a qual a música pertence, caso seja um Single, digite Single: ");
                            String album = input.nextLine();

                            listaDeMusicas.add(new Musica(tituloMusica, duracaoMusica, anoDeLancamentoMusica, artista, genero, album));
                            System.out.println("Recomendação de música salva com sucesso!");

                            for (Musica musica: listaDeMusicas) {
                                if (musica.getTitulo().equals(tituloMusica)){
                                    listaDeAudios.add(musica);
                                    // SIMULANDO UM SISTEMA DE CURTIDAS
                                    int numeroCurtidasMusica = (int) (Math.random() * 1000) + 1;
                                    for (int x=0; x < numeroCurtidasMusica; x++){
                                        musica.curte();
                                    }
                                    // SIMULANDO UM SISTEMA DE VISUALIZAÇÕES DA SUGESTÃO DO USUÁRIO
                                    int numeroReproducoes = (int) (Math.random() * (1000 - numeroCurtidasMusica + 1)) + numeroCurtidasMusica;
                                    for (int i=0; i < numeroReproducoes; i++){
                                        musica.visualizaramSuaRecomendacao();
                                    }
                                }
                            }
                            System.out.println();
                            System.out.print("Gostaria de adicionar sua música sugerida a lista de favoritos? [S/N]: ");
                            String opcaoAdicionarMusicaAosFavoritos = input.nextLine();

                            if(opcaoAdicionarMusicaAosFavoritos.toLowerCase().charAt(0) == 's'){
                                for (Musica musica: listaDeMusicas){
                                    if (musica.getTitulo().equals(tituloMusica)){
                                        listaDeTitulosFavoritos.add(musica);
                                        System.out.println("A música " + tituloMusica + " foi adicionada a lista de favoritos!");
                                    }
                                }
                            } else if (opcaoAdicionarMusicaAosFavoritos.toLowerCase().charAt(0) == 'n'){
                                System.out.println("Certo, por favor fique a vontade para navegar em nossas opções do menu");
                            } else {
                                System.out.println("Por favor, digite apenas Sim ou Não");
                            }
                            break;
                        case "2":
                            System.out.println("Para recomendar um novo podcast à comunidade, primeiro preencha algumas informações importantes");

                            System.out.print("Diga o título do podcast que deseja recomendar: ");
                            String tituloPodcast = input.nextLine();

                            System.out.print("Diga a duração aproximada do episódio em minutos: ");
                            int duracaoPodcast = input.nextInt();

                            System.out.print("Diga o ano de lançamento que o episódio foi lançado: ");
                            int anoDeLancamentoPodcast = input.nextInt();

                            System.out.print("Diga qual é nome do host(apresentador) do podcast: ");
                            input.nextLine();
                            String host = input.nextLine();

                            System.out.print("Diga um pouco sobre o episódio a ser recomendado: ");
                            String descricao = input.nextLine();

                            listaDePodcasts.add(new Podcast(tituloPodcast, duracaoPodcast, anoDeLancamentoPodcast, host, descricao));
                            System.out.println("Recomendação de podcast salva com sucesso!");

                            for (Podcast podcast: listaDePodcasts){
                                if (podcast.getTitulo().equals(tituloPodcast)){
                                    listaDeAudios.add(podcast);

                                    int numeroCurtidasPodcast = (int) (Math.random() * 1000) + 1;
                                    for (int c=0; c < numeroCurtidasPodcast; c++){
                                        podcast.curte();
                                    }

                                    int numeroReproducoes = (int) (Math.random() * (1000 - numeroCurtidasPodcast + 1)) + numeroCurtidasPodcast;
                                    for (int j=0; j < numeroReproducoes; j++){
                                        podcast.visualizaramSuaRecomendacao();
                                    }
                                }
                            }

                            System.out.print("Gostaria de adicionar seu podcast sugerido a lista de favoritos? [S/N]: ");
                            String opcaoAdicionarPodcastAosFavoritos = input.nextLine();

                            if (opcaoAdicionarPodcastAosFavoritos.toLowerCase().charAt(0) == 's'){
                                for (Podcast podcast: listaDePodcasts){
                                    if (podcast.getTitulo().equals(tituloPodcast)) {
                                        listaDeTitulosFavoritos.add(podcast);
                                        System.out.println("O podcast " + tituloPodcast + " foi adicionada a lista de favoritos!");
                                    }
                                }
                            } else if (opcaoAdicionarPodcastAosFavoritos.toLowerCase().charAt(0) == 'n'){
                                System.out.println("Certo, por favor fique a vontade para navegar em nossas opções do menu");
                            } else {
                                System.out.println("Por favor, digite apenas Sim ou Não");
                            }
                            break;
                        default:
                            System.out.println("Por favor, digite uma das opções apresentadas");
                            break;
                    }
                    break;
                case "2":
                    if (listaDeAudios.isEmpty()){
                        System.out.println("Você ainda não sugeriu nenhum título, sugira algum título para poder visualizá-los aqui");
                        break;
                    }
                    System.out.println("""
                            Dentre as categorias abaixo qual gostaria que fosse exibida?
                            1 - Música
                            2 - Podcast
                            3 - Todos os Títulos
                            """);
                    System.out.print("Digite aqui sua opção: ");
                    String opcaoListar = input.nextLine();
                    switch (opcaoListar){
                        case "1":
                            // VERIFICAR SE A LISTA NÃO ESTÁ VAZIA
                            if (listaDeMusicas.isEmpty()){
                                System.out.println("Você ainda não sugeriu nenhuma música, sugira uma música para poder visualizá-las aqui");
                                break;
                            }
                            List<Musica> listaOrdenadaDeMusicas = new ArrayList<>(listaDeMusicas);
                            Collections.sort(listaOrdenadaDeMusicas);
                            System.out.println();
                            System.out.println("Aqui estão as músicas sugeridas em ordem alfabética por nome de música: ");
                            for (Musica musica: listaOrdenadaDeMusicas){
                                System.out.println("● " + musica);
                            }

                            System.out.println();
                            System.out.print("Gostaria que fosse exibida também a lista de músicas por ordem de lançamento? [S/N]: ");
                            String opcaoListarAnoDeLancamentoMusica = input.nextLine();
                            System.out.println();
                            if (opcaoListarAnoDeLancamentoMusica.toLowerCase().charAt(0) == 's'){
                                listaOrdenadaDeMusicas.sort(Comparator.comparing(Musica::getAnoLancamento));
                                System.out.println("Aqui estão as músicas em ordem de lançamento: ");
                                for (Musica musica: listaDeMusicas){
                                    System.out.println("● " + musica);
                                }
                            } else if (opcaoListarAnoDeLancamentoMusica.toLowerCase().charAt(0) == 'n'){
                                System.out.println("Certo, por favor fique a vontade para navegar em nossas opções do menu");
                            } else {
                                System.out.println("Por favor, digite apenas: Sim ou Não");
                            }
                            break;
                        case "2":
                            if (listaDePodcasts.isEmpty()){
                                System.out.println("Você ainda não sugeriu nenhum podcast, sugira um podcast para poder visualizá-los aqui");
                                break;
                            }
                            List<Podcast> listaOrdenadaDePodcasts = new ArrayList<>(listaDePodcasts);
                            Collections.sort(listaOrdenadaDePodcasts);
                            System.out.println();
                            System.out.println("Aqui estão os podcasts sugeridos em ordem alfabética por nome do podcast: ");
                            for (Podcast podcast: listaOrdenadaDePodcasts){
                                System.out.println("● " + podcast);
                            }

                            System.out.print("Gostaria que fosse exibida também a lista de podcasts por ordem de lançamento? [S/N]: ");
                            String opcaoListarAnoDeLancamentoPodcast = input.nextLine();

                            System.out.println();
                            if (opcaoListarAnoDeLancamentoPodcast.toLowerCase().charAt(0) == 's'){
                                listaOrdenadaDePodcasts.sort(Comparator.comparing(Podcast::getAnoLancamento));
                                for (Podcast podcast: listaOrdenadaDePodcasts){
                                    System.out.println("● " + podcast);
                                }
                            } else if (opcaoListarAnoDeLancamentoPodcast.toLowerCase().charAt(0) == 'n') {
                                System.out.println("Certo, por favor fique a vontade para navegar em nossas opções do menu");
                            } else {
                                System.out.println("Por favor, digite apenas: Sim ou Não");
                            }
                            break;
                        case "3":
                            List<Audio> listaOrdenadaDeAudios = new ArrayList<>(listaDeAudios);
                            Collections.sort(listaOrdenadaDeAudios);
                            System.out.println();
                            System.out.println("Aqui estão todos os títulos sugeridos em ordem alfabética por nome: ");
                            for (Audio audio: listaDeAudios){
                                System.out.println("● " + audio);
                            }

                            System.out.print("Gostaria que fosse exibido também a lista de títulos por ordem de lançamentos? [S/N]: ");
                            String opcaoListarAnoDeLancamentoAudio = input.nextLine();

                            System.out.println();
                            if (opcaoListarAnoDeLancamentoAudio.toLowerCase().charAt(0) == 's'){
                                listaOrdenadaDeAudios.sort(Comparator.comparing(Audio::getAnoLancamento));
                                for (Audio audio: listaOrdenadaDeAudios){
                                    System.out.println("● " + audio);
                                }
                            } else if(opcaoListarAnoDeLancamentoAudio.toLowerCase().charAt(0) == 'n'){
                                System.out.println("Certo, por favor fique a vontade para navegar em nossas opções do menu");
                            } else {
                                System.out.println("Por favor, digite apenas: Sim ou Não");
                            }
                            break;
                        default:
                            System.out.println("Por favor, digite uma opção válida");
                            break;
                    }
                    break;
                case "3":
                    System.out.println();
                    if (listaDeTitulosFavoritos.isEmpty()){
                        System.out.println("Você ainda não adicionou nenhum título a lista de favoritos, acrescente algum título para poder visualizá-lo aqui");
                        break;
                    }
                    System.out.println("""
                            Escolha uma das categorias abaixo para serem exibidas da lista de favoritos
                            1 - Música
                            2 - Podcast
                            3 - Todos os Títulos
                            """);
                    System.out.print("Digite sua opção: ");
                    String opcaoExibirListaDeFavoritos = input.nextLine();
                    switch (opcaoExibirListaDeFavoritos){
                        case "1":
                            //
                            boolean existeMusica = false;
                            for (Audio buscaMusica: listaDeTitulosFavoritos){
                                if (buscaMusica instanceof Musica){
                                    existeMusica = true;
                                    break;
                                }
                            }

                            System.out.println();
                            if (existeMusica){
                                System.out.println("Lista de músicas adicionadas aos favoritos: ");
                                for (Audio musica: listaDeTitulosFavoritos){
                                    if (musica instanceof Musica){
                                        System.out.println("● " + musica);
                                    }
                                }
                            }
                            System.out.println("Você ainda não adicionou nenhuma música a lista de favoritos, favorite alguma música para visualizá-lo aqui");
                            break;
                        case "2":
                            //
                            boolean existePodcast = false;
                            for (Audio buscaPodcast: listaDeTitulosFavoritos){
                                if (buscaPodcast instanceof Podcast){
                                    existePodcast = true;
                                    break;
                                }
                            }

                            System.out.println();
                            if (existePodcast){
                                System.out.println("Lista de podcasts adicionadas aos favoritos: ");
                                for (Audio podcast: listaDeTitulosFavoritos){
                                    if (podcast instanceof Podcast){
                                        System.out.println("● " + podcast);
                                        break;
                                    }
                                }
                            }
                            System.out.println("Você ainda não adicionou nenhum podcasts a lista de favoritos, favorite algum podcast para visualizá-lo aqui");
                            break;
                        case "3":
                            //
                            System.out.println();
                            System.out.println("Lista de todos os títulos adicionados aos favoritos: ");
                            for (Audio audio: listaDeTitulosFavoritos){
                                System.out.println("● " + audio);
                            }
                            break;
                        default:
                            System.out.println("Por favor, digite uma opção válida");
                    }
                    break;
                case "4":
                    System.out.println();
                    if (listaDeTitulosFavoritos.isEmpty()) {
                        System.out.println("Você ainda não adicionou nenhum título a lista de favoritos, acrescente algum título para poder visualizar o tempo total de reprodução");
                        break;
                    }

                    CalculadoraDeTempo calculadoraDeTempo = new CalculadoraDeTempo();
                    for (Audio audio: listaDeTitulosFavoritos){
                        calculadoraDeTempo.somaTempo(audio);
                    }
                    System.out.println("O tempo total de reprodução de todos os títulos favoritos é: " + calculadoraDeTempo.getTempoEmMinutos() + "min");
                    break;
                case "5":
                    System.out.println();
                    if (listaDeAudios.isEmpty()){
                        System.out.println("Você ainda não sugeriu nenhum título, sugira algum título para ver o estado de avaliação");
                        break;
                    }
                    System.out.println("""
                            Dentre as categorias abaixo, qual gostaria de visualizar o estado de avaliação?
                            1 - Música
                            2 - Podcast
                            """);
                    System.out.print("Sua opção: ");
                    String opcaoExibirAvaliacao = input.nextLine();
                    FiltroRecomendacao filtro = new FiltroRecomendacao();
                    switch (opcaoExibirAvaliacao){
                        case "1":
                            System.out.println();
                            System.out.print("Digite o nome da música que gostaria de ver o estado de avaliação: ");
                            String nomeDeBuscaMusica = input.nextLine();

                            Musica musicaEncontrada = null;
                            boolean encontrouMusica = false;
                            for (Musica musica: listaDeMusicas){
                                if (musica.getTitulo().equals(nomeDeBuscaMusica)){
                                    musicaEncontrada = musica;
                                    encontrouMusica = true;
                                    break;
                                }
                            }

                            System.out.println();
                            if (encontrouMusica){
                                filtro.filtra(musicaEncontrada);
                                System.out.printf("Taxa de adesão da música %s: ", musicaEncontrada.getTitulo());
                                System.out.printf("%d%% dos que visualiazaram sua recomendação curtiram sua sugestão%n", musicaEncontrada.getClassificacao());
                            } else {
                                System.out.printf("Não foi possível encontrar a música %s%n", nomeDeBuscaMusica);
                                System.out.println("Cerifique-se de que a música inserida foi digitada corretamente");
                            }
                            break;
                        case "2":
                            System.out.println();
                            System.out.print("Digite o nome do podcast que gostaria de ver o estado de avaliação: ");
                            String nomeDeBuscaPodcast = input.nextLine();

                            Podcast podcastEncontrado = null;
                            boolean encontrouPodcast = false;
                            for (Podcast podcast: listaDePodcasts){
                                if (podcast.getTitulo().equals(nomeDeBuscaPodcast)){
                                    podcastEncontrado = podcast;
                                    encontrouPodcast = true;
                                    break;
                                }
                            }

                            System.out.println();
                            if (encontrouPodcast){
                                filtro.filtra(podcastEncontrado);
                                System.out.printf("Taxa de adesão do podcast %s: ", podcastEncontrado.getTitulo());
                                System.out.printf("%d%% dos que visualiazaram sua recomendação curtiram sua sugestão%n", podcastEncontrado.getClassificacao());
                            } else {
                                System.out.printf("Não foi possível encontrar o podcast %s%n", nomeDeBuscaPodcast);
                                System.out.println("Certifique-se de que o podcast inserido foi digitado corretamente");
                            }
                            break;
                        default:
                            System.out.println("Por favor, digite uma opção válida");
                            break;
                    }
                    break;
                case "6":
                    System.out.println();
                    System.out.println("Volte sempre :)");
                    System.out.println("Encerrando o programa...");
                    controle = false;
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida");
                    break;
            }
        }
    }
}
