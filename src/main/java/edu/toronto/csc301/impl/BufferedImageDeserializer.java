package edu.toronto.csc301.impl;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BufferedImageDeserializer extends JsonDeserializer<RenderedImage>
{
    @Override
    public RenderedImage deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException
    {
        byte[] json = arg0.getBinaryValue();
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(json));
        return img;
    }
}
