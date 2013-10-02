package application;

import android.graphics.drawable.Drawable;


public class ProcessItem{


    private int id;
    Drawable icon;
    private String name;
    private String cpu;
    private String ram;
    private String pid;


    public ProcessItem(int id,Drawable icon, String name,String cpu, String ram, String pid) {

        this.id = id;
        this.icon = icon;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.pid = pid;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Drawable getIcone() {
        return icon;
    }

    public void setIcone(Drawable icone) {
        this.icon = icone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    
    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
    
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}