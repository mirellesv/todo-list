package model;

public enum Status {

    TODO(1, "TODO"),
    DOING(2, "Doing"),
    DONE(3, "Done");

    private final int codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Status fromCodigo(int codigo) {
        for (Status s : values()) {
            if (s.codigo == codigo) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + codigo);
    }

    @Override
    public String toString() {
        return descricao;
    }
}