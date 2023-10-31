package egovframework.example.board.vo;

public class FileVo {
	
	private int fileNo; //시퀀스, 게시판 첨부파일의 번호
	private String fileName;  //첨부파일이름
	private String filePath;  //첨부파일경로
	private String saveName;  //첨부파일저장경로
	private int boardNo;      //게시글 번호
	
	
	//getter setter
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
	@Override
	public String toString() {
		return "FileVo [fileNo=" + fileNo + ", fileName=" + fileName + ", filePath=" + filePath + ", saveName="
				+ saveName + ", boardNo=" + boardNo + "]";
	}
	

}
