package fileReconstructor;

import entities.Packet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Deserializer {

    public static void deserializePackets(File packetFile){
        try(FileInputStream inpStream = new FileInputStream(packetFile)){
            byte[] data = inpStream.readAllBytes();
            int payloadSize = Decoder.decodePayloadSize(data);
            createPacket(payloadSize, Decoder.decodeSequenceNumber(data),
                    Decoder.decodeTimestamp(data), Decoder.decodeCheckSum(data),
                    extractPayload(data, payloadSize));
        }catch(IOException io){
            System.err.println(io.getMessage());
        }
    }

    private static byte[] extractPayload(byte[] data, int payloadSize){
        byte[] payload = new byte[payloadSize];
        System.arraycopy(data, (data.length - payloadSize), payload, 0, payloadSize);
        return payload;
    }

    public static void createPacket(int payloadSize, long sequenceNumber, long unixTimeStamp, String checkSum, byte[] payload){
        Packet packet = new Packet(payloadSize, sequenceNumber, unixTimeStamp, checkSum, payload);
        Collector.collectPacket(packet);
    }
}
