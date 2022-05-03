package br.senai.sp.cotia.todolistapp.model;

public class Subtarefa {
    private Long idSubtarefa;
    private String descricao;
    private String foto;
    private boolean concluída;
    private Long idTarefa;

    public Long getIdSubtarefa() {
        return idSubtarefa;
    }

    public void setIdSubtarefa(Long idSubtarefa) {
        this.idSubtarefa = idSubtarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isConcluída() {
        return concluída;
    }

    public void setConcluída(boolean concluída) {
        this.concluída = concluída;
    }

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }
}
