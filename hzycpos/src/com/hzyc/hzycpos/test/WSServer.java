/*package com.hzyc.hzycpos.test;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WSServer {
	
	public Session session;
	
	//��ȡʵ��
	
	
	public WSServer(){
		
		System.out.println("websocket �����ʵ����....=====");
	}
    //���Ӵ�ʱִ��
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session=session;
        SocketSet.setSet(this);
        System.out.println("һ���ͻ������ӽ����� ... ����sessionid�ǣ�" + session.getId());
    }

    //�յ���Ϣʱִ��
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(session.getId()+"�ͻ��˷��͵���Ϣ�ǣ�"+message);
        try{
            this.sendMessage(message);//��Ϣ���ظ��ͻ���
        }catch(Exception e){
            e.printStackTrace();
        }
        //return currentUser + "��" + message;����з���ֵ����ͻ��˷�����Ϣ����յ��������ֵ
    }

    //���ӹر�ʱִ��
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("һ���ͻ��˹ر��ˣ�����sessionid�ǣ�" + session.getId());
        SocketSet.remove(this);
    }

    //���Ӵ���ʱִ��
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    //�Զ���ķ��������ڷ�����Ϣ
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
*/