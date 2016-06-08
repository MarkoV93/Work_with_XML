/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.Comparator;

class Knife implements Comparator<Knife> {

    private String id;
    private String type;
    private int hangry;
    private String Origin;
    private Visual visual;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHangry() {
        return hangry;
    }

    public void setHangry(int hangry) {
        this.hangry = hangry;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    @Override
    public int compare(Knife k1, Knife k2) {
        return k1.getVisual().getBlade().lenght - k2.getVisual().getBlade().lenght;
    }

    static class Visual {

       private String handle;
       private boolean value;
       private Blade blade;

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public Blade getBlade() {
            return blade;
        }

        public void setBlade(Blade blade) {
            this.blade = blade;
        }

        static public class Blade {

          

            int lenght;
            int width;
            String material;
            boolean blood_groove;
        }
    }
       public String toString(){
        return "type: "+this.getType()+"\n"+"Hangry: "+this.getHangry()+"\n"+
                "Origin:"+this.getOrigin()+"\n"+
                "Handle:"+this.getVisual().getHandle()+"\n"+"Value: "+this.getVisual().isValue()+"\n"+
        "Lenght: "+this.getVisual().getBlade().lenght+"\n"+
                "Width: "+this.getVisual().getBlade().width+"\n"+
                "Material: "+this.getVisual().getBlade().material+"\n"+
                "Blood_groove: "+this.getVisual().getBlade().blood_groove+"\n"+
                "==========================================="+"\n";
    }
}
