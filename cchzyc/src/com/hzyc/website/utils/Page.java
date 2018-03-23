package com.hzyc.website.utils;


public class Page {

	public Page(){
		
	}
	
	//起始行数
	String startPage;
	//每页的行数
	String pageSize;
	//当前页
	String nowPage;
	//最大页数
	String maxPage;
	public String getNowPage() {
		return nowPage;
	}
	public String getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(String maxPage) {
		this.maxPage = maxPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	public String getStartPage() {
		return startPage;
	}
	public  void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	//分页方法
	/**
	 * @param Allrow 总个数
	 * @param nowPage 当前页{要查询的页码}
	 * @return
	 */
	public Page fenye(String Allrow1,String nowPage1, String pageSize1){
		//最后一页[最大页]
		int Allrow = Integer.parseInt(Allrow1);
		int nowPage = Integer.parseInt(nowPage1 == null || nowPage1.equals("") || nowPage1.equals("null") ?
				nowPage1 : "1");
		int pageSize = Integer.parseInt(pageSize1);
		
		Integer lastPage = Allrow % pageSize == 0 ? Allrow  / pageSize : Allrow 
				/ pageSize + 1;
		
		//如果未设置 nowPage或者nowPage还小于1??  默认第一页
		if( nowPage < 1){
			nowPage = 1;
		}
		//如果页码翻到了最末  甚至超过 设置当前页为最后一页
		if(nowPage > lastPage){
			nowPage = lastPage;
		}
		
		
		//这是起始数据的行数
		int sPage = ( nowPage  - 1 ) * pageSize ;
		//如果总行数allrow只有0页,那么当前页也为0
		if(Allrow == 0){
			nowPage = 0;
			sPage = 0;
		}
		//  当前第二页 2 * 一页6个 + 6 == 18 
		//如果起始行数大于了数据最大行数 那么起始行数就是最大行数
		if( sPage > Allrow){
			sPage = Allrow;
		}
		Page p = new Page();
		//起始行数
		p.setStartPage(String.valueOf(sPage));
		//每页个数
		p.setPageSize(String.valueOf(pageSize));
		//当前页
		p.setNowPage( String.valueOf(nowPage));
		//最大页
		p.setMaxPage( String.valueOf(lastPage));
		System.out.println("起始行数:"+p.getStartPage() +"==页面个数" + p.getPageSize() +"==当前页"+p.getNowPage() +"==最大页" + p.getMaxPage());
		return p;
	}
	
	
}
