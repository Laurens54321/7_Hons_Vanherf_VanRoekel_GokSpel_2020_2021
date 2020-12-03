package model.database.filecontroller;

import model.DomainException;
import model.GeneriekeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextLoadSaveTemplate<T> {

    File file;
    Scanner scannerFile;
    PrintWriter writerFile;

    public TextLoadSaveTemplate() { }

    public TextLoadSaveTemplate(File file){
        if (file == null) throw new DomainException("TXTREADER: Empty File");
        else {
            this.file = file;
            try{
                scannerFile = new Scanner(file);
            } catch (Exception e) {
                throw new DomainException("TXTREADER: Error Loading file", e);
            }
        }
    }

    public void setFile(File file){
        if (file == null) throw new DomainException("TXTREADER: Empty File");
        else {
            this.file = file;
            try{
                scannerFile = new Scanner(file);
            } catch (Exception e) {
                throw new DomainException("TXTREADER: Error Loading file", e);
            }
        }
    }

    public ArrayList<T> readLine(){
        ArrayList<T> lijst = new ArrayList<>();
        if (file == null) return null;
        else{
            try{
                if (scannerFile.hasNextLine()) {
                    String s = scannerFile.nextLine();
                    String[] var = s.split(",");
                    for (String string : var) {
                        lijst.add((T) string);
                    }
                    return lijst;
                }
                else return null;
            } catch (Exception e) {
                throw new DomainException("TXTREADER: Error reading next line", e);
            }
        }
    }

    public boolean hasNextLine(){
        return scannerFile.hasNextLine();
    }

    public void reloadFile(){
        try{
            scannerFile = new Scanner(file);
        } catch (Exception e) {
            throw new DomainException("TXTREADER: Error reloading file", e);
        }
    }

    public void writeNextLine(ArrayList<T> list) {
        try {
            if (writerFile == null ) writerFile = new PrintWriter(file);
            for (T t : list) {
                writerFile.write(t.toString() + ",");
            }


        } catch (FileNotFoundException e) {
            throw new DomainException("TXTREADER: Error reading player file", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter(){
        writerFile.close();
        writerFile = null;
    }
}
