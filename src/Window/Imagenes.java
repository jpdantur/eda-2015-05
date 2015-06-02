package Window;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Francisco
 */
public class Imagenes {
    private Map<String,ImageIcon> mapa;
    
    public Imagenes(){
        mapa = new HashMap<>();
    }
    
    public ImageIcon getImagen(String nombre){
        
        if(mapa.containsKey(nombre))
            return mapa.get(nombre);
        
        ImageIcon imagen = new javax.swing.ImageIcon(getClass().getResource(nombre));
        mapa.put(nombre, imagen);
        return imagen;
    }
    
}
