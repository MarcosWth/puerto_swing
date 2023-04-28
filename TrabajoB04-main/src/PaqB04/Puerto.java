//Marcos López Gómez

package B04.PaqB04;
public class Puerto extends Contenedores{
    private final int nFilas=10;
    private final int nColumnas=12;
    private boolean vacia;
    public int getnFilas(){return this.nFilas;}
    public int getnColumnas(){return this.nColumnas;}


    private Contenedores[][] matriz = new Contenedores[nFilas][nColumnas];
    public void inicializar(){//Inicializa toda la matriz para evitar errores
        for(int f=0;f<nFilas;f++){
            for (int c=0;c<nColumnas;c++){
                matriz[f][c]=new Contenedores();
            }
        }
    }
    public int agregarContenedor(Contenedores nuevo){
        int lleno=0;//Usaremos esto para indicar si está lleno para la prioridad seleccionada
        if (nuevo.getPrioridad() == 1){
            for(int f=0;f<nFilas;f++){
                //Usamos la prioridad para comprobar si está libre o no
                if(matriz[f][0].getPrioridad() < 1){
                    matriz[f][0]=new Contenedores(nuevo);
                    f=nFilas;
                } else if (f==nFilas-1 && matriz[f][0].getPrioridad() > 0) {
                lleno=1;
            }
            }
        } else if (nuevo.getPrioridad() == 2) {
            for(int f=0;f<nFilas;f++){
                if(matriz[f][1].getPrioridad() < 1){
                    matriz[f][1]=new Contenedores(nuevo);
                    f=nFilas;
                } else if (f==nFilas-1 && matriz[f][1].getPrioridad() > 0) {
                    lleno=2;
                }
            }
        } else if (nuevo.getPrioridad() == 3) {
            for(int c=2;c<nColumnas;c++){
                for(int f=0;f<nFilas;f++){
                    if(matriz[f][c].getPrioridad() < 1){
                        matriz[f][c]=new Contenedores(nuevo);
                        c=nColumnas;
                        f=nFilas;
                    } else if (f==nFilas-1 && c==nColumnas-1 && matriz[f][c].getPrioridad() > 0) {
                    lleno=3;
                }
                }
            }
        }return lleno;
    }
    public int desapilar(int columna){
        int codSalida=0;
        if(columna<1){
            columna=1;
            //Columna errónea, se elegirá la 1ª columna
            codSalida= 1;
        }
        else if(columna>nColumnas){
            columna=nColumnas;
            //Columna errónea, se eligirá la "+nColumnas+"º columna
            codSalida= 2;
        }
        for (int f=nFilas-1;f>=0;f--){
            if(matriz[f][columna-1].getPrioridad() >= 1){
                matriz[f][columna-1].setPrioridadCero();//Ponemos la prioridad a 0 para indicar que está libre
                f=-1;
            }
        }return codSalida;
    }

    public boolean cVacia(int columna){
        if(columna<1){
            columna=1;
            //Columna errónea, se elegirá la 1ª columna

        }
        else if(columna>nColumnas){
            columna=nColumnas;
            //Columna errónea, se eligirá la "+nColumnas+"º columna

        }
        if(matriz[0][columna-1].getPrioridad() < 1){
            return true;//Devuelve true si su prioridad es 0, es decir si está vacía
        }else{return false;}
    }
    public String toString() {
        String hub="\nOCUPACION DEL HUB\n|";
        for (int f = nFilas-1; f >= 0; f--) {
            for (int c = 0; c < nColumnas; c++) {
                if(matriz[f][c].getPrioridad()<1){
                    hub=hub+" |";
                }
                else{
                    hub=hub+matriz[f][c].getIdentificador()+"-"+matriz[f][c].getPeso()+"|";
                }
            }
            hub=hub+"\n|";
        }

        hub=hub.substring(0,hub.length()-1)+"\nLEYENDA:\t|X|: Ocupado\t| |: Libre \n";
        return hub;
        
    }
    public String mostrarDatos(int ident){
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nColumnas;j++) {
                if (matriz[i][j].getIdentificador()==ident && matriz[i][j].getPrioridad()>0){
                    if(!matriz[i][j].getInspeccion()){
                        return "Peso: "+ matriz[i][j].getPeso() +" (t)"+";  País: "+ matriz[i][j].getPais()+ ";  Prioridad: "+ matriz[i][j].getPrioridad()
                                + "\nDescripción: "+ matriz[i][j].getDescripcion()+ "\nDe: "+matriz[i][j].getEnvia()+ "; Para: "+matriz[i][j].getRecibe()+
                                "\nSin inspeccionar en aduanas";
                    }
                    else {return "Peso: "+ matriz[i][j].getPeso() +" (t)"+";  País: "+ matriz[i][j].getPais()+ ";  Prioridad: "+ matriz[i][j].getPrioridad()
                            + "\nDescripción: "+ matriz[i][j].getDescripcion()+ "\nDe: "+matriz[i][j].getEnvia()+ "; Para: "+matriz[i][j].getRecibe()+
                            "\nInspeccionado en aduanas";}
                }
            }
        }
        return "No se encuentra ningún contenedor en el puerto con ese Identificador";
    }
    
    public int numPais(String pais){
        int n=0;
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nColumnas; j++) {
                if (matriz[i][j].getPais()==pais){
                    if (matriz[i][j].getPrioridad()>0){
                        n++;
                    }
                }
            }
        }
        return n;
    }

    public boolean existeId(int Id){
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nColumnas; j++) {
                if (matriz[i][j].getIdentificador()==Id && matriz[i][j].getPrioridad()>0){
                    return true;
                }
            }
        }return false;
    }
    public double pesoTotal(){
        double x=0;
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nColumnas; j++) {
                if (matriz[i][j].getPrioridad()>0){
                    x=x+matriz[i][j].getPeso();
                }
            }
        }
        return x;
    }
    public String toStringPesoT(double x){
        String a="";
        a=a+x;
        return a;
    }
}
