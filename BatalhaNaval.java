package BatalhaNaval;

import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {// Nesse nome estou usando PascalCase pq e nome de uma CLASSE
  private static final int TAMANHO_MATRIZ = 8; // TAMANHO_MATRIZ estou usando snake_case pq e uma CONSTANTE
  private static final int NUMERO_NAVIOS = 10;
  private static final int MAX_TENTATIVAS = 30;
  // Isso facilita a manutenção do codigo e interatividade,
  // e possivel nesse codigo vc aumentar e diminuir o tamanho da matriz, navios e
  // tentativas so alterando os statics finals

  int naviosDestruidos = 0;
  char[][] matriz = new char[TAMANHO_MATRIZ][TAMANHO_MATRIZ];
  char[][] matrizMascarada = new char[TAMANHO_MATRIZ][TAMANHO_MATRIZ]; // convencao para codigos em java é o uso de
  // camelCase para variaveis locais e instancias
  int tentativas = 0;

  public BatalhaNaval() {
    montaMapa();
    imprimiMapa();
    Scanner scanner = new Scanner(System.in);

    while (tentativas < MAX_TENTATIVAS && naviosDestruidos < NUMERO_NAVIOS) {
      emitirMensagem("Digite as coordenadas para atacar(linha e coluna, separadas por espaço)");
      int lin = scanner.nextInt();
      int col = scanner.nextInt();
      validaJogada(lin, col);
    }
    if (tentativas == MAX_TENTATIVAS) {
      emitirMensagem("Acabaram suas tentativas! Derrota :(");
      revelaMapa();
    } else {
      emitirMensagem("Você ganhou com " + tentativas + "tentativas");
    }
    scanner.close();
  }

  public void validaJogada(int lin, int col) { // nas funcoes estou usando a escrita camelCase, a convencao para codigos
                                               // em java é o uso de camelCase para variaveis locais e instancias
    if (lin < 0 || lin >= TAMANHO_MATRIZ || col < 0 || col >= TAMANHO_MATRIZ) {
      emitirMensagem("A linha e coluna informada são inválidas!");
    } else {
      if (matrizMascarada[lin][col] != 'x' && matrizMascarada[lin][col] != 'O') {
        tentativas++;
        if (matriz[lin][col] == 'N') {
          matrizMascarada[lin][col] = 'x';
          naviosDestruidos++;
          imprimiMapa();
          emitirMensagem("Você acertou um navio!");
        } else {
          matrizMascarada[lin][col] = 'O';
          imprimiMapa();
          emitirMensagem("Errou");
        }
      } else {
        emitirMensagem("Você já usou essa casa!");
      }
    }

  }

  public void emitirMensagem(String mensagem) { // Isso aqui e mania de programador frontend em fazer funcao so pra
                                                // mensagemkkkkk, voce pode remover ela e colocar print nas funcoes
                                                // mesmo (
                                                // OUTRO CONCEITO SOLID Princípio da Responsabilidade Única (SRP) )
    System.out.println(mensagem);
  }

  public void montaMapa() {
    Random random = new Random();
    int contador = 0;
    for (int lin = 0; lin < TAMANHO_MATRIZ; lin++) {
      for (int col = 0; col < matriz[lin].length; col++) {
        matriz[lin][col] = '~';
      }
    }
    while (contador < NUMERO_NAVIOS) {
      int lin = random.nextInt(TAMANHO_MATRIZ);
      int col = random.nextInt(TAMANHO_MATRIZ);

      if (matriz[lin][col] != 'N') {
        matriz[lin][col] = 'N';
        contador++;
      }
    }
    matrizMascara();
  }

  public void revelaMapa() {
    System.out.print("  ");
    for (int col = 0; col < TAMANHO_MATRIZ; col++) {
      System.out.print(col + " ");
    }
    for (int lin = 0; lin < TAMANHO_MATRIZ; lin++) {
      System.out.print(lin + " ");
      for (int col = 0; col < TAMANHO_MATRIZ; col++) {
        System.out.print(matriz[lin][col] + " ");
      }
      System.out.println();
    }
  }

  public void matrizMascara() {
    for (int lin = 0; lin < TAMANHO_MATRIZ; lin++) {
      for (int col = 0; col < TAMANHO_MATRIZ; col++) {
        matrizMascarada[lin][col] = '~';
      }
    }
  }

  // Voce pergunta pq fiz uma mascara e nao fiz so uma validacao na hora de
  // imprimir de if matriz[col][lin] == 'N' System.out.print("~") 🤔
  // Segundo os conceitos do SOLID , devemos separar a logica de armzenamento e
  // logica de exibição ( Single
  // Responsibility Principle e Open/Closed Principle ) 🤓
  // A matriz principal (matriz) e responsável pelo armazenamento dos navios 🚢 e
  // da
  // agua 🚿 TLGD?
  // A matriz mascarada 😷 (matrizMascarada) e responsável pela exibição do estado
  // do
  // jogo ao usuário
  // se ficar mt nerdola com cara q n foi vc c muda fdskkkkkk

  public void imprimiMapa() {
    System.out.print("  ");
    for (int col = 0; col < TAMANHO_MATRIZ; col++) {
      System.out.print(col + " ");
    }
    System.out.println();
    for (int lin = 0; lin < TAMANHO_MATRIZ; lin++) {
      System.out.print(lin + " ");
      for (int col = 0; col < TAMANHO_MATRIZ; col++) {
        System.out.print(matrizMascarada[lin][col] + " ");
      }
      System.out.println(); // isso aqui e praticamente um quebra linha
    }

  }

  public static void main(String[] args) {
    new BatalhaNaval();
  }
}