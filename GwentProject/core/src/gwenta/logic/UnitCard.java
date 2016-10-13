package gwenta.logic;

import java.io.Serializable;

import javax.xml.soap.Text;

/**
 * Created by User on 02-06-2016.
 */
public class UnitCard extends ACard implements Serializable {

    int range;
    int power;
    public UnitCard(int ID) {
        this.id = ID;
        if(ID <= 20){
            range = 0;
        }
        if(ID > 20 && ID <= 40){
            range = 1;
        }
        if(ID > 40 && ID <= 60){
            range = 2;
        }
    }

    public UnitCard(int ID, String Texture) {
        this.id = ID;
        if(ID <= 15 && ID > 7){
            range = 0;
        }
        if(ID > 15 && ID < 28){
            range = 1;
        }
        if(ID >=  28){
            range = 2;
        }

        this.Texture = Texture;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    protected String getTexture() {
        return Texture;
    }
}
