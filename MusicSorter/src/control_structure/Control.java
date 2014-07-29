package control_structure;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import file_operations.FileOps;
import logging.Log;

public class Control {
	public static void main(String[] args) throws UnsupportedTagException, InvalidDataException, IOException{
		
		Log log = new Log(Defines.LOGGING_LEVEL, Defines.LOG_FILE_LOCATION);

		log.write(2, "Starting Song Sorter v" + Defines.VERSION + "\r\n");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		log.write(2, " Starting at " + dateFormat.format(new Date()) + "\r\n");
		
		long time_start = System.currentTimeMillis(), time_elapsed;
	
		
		
		time_elapsed = System.currentTimeMillis();
		log.write(2, "Getting All Music Files.\r\n");
		ArrayList<String> files = getFiles();
		log.write(2, String.format("%d songs found.\r\n", files.size()));
		log.write(2, "All Music files obtained. Took " + (System.currentTimeMillis() - time_elapsed) + "ms.\r\n");
			
		parseMP3(files.get(0));
		
		log.write(2, "All operations complete at " + dateFormat.format(new Date()) + ". Took " + (System.currentTimeMillis() - time_start) + "ms.\r\n");
			
		log.write(2, "Operation Complete. Closing log.\r\n");
		log.close();
	}
	
	
	public static ArrayList<String> getFiles(){
		return FileOps.getFilesRecursive(Defines.SONG_DIRECTORY);
	}
	
	public static void parseMP3(String filepath) throws UnsupportedTagException, InvalidDataException, IOException{
		Mp3File mp3file = new Mp3File(filepath);
		System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
		System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
	}
}
