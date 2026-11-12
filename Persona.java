package com.mycompany.contocorrente;


public class Persona {
    private int maxMovimenti = 10;
    private String nome;
    private String cognome;
    private double saldo;
    private double[] movimenti;
    private int numMovimenti;
      
    public Persona(){
        nome = "";
        cognome = "";
        saldo = 0;
        movimenti = new double[maxMovimenti];
        numMovimenti = 0;
    }
    
    public Persona(String nome, String cognome, double saldo){
        this.nome = nome;
        this.cognome = cognome;
        if (saldo >= 0){
            this.saldo = saldo;
        }else{
            saldo = 0;
        }
        movimenti = new double[maxMovimenti];
        numMovimenti = 0;
    }
    
    public Persona(Persona p){
        nome = p.nome;
        cognome = p.cognome;
        saldo = p.saldo;
        movimenti = new double[maxMovimenti];
        for(int i = 0; i < p.numMovimenti; i++){
            movimenti[i] = p.movimenti[i];
        }
        numMovimenti = p.numMovimenti;
    }
    
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getCognome() {
        return cognome;
    }
 
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
 
    public double getSaldo() {
        return saldo;
    }
    
    public boolean versa(double importo){
        if(importo <= 0){
            return false;
        }
        saldo = saldo + importo;
        registaMovimenti(importo);
        return true;
    }
    
    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }
    
    public double[] getMovimenti() {
        double[] copia = new double[numMovimenti];
        for (int i = 0; i < numMovimenti; i++) {
            copia[i] = movimenti[i];
        }
        return copia;
    }
 
    public int getNumMovimenti() {
        return numMovimenti;
    }
    
    public boolean preleva(double importo){
        if(importo <= 0 || importo < saldo){
            return false;
        }
        saldo = saldo - importo;
        registaMovimenti(-importo);
        return true;
    }
    
    public void visualizzaMovimenti(){
        System.out.println("Ultimi muovimenti di: " + nome + " " + cognome + ";");
        if(numMovimenti == 0){
            System.out.println("Nessun movimento ancora;:)");
        }
        for(int i = 0; i < numMovimenti; i++){
            if(movimenti[i] > 0){
                System.out.printf("  %2d) versamento  +%.2f euro%n", i + 1, movimenti[i]);
            }else{
                System.out.printf("  %2d) prelievo    -%.2f euro%n", i + 1, -movimenti[i]);
            }
        }
    }
    
    public void visualizzaDettagli(){
        System.out.println("Correntista: " + nome + " " + cognome);
        System.out.printf("Saldo: %.2 euro%n", saldo);
    }
    
    
    private void registaMovimenti(double importo){
        if (numMovimenti == maxMovimenti){
            for(int i = 0; i < maxMovimenti; i++){
                movimenti[i] = movimenti[1 +i];
            }
            numMovimenti--;
        }
        movimenti[numMovimenti] = importo;
        numMovimenti--;
    }
}
