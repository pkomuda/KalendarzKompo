package com.itpk.kalendarz.prezentacja;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Klasa umozliwiajaca wydawanie sygnalow dzwiekowych
 */
public class Dzwieki
{
    public static float CZESTOTLIWOSC_PROBKOWANIA = 8000f;

    public static void ton(int hz, int msecs) throws LineUnavailableException
    {
        ton(hz, msecs, 1.0);
    }

    public static void ton(int hz, int msecs, double vol) throws LineUnavailableException
    {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(CZESTOTLIWOSC_PROBKOWANIA,8,1,true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i=0; i < msecs*8; i++)
        {
            double angle = i / (CZESTOTLIWOSC_PROBKOWANIA/hz)*2.0*Math.PI;
            buf[0] = (byte)(Math.sin(angle)*127.0*vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
}