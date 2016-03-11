package web.manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.Book;
import domain.Category;
import domain.Page;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("addUI")) {
			addUI(request, response);
		}
		if (method.equalsIgnoreCase("add")) {
			add(request, response);
		}
		if(method.equalsIgnoreCase("list")){
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		BusinessServiceImpl service = new BusinessServiceImpl();
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = doupLoad(request);
			BusinessServiceImpl service = new BusinessServiceImpl();
			book.setId(WebUtils.makeID());
			service.addBook(book);
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private Book doupLoad(HttpServletRequest request) {
		//把上传的图片保存到images目录中，并把request中的请求参数封装到Book中
		Book book = new Book();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(book, name, value);
				}else{
					String filename = item.getName();
					String savefilename = makeFileName(filename);//得到保存在硬盘的文件名
					String savepath= this.getServletContext().getRealPath("/images");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					book.setImage(savefilename);
					System.out.println(book.getImage());
				}
			}
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String makeFileName(String filename){
		String ext = filename.substring(filename.lastIndexOf(".") + 1);//lastIndexOf("\\.")这样写不行
		return UUID.randomUUID().toString() + "." + ext;
	}

	private void addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> category = service.getAllCategory();
		request.setAttribute("categories", category);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
