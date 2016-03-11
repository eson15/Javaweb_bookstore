package domain;

import java.util.List;

public class Page {
	
	private int totalpage; //记住总页数
	private int pagesize = 3; //页面大小
	private int totalrecord; //总记录数
	private int pagenum; //记住当前页
	private List list; //记住页面数据
	private int startpage; //起始页号
	private int endpage;
	private int startindex; //记住用户想看的 页的数据从哪个地方开始取
	
	public Page(int pagenum, int totalrecord){
		this.pagenum = pagenum;
		this.totalrecord = totalrecord;
		//算出总页数
		this.totalpage = (this.totalrecord + this.pagesize - 1) / this.pagesize;
		//算出用户想看的页的数据从数据库哪个地方开始取
		this.startindex = (this.pagenum - 1) * this.pagesize;
		
		if(this.totalpage <= 3){
			this.startpage = 1;
			this.endpage = this.totalpage;
		}else{
			this.startpage = pagenum - 1;
			this.endpage = pagenum + 1;
			
			if(this.startpage < 1){
				this.startpage = 1;
				this.endpage = 3;
			}
			if(this.endpage > this.totalpage){
				this.endpage = this.totalpage;
				this.startpage = this.totalpage - 2;
			}
		}
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
}
