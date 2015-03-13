package PegSolitaire.dom.io;

import PegSolitaire.dom.field.Field;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by dennis on 13/03/15.
 */
public class FieldSerializer {
    private final Path serialFile;

    public FieldSerializer() {
        FileSystem f = FileSystems.getDefault();
        serialFile = f.getPath("field.ser");
    }

    public void serializeField(Field field) {
        try {
            FileOutputStream fout = new FileOutputStream(serialFile.toString());
            ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(field);
            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Field deserializeField() {
        Field field = null;
        try {
            FileInputStream fin = new FileInputStream(serialFile.toString());
            ObjectInputStream in = new ObjectInputStream(fin);
            field = (Field) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return field;
    }
}
