	package com.nyW;
	
	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.util.Random;

	import javax.imageio.ImageIO;

	import com.swetake.util.Qrcode;
	
public class CreateQRcardcode {
	
	


		public static void main(String[] args) throws IOException {
			Qrcode qrcode=new Qrcode();
			qrcode.setQrcodeErrorCorrect('H');                                                                                                                                    
			qrcode.setQrcodeEncodeMode('B');
			qrcode.setQrcodeVersion(30);
			String url="BEGIN:VCARD\n" + 
					  "PHOTO;VALUE=uri:http://img4.imgtn.bdimg.com/it/u=3630352509,3120025421&fm=27&gp=0.jpg\n" + 
					  "N:��\n"+
					  "FN:��\n" + 
					  "ORG:�ӱ��Ƽ�ʦ��ѧԺ\n" + 
					  "TITLE:java��������ʦ\n" + 
					  "ADR;WORK:�ػʵ��ӱ��Ƽ�ʦ��ѧԺ\n" + 
					  "ADR;HOME:�ӱ�ʯ��ׯ\n" + 
					  "TEL;CELL,VOICE:13785136940\n"  + 
					  "URL;WORK;:http://www.dijiaxueshe.com\n" + 
					  "EMAIL;INTERNET,HOME:329860115@qq.com\n" + 
					  "END:VCARD";
			byte[] data=url.getBytes();
			boolean [][] qrdata=qrcode.calQrcode(data);
			int imageSize=67+12*(30-1);
			int pixoff=4;
		
			//����ͼƬ����
			BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
			//ͼƬ�滭
			Graphics2D gs=bufferedImage.createGraphics();
			//���ñ���ɫ
			gs.setBackground(Color.YELLOW);
			//�������
			gs.clearRect(0,0,imageSize,imageSize);
			//��ά��滭
			
			int startR=255,startG=0,startB=0;
			int endR=0,endG=0,endB=255;
			for(int i=0;i<qrdata.length;i++){
				for(int j=0;j<qrdata.length;j++){
					if(qrdata[i][j]){

						int num1=startR+(endR-startR)*(i+1)/qrdata.length;
						int num2=startG+(endG-startG)*(i+1)/qrdata.length;
						int num3=startB+(endB-startB)*(i+1)/qrdata.length;
						Color color=new Color(num1,num2,num3);
						gs.setColor(color);
						gs.fillRect(i*3+pixoff/2, j*3+pixoff/2, 3, 3);
					}
				}
			}
			BufferedImage logo=ImageIO.read(new File("I:/photo/IMG_6367.jpg"));
			int logoSize=imageSize/4;
			int o=(imageSize-logoSize)/2;
			gs.drawImage(logo, o, o, logoSize, logoSize,null);
			gs.dispose();
			bufferedImage.flush();
			try{
				ImageIO.write(bufferedImage,"png",new File("D:/QRcode.png"));
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("����������");
			}
			System.out.println("��ά����������D:/QRcode.png");
		}

	}

