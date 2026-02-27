package model;

public class NivelPrioridade implements Comparable<NivelPrioridade> {
    private int nivelPrioridade;
    private String descricaoPrioridade;

    public NivelPrioridade(int nivelPrioridade){
        this.nivelPrioridade = nivelPrioridade;
        switch(nivelPrioridade){
            case 1:
                this.descricaoPrioridade = "Baixo";
                break;
            case 2:
                this.descricaoPrioridade = "Moderado";
                break;
            case 3:
                this.descricaoPrioridade = "Médio";
                break;
            case 4:
                this.descricaoPrioridade = "Alto";
                break;
            case 5:
                this.descricaoPrioridade = "Crítico";
                break;
            default:
                throw new IllegalArgumentException("Nível inválido!");
        }
    }

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }

    public String getDescricaoPrioridade() {
        return descricaoPrioridade;
    }

    public void setDescricaoPrioridade(String descricaoPrioridade) {
        this.descricaoPrioridade = descricaoPrioridade;
    }

    @Override
    public String toString() {
        return "    Nível: " + nivelPrioridade + "\n" +
                "    Descrição: " + descricaoPrioridade;
    }

    @Override
    public int compareTo(NivelPrioridade outro) {
        return Integer.compare(outro.nivelPrioridade, this.nivelPrioridade);
    }
}
