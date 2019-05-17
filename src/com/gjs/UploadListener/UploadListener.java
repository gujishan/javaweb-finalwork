package com.gjs.UploadListener;

import org.apache.commons.fileupload.ProgressListener;

import com.gjs.UploadStatus.UploadStatus;

public class UploadListener implements ProgressListener{
	
	private UploadStatus status;
	
	public UploadListener(UploadStatus status){
		
		this.status=status;
	}
	
	public void update(long bytesRead,long contentLength,int items){
		
		status.setBytesRead(bytesRead);  //已读取的数据长度
		status.setContentLength(contentLength);  //文件总长度
		status.setItems(items);       //正在上传第几个文件
	}
 
}