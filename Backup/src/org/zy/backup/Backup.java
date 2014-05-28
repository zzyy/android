package org.zy.backup;

import java.util.List;

import org.zy.backup.model.Contact;


public class Backup {
	private static Backup backup;
	private Backup(){}
	public static synchronized Backup newInstance(){
		if(backup == null){
			backup = new Backup();
		}
		return backup;
	}
	
	public List<Contact> contacts = null;
}
