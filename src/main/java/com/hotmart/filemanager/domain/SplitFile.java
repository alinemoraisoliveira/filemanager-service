package com.hotmart.filemanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SLIT_FILE")
public class SplitFile implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "CHUNK_NUMBER", nullable = false)
	private int chunkNumber;

	@Column(name = "ARQUIVO", nullable = true)
	private byte[] splitFile;

	@Column(name = "FILE_ID", nullable = false)
	private Long fileId;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="FILE_ID", referencedColumnName="ID", nullable = false, updatable = false, insertable = false)
	private File file;

	public SplitFile() {
		super();
	}

	public SplitFile(Long id, String name, int chunkNumber, byte[] splitFile, Long fileId, File file) {
		super();
		this.id = id;
		this.name = name;
		this.chunkNumber = chunkNumber;
		this.splitFile = splitFile;
		this.fileId = fileId;
		this.file = file;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChunkNumber() {
		return chunkNumber;
	}
	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}
	public byte[] getSplitFile() {
		return splitFile;
	}
	public void setSplitFile(byte[] splitFile) {
		this.splitFile = splitFile;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}
