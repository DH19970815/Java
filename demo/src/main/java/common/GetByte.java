package common;


import java.io.*;

public class GetByte {
    public static byte[] getbyte(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();

    }

    public static Object DeSerialization(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream =new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();

    }



}
