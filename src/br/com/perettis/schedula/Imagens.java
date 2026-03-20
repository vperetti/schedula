/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author vinicius
 */
public class Imagens {
    
    public Image getIcone(){
      
      return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/perettis/schedula/resources/iconeFrame.png"));
    }

}
