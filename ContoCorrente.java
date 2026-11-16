package com.mycompany.contocorrente;

import java.util.Scanner;

public class ContoCorrente {

    private static Scanner tastiera = new Scanner(System.in);

    public static void main(String[] args) {
        Persona[] conti = new Persona[10];
        int numConti;
        int selezionato;
        int scelta;

        System.out.println("=== GESTIONE CONTI CORRENTI ===");
        System.out.println("Per iniziare crea il primo conto.");
        conti[0] = creaConto();
        numConti = 1;
        selezionato = 0;

        do {
            Persona conto = conti[selezionato];

            System.out.println();
            System.out.println("Conto selezionato: n. " + (selezionato + 1) + " - "
                    + conto.getNome() + " " + conto.getCognome());
            System.out.println("1) Versa");
            System.out.println("2) Preleva");
            System.out.println("3) Visualizza dettagli del conto");
            System.out.println("4) Visualizza ultimi movimenti");
            System.out.println("5) Modifica dati del correntista");
            System.out.println("6) Crea un nuovo conto");
            System.out.println("7) Crea un conto vuoto");
            System.out.println("8) Crea una copia del conto selezionato");
            System.out.println("9) Cambia conto selezionato");
            System.out.println("0) Esci");
            scelta = leggiIntero("Scelta: ");

            switch (scelta) {
                case 1: {
                    double importo = leggiNumero("Importo da versare: ");
                    if (conto.versa(importo)) {
                        System.out.println("Versamento eseguito.");
                    } else {
                        System.out.println("Versamento rifiutato: l'importo deve essere maggiore di 0.");
                    }
                    break;
                }
                case 2: {
                    double importo = leggiNumero("Importo da prelevare: ");
                    if (conto.preleva(importo)) {
                        System.out.println("Prelievo eseguito.");
                    } else {
                        System.out.println("Prelievo rifiutato: importo non valido o saldo insufficiente.");
                    }
                    break;
                }
                case 3:
                    conto.visualizzaDettagli();
                    break;
                case 4:
                    conto.visualizzaMovimenti();
                    break;
                case 5: {
                    conto.setNome(leggiTesto("Nuovo nome: "));
                    conto.setCognome(leggiTesto("Nuovo cognome: "));
                    double saldo = leggiNumero("Nuovo saldo: ");
                    if (saldo >= 0) {
                        conto.setSaldo(saldo);
                    } else {
                        System.out.println("Saldo negativo non accettato: il saldo resta invariato.");
                    }
                    System.out.println("Dati aggiornati.");
                    break;
                }
                case 6:
                case 7:
                case 8:
                    if (numConti == conti.length) {
                        System.out.println("Hai raggiunto il numero massimo di conti.");
                    } else {
                        if (scelta == 6) {
                            conti[numConti] = creaConto();
                        } else if (scelta == 7) {
                            conti[numConti] = new Persona();
                            System.out.println("Conto vuoto creato: inserisci i dati con l'opzione 5.");
                        } else {
                            conti[numConti] = new Persona(conto);
                            System.out.println("Copia creata con gli stessi dati e movimenti.");
                        }
                        selezionato = numConti;
                        numConti++;
                        System.out.println("Ora stai lavorando sul conto n. " + numConti + ".");
                    }
                    break;
                case 9: {
                    for (int i = 0; i < numConti; i++) {
                        System.out.println("  " + (i + 1) + ") " + conti[i].getNome() + " "
                                + conti[i].getCognome());
                    }
                    int numero = leggiIntero("Numero del conto: ");
                    if (numero >= 1 && numero <= numConti) {
                        selezionato = numero - 1;
                    } else {
                        System.out.println("Numero di conto inesistente.");
                    }
                    break;
                }
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);
    }

    private static Persona creaConto() {
        String nome = leggiTesto("Nome: ");
        String cognome = leggiTesto("Cognome: ");
        double saldo = leggiNumero("Saldo iniziale: ");
        if (saldo < 0) {
            System.out.println("Saldo negativo non accettato: il conto parte da 0.");
        }
        return new Persona(nome, cognome, saldo);
    }

    private static String leggiTesto(String messaggio) {
        System.out.print(messaggio);
        return tastiera.nextLine().trim();
    }

    private static int leggiIntero(String messaggio) {
        while (true) {
            String riga = leggiTesto(messaggio);
            try {
                return Integer.parseInt(riga);
            } catch (NumberFormatException e) {
                System.out.println("Valore non valido: inserisci un numero intero.");
            }
        }
    }

    private static double leggiNumero(String messaggio) {
        while (true) {
            String riga = leggiTesto(messaggio).replace(',', '.');
            try {
                return Double.parseDouble(riga);
            } catch (NumberFormatException e) {
                System.out.println("Valore non valido: inserisci un numero (es. 150 o 150,50).");
            }
        }
    }
}
