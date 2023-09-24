import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Estudiante {
    private int codEstudiante;
    private String nombreEstudiante;

    private LocalDateTime fechaCobro;

    private BigDecimal cuotaMes;

    public Estudiante(int codigo, String nombre, LocalDateTime fecha, BigDecimal cuota) {
        this.codEstudiante = codigo;
        this.nombreEstudiante = nombre;
        this.fechaCobro = fecha;
        this.cuotaMes = cuota;
    }

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public LocalDateTime getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(LocalDateTime fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public BigDecimal getCuotaMes() {
        return cuotaMes;
    }

    public void setCuotaMes(BigDecimal cuotaMes) {
        this.cuotaMes = cuotaMes;
    }

    @Override
    public String toString() {
        return "Estudiante => codEstudiante=" + codEstudiante +
                ", nombreEstudiante='" + nombreEstudiante + '\'' +
                ", fechaCobro=" + fechaCobro + ", cuotaMes=" + cuotaMes;
    }
}
