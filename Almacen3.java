/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package almacen3;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jillder
 */
public class Almacen3 {
    public static final int MAX_LENGTH = 5;
    public static Electrodomestico refrigeradoras[] = new Electrodomestico[MAX_LENGTH];
    public static Electrodomestico televisores[] = new Electrodomestico[MAX_LENGTH];
    public static Electrodomestico lavadoras[] = new Electrodomestico[MAX_LENGTH];
    public static int cabezaRefrigeradoras = -1;
    public static int cabezaTelevisores = -1;
    public static int cabezaLavadoras = -1;
    public static Electrodomestico auxiliar[] = new Electrodomestico[MAX_LENGTH];
    public static int cabezaAux = -1;

    public static void main(String[] args) {
        Menu();
    }
    
    public static void Menu(){
        String texto = "";
        String entrada = "";
        int opcion = 0;

        texto = "==== Menu Manejo Electrodomesticos====\n"+
                "1- Insertar electrodomestico\n"+
                "2- Despachar electrodomestico\n"+
                "3- Buscar electrodomestico\n"+
                "4- Imprimir lista de electrodomesticos\n"+
                "5- Contar electrodomesticos\n"+
                "6- Salir\n";
        entrada = JOptionPane.showInputDialog(null, texto);
        opcion = Integer.parseInt(entrada);
        Opciones(opcion);
    }

    public static void Opciones(int opcion){
        switch (opcion){
            case 1:
                Insertar();
                Menu();
                break;
            case 2:
                Despachar();
                Menu();
                break;
            case 3:
                Buscar();
                Menu();
                break;
            case 4:
                Imprimir();
                Menu();
                break;
            case 5:
                Contar();
                Menu();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida.");
                Menu();
                break;
        }
    }

