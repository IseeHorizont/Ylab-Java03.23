package io.ylab.intensive.lesson4.eventsourcing.utils;

import io.ylab.intensive.lesson4.eventsourcing.data.Message;

import java.io.*;

/**
 * Mapper class
 */
public class MessageMapper {

    /**
     * Message -> byte[]
     * @param message converting message
     * @return message in bytes
     */
    public static byte[] getBytes(Message message) {
        byte[] serializedMessage = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(message);
            oos.flush();
            serializedMessage = bos.toByteArray();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return serializedMessage;
    }

    /**
     * byte[] -> Message
     * @param bytes message in bytes
     * @return Message
     */
    public static Message toObject(byte[] bytes) {
        Message message = null;
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            message = (Message) in.readObject();
        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return message;
    }
}
