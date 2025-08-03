package br.com.dio.model;

public class Space {

    private Integer atual;
    private final int esperado;
    private final boolean fixa;


    public Space(final int esperado, final boolean fixa) {
        this.esperado = esperado;
        this.fixa= fixa;
        if (fixa){
            atual = esperado;
        }
    }

    public Integer getAtual() {
        return atual;
    }

    public void setAtual(final Integer atual) {
        if (fixa) return;
        this.atual = atual;
    }

    public void clearSpace(){
        setAtual(null);
    }

    public int getEsperado() {
        return esperado;
    }

    public boolean isFixa() {
        return fixa;
    }
}
