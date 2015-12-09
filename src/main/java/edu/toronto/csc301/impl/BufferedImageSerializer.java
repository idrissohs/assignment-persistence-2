package edu.toronto.csc301.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BufferedImageSerializer extends JsonSerializer<BufferedImage>
{
    @Override
    public void serialize(BufferedImage arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException
    {

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(arg0.getWidth() * arg0.getHeight() * 4);
        ImageIO.write(arg0, "BMP", byteOut);
        arg1.writeBinary(byteOut.toByteArray());
    }
}
