//Marcos López Gómez

package B04.PaqB04;

public class Contenedores {
    private int identificador;
    private int peso;
    private String pais;
    private boolean inspeccion;
    private int prioridad;
    private String descripcion;
    private String envia;
    private String recibe;

    //CONSTRUCTORES
    public Contenedores(int identificador, int peso, String pais, boolean inspeccion, int prioridad, String descripcion, String envia, String recibe){
        this.identificador=identificador;
        this.peso=peso;
        this.pais=pais;
        this.inspeccion=inspeccion;
        this.prioridad=prioridad;
        if (descripcion.length() > 100) {
            this.descripcion = descripcion.substring(0, 97) + "...";
        }
        else this.descripcion=descripcion;
        
        if (envia.length() > 20) {
            this.envia = envia.substring(0,17) + "...";
        }
        else this.envia = envia;
        
        if (recibe.length() > 20) {
            this.recibe = recibe.substring(0,17) + "...";
        }
        else this.recibe = recibe;
    }
    public Contenedores() {
    }
    public Contenedores(Contenedores a){
        this.identificador=a.getIdentificador();
        this.peso=a.getPeso();
        this.pais=a.getPais();
        this.inspeccion=a.getInspeccion();
        this.prioridad=a.getPrioridad();
        this.descripcion=a.getDescripcion();
        this.envia=a.getEnvia();
        this.recibe=a.getRecibe();
    }

    //SETTERS
    public void setDescripcion (String descripcion){
        if (descripcion.length() > 100) {
            this.descripcion = descripcion.substring(0, 97) + "...";
        }
        else this.descripcion=descripcion;
    }
    public void setIdentificador ( int identificador){
        this.identificador = identificador;
    }
    public void setEnvia (String envia){
        if (envia.length() > 20) {
            this.envia = envia.substring(0,17) + "...";
        }
        else this.envia = envia;
    }
    public void setInspeccion ( boolean inspeccion){
        this.inspeccion = inspeccion;
    }
    public void setPais (String pais){
        this.pais = pais;
    }
    public int setPeso ( int peso){
        //no puede tener peso negativo
        if (peso > 0) {
            this.peso = peso;
            return 0;
        } else {
            //El peso no puede ser negativo
            return 1;
        }
    }
    public int setPrioridad ( int prioridad){
        if (prioridad == 1 || prioridad == 2 || prioridad == 3) {
            this.prioridad = prioridad;
            return 0;
        } else {
            this.prioridad = 3;
            //La prioridad dada no es posible (asigne una del 1 al 3). La prioridad del paquete ha sido asignada a 3
            return 1;
        }
    }
    public void setPrioridadCero(){
        this.prioridad=0;//Esto lo usaremos para indicar que un contenedor está libre
    }
    public void setRecibe (String recibe){
        if (recibe.length() > 20) {
            this.recibe = recibe.substring(0,17) + "...";
        }
        else this.recibe = recibe;
    }


    //GETTERS
    public int getIdentificador() {
        return identificador;
    }
    public int getPeso() {
        return peso;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getEnvia() {
        return envia;
    }
    public String getPais() {
        return pais;
    }
    public String getRecibe() {
        return recibe;
    }
    public boolean getInspeccion(){
        return inspeccion;
    }
    
    
}
