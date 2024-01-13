package act_5_incidencia;

public class ServeiTecnic {
    private Integer numIncidencia;
    private Integer maxIncidencies;
    private Integer idNovaIncidencia;

    private Integer incidenciaResuelta = 0;

    public ServeiTecnic() {

    }

    public ServeiTecnic(Integer numIncidencia, Integer maxIncidencies, Integer idNovaIncidencia) {
        this.numIncidencia = numIncidencia;
        this.maxIncidencies = maxIncidencies;
        this.idNovaIncidencia = idNovaIncidencia;
    }

    public void setIdNovaIncidencia(Integer idNovaIncidencia) {
        this.idNovaIncidencia = idNovaIncidencia;
    }

    public void setMaxIncidencies(Integer maxIncidencies) {
        this.maxIncidencies = maxIncidencies;
    }

    public void setNumIncidencia(Integer numIncidencia) {
        this.numIncidencia = numIncidencia;
    }

    public void setIncidenciaResuelta(Integer incidenciaResuelta) {
        this.incidenciaResuelta = incidenciaResuelta;
    }

    public Integer getIdNovaIncidencia() {
        return idNovaIncidencia;
    }

    public Integer getMaxIncidencies() {
        return maxIncidencies;
    }

    public Integer getNumIncidencia() {
        return numIncidencia;
    }

    public Integer getIncidenciaResuelta() {
        return incidenciaResuelta;
    }

    public void incidenciaResulesta() {
        this.incidenciaResuelta++;
    }
}
