package com.hzyc.website.utils;


public class Page {

	public Page(){
		
	}
	
	//��ʼ����
	String startPage;
	//ÿҳ������
	String pageSize;
	//��ǰҳ
	String nowPage;
	//���ҳ��
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
	
	
	
	//��ҳ����
	/**
	 * @param Allrow �ܸ���
	 * @param nowPage ��ǰҳ{Ҫ��ѯ��ҳ��}
	 * @return
	 */
	public Page fenye(String Allrow1,String nowPage1, String pageSize1){
		//���һҳ[���ҳ]
		int Allrow = Integer.parseInt(Allrow1);
		int nowPage = Integer.parseInt(nowPage1 == null || nowPage1.equals("") || nowPage1.equals("null") ?
				nowPage1 : "1");
		int pageSize = Integer.parseInt(pageSize1);
		
		Integer lastPage = Allrow % pageSize == 0 ? Allrow  / pageSize : Allrow 
				/ pageSize + 1;
		
		//���δ���� nowPage����nowPage��С��1??  Ĭ�ϵ�һҳ
		if( nowPage < 1){
			nowPage = 1;
		}
		//���ҳ�뷭������ĩ  �������� ���õ�ǰҳΪ���һҳ
		if(nowPage > lastPage){
			nowPage = lastPage;
		}
		
		
		//������ʼ���ݵ�����
		int sPage = ( nowPage  - 1 ) * pageSize ;
		//���������allrowֻ��0ҳ,��ô��ǰҳҲΪ0
		if(Allrow == 0){
			nowPage = 0;
			sPage = 0;
		}
		//  ��ǰ�ڶ�ҳ 2 * һҳ6�� + 6 == 18 
		//�����ʼ��������������������� ��ô��ʼ���������������
		if( sPage > Allrow){
			sPage = Allrow;
		}
		Page p = new Page();
		//��ʼ����
		p.setStartPage(String.valueOf(sPage));
		//ÿҳ����
		p.setPageSize(String.valueOf(pageSize));
		//��ǰҳ
		p.setNowPage( String.valueOf(nowPage));
		//���ҳ
		p.setMaxPage( String.valueOf(lastPage));
		System.out.println("��ʼ����:"+p.getStartPage() +"==ҳ�����" + p.getPageSize() +"==��ǰҳ"+p.getNowPage() +"==���ҳ" + p.getMaxPage());
		return p;
	}
	
	
}
