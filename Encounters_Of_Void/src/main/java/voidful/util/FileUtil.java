package voidful.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dom4j.Document;

public class FileUtil {
	public final static File DIRECTORY = new File(System.getProperty("user.home")+"/EnOVoid");
	public final static File SESSION = new File(DIRECTORY.getAbsolutePath().concat("/Sessions"));
	public final static void saveFile(File path,String name, Document doc)
	{
		try(BufferedWriter bW = new BufferedWriter(new FileWriter(path.getAbsolutePath().concat("/"+name)))){
			bW.write(doc.asXML());
		} catch (IOException e) {
			e.printStackTrace();
			DialogUtil.showError(e.getMessage());
		}
	}
	public final static List<File> getAllSessions(){
		
		return Arrays.asList(SESSION.listFiles());
		
	}
}
