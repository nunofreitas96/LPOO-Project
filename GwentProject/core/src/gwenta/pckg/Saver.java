package gwenta.pckg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import gwenta.Gwent;
import gwenta.logic.ACard;

/**
 * Created by Repas on 04/06/2016.
 */
public class Saver implements Serializable{
    final Gwent game;

    public Saver(final Gwent game) {
        this.game = game;

    }

    public static void saveDecks(ArrayList<ArrayList<ACard>> Decks) throws IOException{
        FileHandle file = Gdx.files.local("decks.dat");
        file.file().createNewFile();
        if(file.exists()){
            System.out.println("here it is " + Gdx.files.getLocalStoragePath() + file.toString());

        }else{
            System.out.println("whre are you?");
        }
        OutputStream out = null;
        try{
            file.writeBytes(serialize(Decks), false);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }finally{
            if(out != null){
                try{out.close();
                }catch(Exception ex){}
            }
            System.out.println("Closing!");
    }
        System.out.println("Saving Decks");




    }

    public static ArrayList<ArrayList<ACard>> readDecks()throws IOException,ClassNotFoundException{
        ArrayList<ArrayList<ACard>> decks = null;
        FileHandle file = Gdx.files.local("decks.dat");
        if(!Gdx.files.local("decks.dat").exists()){
            ArrayList<ArrayList<ACard>> Decks = new ArrayList<ArrayList<ACard>>();
            for(int i =0; i < 5; i++){
                ArrayList<ACard> exDeck = new ArrayList<ACard>();
                Decks.add(exDeck);
            }
            System.out.println("Didnt find" + Gdx.files.getLocalStoragePath());
            try {
                saveDecks(Decks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        decks = (ArrayList<ArrayList<ACard>>) deserialize(file.readBytes());
        return decks;
    }


    private static byte[] serialize(Object obj) throws IOException{
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream O = new ObjectOutputStream(out);
    O.writeObject(obj);
    return out.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws IOException,ClassNotFoundException{
        ByteArrayInputStream In = new ByteArrayInputStream(bytes);
        ObjectInputStream O = new ObjectInputStream(In);
        return O.readObject();
    }



}
