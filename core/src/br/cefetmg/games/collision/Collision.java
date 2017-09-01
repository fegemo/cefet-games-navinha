package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Utilitário para verificação de colisão.
 *
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class Collision {

    /**
     * Verifica se dois círculos em 2D estão colidindo.
     * @param c1 círculo 1
     * @param c2 círculo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean circlesOverlap(Circle c1, Circle c2) {
        Vector2 vector1 = new Vector2(c1.x, c1.y);
        Vector2 vector2 = new Vector2(c2.x, c2.y);
        
        if(vector1.dst(vector2) <= c1.radius + c2.radius)
            return true;
        return false;
    }

    /**
     * Verifica se dois retângulos em 2D estão colidindo.
     * Esta função pode verificar se o eixo X dos dois objetos está colidindo
     * e então se o mesmo ocorre com o eixo Y.
     * @param r1 retângulo 1
     * @param r2 retângulo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {
        Vector2 vectorXAxis = new Vector2(r1.x - r2.x, r1.y - r2.y);
        Vector2 vectorYAxis = new Vector2(r1.x - r2.x, r1.x + r1.height - r2.x - r2.height);
        
        if(vectorXAxis.len() < Math.max(r1.width, r2.width))
            if(vectorYAxis.len() < Math.max(r1.height, r2.height))
                return true;
        return false;
    }
    
    /**
     * Verifica se um círculo e um retângulo colidem.
     * @param c círculo
     * @param r retângulo
     * @return true se há colisão, false caso contrário
     */
    public static final boolean rectAndCircleOverlap(Circle c, Rectangle r) {
        Vector2 vectorConnectingCenters = new Vector2(c.x - (r.x + r.width/2), c.y - (r.y + r.height/2));
        Vector2 vectorXAxisRectangle = new Vector2((r.x + r.width/2) - r.x, 0);
        Vector2 vectorYAxisRectangle = new Vector2(0, (r.y + r.height) - (r.y + r.height/2));
        
        float distanceXBetweenCenters = (new Vector2((r.x + r.width/2) - c.x, 0)).len();
        float distanceYBetweenCenters = (new Vector2(0, (r.y + r.height/2) - c.y)).len();
        
        Vector2 vectorClampedX = vectorXAxisRectangle.clamp(0, distanceXBetweenCenters);
        Vector2 vectorClampedY = vectorYAxisRectangle.clamp(0, distanceYBetweenCenters);
        
        if(vectorClampedY.dst(c.x, c.y) <= r.width + c.radius) return true;
        if(vectorClampedX.dst(c.x, c.y) <= r.height + c.radius) return true;
        return false;
    }
}
