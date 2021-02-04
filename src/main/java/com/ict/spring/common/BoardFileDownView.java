package com.ict.spring.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//스프링에서 BeanNameViewResolver에 의해 실행되는 뷰클래스가 되려면
//반드시 스프링이 제공하는 AbstractView를 상속받은 후손클래스여야 함 > 그래야 뷰클래스가됨
@Component("filedown2")
public class BoardFileDownView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//컨트롤러에서 뷰리졸버로 리턴한 model 정보가 자동 전달됨
		//전달온 model 안의 객체 추출
		Model downFileModel = (Model)model.get("downFile");
		File renameFile = (File)downFileModel.getAttribute("renameFile");
		//File  renameFile = (File)((Model)model.get("downFile")).getAttribute("renameFile");
		String originalFilename = (String)downFileModel.getAttribute("originalFilename");
		
		//한글 파일명 인코딩 처리를 위해 파일명만 추출함
		response.setContentType("text/plain; charset=utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ new String(originalFilename.getBytes("utf-8"), "ISO-8859-1") + "\"" );
		response.setContentLength((int)renameFile.length());
		
		//파일을 읽어서 클라이언트 쪽으로 내보낸다.
		//저장된 폴더에서 대상 파일을 읽어서, 요청한 클라이언트 브라우저로 보내기 위한
		//입출력 스트림 생성함
		OutputStream out = response.getOutputStream();
		
		try(FileInputStream fin = new FileInputStream(renameFile);){
			FileCopyUtils.copy(fin, out);
		}catch(Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
	}
}
