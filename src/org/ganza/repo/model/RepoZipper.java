package org.ganza.repo.model;

import java.io.File;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;

public class RepoZipper
{	
	private String zipfile_path;
	private ZipFile zipfile;
	ZipParameters params = new ZipParameters();
	
	public RepoZipper(String zipfile_path) throws ZipException
	{
		this.zipfile_path = zipfile_path;
		
		zipfile = new ZipFile(zipfile_path);
		
		params.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	}
	
	public void setPath(String path){
		
		zipfile_path = path;
	}
	
	public String getPath(){
		
		return zipfile_path;
	}
	
	//add to zip
	public void add(File file) throws ZipException
	{	
		zipfile.addFile(file, params);
	}
	
	public void add(RepoFile rf) throws ZipException
	{
		add(rf.getFile());
	}
	
	public void restore(String destination) throws ZipException
	{
		zipfile.extractAll(destination);
	}
	
}
