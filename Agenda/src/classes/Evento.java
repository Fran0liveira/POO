package classes;

import classes.Agenda;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Evento {
	
//	O sistema requer assim:
//		� A criação, consulta e remoção de agendas.
//		� A adição, remoção e consulta de eventos presentes em agendas espec�ficas.
//		� Visualiza��o de agenda em modos diferentes (Diário, Semanal, Mensal, Anual)
//	
    //atributos
    private int id = 0;
    private String nomeEvento;
    private Date data;
    private boolean global;
    private Date duracao;
    public Agenda agenda;


    //contrutor 
    public Evento(String nomeEvento, Calendar data, String horario, String duracao, boolean global){
        this.setId();
        this.setNomeEvento(nomeEvento);
        this.setGlobal(global);
        this.setHorario(horario);
        this.setData(data);
    }
    public Evento(String nomeEvento, int dia, int mes, int ano, int hora, int minuto, boolean global, int duracaoHora, int duracaoMinuto){
        this.setId();
        this.setNomeEvento(nomeEvento);
        this.setGlobal(global);
        this.setData(dia, mes, ano, hora, minuto);
        this.setDuracao(duracaoHora, duracaoMinuto);
    }
    
    public Evento(String nomeEvento){
        this.setNomeEvento(nomeEvento);
        this.setId();
    }

    public int getId() {
        return id;
    }
    public void setId(){
        id = this.hashCode();
    }
    
    public String getNome() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        if(nomeEvento.trim().equals("")){
            throw new RuntimeException("Nome do evento não pode ficar em branco!");
        }
            this.nomeEvento = nomeEvento;
    }
    public Date getData(){
        return data;
    }
    public int getMes(){
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(this.getData());
        return calendario.get(Calendar.MONTH)+1;
    }
    public int getDia(){
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(this.getData());
        return calendario.get(Calendar.DAY_OF_MONTH);
    }
    public int getAno(){
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(this.getData());
        return calendario.get(Calendar.YEAR);
    }
    
    //define a data separando dia, mes, ano, horas, minutos
    public void setData(int dia, int mes, int ano, int hora, int minuto) {
        
        Calendar c1 = GregorianCalendar.getInstance();
        if(dia < 1 || dia > 30){
            throw new RuntimeException("Dia informado não é válido!");
        }
        if(mes < 1 || mes > 12){
            throw new RuntimeException("Mês informado não é válido!");
        }
        if(ano < c1.get(Calendar.YEAR)){
            throw new RuntimeException("Ano informado não é válido!");
        }
        if(hora < 0 || hora > 24){
            throw new RuntimeException("Hora informada não é válida");
        }
        if(minuto < 0 || minuto > 60){
            throw new RuntimeException("Minuto informado não é válido");
        }
       
        c1.set(Calendar.YEAR, ano);
        c1.set(Calendar.MONTH, mes-1);
        c1.set(Calendar.DAY_OF_MONTH, dia);
        this.data= c1.getTime();
    }
    public void setData(int dia, int mes, int ano) {
        
        Calendar c1 = GregorianCalendar.getInstance();
        if(dia < 1 || dia > 30){
            throw new RuntimeException("Dia informado não é válido!");
        }
        if(mes < 1 || mes > 12){
            throw new RuntimeException("Mês informado não é válido!");
        }
        if(ano < c1.get(Calendar.YEAR)){
            throw new RuntimeException("Ano informado não é válido!");
        }
        
        c1.set(Calendar.YEAR, ano);
        c1.set(Calendar.MONTH, mes-1);
        c1.set(Calendar.DAY_OF_MONTH, dia);
        this.data= c1.getTime();
    }
    public void setData(Calendar data){
        
    }
    public void setHorario(String horario){
        Calendar c1 = GregorianCalendar.getInstance();
        
        int hora = Integer.parseInt(horario.substring(0, 1));
        int minuto = Integer.parseInt(horario.substring(3, 4));
        
        if( hora < 0 || hora > 24){
            throw new RuntimeException("Hora informada não é válida");
        }
        if( minuto < 0 || minuto > 60){
            throw new RuntimeException("Minuto informado não é válido");
        }
        c1.set(Calendar.HOUR_OF_DAY, hora);
        c1.set(Calendar.MINUTE, minuto);
        this.data = c1.getTime();
    }
    public String getHorario(){
        String h = horarioToString();
        return h;
    }
    public String horarioToString(){
        DateFormat df = new SimpleDateFormat("HH:mm");
        String s = df.format(this.getData());
        return s;
    }
    
     public String dataToString(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String s = df.format(this.getData());
        return s;
     }

    public String eventoToString(){
        StringBuilder sb = new StringBuilder("");
            sb.append("ID do Evento: ");
            sb.append(this.getId());
            sb.append("\n");
            sb.append("Nome do Evento: ");
            sb.append(this.getNome());
            sb.append("\n");
            sb.append("Data do Evento: ");
            sb.append(this.dataToString());
            sb.append("\n");
            sb.append("Evento é global ?: ");
            sb.append(this.isGlobal());
            sb.append("\n");
            sb.append("Duraçao do Evento: ");
            if(this.duracaoToString().equals("00:00")){
                sb.append("O dia todo");
            }
            else{
                sb.append(this.duracaoToString());
            }
            sb.append("\n");
            sb.append("\n");
            return sb.toString();
    
    }
    //verifica se o evento é global (presente em todas agendas)
    public boolean isGlobal(){
        return (this.global == true);
    }
    //determina a duração em horas e minutos do Evento
    private void setDuracao(int duracaoHora, int duracaoMinuto){
        Calendar c1 = GregorianCalendar.getInstance();
        if(duracaoHora < 0 || duracaoHora > 24 ){
            throw new RuntimeException("As horas informadas são inválidas!"); 
        }
        if(duracaoMinuto < 0 || duracaoMinuto > 60){
            throw new RuntimeException("Os minutos informados são inválidos!"); 
        }
        c1.set(Calendar.HOUR_OF_DAY, duracaoHora);
        c1.set(Calendar.MINUTE, duracaoMinuto);
        this.duracao = c1.getTime();
    }
    public void setDuracao(String s){
        
        this.setDuracao(Integer.parseInt(s.substring(0, 1)),Integer.parseInt(s.substring(3, 4)));
    }
    public Date getDuracao(){
        return duracao;
    }
    public String duracaoToString(){
        DateFormat df = new SimpleDateFormat("HH:mm");
        String s = df.format(this.getDuracao());
        return s;
    }
    public void setGlobal(boolean global){
        this.global = global;
    }

}