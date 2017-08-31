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
}
