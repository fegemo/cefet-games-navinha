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
    public static final boolean rectAndCircleOverlap(Rectangle r, Circle c) {
        //Vetor que liga os centros das figuras geométricas
        Vector2 vectorConnectingCenters = new Vector2((r.x + r.width/2) - c.x, (r.y + r.height/2) - c.y);
        //Vector interno ao retângulo, paralelo ao eixo Y e de comprimento ALTURA/2
        Vector2 vectorYAxisRectangle = new Vector2(0, (r.y + r.height) - (r.y + r.height/2));
        
        //Distância unidimensional entre as coordenadas Y do retângulo e do círculo
        float distanceYBetweenCenters = (new Vector2(0, (r.y + r.height/2) - c.y)).len();
        
        //Limita-se o tamanho do vetor 'vectorYAxisRectangle' garantindo que ele está dentro do retângulo & 
        //não ultrapassa a altura em relação ao centro do círculo
        Vector2 vectorClampedY = vectorYAxisRectangle.clamp(0, Math.min(distanceYBetweenCenters, r.height/2));
        
        //Se houver colisão, a subtração de vetores abaixo resultará em um vetor paralelo ao eixo X
        if(vectorConnectingCenters.sub(vectorClampedY).len() <= r.height/2 + c.radius) return true; //Utliza-se altura pois o retângulo do tiro está em pé
        
        return false;
    }
}