    public static void Insertar(){
        String tipoStr = "";
        String entrada = "";
        String[] marca_modelo_array = null;
        String marca = "";
        String modelo = "";
        int tipoIndex = 0;
        int contadoractual = 0;
        Electrodomestico nuevoElectrodomestico = null;

        String[] tipos = {"refrigeradoras", "televisores", "lavadoras"};
        tipoStr = JOptionPane.showInputDialog(null, "Seleccione el tipo de electrodomestico\n"+
                    "1. Refrigeradoras\n"+
                    "2. Televisores\n"+
                    "3. Lavadoras\n"+
                    "Ingrese una opcion:");
            tipoIndex = Integer.parseInt(tipoStr)-1;
            
            switch (tipos[tipoIndex]){
                case "refrigeradoras":
                    contadoractual = cabezaRefrigeradoras;
                    break;
                case "televisores":
                    contadoractual = cabezaTelevisores;
                    break;
                case "lavadoras":
                    contadoractual = cabezaLavadoras;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida.");
                    Insertar();
                    break;
            }
            if (contadoractual < MAX_LENGTH-1){
                entrada = JOptionPane.showInputDialog(null, "Ingresa la marca y el modelo: \nSeparado por '-' ");
                marca_modelo_array = entrada.split("-");
                marca = marca_modelo_array[0];
                modelo = marca_modelo_array[1];

                nuevoElectrodomestico = new Electrodomestico(tipos[tipoIndex], modelo, marca);

                switch (tipos[tipoIndex]){
                    case "refrigeradoras":
                        apilarRefrigeradoras(nuevoElectrodomestico);
                        break;
                    case "televisores":
                        apilarTelevisores(nuevoElectrodomestico);
                        break;
                    case "lavadoras":
                        apilarLavadoras(nuevoElectrodomestico);
                        break;
                }
                JOptionPane.showMessageDialog(null, "Electrodomestico agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "La pila de " + tipos[tipoIndex] + " esta llena.");
            }
    }

    public static void Despachar(){
        String texto = "";
        String entrada = "";
        String buscado = "";
        int opcion = 0;
        Electrodomestico electrodomestico = null;
        boolean bandera = false;

        texto = "Seleccione la pila:\n"+
                "1. Refrigeradoras\n"+
                "2. Televisores\n"+
                "3. Lavadoras\n";
        entrada = JOptionPane.showInputDialog(null, texto);
        opcion = Integer.parseInt(entrada);

        switch (opcion){
            case 1:
                buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea despachar: \nEjemplo: LG-001");
                if (!vaciaRefrigeradoras()){
                    do{
                        electrodomestico = desapilarRefrigeradoras();
                        if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                            bandera = true;
                            break;
                        }
                        else{
                            apilarAuxiliar(electrodomestico);
                        }
                    }while (!vaciaRefrigeradoras());
                    
                    if (!vaciaAuxiliar()){
                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarRefrigeradoras(electrodomestico);
                        }while (!vaciaAuxiliar());
                    }
                }
                break;
            case 2:
                buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea despachar: \nEjemplo: LG-001");
                if (!vaciaTelevisores()){
                    do{
                        electrodomestico = desapilarTelevisores();
                        if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                            bandera = true;
                            break;
                        }
                        else{
                            apilarAuxiliar(electrodomestico);
                        }
                    }while (!vaciaTelevisores());

                    if (!vaciaAuxiliar()){
                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarTelevisores(electrodomestico);
                        }while (!vaciaAuxiliar());
                    }
                }
                break;
            case 3:
                buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea despachar: \nEjemplo: LG-001");
                if (!vaciaLavadoras()){
                    do{
                        electrodomestico = desapilarLavadoras();
                        if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                            bandera = true;
                            break;
                        }
                        else{
                            apilarAuxiliar(electrodomestico);
                        }
                    }while (!vaciaLavadoras());

                    if (!vaciaAuxiliar()){
                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarLavadoras(electrodomestico);
                        }while (!vaciaAuxiliar());
                    }
                }
                break;
            default:
                 JOptionPane.showMessageDialog(null, "Ingrese una opcion valida.");
                 Despachar();
                 break;
        }

        if (bandera){
            JOptionPane.showMessageDialog(null, "El electrodomestico fue despachado.");
        }
        else{
            JOptionPane.showMessageDialog(null, "No se encontro el electrodomestico.");
        }
    }

    public static void Buscar(){
        String texto = "";
        String entrada = "";
        String buscado = "";
        String[] marca_modelo_array = null;
        String marca = "";
        String modelo = "";
        int opcion = 0;
        Electrodomestico electrodomestico = null;
        boolean bandera = false;

        if(vaciaRefrigeradoras() && vaciaTelevisores() && vaciaLavadoras()){
            JOptionPane.showMessageDialog(null, "Las pilas estan vacias.");
        }
        else{
            texto = "Seleccione la pila:\n"+
                    "1. Refrigeradoras\n"+
                    "2. Televisores\n"+
                    "3. Lavadoras\n";
            entrada = JOptionPane.showInputDialog(null, texto);
            opcion = Integer.parseInt(entrada);

            switch (opcion){
                case 1:
                    buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea buscar: \nEjemplo: LG-001");
                    if (!vaciaRefrigeradoras()){
                        do{
                            electrodomestico = desapilarRefrigeradoras();
                            apilarAuxiliar(electrodomestico);
                            if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                                bandera = true;
                                break;
                            }
                        }while (!vaciaRefrigeradoras());

                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarRefrigeradoras(electrodomestico);
                        }while (!vaciaAuxiliar());
                    }
                case 2:
                    buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea buscar: \nEjemplo: LG-001");
                    if (!vaciaTelevisores()){
                        do{
                            electrodomestico = desapilarTelevisores();
                            apilarAuxiliar(electrodomestico);
                            if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                                bandera = true;
                                break;
                            }
                        }while (!vaciaTelevisores());

                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarTelevisores(electrodomestico);
                        }while (!vaciaAuxiliar());
                    }
                    break;
                case 3:
                    buscado = JOptionPane.showInputDialog(null, "Ingrese el electrodomestico que desea buscar: \nEjemplo: LG-001");
                    if (!vaciaLavadoras()){
                        do{
                            electrodomestico = desapilarLavadoras();
                            apilarAuxiliar(electrodomestico);
                            if ((electrodomestico.getMarca() + "-" + electrodomestico.getModelo()).equalsIgnoreCase(buscado)){
                                bandera = true;
                                break;
                            }
                        }while (!vaciaLavadoras());

                        do{
                            electrodomestico = desapilarAuxiliar();
                            apilarLavadoras(electrodomestico);
                        }while (!vaciaLavadoras());
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida.");
                    Buscar();
                    break;
                }

            if (bandera){
                JOptionPane.showMessageDialog(null, "Electrodomestico encontrado");
            }
            else{
                JOptionPane.showMessageDialog(null, "Electrodomestico no encontrado");
            }
        }
    }

    public static void Imprimir(){
        String salida = "";
        Electrodomestico electrodomestico = null;

        if (vaciaRefrigeradoras() && vaciaTelevisores() && vaciaLavadoras()){
            JOptionPane.showMessageDialog(null, "Las pilas estan vacias.");
        }
        else{
            if(!vaciaRefrigeradoras()){
                salida += "=== Pila de Refrigeradoras ===\n";
                do{
                    electrodomestico = desapilarRefrigeradoras();
                    apilarAuxiliar(electrodomestico);
                    salida += electrodomestico.toString() + "\n";
                }while (!vaciaRefrigeradoras());

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarRefrigeradoras(electrodomestico);
                }while (!vaciaAuxiliar());
            }
        
            if(!vaciaTelevisores()){
                salida += "=== Pila de Televisores ===\n";
                do{
                    electrodomestico = desapilarTelevisores();
                    apilarAuxiliar(electrodomestico);
                    salida += electrodomestico.toString() + "\n";
                }while (!vaciaTelevisores());

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarTelevisores(electrodomestico);
                }while (!vaciaAuxiliar());
            }

            if(!vaciaLavadoras()){
                salida += "=== Pila de Lavadoras ===\n";
                do{
                    electrodomestico = desapilarLavadoras();
                    apilarAuxiliar(electrodomestico);
                    salida += electrodomestico.toString() + "\n";
                }while (!vaciaLavadoras());

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarLavadoras(electrodomestico);
                }while (!vaciaAuxiliar());
            }

            JOptionPane.showMessageDialog(null, salida);
        }
    }

    public static void Contar(){
        Electrodomestico electrodomestico = null;
        String salida = "";
        int contador = 0;

        if(vaciaRefrigeradoras() && vaciaTelevisores() && vaciaLavadoras()){
            JOptionPane.showMessageDialog(null, "Las pilas estan vacias.");
        }
        else{
            if (!vaciaRefrigeradoras()){
                salida += "Refrigeradoras: ";
                do{
                    electrodomestico = desapilarRefrigeradoras();
                    apilarAuxiliar(electrodomestico);
                    contador++;
                }while (!vaciaRefrigeradoras());
                salida += Integer.toString(contador) + "\n";

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarRefrigeradoras(electrodomestico);
                }while (!vaciaAuxiliar());
            }

            if (!vaciaTelevisores()){
                contador = 0;
                salida += "Televisores: ";
                do{
                    electrodomestico = desapilarTelevisores();
                    apilarAuxiliar(electrodomestico);
                    contador++;
                }while (!vaciaTelevisores());
                salida += Integer.toString(contador) + "\n";

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarTelevisores(electrodomestico);
                }while (!vaciaAuxiliar());
            }

            if (!vaciaLavadoras()){
                contador = 0;
                salida += "Lavadoras: ";
                do{
                    electrodomestico = desapilarLavadoras();
                    apilarAuxiliar(electrodomestico);
                    contador++;
                }while (!vaciaLavadoras());
                salida += Integer.toString(contador) + "\n";
            

                do{
                    electrodomestico = desapilarAuxiliar();
                    apilarLavadoras(electrodomestico);
                }while (!vaciaAuxiliar());
            }

            JOptionPane.showMessageDialog(null, salida);
        }
    }




    public static void apilarRefrigeradoras(Electrodomestico nuevoElectrodomestico){
        if (cabezaRefrigeradoras < refrigeradoras.length -1){
            cabezaRefrigeradoras++;
            refrigeradoras[cabezaRefrigeradoras] = nuevoElectrodomestico;
        }
    }

    public static void apilarTelevisores(Electrodomestico nuevoElectrodomestico){
        if (cabezaTelevisores < televisores.length -1){
            cabezaTelevisores++;
            televisores[cabezaTelevisores] = nuevoElectrodomestico;
        }
    }

    public static void apilarLavadoras(Electrodomestico nuevoElectrodomestico){
        if (cabezaLavadoras < lavadoras.length -1){
            cabezaLavadoras++;
            lavadoras[cabezaLavadoras] = nuevoElectrodomestico;
        }
    }

    public static void apilarAuxiliar(Electrodomestico electrodomestico){
        if (cabezaAux < auxiliar.length -1){
            cabezaAux++;
            auxiliar[cabezaAux] = electrodomestico;

        }
    }

    public static Electrodomestico desapilarRefrigeradoras(){
        if (!vaciaRefrigeradoras()){
            Electrodomestico electrodomesticoDesapilado = refrigeradoras[cabezaRefrigeradoras];
            cabezaRefrigeradoras--;
            return electrodomesticoDesapilado;
        }
        return null;
    }

    public static Electrodomestico desapilarTelevisores(){
        if (!vaciaTelevisores()){
            Electrodomestico electrodomesticoDesapilado = televisores[cabezaTelevisores];
            cabezaTelevisores--;
            return electrodomesticoDesapilado;
        }
        return null;
    }

    public static Electrodomestico desapilarLavadoras(){
        if (!vaciaLavadoras()){
            Electrodomestico electrodomesticoDesapilado = lavadoras[cabezaLavadoras];
            cabezaLavadoras--;
            return electrodomesticoDesapilado;
        }
        return null;
    }

    public static Electrodomestico desapilarAuxiliar(){
        if (!vaciaAuxiliar()){
            Electrodomestico electrodomesticoDesapilado = auxiliar[cabezaAux];
            cabezaAux--;
            return electrodomesticoDesapilado;
        }
        return null;
    }

    public static boolean vaciaRefrigeradoras(){
        if (cabezaRefrigeradoras == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean vaciaTelevisores(){
        if (cabezaTelevisores == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean vaciaLavadoras(){
        if (cabezaLavadoras == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean vaciaAuxiliar(){
        if (cabezaAux == -1){
            return true;
        }
        else{
            return false;
        }
    }

    static class Electrodomestico{
        private String tipo;
        private String modelo;
        private String marca;

    public Electrodomestico (String tipo, String modelo, String marca){
        this.tipo = tipo;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getTipo(){
        return tipo;
    }

    public String getModelo(){
        return modelo;
    }

    public String getMarca(){
        return marca;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    @Override
    public String toString(){
        return "Tipo: " + tipo +  ", Modelo: " + modelo + ", Marca: " + marca;
    }
    }
}
